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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug.js"></script>        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <script>
			/* $('document').ready(function(){
				var options = $("#exportvalue");
				options.change(function() {
					var selected-option = options.options[options.selectedIndex].value;
				  	if(selected-option === ".png"){
					    document.getElementById("export-button-png").style.display = "block";
					    document.getElementById("export-button-pdf").style.display = "none";
					    $("#export-button-pdf").attr("disabled", true);
					    $("#export-button-png").attr("disabled", false);
				 	 } 
				  	else 
				  	{
					    document.getElementById("export-button-png").style.display = "block";
					    document.getElementById("export-button-pdf").style.display = "none";
					    $("#export-button-png").attr("disabled", true);
					    $("#export-button-pdf").attr("disabled", false);
				  	}
				  
				});
			});  */
			
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
						            <select id="exportvalue" name="exportvalue">
						                  <option value=".png" selected="selected">.png</option>
						                  <option value=".pdf">.pdf</option>
						            </select><br/><br/>
						            <!-- <button id="export-button">Export</button> -->
						            <%-- <a id="export-button-png" download="collage.png" href="data:image/png;base64, <%=(String)session.getAttribute("collage") %>"><input id="export-button" type="submit" value="Export Collage"></a> --%>
									<a id="export-button" ><input id="export-button" type="submit" value="Export Collage"  onclick="return exportPdf()"></a>
									
						            
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
