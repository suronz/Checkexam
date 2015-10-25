$(document).ready(function() {
	var quesSeparator = "nextQues";
	var option1Separator = "nextOption1";
	var option2Separator = "nextOption2";
	var option3Separator = "nextOption3";
	var option4Separator = "nextOption4";
	var option5Separator = "nextOption5";
	var ansSeparator = "nextAns";
	
	$(function() {
	    $( ".datepicker" ).datepicker();
	  });
	
	$("#paraBtnId").click(function() {
		$("#addQuestionFormId\\:quesCatHidden").val("Paragraph");
		$("#paraDivId").css("display","inline");
		$("#paraQuestionDivId").css("display","none");
		$("#singleQuesDivId").css("display","none");
		$("#imgDivId").css("display","none");
		$("#imgQuestionDivId").css("display","none");
	});
	
	$("#singleBtnId").click(function() {
		$("#addQuestionFormId\\:quesCatHidden").val("Single");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","none");
		$("#singleQuesDivId").css("display","inline");
		$("#imgDivId").css("display","none");
		$("#imgQuestionDivId").css("display","none");
	});
	
	$("#paraProceedBtnId").click(function() {
		$("#questionTypeDivId").css("display","none");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","inline");
		$("#imgDivId").css("display","none");
		$("#imgQuestionDivId").css("display","none");
	});
	
	$("#imageBtnId").click(function() {
		$("#addQuestionFormId\\:quesCatHidden").val("Image");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","none");
		$("#singleQuesDivId").css("display","none");
		$("#imgDivId").css("display","inline");
		$("#imgQuestionDivId").css("display","none");
	});
	
	$("#imgProceedBtnId").click(function() {
		$("#questionTypeDivId").css("display","none");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","none");
		$("#imgProceedBtnId").css("display","none");
		$("#imgDivId").css("display","none");
		$("#imgQuestionDivId").css("display","inline");
	});
	
	/* Implementation for Para Question - Start */
	$("#paraAddMoreQuesBtnId").click(function() {
		alert("Add More Ques!! "+quesSeparator);
		$("#addQuestionFormId\\:paraQuesStrHidden").val($("#addQuestionFormId\\:paraQuesStrHidden").val()+$("#addQuestionFormId\\:paraQuesId").val()+quesSeparator);
		$("#addQuestionFormId\\:paraOption1StrHidden").val($("#addQuestionFormId\\:paraOption1StrHidden").val()+$("#addQuestionFormId\\:paraOption1Id").val()+option1Separator);
		$("#addQuestionFormId\\:paraOption2StrHidden").val($("#addQuestionFormId\\:paraOption2StrHidden").val()+$("#addQuestionFormId\\:paraOption2Id").val()+option2Separator);
		$("#addQuestionFormId\\:paraOption3StrHidden").val($("#addQuestionFormId\\:paraOption3StrHidden").val()+$("#addQuestionFormId\\:paraOption3Id").val()+option3Separator);
		$("#addQuestionFormId\\:paraOption4StrHidden").val($("#addQuestionFormId\\:paraOption4StrHidden").val()+$("#addQuestionFormId\\:paraOption4Id").val()+option4Separator);
		$("#addQuestionFormId\\:paraOption5StrHidden").val($("#addQuestionFormId\\:paraOption5StrHidden").val()+$("#addQuestionFormId\\:paraOption5Id").val()+option5Separator);
		$("#addQuestionFormId\\:paraAnsStrHidden").val($("#addQuestionFormId\\:paraAnsStrHidden").val()+$("#addQuestionFormId\\:paraAnsId").val()+ansSeparator);
		
		$("#addQuestionFormId\\:paraQuesId").val("");
		$("#addQuestionFormId\\:paraOption1Id").val("");
		$("#addQuestionFormId\\:paraOption2Id").val("");
		$("#addQuestionFormId\\:paraOption3Id").val("");
		$("#addQuestionFormId\\:paraOption4Id").val("");
		$("#addQuestionFormId\\:paraOption5Id").val("");
		$("#addQuestionFormId\\:paraAnsId").val("");
	});
	
	$("#addQuestionFormId\\:paraSaveQuesBtnId").click(function() {
		alert("save all Ques!! ");
		$("#addQuestionFormId\\:paraQuesStrHidden").val($("#addQuestionFormId\\:paraQuesStrHidden").val()+$("#addQuestionFormId\\:paraQuesId").val()+quesSeparator);
		$("#addQuestionFormId\\:paraOption1StrHidden").val($("#addQuestionFormId\\:paraOption1StrHidden").val()+$("#addQuestionFormId\\:paraOption1Id").val()+option1Separator);
		$("#addQuestionFormId\\:paraOption2StrHidden").val($("#addQuestionFormId\\:paraOption2StrHidden").val()+$("#addQuestionFormId\\:paraOption2Id").val()+option2Separator);
		$("#addQuestionFormId\\:paraOption3StrHidden").val($("#addQuestionFormId\\:paraOption3StrHidden").val()+$("#addQuestionFormId\\:paraOption3Id").val()+option3Separator);
		$("#addQuestionFormId\\:paraOption4StrHidden").val($("#addQuestionFormId\\:paraOption4StrHidden").val()+$("#addQuestionFormId\\:paraOption4Id").val()+option4Separator);
		$("#addQuestionFormId\\:paraOption5StrHidden").val($("#addQuestionFormId\\:paraOption5StrHidden").val()+$("#addQuestionFormId\\:paraOption5Id").val()+option5Separator);
		$("#addQuestionFormId\\:paraAnsStrHidden").val($("#addQuestionFormId\\:paraAnsStrHidden").val()+$("#addQuestionFormId\\:paraAnsId").val()+ansSeparator);
	});
	/* Implementation for Para Question - End */
	
	/* Implementation for Image Question - Start */
	$("#imgAddMoreQuesBtnId").click(function() {
		alert("Add More Ques!! "+quesSeparator);
		$("#addQuestionFormId\\:imgQuesStrHidden").val($("#addQuestionFormId\\:imgQuesStrHidden").val()+$("#addQuestionFormId\\:imgQuesId").val()+quesSeparator);
		$("#addQuestionFormId\\:imgOption1StrHidden").val($("#addQuestionFormId\\:imgOption1StrHidden").val()+$("#addQuestionFormId\\:imgOption1Id").val()+option1Separator);
		$("#addQuestionFormId\\:imgOption2StrHidden").val($("#addQuestionFormId\\:imgOption2StrHidden").val()+$("#addQuestionFormId\\:imgOption2Id").val()+option2Separator);
		$("#addQuestionFormId\\:imgOption3StrHidden").val($("#addQuestionFormId\\:imgOption3StrHidden").val()+$("#addQuestionFormId\\:imgOption3Id").val()+option3Separator);
		$("#addQuestionFormId\\:imgOption4StrHidden").val($("#addQuestionFormId\\:imgOption4StrHidden").val()+$("#addQuestionFormId\\:imgOption4Id").val()+option4Separator);
		$("#addQuestionFormId\\:imgOption5StrHidden").val($("#addQuestionFormId\\:imgOption5StrHidden").val()+$("#addQuestionFormId\\:imgOption5Id").val()+option5Separator);
		$("#addQuestionFormId\\:imgAnsStrHidden").val($("#addQuestionFormId\\:imgAnsStrHidden").val()+$("#addQuestionFormId\\:imgAnsId").val()+ansSeparator);
		
		$("#addQuestionFormId\\:imgQuesId").val("");
		$("#addQuestionFormId\\:imgOption1Id").val("");
		$("#addQuestionFormId\\:imgOption2Id").val("");
		$("#addQuestionFormId\\:imgOption3Id").val("");
		$("#addQuestionFormId\\:imgOption4Id").val("");
		$("#addQuestionFormId\\:imgOption5Id").val("");
		$("#addQuestionFormId\\:imgAnsId").val("");
	});
	
	$("#addQuestionFormId\\:imgSaveQuesBtnId").click(function() {
		alert("save all Ques!! ");
		$("#addQuestionFormId\\:imgQuesStrHidden").val($("#addQuestionFormId\\:imgQuesStrHidden").val()+$("#addQuestionFormId\\:imgQuesId").val()+quesSeparator);
		$("#addQuestionFormId\\:imgOption1StrHidden").val($("#addQuestionFormId\\:imgOption1StrHidden").val()+$("#addQuestionFormId\\:imgOption1Id").val()+option1Separator);
		$("#addQuestionFormId\\:imgOption2StrHidden").val($("#addQuestionFormId\\:imgOption2StrHidden").val()+$("#addQuestionFormId\\:imgOption2Id").val()+option2Separator);
		$("#addQuestionFormId\\:imgOption3StrHidden").val($("#addQuestionFormId\\:imgOption3StrHidden").val()+$("#addQuestionFormId\\:imgOption3Id").val()+option3Separator);
		$("#addQuestionFormId\\:imgOption4StrHidden").val($("#addQuestionFormId\\:imgOption4StrHidden").val()+$("#addQuestionFormId\\:imgOption4Id").val()+option4Separator);
		$("#addQuestionFormId\\:imgOption5StrHidden").val($("#addQuestionFormId\\:imgOption5StrHidden").val()+$("#addQuestionFormId\\:imgOption5Id").val()+option5Separator);
		$("#addQuestionFormId\\:imgAnsStrHidden").val($("#addQuestionFormId\\:imgAnsStrHidden").val()+$("#addQuestionFormId\\:imgAnsId").val()+ansSeparator);
	});
	/* Implementation for Image Question - End */
});