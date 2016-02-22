/**
 * initialize
 */
$(function() {
	$.namespace("edu.hpc.its");
	$.extend(edu.hpc.its, $.fn.its);
	var its = edu.hpc.its;
	
	its.init($("#its_map"),"127.0.0.1:8080/center/view01");
});
