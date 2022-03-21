<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>registration</title>
</head>
<body>
<h1>REGISTRATION</h1>

<form action="regdetails" method="post" >
Enter Name<input type="text" name="cname" pattern="[a-z A-Z]{3,20}" title="Should only contain letters"><br>
<br>

Enter PhoneNo<input type="tel" name="phoneno" size="10" pattern="[1-9]{3,11}"   title="Should only contain numerical Values with min 3 and max 10 values"><br>
<br>
Enter AccountNo<input type="text" name="acNo" pattern="[1-9]{3,15}" title="Should only contain numerical Values with min 3 and max 15 values" ><br>
<br>
Enter Age <input type="text" name="age" min="18" max="80" value="18" title="age should be above 18" ><br>
<br>
Enter Salary<input type="text" name="salary" min="20000" max="1000000" value="20000" title="Salary should be above 20000"><br>
<br>
<input type="submit" value="Submit"><br>
<br>
</form>

</body>
</html>