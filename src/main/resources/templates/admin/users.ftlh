<!DOCTYPE html>
<html lang="en">
<head>
    <title>users</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Логин</th>
        <th>Количество аккаунтов</th>
        <th>Добавить аккаунт</th>
    </tr>
    </thead>

    <tbody>
    <#list users as user>
    <tr>
        <th> ${user.login} </th>
        <th> ${user.getAccountsSize()} </th>
        <th>
            <form action="/admin/deactivate_account" method="post" >
                <br>
                <input type="hidden" name="userId" value=${user.id}>
                <input type="submit" value="добавить аккаунт">
            </form>
        </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<a href="/logout">выйти из профиля</a>

</body>
</html>