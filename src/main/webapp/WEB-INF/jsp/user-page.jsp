<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>CleverBank - Profile</title>
</head>
<body>
    <h3> Username: ${userAccount.username} </h3>
    <h4> Account : ${userAccount.id} - '${userAccount.title}' </h4>
    <p> Amount: ${userAccount.amount} - ${userAccount.currency}</p>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="ACCOUNT_REPLENISHMENT"/>
        <input type="hidden" name="accountId" value="${userAccount.id}"/>
        <input type="number" name="amount" value="100.00" min="0.01"  step="any"/>
        <input type="submit" class="btn btn-primary" value="REPLENISHMENT"/>
    </form>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="ACCOUNT_DEBITING"/>
        <input type="hidden" name="accountId" value="${userAccount.id}"/>
        <input type="number" name="amount" value="0.01" max="${userAccount.amount}" min="0.01"  step="any" />
        <input type="submit" class="btn btn-primary" value="DEBITING"/>
    </form>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="EXTRACT"/>
        <p>Start date:
<%--            <fmt:formatDate value="${userAccount.created}" pattern="dd-MM-yyyy" var="myDate" />--%>
            <input type="datetime-local" name="startDate" value="${userAccount.created}"/>

        </p>
        <p>End date:
            <input type="datetime-local" name="endDate"  >
        </p>
        <input type="hidden" name="accountId" value="${userAccount.id}"/>
        <input type="submit" class="btn btn-primary" value="GET EXTRACT"/>
    </form>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="EXPENSES"/>
        <p>Start date:
            <input type="datetime-local" name="startDate" value="${userAccount.created}"/>
        </p>
        <p>End date:
            <input type="datetime-local" name="endDate"  >
        </p>
        <input type="hidden" name="accountId" value="${userAccount.id}"/>
        <input type="submit" class="btn btn-primary" value="GET EXPENSES"/>
    </form>

    <form action="Controller" method="post">
        <input type="hidden" name="command" value="TRANSFER_TO_ANOTHER_ACCOUNT"/>
        <input type="hidden" name="accountId" value="${userAccount.id}"/>
        <label>
            Recipient account:
            <input type="text" name="recipientAccountId" value="1"/>
        </label>
        <label>
            Amount:
            <input type="number" name="amount" value="0.01" max="${userAccount.amount}" min="0.01"  step="any" />
        </label>
        <input type="submit" class="btn btn-primary" value="TRANSFER"/>
    </form>
</body>
</html>
