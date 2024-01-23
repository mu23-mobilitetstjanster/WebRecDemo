<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="/home/login" method="POST">
    <div>
        <label>Username</label>
        <input name="username" type="text" />
    </div>

    <div>
        <label>Password</label>
        <input name="password" type="password" />
    </div>

    <button>Login</button>
</form>