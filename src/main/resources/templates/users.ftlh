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
            <form action="/admin/add_account" method="post" >
                <br>
                <input type="hidden" name="userId" value=${user.id}>
                <input type="submit" value="добавить аккаунт">
            </form>
        </th>
    </tr>
    </#list>
    </tbody>
</table>

</body>
</html>