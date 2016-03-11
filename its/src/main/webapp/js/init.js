/**
 * initialize
 */
$(function() {
	$.namespace("org.hpc.its");
	$.extend(org.hpc.its, $.fn.its);
	var its = org.hpc.its;
	
	its.map.width = $(window).get(0).innerWidth;
	its.map.height = $(window).get(0).innerHeight;
	
	its.init($("#its_map"),"127.0.0.1:8080/its/view01");
});
