<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CleverBank - CRUD</title>
</head>
<body>
<label for="tableNameSel">Table:</label>
<select id="tableNameSel" onchange="selectedTableClick();">
    <option value=”USERS” selected="selected">USERS</option>
    <option value=”ACCOUNTS”>ACCOUNTS</option>
    <option value=”BANKS”>BANKS</option>
    <option value=”TRANSACTIONS”>TRANSACTIONS</option>
</select>
<form id="updateQueryForm" action="Controller" method="post">
    <input type="hidden" name="command" value="${TABLE_NAME}_FIND_ALL"/>
    <input type="submit" class="btn btn-primary" value="findAll" onclick="selectedTableClick()"/>
</form>
<h2>${TABLE_NAME}</h2>
<h3>Create:</h3>
<form action="Controller" method="post">
    <input type="hidden"  name="command" value="${TABLE_NAME}_CREATE"/>
    <textarea name="jsonData" rows="10" cols="50"></textarea>

    <input type="submit" class="btn btn-primary" value="Execute"/>
</form>

<h3>Update:</h3>
<form id="updateQueryForm" action="Controller" method="post">
    <input type="hidden" name="command" value="${TABLE_NAME}_UPDATE"/>
    <textarea name="jsonData" rows="10" cols="50" >${currentEntity}</textarea>

    <input type="submit" class="btn btn-primary" value="Execute"/>
</form>

<jsp:include page="/WEB-INF/jsp/parts/entity-list.jsp"/>

<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script type="text/javascript">

   var selectVals = ['USERS', 'ACCOUNTS', 'BANKS', 'TRANSACTIONS'];

   function selectedTableClick() {
       for (var n = selectVals.length; n--;)
           if ($("#tableNameSel :selected").text() === selectVals[n]) {
               jQuery($('input[name="command"]')).each( function () {
                   var command = $(this).val();
                   command = selectVals[n] + command.substr(command.indexOf("_"));
                   $(this).val(command);
               });
               return;
           }
   }
</script>

</body>
</html>
