# Clever-Bank

## Description
- **[Technologies](#Technologies)**
- **[Installation](#Installation)**
- **Startup**
  - [Project Property](#Property)
  - [PostgreSQL](#StartupPostgreSQL)
  - [Tomcat 10.1.12](#StartupTomcat)
  - [Enter data](#Enterdata)
- **[Patterns](#Patterns)**
- **[CRUD](#CRUD)** 
- **[Receipt Writers](#writers)**
- **[AspectJ](#AspectJ)**
- **[Servlet controller](#Servlet)**
- **[Asynchronous interest calculation request](#AsynchronousRequest)**
- [Request with transaction isolation level: transferCashOperation()](src/main/java/ru/clevertec/cleverbank/service/impl/AccountTransactionServiceImpl.java)

##  <a id="Technologies"></a> Technologies
- Java 17
- Gradle 8.0
- PostgreSQL
- JDBC
- Lombok
- AspectJ
- Servlets (Tomcat 10.1.12)

## <a id="Installation"></a> Installation

1. Download the source project from [GitHub](https://github.com/KiokK/)
2. Run command to build the project ```./gradlew clean build```

### <a id="Property"></a>Project Property
Set configuration params in **[PropertyConstant](src/main/java/ru/clevertec/cleverbank/configs/PropertyConstant.java)**

### <a id="StartupPostgreSQL"></a> PostgreSQL

1. Create db schema [schema.sql](src/main/java/resources/schema.sql)
2. Create db data [data.sql](src/main/java/resources/data.sql)

### <a id="StartupTomcat"></a> Tomcat 10.1.12

- Default URL: http://localhost:8080/Gradle___ru_clevertec___Clever_Bank_1_0_SNAPSHOT_war/

Location of pdf and txt docs:
- ~\Libs\apache-tomcat-10.1.12\bin\statement-money\
- ~\Libs\apache-tomcat-10.1.12\bin\check\

Java app on tomcat server saving files on server instead of path because of 2 reasons:

1. security, the user your are using to start tomcat service not have access outside 'c:' drive or user/localhost so you can not create files out side folder have privileges on, and it is not recommended to run tomcat service with root "if it is possible"
2. OS is different, if someone are hosting application on linux, he does not have 'c:' drive or user/localhost

### <a id="Enterdata"></a> Enter data
 Enter page:
 - admin: (username: admin, password: admin) 
   - select tables and click 'find all'
   - click 'find by id' to chose data for update
   - make update and create queries (use 'find by id' earlier)
 - user: 
   - (username: user, password: user, accountId: 3)
   - (username: user, password: accountId: 4)
   - (username: user, password: accountId: 5)

##  <a id="Patterns"></a> Patterns
- [Receipt Factory](src/main/java/ru/clevertec/cleverbank/factory/ReceiptFactory.java) for bank receipt creation
- [Component Factory](src/main/java/ru/clevertec/cleverbank/configs/container/ComponentFactory.java) for 'component' creation (services, factories)
- [Command](src/main/java/ru/clevertec/cleverbank/command/Command.java) for execute http requests

##  <a id="CRUD"></a> CRUD
You should have admin role
- **[service layer](src/main/java/ru/clevertec/cleverbank/service/)**
- **[dao layer](src/main/java/ru/clevertec/cleverbank/dao/)**
- **[connection pool](src/main/java/ru/clevertec/cleverbank/configs/connection/DataSource.java)**
- Operations are implemented for 4 tables with [command pattern](src/main/java/ru/clevertec/cleverbank/command/impl/admin/). 
- Data is mapped with [json mappers (using jackson JsonMapper)](src/main/java/ru/clevertec/cleverbank/mapper/json/)
- [Mapstruct mapper](src/main/java/ru/clevertec/cleverbank/mapper/impl/) is used for Entity<->DTO mapping
- [custom sql mapper](src/main/java/ru/clevertec/cleverbank/mapper/sql/) is used for Entity<->sql ResultSet mapping

##  <a id="writers"></a> Receipt Writers
For writing receipts to .txt and .pdf - [writers](src/main/java/ru/clevertec/cleverbank/writer/)

##  <a id="AspectJ"></a> AspectJ
@CustomLog for logging services args and return values - [aop](src/main/java/ru/clevertec/cleverbank/aop/)

##  <a id="Servlet"></a> Servlet controller
Receive HttpRequest -> [command.execute()](src/main/java/ru/clevertec/cleverbank/command/Command.java)  -> do request forward - [Controller](src/main/java/ru/clevertec/cleverbank/controller/Controller.java)

##  <a id="AsynchronousRequest"></a> Asynchronous interest calculation request
[StartupListener](src/main/java/ru/clevertec/cleverbank/listener/StartupListener.java)
