$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//Save--------------------------------
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateResearcherForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidResearcherIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ResearchersAPI",
		type : type,
		data : $("#formResearcher").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherSaveComplete(response.responseText, status);
		}
		});
	
});

function onResearcherSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divResearchersGrid").html(resultSet.data);
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
	$("#hidResearcherIDSave").val("");
	$("#formResearcher")[0].reset();
}

//remove
$(document).on("click", ".btnRemove", function(event)
		{
		$.ajax(
		{
		url : "ResearchersAPI",
		type : "DELETE",
		data : "rId=" + $(this).data("researcherid"),
		dataType : "text",
		complete : function(response, status)
		{
			onResearcherDeleteComplete(response.responseText, status);
		}
	});
});

function onResearcherDeleteComplete(response, status)
{
	if (status == "success")
	{
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully deleted.");
		$("#alertSuccess").show();
		$("#divResearchersGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
	} else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidResearcherIDSave").val($(this).data("researcherid"));
		$("#rName").val($(this).closest("tr").find('td:eq(0)').text());
		$("#pName").val($(this).closest("tr").find('td:eq(1)').text());
		$("#rDate").val($(this).closest("tr").find('td:eq(2)').text());
		$("#rDes").val($(this).closest("tr").find('td:eq(3)').text());
});

//CLIENT-MODEL================================================================
function validateResearcherForm()
{
	// CODE
	if ($("#rName").val().trim() == "")
	{
		return "Insert Reseacher Name.";
	}
	
	// NAME
	if ($("#pName").val().trim() == "")
	{
		return "Insert Project Name.";
	}
	
	// PRICE-------------------------------
	if ($("#rDate").val().trim() == "")
	{
		return "Insert Date.";
	}
	
	// DESCRIPTION------------------------
	if ($("#rDes").val().trim() == "")
	{
		return "Insert Research Description.";
	}
	
	return true;
}
