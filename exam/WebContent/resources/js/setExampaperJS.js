$(document).ready(function() {
	$("#singleQuesBtnId").click(function() {
		//alert('Single');
		$("#singleQuesDivId").css("display","inline");
		$("#paraQuesDivId").css("display","none");
	});
	$("#paraQuesBtnId").click(function() {
		//alert('Para');
		$("#paraQuesDivId").css("display","inline");
		$("#singleQuesDivId").css("display","none");
	});
	$("#setExamPageId\\:createExamId").click(function(){
		var examPaperName = $("#setExamPageId\\:examName").val()+$("#setExamPageId\\:paperNo").val();
		if($("#examPapers").val().contains(examPaperName.toUpperCase())) {
			alert("THIS EXAM NAME AND PAPER NO IS ALREADY PRESENT. PLEASE PROVIDE ANOTHER EXAM NAME AND PAPER NO");
			return false;
		}
        return true;
    });
	/*$(".viewParaClass").click(function(){
        $.ajax({url: "#{examBean.showParagraph}", 
        	success: function(result){
            //$("#div1").html(result);
        	alert("Success!!");
        }});
    });*/
});

function myFunc(data) {
    	alert('Success'+data);
    	$("#viewParaQuesHdnId").val(data);
    	return true;
}