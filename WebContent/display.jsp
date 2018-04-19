<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=3">
        <link rel="stylesheet" type="text/css" href="display.css?v=1">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <script>
			function exportPdf() {
				var options = $("#exportvalue");
				var selected_option = $("#exportvalue option:selected").text();
				if (selected_option === ".png") {
					$('#hey').click();
				} else {
					var imgData;
					html2canvas($("#collage-pic"), {
	                    useCORS: true,
	                    onrendered: function (canvas) {
	                        imgData = canvas.toDataURL(
	                           'image/png');
	                        var doc = new jsPDF('p', 'pt', 'a4');
	                        doc.addImage(imgData, 'PNG', 10, 10);
	                        doc.save('collage.pdf');
	                    }
	                });
				}
			}
        </script>
        <script type="text/javascript">
	        function changeDisplayedImage(i) {
	        		
				newImage = (document.getElementByClassName(".\\"+ i)).src;
				
				document.querySelector('#collage-pic').src = newImage;
				
				newTopic = document.getElementByClassName(".\\"+i).id;
				
				document.querySelector('.title').innerHTML = newTopic;
			}
        </script>
        <%
        ArrayList<String> collages = ((ArrayList<String>) session.getAttribute("collages"));
        ArrayList<String> titles = ((ArrayList<String>) session.getAttribute("titles"));
        %>
    </head>

    <body>
			<%
				if (session.getAttribute("collage") != null) {
			%>
					<div class="title"> Collage for topic <%=(String)session.getAttribute("topic") %></div>
			<% 		
				} else {
					if (collages != null && collages.size() > 0) {
			%>
					<div class="title"> Collage for topic <%=titles.get(0)%></div>
			<% 
					} else {
			%>
					<div class="title">Please build a collage!</div>
			<%
					}
				}
			%>
    			
    			<div class="container">
    				<div class="contents">
    					<div class="spacer">&nbsp;</div>
    					<div class="collage">
    						<%
    							if (session.getAttribute("collage") != null) {
    						%>
    								<img id="collage-pic" src="data:image/png;base64, <%=(String) session.getAttribute("collage") %>" >
    						<% 		
    							} else {
    								if (collages != null && collages.size() > 0) {
    						%>
    								<img id="collage-pic" src="data:image/png;base64, <%=collages.get(0)%>" >
    						<% 
    								} else {
    						%>
    								<img src="logo.png">
    						<%
    								}
    							}
    						%>
    						
    					</div>
    					<div class="export">
<!--     						<div class="selectdiv"> -->
    							<div>Export as:</div>

	    						<select id="exportvalue">
	    							<option selected="selected">.png </option>
	    							<option>.pdf </option>
	    						</select>
<!--     						</div> -->
							<a><input id="export-button" type="submit" value="Export Collage"  onclick="return exportPdf()"></a>
    					</div>
    				</div>
    			</div>

    			<div class="buttons">
    				<form id="saveform" method="GET" action="SaveServlet">
					<button id="save-button">Save to Gallery</button>
				</form>
    				<button id="build-another-button" onclick="location.href='index.jsp'">Build Another Collage</button>
    			</div>

    			<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
			<a id="export-button-png" download="collage.png" href="data:image/pdf;base64, <%=(String)session.getAttribute("collage") %>" hidden><input id="export-button" type="submit" value="Export Collage" ></a>

			<div id="gallery-title">Gallery:</div>

			<div class="gallery" id = galleryTable>
					    <%
				    if (collages != null) {
					    	for(int i = 0; i < ((ArrayList<String>)session.getAttribute("collages")).size(); i+=1) { 
					    		
					    	%>
				        	<img id=<%=titles.get(i)%> class=<%=i%> width="100" height="100" src="data:image/png;base64, <%=((ArrayList<String>)session.getAttribute("collages")).get(i) %>" onclick=changeDisplayedImage(<%=i%>)>
				    			<% } 	
				    }
					    %>
			</div>
<%-- 		<table id="layout">
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
								<img id="example-image" src="data:image/png;base64, <%=((ArrayList<String>)session.getAttribute("collages")).get(0) %>">
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
						            <select id="exportvalue" name="exportvalue">
						                  <option value=".png" selected="selected">.png</option>
						                  <option value=".pdf">.pdf</option>
						            </select><br/><br/>
						            <!-- <button id="export-button">Export</button> -->
									<a><input id="export-button" type="submit" value="Export Collage"  onclick="return exportPdf()"></a>
									<form id="saveform" method="GET" action="SaveServlet">
						            		<button id="save-button">Save to Gallery</button>
						            </form>
						        </div>
			         		</td>
		        			</tr>
					</table>
				</td>
			</tr>
			</table>
			<label>Gallery:</label><br />
			<table id = "galleryTable">
			<tr colspan = "3">
						<tr>
					    <% for(int i = 0; i < ((ArrayList<String>)session.getAttribute("collages")).size(); i+=1) { %>
					        <td>
					        		<img id="example-image" width="100" height="100" src="data:image/png;base64, <%=((ArrayList<String>)session.getAttribute("collages")).get(i) %>">
					    		</td>
					    <% } %>
						</tr>
			</tr>

			</table>
<%-- 			<tr colspan = "3">
				<td>
					<label>Gallery:</label><br />
						<tr>
					    <% for(int i = 0; i < ((ArrayList<String>)session.getAttribute("collages")).size(); i+=1) { %>
					        <tr>
					            <td><%=allFestivals.get(i).getFestivalName()%></td>
					        </tr>
					        <td>
					        		<img id="example-image" width="100" height="100" src="data:image/png;base64, <%=((ArrayList<String>)session.getAttribute("collages")).get(i) %>">
					    		</td>
					    <% } %>
						</tr>

				</td>
			</tr>
		</table> --%>
		<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
		<a id="export-button-png" download="collage.png" href="data:image/pdf;base64, <%=(String)session.getAttribute("collage") %>" hidden><input id="export-button" type="submit" value="Export Collage" ></a>
    </body>
</html>
