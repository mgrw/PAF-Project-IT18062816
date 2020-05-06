// hide the divisions used to show the status messages on the page load
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide(); 
}); 

//Save button 
$(document).on("click", "#btnSave", function(event)
{
	// Clear status msgesx
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
		 
	// Form validation
	var status = validateLabTestForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid
	//$("#formPayment").submit();
	var type = ($("#hidLabTestIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
			{
			 url : "LabTestAPI",
			 type : type,
			 data : $("#formLabTest").serialize(),
			 dataType : "text",
			 complete : function(response, status)
			 {
				 onLabTestSaveComplete(response.responseText, status);
			 }
			});
});

function onLabTestSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divLabTestGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidLabTestIDSave").val("");
	$("#formLabTest")[0].reset(); 
}

//CLIENTMODEL
function validateLabTestForm()
{
	

	if ($("#PatientID").val().trim() == "")
	{
		return "PatientID";
	} 
	
	// is numerical value
	var tmpPatientID = $("#PatientID").val().trim();
	if (!$.isNumeric(tmpPatientID))
	{
	return "Insert a numerical value for PatientID.";
	}
	
	if ($("#TestName").val().trim() == "")
	{
		return "TestName";
	}
	
	if ($("#TestType").val().trim() == "")
	{
		return "Insert TestType";
	}
	
	// is String value
	var tmpTestType = $("#TestType").val().trim();
	if ($.isNumeric(tmpTestType))
	{
	return "Insert a Valid TestType (Starts with letters!)";
	}
	
	if ($("#TestDescription").val().trim() == "")
	{
		return "Insert TestDescription";
	}
	
	
	//is String value
	var tmpTestDescription = $("#TestDescription").val().trim();
	if ($.isNumeric(tmpTestDescription))
	{
	return "Insert a Valid TestDescription (Starts with letters!)";
	}
	
	
	if ($("#LabDate").val().trim() == "")
	{
		return "Insert LabDate";
	}
	

	return true;
}

//Update Button

$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidLabTestIDSave").val($(this).closest("tr").find('#hidLabTestIDUpdate').val());
	//$("#LabTestID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#PatientID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#TestName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#TestType").val($(this).closest("tr").find('td:eq(2)').text());
	$("#TestDescription").val($(this).closest("tr").find('td:eq(3)').text());
	$("#LabDate").val($(this).closest("tr").find('td:eq(4)').text());
});

//DELETE Button

$(document).on("click", ".btnRemove", function(event) {  
	
	$.ajax(  {   
		
		url : "LabTestAPI",   
		type : "DELETE",   
		data : "LabTestID=" + $(this).data("labtestid"),   
		dataType : "text",   
		complete : function(response, status)   {    
			onLabTestDeleteComplete(response.responseText, status);   
			
		}  
	}); 
	
}); 

function onLabTestDeleteComplete(response, status) {  
	
	if (status == "success")  {   
		
		var resultSet = JSON.parse(response); 

			if (resultSet.status.trim() == "success")   {    
				
				$("#alertSuccess").text("Successfully deleted.");    
				$("#alertSuccess").show(); 
				$("#divLabTestGrid").html(resultSet.data);   
				
			} else if (resultSet.status.trim() == "error")   {    
				
				$("#alertError").text(resultSet.data);    
				$("#alertError").show();   
				
			} 

	} else if (status == "error")  {   
		
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
		
	} else  {   
		
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
		
	} 
	
}
