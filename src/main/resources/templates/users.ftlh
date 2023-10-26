<!DOCTYPE html>
<html lang="en">
<head>

    <title>Login</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Логин</th>
        <th>Имя</th>
        <th>Добавить счёт</th>
    </tr>
    </thead>

    <tbody>
    <#list users as user>
    <tr>
        <th> ${user.login} </th>
        <th> ${user.name} </th>
        <th>
            <form action="/add_account" method="post" >
                <input type="submit" value="добавить аккаунт">
            </form>
        </th>
    </tr>
    </#list>
    </tbody>
</table>

</body>
</html>