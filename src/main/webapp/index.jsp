<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CleverBank - Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>

<div class="container">
    <br/>
    <h3>Enter with username and password:</h3>
    <form action="Controller" method="post">

        <input type="hidden" name="command" value="LOGIN"/>

        <div id="login" class=" form-group row">
            <label for="login" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-7">
                <input type="text" class="form-control" name="username"
                       placeholder="Enter login">
            </div>
        </div>

        <div id="password" class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-7">
                <input type="password" class="form-control" name="password"
                       placeholder="Enter Password">
            </div>
        </div>
        <div id="accountId" class="form-group row">
            <label for="accountId" class="col-sm-2 col-form-label">Account Id</label>
            <div class="col-sm-7">
                <input type="number" class="form-control" name="accountId">
            </div>
        </div>

        <input type="submit" class="btn btn-primary" value="Login"/>
    </form>

    <label style="color: red"> ${message} </label>
</div>
</body>
</html>