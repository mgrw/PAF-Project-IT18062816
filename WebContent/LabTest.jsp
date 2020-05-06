<%@page import="com.hcs.controller.LabTest" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LabTest Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/labtest.js"></script>
</head>

<body>

	<div class="container">
	
		<div class="row">
		
			<div class="col-6">
			
				<h1>LabTest Management</h1>
				
				<form id="formLabTest" name="formLabTest">
			
					PatientID : <input id="PatientID" name="PatientID" type="text"
						class="form-control form-control-sm"> 
			
					<br> 
			
					TestName :<input id="TestName" name="TestName" type="text"
						class="form-control form-control-sm"> 
			
					<br>
			
					TestType : <input id="TestType" name="TestType" type="text"
						class="form-control form-control-sm"> 
			
					<br>
			
					TestDescription: <input id="TestDescription" name="TestDescription" type="text"
						class="form-control form-control-sm">
			
					<br> 
					
					LabDate: <input id="LabDate" name="LabDate" type="text"
						class="form-control form-control-sm">
			
					<br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidLabTestIDSave" name="hidLabTestIDSave" value="">
			
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divLabTestGrid">
				<%
					LabTest LabTestObj = new LabTest();
					out.print(LabTestObj.readLabTest());
					%>
				</div>
				
				
			</div>
		</div>
	</div>
</body>
</html>