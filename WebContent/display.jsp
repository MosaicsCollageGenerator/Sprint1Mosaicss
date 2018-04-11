<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=3">
        <link rel="stylesheet" type="text/css" href="display.css?v=3">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    </head>
    <body>
        <div id="container">
        		<!--Title -->
        		<div id="title">
            		<h1 id="display-title">Collage for topic  <%=(String)session.getAttribute("topic") %> </h1>        		            	
            	</div>
            <!--Collage Display -->
            	<div id="collage-display">
            	
            		<img id="example-image" src="data:image/png;base64,<%=(String)session.getAttribute("collage") %> ">
   
            	</div>
            	<table id="buttons-table"> 
            		<tr>
            			<td>
			            <!--Export Button -->
			            <label>Export as:</label><br />
			            <select name="exportvalue">
			                  <option value=".png">.png</option>
			                  <option value=".pdf">.pdf</option>
			            </select><br/><br/>
			            <button id="export_button">Export</button>
			         </td>
		        </tr> 
		        <tr>  
		        		<td>
		            <!--Save to Gallery Button -->
		            <button id="save_button">Save to Gallery</button>
		            </td>
				</tr>
			</table>
			
			<!--Build Another Collage -->
			<div id="build-another-collage">
				<button id="build-button" onclick="location.href='index.jsp'">Build Another Collage</button>
            </div>
            
            <!-- Gallery -->
            <div id="footer">
                
                <label>Gallery:</label><br />   
                <div id="collage-history">
                	
                </div> 
            </div>
        </div>
    </body>
</html>