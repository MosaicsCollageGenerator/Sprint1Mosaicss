<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSAICS</title>
        <link rel="stylesheet" type="text/css" href="base.css?v=1">
    </head>
    <body>
        <div id="content">
            <h1 id="titletext">Collage for topic _____</h1>
            <table>
                <tr>
                    <td width="1100" align="right">
                        <article>
                            <img class="big" width="800" height="600" src="scrum2.jpg" alt="Primary Collage"><br><br>
                        </article>
                    </td>
                    <td>
                        <aside id="sidebar" width="200" align="center">
                            <label>Export as:</label><br />
                            <select name="exportvalue">
                                  <option value=".png">.png</option>
                                  <option value=".pdf">.pdf</option>
                            </select><br/><br/>
                            <button type = "button" id="export_button">Export</button>
                            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                            <button type = "button" id="save_button">Save to Gallery</button>
                        </aside>
                    </td>
                </tr>
            </table>
            
            <footer align="center">
                <button type = "button" id="searchbutton" onclick="location.href='index.jsp'">Build Another Collage</button>
                <br /><br />
                <label>Gallery:</label><br />
                <img class="small" src="img_8173.jpeg" width="200" height="150"> <img class="small" src="Image uploaded from iOS (1).jpg" width="200" height="150">
                
            </footer> 
        </div>
    </body>
</html>