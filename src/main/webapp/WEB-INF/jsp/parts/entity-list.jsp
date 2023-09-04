<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
<h4> Table values: </h4>
<table>
    <c:forEach items="${entityList}" var="entity">
        <tr>
            <td>${entity.toString()}</td>
            <td>
                <form action="Controller" method="post">

                    <input type="hidden" name="command" value="${TABLE_NAME}_FIND_BY_ID"/>
                    <input type="hidden" name="id" value="${entity.id}"/>

                    <input type="submit" class="btn btn-primary" value="find by id"/>
                </form>
            </td>
            <td>
                <form action="Controller" method="post">

                    <input type="hidden" name="command" value="${TABLE_NAME}_DELETE_BY_ID"/>
                    <input type="hidden" name="id" value="${entity.id}"/>

                    <input type="submit" class="btn btn-primary" value="delete by id"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</p>