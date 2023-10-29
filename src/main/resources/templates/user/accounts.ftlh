<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accounts</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Список ваших аккаунтов</th>
    </tr>
    </thead>

    <tbody>
    <#list user.accounts as account>
    <tr>
        <th><a href="/user/account/${account.id}">аккаунт под индексом ${account.id}</a> </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<a href="/logout">выйти из профиля</a>

</body>
</html>