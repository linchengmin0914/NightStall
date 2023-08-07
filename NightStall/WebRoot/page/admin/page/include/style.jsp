<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxAdmin}/lib/html5.js"></script>
<script type="text/javascript" src="${ctxAdmin}/lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxAdmin}/lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxAdmin}/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="${ctxAdmin}/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<link href="${ctxAdmin}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />

<link href="${ctxAdmin}/css/hsCheckData.css" rel="stylesheet" />


<script type="text/javascript" src="${ctxAdmin}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/lib/Validform/5.3.2/Validform.min.js"></script> 
<%--
<script type="text/javascript" src="${ctxAdmin}/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="${ctxAdmin}/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="${ctxAdmin}/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
--%>
<script type="text/javascript" charset="utf-8" src="${ctxAdmin}/lib/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxAdmin}/lib/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.all.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctxAdmin}/lib/ueditor1_4_3_3-utf8-jsp/utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript" src="${ctxAdmin}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctxAdmin}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/js/H-ui.admin.js"></script> 

<script type="text/javascript" src="${ctxAdmin}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctxAdmin}/js/jquery.form.min.js"></script>

<script type="text/javascript" src="${ctxAdmin}/js/cityDataCN.js"></script>
<script type="text/javascript" src="${ctxAdmin}/js/hsCheckData.js"></script>
<script type="text/javascript" src="${ctx}/js/html5ImgCompress.min.js" ></script>

<script type="text/javascript">
$(function(){
	
});

UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
        return '${ctx}/uploadImages.html';
    } else if (action == 'uploadvideo') {
        return '${ctx}/uploadImages.html';
    } else {
    	var url = this._bkGetActionUrl.call(this, action);
    	console.log(url);
        return url;
    }
}

function ajaxForm(url,sucFunc) {
	var data = $('#form-add').serialize();
	ajaxFunc(url, data, sucFunc);
}

function ajaxFunc(url, data, sucFunc) {
	$.ajax({
        type: "POST",
        url:url,
        data:data,
        dataType: "json",
        timeout: 60000, //超时时间
        error: function(request) {
            Alert("Connection error");
        },
        success: function(data) {
            sucFunc(data);
        }
    });
}

function ajaxFuncTime(url, data, time, sucFunc) {
	$.ajax({
        type: "POST",
        url:url,
        data:data,
        dataType: "json",
        timeout: time, //超时时间
        error: function(request) {
            Alert("Connection error");
        },
        success: function(data) {
            sucFunc(data);
        }
    });
}

//异步上传图片
function uploadPic(picInput){
    //定义参数
    var options={
        url:"${ctx}/uploadPic.html",
        dataType:"text",
        type:"post",
        timeout: 60000, //超时时间：30秒
        success:function(data){
            //将字符串格式转化成json对象
            data=eval("("+data+")");
            $("#" + picInput).val(data.resDes);
        },
        error:function(){
           // alert("upload error");
        }
    };
    //使用jquery.form异步上传图片
    if($('#form-' + picInput).length > 0) {
    	$('#form-' + picInput).ajaxSubmit(options);
    } else {
    	$('#form-add').ajaxSubmit(options);
    }
    
    
}   

//异步上传图片
function uploadPic(picInput, ppath){
    //定义参数
    var url = "${ctx}/uploadPic.html";
    if(ppath != undefined) url += "?ppath=" + ppath;
    var options={
        url:url,
        dataType:"text",
        type:"post",
        timeout: 60000, //超时时间：30秒
        success:function(data){
            //将字符串格式转化成json对象
            data=eval("("+data+")");
            $("#" + picInput).val(data.resDes);
        },
        error:function(){
           // alert("upload error");
        }
    };
    //使用jquery.form异步上传图片
    if($('#form-' + picInput).length > 0) {
    	$('#form-' + picInput).ajaxSubmit(options);
    } else {
    	$('#form-add').ajaxSubmit(options);
    }
}  

//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null ; 
	if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
}

//提示信息
var lang = {
    "sProcessing": "数据正在加载中...",
    "sLengthMenu": "每页 _MENU_ 项",
    "sZeroRecords": "没有匹配结果",
    "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
    "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
    "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
    "sInfoPostFix": "",
    "sSearch": "搜索:",
    "sUrl": "",
    "sEmptyTable": "表中数据为空",
    "sLoadingRecords": "载入中...",
    "sInfoThousands": ",",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上页",
        "sNext": "下页",
        "sLast": "末页",
        "sJump": "跳转"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
        "sSortDescending": ": 以降序排列此列"
    }
};

function showImg(title,url,id,w,h){
	layer_show(title,url,w,h);
}


//$(window).bind('beforeunload',function(){ 
//	return '您确定离开此页面吗？'; 
//}); 
</script>


<style type="text/css">
#BgDiv1{background-color:#000; position:absolute; z-index:9999;  display:none;left:0px; top:0px; width:100%; height:100%;opacity: 0.6; filter: alpha(opacity=60);}
.DialogDiv{position:absolute;z-index:99999;}
.U-guodu-box { padding:5px 15px;  background:#3c3c3f; filter:alpha(opacity=90); -moz-opacity:0.9; -khtml-opacity: 0.9; opacity: 0.9;  min-heigh:200px; border-radius:10px;}
.U-guodu-box div{ color:#fff; line-height:20px; font-size:12px; margin:0px auto; height:100%; padding-top:10%; padding-bottom:10%;}
</style>

<script language="javascript" type="text/javascript">
	 function hiddenDiv() {
		 hiddenDivTit("图片上传中，请稍后...");
	 }
	 
	 
	 function hiddenDivTit(title) {
		 $("#BgDiv1").css({ display: "block", height: $(document).height() });
		 var yscroll = document.documentElement.scrollTop;
		 var screenx=$(window).width();
		 var screeny=$(window).height();
		 $(".DialogDiv").css("display", "block");
		 $(".DialogDiv").css("top",yscroll+"px");
		 var DialogDiv_width=$(".DialogDiv").width();
		 var DialogDiv_height=$(".DialogDiv").height();
		 $(".DialogDiv").css("left",(screenx/2-DialogDiv_width/2)+"px")
		 $(".DialogDiv").css("top",(screeny/2-DialogDiv_height/2)+"px")
		 $("body").css("overflow","hidden");
		 $('#subtext').html(title);
	 }
	 
	 function showDiv() {
		 $("#BgDiv1").css({ "display": "none"});
		 $(".DialogDiv").css("display", "none");
		 $("body").css("overflow","auto");
	 }
 </script>

<div id="BgDiv1"></div>
<div class="U-login-con">
      	<div class="DialogDiv"  style="display:none; ">
              <div class="U-guodu-box">
              <div>
              <table width="100%" cellpadding="0" cellspacing="0" border="0" >
                  <tr><td  align="center"><img src="${imgPrefix}/images/loading.gif"></td></tr>
                  <tr><td id="subtext" valign="middle" align="center" style="color: #FFF;">提交中，请稍后...</td></tr>
              </table>
              </div>
      	</div>
	</div>
    <div class="cl"></div>
</div>    