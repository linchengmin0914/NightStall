<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/import.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="../include/style.jsp" %>
<title>角色管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*1-100" nullmsg="名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-8">
				<textarea id="des" name="des" cols="" rows="" class="textarea"  placeholder="请输入..." dragonfly="true" onKeyUp="textarealength(this,1000)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/1000</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">网站角色：</label>
			<div class="formControls col-10">
				<c:forEach items="${rightMap}" var="map">
				<dl class="permission-list">
					<dt>
						<label>
							<input type="checkbox" value="${map.key.id}" name="rightId" class="right1 right">
							${map.key.name}</label>
					</dt>
					<dd>
						<dl class="cl permission-list2">
							<c:forEach items="${map.value}" var="right">
							<dt style="width: auto;padding: 5px;">
								<label class="">
									<input type="checkbox" value="${right.id}" name="rightId" class="right2 right">
									${right.name}</label>
							</dt>
							</c:forEach>
						</dl>
					</dd>
				</dl>
				</c:forEach>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$(".right1").click(function(){
		$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
	});
	
	$(".right2").click(function(){
		var objs = $(this).parent().parent().parent().parent().parent().find("dd input:checkbox");
		var len = objs.length;
		var num = 0;
		for(var i = 0;i<len;i++) {
			if($(objs[i]).is(":checked")){ //jQuery方式判断
	            num++;
	        }
		}
		if(num > 0) {
			$(this).parent().parent().parent().parent().parent().find('.right1').prop("checked",true);
		} else {
			$(this).parent().parent().parent().parent().parent().find('.right1').prop("checked",false);
		}
	});
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
		 	var objs = $('.right');
		 	var len = objs.length;
			var arr = new Array();
			for(var i = 0;i<len;i++) {
				if($(objs[i]).is(":checked")){ //jQuery方式判断
		            arr.push($(objs[i]).val());
		        }
			}
		 	var rightIds = arr.join(",");
			
            ajaxForm("${ctx}/admin/role/add/done.html?rightIds=" + rightIds, function(data) {
				if(data.resCode == 1) {
					layer.msg(data.resDes, {icon: 6,time:1000});
					
					setTimeout(function () { 
				        var index = parent.layer.getFrameIndex(window.name);
						parent.$('.btn-refresh').click();
						parent.layer.close(index);
				    }, 1000);
				} else {
					layer.msg(data.resDes,{icon: 5,time:1000});
				}
			});
            return false;  
        }  
    });  
	
	
	
});
</script>
</body>
</html>
