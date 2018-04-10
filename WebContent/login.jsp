<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mosaics Login</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=2">
        <link rel="stylesheet" type="text/css" href="login.css?v=2">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    </head>
    <body>
        <div id="container">
        		<div id="login-wrap" class="wrapper">
	               <form id="login-form">
	               		<div id="logo">
	               			<img src="logo.png">
	               		</div>
	                    <div id="content"> 
	                
	                        <label for="username"><b>Username</b></label>
	                        <input class="input" placeholder="Enter username" type="text" name="username" align="center" /><br>
	                        
	                        <label for="password"><b>Password</b></label>
	                        <input class="input" placeholder="Enter password" type="password" name="password"/><br>
	                        
	                        <input id="login-button" type="button" name="submit" value=Login onclick="location.href='options.jsp'">                 
	                    </div>
	                </form>  
					<button onclick="location.href='register.jsp'">Register</button>
	         </div>    
	    </div>
    
    </body>
</html>