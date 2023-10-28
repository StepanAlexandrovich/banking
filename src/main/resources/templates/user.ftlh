<!DOCTYPE html>
<html lang="en">
<head>

    <title>User</title>
</head>
<body>
Вы вошли как user c логином ${user.login}
<table>
    <thead>
    <tr>
        <th>Список аккаунтов</th>
    </tr>
    </thead>

    <tbody>
    <#list accounts as account>
    <tr>
        <th> ${account.id} </th>
    </tr>
    </#list>
    </tbody>
</table>

</body>
</html>