$(document).ready(function() {
	$("#singleQuesBtnId").click(function() {
		//alert('Single');
		$("#singleQuesDivId").css("display","inline");
		$("#paraQuesDivId").css("display","none");
		$("#imgQuesDivId").css("display","none");
	});
	$("#paraQuesBtnId").click(function() {
		//alert('Para');
		$("#paraQuesDivId").css("display","inline");
		$("#singleQuesDivId").css("display","none");
		$("#imgQuesDivId").css("display","none");
	});
	$("#imgQuesBtnId").click(function() {
		//alert('Img');
		$("#paraQuesDivId").css("display","none");
		$("#singleQuesDivId").css("display","none");
		$("#imgQuesDivId").css("display","inline");
	});
	$("#setExamPageId\\:createExamId").click(function(){
		var examPaperName = $("#setExamPageId\\:examName").val()+$("#setExamPageId\\:paperNo").val();
		if($("#examPapers").val().contains(examPaperName.toUpperCase())) {
			alert("THIS EXAM NAME AND PAPER NO IS ALREADY PRESENT. PLEASE PROVIDE ANOTHER EXAM NAME AND PAPER NO");
			return false;
		}
        return true;
    });
});

function myFunc(data) {
    	alert('Success'+data);
    	$("#viewParaQuesHdnId").val(data);
    	return true;
}