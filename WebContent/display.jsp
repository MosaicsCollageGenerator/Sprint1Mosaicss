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
        		var myVar;
        		function myFunction() {
        			myVar = setTimeout(showPage, 3000);
        		}
        		
        		function showPage() {
        			  document.getElementById("loader").style.display = "none";
        			  document.getElementById("image-div").style.display = "block";
        			}
			function exportPdf() {
				var options = $("#exportvalue");
				var selected_option = $("#exportvalue option:selected").text();
				if (selected_option === ".png") {
					$('#hey').click();
				} else {
					var imgData;
					html2canvas($("#example-image"), {
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
    </head>
    
    <body onload="myFunction()">
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
								<div id="loader" >
									<img id="loading-image" src ="loadinggif.gif">
								</div>
								<div id="image-div" style ="display:none;">
									<img id="example-image" src="data:image/png;base64, <%=(String)session.getAttribute("collage") %>">
								</div>
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
			<tr colspan = "3">
				<td>
					<label>Gallery:</label><br />
					
				</td>
			</tr>
		</table>
		<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
		<a id="export-button-png" download="collage.png" href="data:image/pdf;base64, <%=(String)session.getAttribute("collage") %>" hidden><input id="export-button" type="submit" value="Export Collage" ></a>
    </body>
</html>
