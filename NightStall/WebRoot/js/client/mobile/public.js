$(document).ready(function() {
    //轮播
	if($("div").hasClass("fcousDiv")){
		$(".slider").mobileSlider({width:640,scale:2.15});
	}
	
	
	$('#paiXunBtn').click(function(){
		$('#shaiXuanBtn').removeClass('active');
		$(this).toggleClass('active');
		
		$('#shaiXuanWrap').addClass('hide');
		$('#paiXunWrap').toggleClass('hide');
	});
	
	$('#shaiXuanBtn').click(function(){
		$('#paiXunBtn').removeClass('active');
		$(this).toggleClass('active');
		
		$('#paiXunWrap').addClass('hide');
		$('#shaiXuanWrap').toggleClass('hide');
	});
	
});