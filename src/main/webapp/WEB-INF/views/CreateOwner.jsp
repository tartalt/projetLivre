<!DOCTYPE html>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/5.3.3/css/bootstrap.min.css" />
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
<header>
    <h1>owner Creation</h1>
</header>
<main>
    <form action="saveOwner" method="post">
        <div>
            <label for="firstName">First Name : </label>
            <input type="text" id="firstName" name="firstName">
        </div>
        <div>
            <label for="lastName">Last Name : </label>
            <input type="text" id="lastName" name="lastName">
        </div>
        <div>
            <label for="email">Email : </label>
            <input type="email" id="email" name="email">
        </div>
        <div>
            <label for="address">Address : </label>
            <input type="text" id="address" name="address">
        </div>
        <div>
            <label for="phone">Phone : </label>
            <input type="text" id="phone" name="phone">
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</main>
<footer>
    <a href="OwnerList">Customers List</a>
</footer>
</body>
</html>

