<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../include/import.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="../../include/style.jsp" %>
<title>门店管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
<span class="c-gray en">&gt;</span> 门店管理 
<span class="c-gray en">&gt;</span> 门店列表 
<a class="btn btn-success radius r mr-20 btn-refresh" style="line-height:1.6em;margin-top:3px" href="javascript:void(0);" onclick="location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div class="pd-20" style="padding-top: 10px;">
	<div class="text-c col-12">
		<label class="form-label col-1" style="line-height: 30px;">门店名称：</label>
		<div class="formControls col-3">
			<input type="text" class="input-text" value="" placeholder="输入要查询的关键词" id="name">
		</div>
		
		<label class="form-label col-1" style="line-height: 30px;">是否开业：</label>
		<div class="col-3">
		<span class="select-box">
			<select id="status" class="select">
				<option value="">所有</option>
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
		</span>
		</div>
		
		<div class="col-1">
		<button type="button" class="btn btn-success" onclick="search();"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</div>
		<button type="button" class="btn btn-success search_btn hidden" onclick="search_BC();"><i class="Hui-iconfont">&#xe665;</i> </button>
	</div>
	
	<div class="cl pd-5 bg-1 bk-gray mt-10 mb-10 col-12"> 
		<span class="l">
			<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
			<a href="javascript:;" onclick="add('门店添加','${ctx}/admin/01/store/add.html','800','600')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加门店</a>
		</span> 
	</div>
	
	<table class="table table-border table-bordered table-bg table-hover table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="150">门店名称</th>
				<th width="100">封面图</th>
				<th width="50">联系电话</th>
				<th width="150">地址</th>
				<th width="100">营业时间</th>
				<th width="50">是否开业</th>
				<th width="130">发布时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
	</table>
	
</div>
<script type="text/javascript">
var table;
$(function(){
	//初始化表格
    table = $(".table-sort").dataTable({
        language:lang,  //提示信息
        autoWidth: true,  //自动调整列宽
        stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
        processing: true,  //隐藏加载提示,自行处理
        serverSide: true,  //启用服务器端分页
        searching: false,  //原生搜索
        orderMulti: false,  //启用多列排序
        aaSorting: [[1, "desc" ]],//默认第几个排序
		bStateSave: true,//状态保存
        //order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
        renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
        pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
        createdRow: function( row, data, dataIndex ) {
    				for(var i = 0;i<7; i++) {
    					$(row).children('td').eq(i).attr('style', 'text-align: center;')
    				}
                },
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param.pageSize = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
            param.start = data.start;//开始的记录序号
            param.page = (data.start / data.length)+1;//当前页码
            param.orderBy = data.columns[data.order[0].column].data;//排序字段
            param.ascOrDesc = data.order[0].dir;//正序OR反序
            param.name = $('#name').val();
            param.status = $('#status').val();
            //ajax请求数据
            $.ajax({
                type: "POST",
                url: "${ctx}/admin/01/store/list.html",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    setTimeout(function () {
                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.total;//返回数据全部记录
                        returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.data;//返回的数据列表
                        //console.log(returnData);
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }, 200);
                }
            });
        },
        //列表表头字段
        columns: [
        	{
        		orderable: false,
				className : "nosort",
				render : function(data, type, full, meta) {
		        	html = '<input type="checkbox" value="' + full.id + '" name="idcheck" class="idcheck">';
		            return html;
				}
			},
            { "data": "id" },
            { "data": "name" },
            {
				orderable: false,
				render : function(data, type, full, meta) {
            		if($.trim(full.cover) == '') {
            			return "";
            		}
		            return '<img src="${ctx}/' + full.cover + '" height="100"/>';
				}
			},
            { "data": "phone" },
            { "data": "address" },
            { "data": "opentime" },
            {
				orderable: false,
				render : function(data, type, full, meta) {
					if(full.status == 1) {
						return '<span class="label label-success radius">开业</span>';
					}
					
					if(full.status == 0) {
						return '<span class="label radius">停业</span>';
					}
				
					return '';
				}
			},
            { "data": "createTime" },
            {
            	orderable: false,
				className : "nosort",
				render : function(data, type, full, meta) {
		        	html = '<a title="编辑" href="javascript:;" onclick="edit(\'门店编辑\',\'${ctx}/admin/01/store/update.html?id=' + full.id + '\',\'1\',\'800\',\'600\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>' +
		        			'<a title="删除" href="javascript:;" onclick="del(this,\'' + full.id + '\')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>';
		            
        			if(full.status == 0) {
						html += '<a style="text-decoration:none" onClick="start(this,\'' + full.id + '\')" href="javascript:;" title="标记为启用"><i class="Hui-iconfont">&#xe615;</i></a> ';
					} else {
						html += '<a style="text-decoration:none" onClick="stop(this,\'' + full.id + '\')" href="javascript:;" title="标记为未启用"><i class="Hui-iconfont">&#xe631;</i></a>';
					}
		        			
		            return html;
				}
			}
        ]
    }).api();
});

function search() {  
    table.ajax.reload();  
}  

function search_BC() {  
    table.ajax.reload(null, false);  
}  


</script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*增加*/
function add(title,url,w,h){
	layer_show(title,url,w,h);
}

function datadel() {
	var checkObjs = $('.idcheck');
	var arr = new Array();
	for(var i = 0;i< checkObjs.length; i++) {
		if($(checkObjs[i]).is(":checked")){ //jQuery方式判断
			arr.push($(checkObjs[i]).val());
        }
	}
	var ids = arr.join(',');
	layer.confirm('确认要删除吗？',function(index){
		ajaxForm("${ctx}/admin/01/store/delete/mul/done.html?ids=" + ids, function(data) {
			if(data.resCode == 1) {
				for(var i = 0;i< checkObjs.length; i++) {
					if($(checkObjs[i]).is(":checked")){ //jQuery方式判断
						$(checkObjs[i]).parents("tr").remove();
			        }
				}
				
				layer.msg('已删除!',{icon:1,time:1000});
			} else {
				layer.msg(data.resDes,{icon: 5,time:1000});
			}
		});
	});
}

/*删除*/
function del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		ajaxForm("${ctx}/admin/01/store/delete/done.html?id=" + id, function(data) {
			if(data.resCode == 1) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			} else {
				layer.msg(data.resDes,{icon: 5,time:1000});
			}
		});
	});
}

/*编辑*/
function edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*停用*/
function stop(obj,id){
	layer.confirm('确认要将该记录标记为停业吗？',function(index){
		ajaxForm("${ctx}/admin/01/store/update/done.html?id=" + id + "&status=0", function(data) {
			if(data.resCode == 1) {
				$('.search_btn').click();
				layer.msg('已经修改为停业!',{icon: 5,time:1000});
			} else {
				layer.msg(data.resDes,{icon: 5,time:1000});
			}
		});
		
		
	});
}

/*启用*/
function start(obj,id){
	layer.confirm('确认要将该记录标记为开业吗？',function(index){
		ajaxForm("${ctx}/admin/01/store/update/done.html?id=" + id + "&status=1", function(data) {
			if(data.resCode == 1) {
				$('.search_btn').click();
				layer.msg('已经修改为开业!', {icon: 6,time:1000});
			} else {
				layer.msg(data.resDes,{icon: 5,time:1000});
			}
		});
		
	});
}

</script>
</body>
</html>
