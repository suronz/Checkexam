$(document).ready(function() {
	var paraQuesSeparator = "nextQues";
	var paraOption1Separator = "nextOption1";
	var paraOption2Separator = "nextOption2";
	var paraOption3Separator = "nextOption3";
	var paraOption4Separator = "nextOption4";
	var paraOption5Separator = "nextOption5";
	var paraAnsSeparator = "nextAns";
	
	$(function() {
	    $( ".datepicker" ).datepicker();
	  });
	
	$("#paraBtnId").click(function() {
		$("#addQuestionFormId\\:quesCatHidden").val("Paragraph");
		$("#paraDivId").css("display","inline");
		$("#paraQuestionDivId").css("display","none");
		$("#singleQuesDivId").css("display","none");
	});
	
	$("#singleBtnId").click(function() {
		$("#addQuestionFormId\\:quesCatHidden").val("Single");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","none");
		$("#singleQuesDivId").css("display","inline");
	});
	
	$("#proceedBtnId").click(function() {
		$("#questionTypeDivId").css("display","none");
		$("#paraDivId").css("display","none");
		$("#paraQuestionDivId").css("display","inline");
	});
	
	$("#paraAddMoreQuesBtnId").click(function() {
		alert("Add More Ques!! "+paraQuesSeparator);
		$("#addQuestionFormId\\:paraQuesStrHidden").val($("#addQuestionFormId\\:paraQuesStrHidden").val()+$("#addQuestionFormId\\:paraQuesId").val()+paraQuesSeparator);
		$("#addQuestionFormId\\:paraOption1StrHidden").val($("#addQuestionFormId\\:paraOption1StrHidden").val()+$("#addQuestionFormId\\:paraOption1Id").val()+paraOption1Separator);
		$("#addQuestionFormId\\:paraOption2StrHidden").val($("#addQuestionFormId\\:paraOption2StrHidden").val()+$("#addQuestionFormId\\:paraOption2Id").val()+paraOption2Separator);
		$("#addQuestionFormId\\:paraOption3StrHidden").val($("#addQuestionFormId\\:paraOption3StrHidden").val()+$("#addQuestionFormId\\:paraOption3Id").val()+paraOption3Separator);
		$("#addQuestionFormId\\:paraOption4StrHidden").val($("#addQuestionFormId\\:paraOption4StrHidden").val()+$("#addQuestionFormId\\:paraOption4Id").val()+paraOption4Separator);
		$("#addQuestionFormId\\:paraOption5StrHidden").val($("#addQuestionFormId\\:paraOption5StrHidden").val()+$("#addQuestionFormId\\:paraOption5Id").val()+paraOption5Separator);
		$("#addQuestionFormId\\:paraAnsStrHidden").val($("#addQuestionFormId\\:paraAnsStrHidden").val()+$("#addQuestionFormId\\:paraAnsId").val()+paraAnsSeparator);
		
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
		$("#addQuestionFormId\\:paraQuesStrHidden").val($("#addQuestionFormId\\:paraQuesStrHidden").val()+$("#addQuestionFormId\\:paraQuesId").val()+paraQuesSeparator);
		$("#addQuestionFormId\\:paraOption1StrHidden").val($("#addQuestionFormId\\:paraOption1StrHidden").val()+$("#addQuestionFormId\\:paraOption1Id").val()+paraOption1Separator);
		$("#addQuestionFormId\\:paraOption2StrHidden").val($("#addQuestionFormId\\:paraOption2StrHidden").val()+$("#addQuestionFormId\\:paraOption2Id").val()+paraOption2Separator);
		$("#addQuestionFormId\\:paraOption3StrHidden").val($("#addQuestionFormId\\:paraOption3StrHidden").val()+$("#addQuestionFormId\\:paraOption3Id").val()+paraOption3Separator);
		$("#addQuestionFormId\\:paraOption4StrHidden").val($("#addQuestionFormId\\:paraOption4StrHidden").val()+$("#addQuestionFormId\\:paraOption4Id").val()+paraOption4Separator);
		$("#addQuestionFormId\\:paraOption5StrHidden").val($("#addQuestionFormId\\:paraOption5StrHidden").val()+$("#addQuestionFormId\\:paraOption5Id").val()+paraOption5Separator);
		$("#addQuestionFormId\\:paraAnsStrHidden").val($("#addQuestionFormId\\:paraAnsStrHidden").val()+$("#addQuestionFormId\\:paraAnsId").val()+paraAnsSeparator);
	});
});