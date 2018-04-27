<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList, main.java.model.Collage" %>
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
        
	        $(document).ready(function(){
	            $("#build-button").click(function(){
					if(validateForms()){
						loadImage();
						$("#collage-title").text("Collage for topic " + $("#search_text").val());
						var topic = $("#search_text").val(); 
						var shape = $("#shape_text").val();
						var height = $("#height_value").val();
						var width = $("#width_value").val();
						var filter = $("#filter_value").val();
						var border = $("#border_value").is(":checked");
						var rotation = $("#rotation_value").is(":checked");	
						
						var getRequest = "http://localhost:8080/Mosaicss/build?search_text=" + topic + "&shape_text=" + shape + "&heightvalue=" + height + "&widthvalue="
								+ width + "&filter=" + filter + "&border=" + border + "&rotation=" + rotation; 
						$.ajax({
							url: getRequest,
							success: function(result){
								var htmlResult = $.parseHTML(result);
								var imgString = $(htmlResult).find("#collage-pic");
								$("#collage-div").empty();
								$("#collage-div").append(imgString);
							} 						
						});
					}
	            });
	            
	            $(".display_button").click(function(){
	            	// this is save_button
	            		var topic = document.getElementById("collage-pic").className;
	            		var src = document.getElementById("collage-pic").src;
	            		var getRequest = "http://localhost:8080/Mosaicss/build?search_text=" + topic + "&shape_text=" + shape + "&heightvalue=" + height + "&widthvalue="
						+ width + "&filter=" + filter + "&border=" + border + "&rotation=" + rotation; 
					$.ajax({
						url: getRequest,
						success: function(result){
							var htmlResult = $.parseHTML(result);
							var imgString = $(htmlResult).find("#collage-pic");
							$("#collage-div").empty();
							$("#collage-div").append(imgString);
						} 
						
					});
	            });
	            
	            $("#delete-button").click(function(){
					if(validateForms()){
						// get title/id from main displayed image
						$("#collage-title").text("Collage for topic " + $("#search_text").val());
						var topic = $("#collage-title").val(); 
						var shape = $("#shape_text").val();
						var height = $("#height_value").val();
						var width = $("#width_value").val();
						var filter = $("#filter_value").val();
						var border = $("#border_value").is(":checked");
						var rotation = $("#rotation_value").is(":checked");	
						
						var getRequest = "http://localhost:8080/Mosaicss/DeleteServlet?topic=" + topic + "&shape_text=" + shape + "&heightvalue=" + height + "&widthvalue="
								+ width + "&filter=" + filter + "&border=" + border + "&rotation=" + rotation; 
						$.ajax({
							url: getRequest,
							success: function(result){
								var htmlResult = $.parseHTML(result);
								var imgString = $(htmlResult).find("#collage-pic");
								$("#collage-div").empty();
								$("#collage-div").append(imgString);
							} 
							
						});
					}
	            });
	        });
	        
	        function validateForms() {
				var topic = $("#search_text").val(); 
				var shape = $("#shape_text").val();
				
				if(!topic) {
					console.log("Empty")
					return false;
				} 
				if(!shape){
					return false;
				}
				loadImage();
				return true;
			}
			
			function loadImage() {
				document.getElementById("collage-pic").src = "loadinggif.gif";
			}
        
			function exportPdf() {
				var options = $("#exportvalue");
				var selected_option = $("#exportvalue option:selected").text();
				if (selected_option === ".png ") {
					var imgSrc = document.getElementById("collage-pic").src;
					var a = document.getElementById("export-button-png");
					a.href = imgSrc
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
	        		var num = i.toString();
				newImage = (document.getElementsByClassName(num)).src;
				
				document.querySelector('#collage-pic').src = newImage;
				newTopic = document.getElementsByClassName(num).id;
				/* newTopic = document.getElementsByClassName(".\\"+i).id; */
				
				document.querySelector('.title').innerHTML = newTopic;
				
				console.log(num)
			}
        </script>
        <%
        ArrayList<String> collages = ((ArrayList<String>) session.getAttribute("collages"));
        ArrayList<String> titles = ((ArrayList<String>) session.getAttribute("titles"));
        ArrayList<Collage> collages_class = ((ArrayList<Collage>) session.getAttribute("collages_classes"));
        %>
    </head>

    <body>
			<%
				if (session.getAttribute("collage") != null) {
			%>
					<div class="title" id="collage-title"> Collage for topic <%=(String)session.getAttribute("topic") %></div>
			<% 		
				} else {
					if (collages != null && collages.size() > 0) {
			%>
					<div class="title" id="collage-title"> Collage for topic <%=titles.get(0)%></div>
			<% 
					} else {
			%>
					<div class="title" id="collage-title">Please build a collage <%=(String)session.getAttribute("username") %>!</div>
			<%
					}
				}
			%>
    			
    			<div class="container">
    				<div class="contents">
    					<div class="spacer">
				            <div>				            		
				                <!--Topic text input-->
				                <div class="label-divs">
				                <label>Topic</label>
				                <input type="text" id="search_text" name="search_text" placeholder="Enter topic"> <br />
				                </div>
				                
				                <div class="label-divs">
				                <!--Shape text input  -->
				                <label>Shape</label>
				                <input type="text" id="shape_text" name="shape_text" placeholder="Enter shape"> <br />
				                </div>
				                
				                <div class="label-divs">
				                <!--Height drop down  -->
				                <label>Height</label>
				                <select name="heightvalue" id="height_value">
				                  <option value="300">300</option>
				                  <option value="400">400</option>
				                  <option value="500">500</option>
				                  <option value="600">600</option>
				                  <option value="700">700</option>
				                  <option value="800">800</option>
				                  <option value="900">900</option>
				                  <option value="1000">1000</option>
				                </select><br />
				                </div>
				                
				                <div class="label-divs">
				                <!--Width drop down  -->
				                <label>Width</label>
				                <select name="widthvalue" id="width_value">
				                  <option value="500">500</option>
				                  <option value="600">600</option>
				                  <option value="700">700</option>
				                  <option value="800">800</option>
				                  <option value="900">900</option>
				                  <option value="1000">1000</option>
				                  <option value="1100">1100</option>
				                  <option value="1200">1200</option>
				                </select><br />
				                </div>
				                
				                	<div class="label-divs">
				                <!--Width drop down  -->
				                <label>Filter: </label>
				                <select id="filter_value" name="filter">
				                  <option value="0">None</option>
				                  <option value="1">Sepia</option>
				                  <option value="2">Black & White</option>
				                  <option value="3">Greyscale</option>
				                </select><br />
				                </div>
				                
				                <div class="label-divs">
				                <!--Border checkbox  -->
				                    <label>Border</label>
				                <input type= "checkbox" id="border_value" name="border"/> <br />
				                </div>
				                
				                <div class="label-divs">
				                <label>Rotation</label>
				                <input type= "checkbox" id="rotation_value" name="rotation"/> <br />
				                </div>
				                   
				                <input type="submit" class="butt" id="build-button" value="Build Collage"/>	                
				            </div>
    					</div> <!-- End of Spacer Div -->
    					<div id="collage-div" class="collage">
    						<%
    							if (session.getAttribute("collage") != null) {
    						%>
    								<img id="collage-pic" src="data:image/png;base64, <%=(String) session.getAttribute("collage") %>" >
    						<% 		
    							} else {
    								if (collages != null && collages.size() > 0) {
    						%>
    								<img class="<%=collages_class.get(0).getTitle()%>" id="collage-pic" src="data:image/png;base64, <%=collages.get(0)%>" >
    						<% 
    								} else {
    						%>
    								<img id="collage-pic" src="logo.png">
    						<%
    								}
    							}
    						%>
    						
    					</div>
    					<div class="export">
    							<div>Export as:</div>

	    						<select id="exportvalue">
	    							<option selected="selected">.png </option>
	    							<option>.pdf </option>
	    						</select>
							<a><input id="export-button" type="submit" value="Export Collage"  onclick="return exportPdf()"></a>
    					</div>
    				</div>
    			</div>

    			<div class="buttons">
				<button class = "display_button" id="save-button">Save to Gallery</button>
				<form id="deleteform" method="GET" action="DeleteServlet">
						<button class = "display_button" id="delete-button">Delete from Gallery</button>
				</form>
    				<button id="build-another-button" onclick="location.href='display.jsp'" hidden>Build Another Collage</button>
    			</div>
  
    			
    			<button id="hey" onclick="document.getElementById('export-button-png').click()" hidden>DO NOT DELETE</button>
			<a id="export-button-png" download="collage.png" href="data:image/png;base64, <%=(String)session.getAttribute("collage")%>" hidden><input id="export-button" type="submit" value="Export Collage" ></a>
			<div id="gallery-title">Gallery:</div>

			<div class="gallery" id = galleryTable>
					    <%
				    if (collages != null) {
					    	for(int i = 0; i < ((ArrayList<String>)session.getAttribute("collages")).size(); i+=1) { 
					    		
					    	%>
				        	<img id=<%=titles.get(i)%> class=<%=i%> width="100" height="100" src="data:image/png;base64,<%=((ArrayList<String>)session.getAttribute("collages")).get(i)%>" onclick=changeDisplayedImage(<%=i%>)>
				    			<% } 	
				    }
					    %>
			</div>
    </body>
</html>
