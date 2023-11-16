<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Accounts</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<#if account?? && noErrors??>
Имя аккаунта : ${account.name}
<br>
<#else>
<form action="/user/add_account" method="post" >

    Имя аккаунта
    <@spring.formInput "account.name" />
    <@spring.showErrors "<br>" />

    <br>
    <input type="submit" value="создать аккаунт">
    <br>
</form>
</#if>

<br>
<table>
    <thead>
    <tr>
        <th>Список ваших аккаунтов</th>
    </tr>
    </thead>

    <tbody>
    <#list user.accounts as account>
    <tr>
        <th><a href="/user/account/${account.id}">аккаунт под именем ${account.name}</a> </th>
    </tr>
    </#list>
    </tbody>
</table>

<br>
<a href="/logout">выйти из профиля</a>

</body>
</html>