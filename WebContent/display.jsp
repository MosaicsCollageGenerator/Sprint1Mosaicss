<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=3">
        <link rel="stylesheet" type="text/css" href="display.css?v=2">
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
			
			$
        </script>
    </head>
    
    <body>
    
    			<div class="title"> Collage for topic <%=(String)session.getAttribute("topic") %></div>
    			<div class="container">
    				<div class="contents">
    					<div class="spacer">&nbsp;</div>
    					<div class="collage">
    						<img id="collage-pic" src="logo.png"><%-- src="data:image/png;base64, <%=(String)session.getAttribute("collage") %>" --%>>
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
    				<button>Save to Gallery</button>
    				<button id="build-another-button" onclick="location.href='index.jsp'">Build Another Collage</button>
    			</div>
    			
    			<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
			<a id="export-button-png" download="collage.png" href="data:image/pdf;base64, <%=(String)session.getAttribute("collage") %>" hidden><input id="export-button" type="submit" value="Export Collage" ></a>
			
			<div id="gallery-title">Gallery:</div> 
			
			<div class="gallery">
				<img class="gallery-pic" src="error.png" onclick="changeImg()">
				<img class="gallery-pic" src="logo.png">
				<img class="gallery-pic" src="loadingimage.gif">
				<img class="gallery-pic" src="logo.png">
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
						            <select id="exportvalue" name="exportvalue">
						                  <option value=".png" selected="selected">.png</option>
						                  <option value=".pdf">.pdf</option>
						            </select><br/><br/>
						            <!-- <button id="export-button">Export</button> -->
									<a><input id="export-button" type="submit" value="Export Collage"  onclick="return exportPdf()"></a>
									
						            <button id="save-button">Save to Gallery</button>
						        </div>
			         		</td>
		        			</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<label>Gallery:</label><br />
						<div id="previous-collages">
							<div>
								<div class="saved-img-div"><img class="saved-img" src="logo.png"></div>
								<div class="saved-img-div"><img class="saved-img" src="logo.png"></div>
								<div class="saved-img-div"><img class="saved-img" src="logo.png"></div>
								<div class="saved-img-div"><img class="saved-img" src="logo.png"></div>
							</div>
						</div>
				</td>
			</tr>
		</table>
		<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
		<a id="export-button-png" download="collage.png" href="data:image/pdf;base64, <%=(String)session.getAttribute("collage") %>" hidden><input id="export-button" type="submit" value="Export Collage" ></a> --%>
    </body>
</html>
