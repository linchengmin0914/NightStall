var chooseImg = '';//选中准备挑换的图片
var choosePic = '';

$(document).on('click','.show-pic-div img',function(){
	if($('.shadow').length > 0){
		if($(this).hasClass('shadow')){
			$(this).removeClass('shadow');
		}else{
			var src = $(this).attr('src');//被替换的图片
			var pic = $(this).parent().parent().parent().attr('pic');//被替换的图片
			$(this).attr('src',chooseImg);
			$(this).parent().parent().parent().attr('pic',choosePic);
			$('.shadow').parent().parent().parent().attr('pic',pic);
			$('.shadow').attr('src',src).removeClass('shadow');
		}
	}else{
		chooseImg = $(this).attr('src');
		choosePic = $(this).parent().parent().parent().attr('pic');
		$(this).addClass('shadow');
	}
});