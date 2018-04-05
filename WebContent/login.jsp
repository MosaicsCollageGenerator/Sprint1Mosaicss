<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mosaics Login</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=2">
    </head>
    <body>
        <div id="content">
            <h1 id="titletext">Mosaics Login </h1>
               <form id="loginform">
                    <div class="field-wrap"> 
                        <label>Username</label>
                        <input class="input" placeholder="Enter username" type="text" name="username" align="center" /><br>
                        <label>Password</label>
                        <input class="input" placeholder="Enter password" type="password" name="password"/><br>
                        <span>
                            <input id="login" type="button" name="submit" value=Login onclick="location.href='options.jsp'">
                        </span>
                        
                    </div>
                </form>       
        </div>
    
    </body>
</html></html>