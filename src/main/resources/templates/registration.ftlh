<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">
<head>

    <title>Registration</title>
</head>
<body>
<#if userCreateDto?? && noErrors??>
Login : ${userCreateDto.login}
<br>
Password : ${userCreateDto.password}
<br>
<#else>
<form action="/registration" method="post" >

    Login
    <@spring.formInput "userCreateDto.login" />
    <@spring.showErrors "<br>" />

    <br>
    Password
    <@spring.formInput "userCreateDto.password" />
    <@spring.showErrors "<br>" />

    <br>
    <input type="submit" value="Registration">
    <br>
</form>
</#if>
</body>
</html>