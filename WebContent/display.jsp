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
		<table>
			<tr>
				<td colspan="3" id="title-row">
					<h1 id="display-title">Collage for topic <%=(String)session.getAttribute("topic") %> </h1>
				</td>
			</tr>
			<tr>
				<td id="buffer-column"></td>
				<td id="display-collage">
					<table>
						<tr>
							<td>
								<img id="example-image" src="data:image/png;base64, <%=(String)session.getAttribute("collage") %>">
							</td>
						</tr>
						<tr>
							<td>
					            <!--Build Another Collage Button -->
					            <button id="build-another-button" onclick="location.href='index.jsp'">Build Another Collage</button>
			            		</td>
						</tr>
					</table>
				</td>
				<td id="export-column">
					 <table id="buttons-table">
            				<tr>
            					<td>
					            <!--Export Button -->
					            <div class="selectdiv">
						            <label>Export as:</label><br />
						            <select name="exportvalue">
						                  <option value=".png">.png</option>
						                  <option value=".pdf">.pdf</option>
						            </select><br/><br/>
						            <button id="export-button">Export</button>
						            <button id="save-button">Save to Gallery</button>
						        </div>
			         		</td>
		        			</tr>
					</table>
				</td>
			</tr>
			<tr colspan = "3">
				<td>
					<label>Gallery:</label><br />
					
				</td>
			</tr>
		</table>
    </body>
</html>
