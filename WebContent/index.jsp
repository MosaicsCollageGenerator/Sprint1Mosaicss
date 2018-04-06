<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=1">
        <link rel="stylesheet" type="text/css" href="index.css?v=1">
        <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    </head>
    <body>
    		<div id="container">
	        <div id="index-wrap" class="wrapper">
	        <h1 id="titletext">Collage Builder</h1>
	            <form id = "searchform">
	            
	                <!--Topic text input-->
	                <div class="label-divs">
	                <label>Topic</label>
	                <input type="text" id="search_text" placeholder="Enter topic"> <br />
	                </div>
	                
	                <div class="label-divs">
	                <!--Shape text input  -->
	                <label>Shape</label>
	                <input type="text" id="shape_text" placeholder="Enter shape"> <br />
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
	                <label>Filter: </label>
	                
	                <!--Filter Radio buttons  -->
	                <input type="radio" id="none"
	                 name="filter" value="none">
	                <label for="none">None</label>
	            
	                <input type="radio" id="sepia"
	                 name="filter" value="sepia">
	                <label for="sepia">Sepia</label>
	            
	                <input type="radio" id="bandw"
	                 name="filter" value="bandw">
	                <label for="bandw">Black & White</label>
	                
	                <input type="radio" id="grey"
	                 name="filter" value="grey">
	                <label for="grey">Grey</label> <br />
	                </div>
	                
	                <div class="label-divs">
	                <!--Border checkbox  -->
	                    <label>Border</label>
	                <input type= "checkbox" id="border" name="border"/> <br />
	                </div>
	                
	                <div class="label-divs">
	                <label>Rotation</label>
	                <input type= "checkbox" id="border" name="border"/> <br />
	                </div>
	                   
	                <span>
	                    <button type="button" id="build-button" onclick="location.href='display.jsp'">Build Collage</button>
	                </span>
	            </form>
	        </div>
		</div>
    </body>
</html>