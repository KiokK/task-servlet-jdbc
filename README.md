# Clever-Bank

## Description
- **[Technologies](#Technologies)**
- **[Installation](#Installation)**
- **Startup**
  - [Project Property](#Property)
  - [PostgreSQL](#StartupPostgreSQL)
  - [Tomcat 10.1.12](#StartupTomcat)
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

##  <a id="Patterns"></a> Patterns
- [Receipt Factory](src/main/java/ru/clevertec/cleverbank/factory/ReceiptFactory.java) for bank receipt creation
- [Component Factory](src/main/java/ru/clevertec/cleverbank/configs/container/ComponentFactory.java) for 'component' creation (services, factories)
- [Command](src/main/java/ru/clevertec/cleverbank/command/Command.java) for execute http requests

##  <a id="CRUD"></a> CRUD
You should has admin role (username: admin, password: admin)
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
