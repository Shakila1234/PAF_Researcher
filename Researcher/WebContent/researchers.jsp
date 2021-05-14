<%@ page import="com.Researcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Researcher Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/researchers.js"></script>
<meta name="viewpoint" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="container"><div class="row"><div class="col-6"> 

	<h1>Researchers Details</h1>
 			

	<form id='formResearcher' name='formResearcher' method='post' action='researchers.jsp'>
			Research Name: <input id='rName' name='rName' type='text' class='form-control form-control-sm'><br>
			Product Name: <input id='pName' name='pName' type='text' class='form-control form-control-sm'><br> 
			Date: <input id='rDate' name='rDate' type='text' class='form-control form-control-sm'><br> 
			Description: <input id='rDes' name='rDes' type='text' class='form-control form-control-sm'><br> 
			<input id="btnSave" name="btnSave" type="button" value="Save" class='btn btn-primary'>
			<input type='hidden' id='hidResearcherIDSave' name='hidResearcherIDSave' value=''>
	</form>
	


	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>

	<br>
	<div id="divResearchersGrid">
	<%
		Researcher researcherObj = new Researcher();
		out.print(researcherObj.readResearcher());
	%>
	
	</div>
</div> </div> </div>

</body>
</html>