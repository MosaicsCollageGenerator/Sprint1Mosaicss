<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=1">
        <link rel="stylesheet" type="text/css" href="options.css?v=1">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    </head>
    <body>
    		<div class="container">
    			<div id="options-wrap" class="wrapper">
		        <div id="content">
			        <h1 id="options-title">Welcome <%=(String)session.getAttribute("username") %>!</h1>
		            <form id="optionform">
		            		<div id="first-button">
		                <!--Build Collage button-->
		                <button type="button" id="build_collage" onclick="location.href='index.jsp'">Build Collage</button>
		                </div>
		                
		                <div id="second-button">
		                <!--See Collage History button-->
		                <button type="button" id="collage_history" onclick="location.href='display.jsp'">See Collage History</button><br/>
		                 </div>
		            </form>
		        </div>
		    </div>
	     </div>
    </body>
</html>