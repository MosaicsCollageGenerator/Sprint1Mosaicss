<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=1">
        <link rel="stylesheet" type="text/css" href="display.css?v=1">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    </head>
    <body>
        <div id="container">
        		
        		<!--Title -->
        		<div id="title">
            		<h1 id="display-title">Collage for topic <%=(String)session.getAttribute("topic") %> </h1>
            	</div>
            	
            <!--Collage Display -->
            	<div id="collage-display">
            	
            		<img id="example-image" src="logo.png">
   
            	</div>
            	
            	<table> 
            		<tr>
		            <!--Export Button -->
		            	<div id="export"> 
		            <label>Export as:</label><br />
		            <select name="exportvalue">
		                  <option value=".png">.png</option>
		                  <option value=".pdf">.pdf</option>
		            </select><br/><br/>
		            <button type = "button" id="export_button">Export</button>
		            </div>
		        </tr> 
		        <tr>   
		            <!--Save to Gallery Button -->
		            <div id="save">
		            <button type = "button" id="save_button">Save to Gallery</button>
					</div>
				</tr>
			</table>
			
			<!--Build Another Collage -->
			<div id="Build Another Collage">
			<button type = "button" id="searchbutton" onclick="location.href='index.jsp'">Build Another Collage</button>
            </div>
            
            <!-- Gallery -->
            <footer align="center">
                
                <label>Gallery:</label><br />
                
                
            </footer> 
        </div>
    </body>
</html>