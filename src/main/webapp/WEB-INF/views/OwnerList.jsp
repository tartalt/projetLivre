<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
<header>
    <h1>Owners List</h1>
</header>
<main>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">FIRST NAME</th>
            <th scope="col">LAST NAME</th>
            <th scope="col">EMAIL</th>
            <th scope="col">ADDRESS</th>
            <th scope="col">PHONE</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${ownersVue}" var="owner">
                <tr>
                    <td>${owner.id}</td>
                    <td>${owner.firstName}</td>
                    <td>${owner.lastName}</td>
                    <td>${owner.email}</td>
                    <td>${owner.address}</td>
                    <td>${owner.phone}</td>
                    <td>
                        <a onclick="return confirm('Sure?')" href="DeleteOwner?id=${owner.id}">Delete</a>
                    </td>
                    <td>
                        <a href="EditOwner?id=${owner.id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</main>
<footer>
    <a href="CreateOwner">Customer Creation</a>
</footer>
</body>
</html>

