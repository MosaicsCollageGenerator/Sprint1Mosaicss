<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create Account</title>
	    <link rel="stylesheet" type="text/css" href="base.css?v=2">
        <link rel="stylesheet" type="text/css" href="register.css?v=2">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
</head>
<body>
	   <div id="container">
        		<div id="register-wrap" class="wrapper">
	               <form id="register-form">
	               	    <div id="register-title">
	               			<h1> Create an Account </h1>
	               		</div>
	                    <div id="content"> 
	                
	                        <label for="username"><b>Create username: </b></label>
	                        <input class="input" placeholder="Enter username" type="text" name="username" align="center" /><br>
	                        
	                        <label for="password"><b>Create password: </b></label>
	                        <input class="input" placeholder="Enter password" type="text" name="password"/><br>               
	                    </div>
	                </form>  
					<button>Register</button>
					<button onclick="location.href='login.jsp'">Cancel</button>
	         </div>    
	    </div>
</body>
</html>