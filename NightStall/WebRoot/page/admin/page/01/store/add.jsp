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
<style type="text/css">
    #panel {
        position: absolute;
        background: #FFF;
        width:350px;
        padding: 20px;
        z-index: 9999;
        top: 30px;
        left: 30px;
    }

    #suggestionList {
        list-style-type: none;
        padding: 0;
        margin: 0;
    }
    
    #suggestionList li a {
        margin-top: -1px; 
        background-color: #f6f6f6;  
        text-decoration: none;
        font-size: 14px; 
        color: black; 
        display: block; 
    }

    #suggestionList li .item_info{
        font-size: 12px;
        color:grey;
        
    }
    
    #suggestionList li a:hover:not(.header) {
        background-color: #eee;
    }
</style>
<title>门店管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>门店名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*1-100" nullmsg="门店名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>封面：</label>
			<div class="formControls col-8">
				<span class="btn-upload form-group">
					<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传</a>
					<input id="pic" type="file" name="" accept="image/*" class="input-file">
				</span>
				<a href="javascript:void();" class="btn btn-danger upload-btn"  onclick="delIMg('img0','cover','pic')"><i class="Hui-iconfont">&#xe6e2;</i> 清空</a>
				
				<br><br>
				<img src="${ctx}/images/logo.png" id="img0" style="height: 50px;">
				<input type="hidden" id="cover" name="cover" value="images/logo.png"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>联系电话：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="phone" name="phone" datatype="*1-100" nullmsg="联系电话不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>营业时间：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="opentime" name="opentime" datatype="*1-100" nullmsg="营业时间不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>起送价：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="minDeliveryPrice" name="minDeliveryPrice" datatype="*1-100" nullmsg="起送价不能为空！" >
			</div>
			<div class="col-2">（元）</div>
		</div>	
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>派送时间：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="deliveryTime" name="deliveryTime" datatype="*1-100" nullmsg="派送时间不能为空！" >
			</div>
			<div class="col-2">（分钟）</div>
		</div>	
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>派送距离：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="distanceFormat" name="distanceFormat" datatype="*1-100" nullmsg="派送距离不能为空！" >
			</div>
			<div class="col-2">（公里）</div>
		</div>	
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>推荐指数：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="5" placeholder="" id="overall" name="overall" datatype="*1-100" nullmsg="推荐指数不能为空！" >
			</div>
			<div class="col-2">（星）</div>
		</div>	
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>销售单数：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="0" placeholder="" id="msaleNum" name="msaleNum" datatype="*1-100" nullmsg="销售单数不能为空！" >
			</div>
			<div class="col-2">（单）</div>
		</div>	
		
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-8">
				<textarea id="summary" name="summary" cols="" rows="" class="textarea"  placeholder="请输入门店的描述信息..." dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>门店地址：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="keyword" name="address" datatype="*1-100" nullmsg="门店地址不能为空！" oninput="getSuggestions()"/>
				<ul id="suggestionList">
        		</ul>
			</div>
			<div class="col-2">
				<input id="search" type="button" class="btn" value="搜索" onclick="searchByKeyword()" />
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-2"> </div>
			<div id="container" class="col-8" style="height: 300px;">
			
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>纬度：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="latitude" name="latitude" readonly="readonly"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>经度：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="longitude" name="longitude" readonly="readonly"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
		<input type="hidden" id="base64" name="base64" value=""/>
	</form>
</div>

<script charset="utf-8" src="https://map.qq.com/api/gljs?v=1.exp&libraries=service&key=FLSBZ-JZMCB-OTNUS-J4U7E-5W6KS-ETB7R"></script>
<script type="text/javascript">
var map = new TMap.Map('container', {
  zoom: 14,
  center: new TMap.LatLng(24.498404,118.126801),
});

var markers = new TMap.MultiMarker({
	  map: map,
	  geometries: [],
	});

var suggestionList = [];
var search = new TMap.service.Search({ pageSize: 10 }); // 新建一个地点搜索类
var suggest = new TMap.service.Suggestion({
  // 新建一个关键字输入提示类
  pageSize: 10, // 返回结果每页条目数
  region: '厦门', // 限制城市范围
  regionFix: true, // 搜索无结果时是否固定在当前城市
});

var infoWindowList = Array(10);

//绑定点击事件
map.on("click",function(evt){
    var lat = evt.latLng.getLat().toFixed(6);
    var lng = evt.latLng.getLng().toFixed(6);
    $("#latitude").val(lat);
    $("#longitude").val(lng);
    
    removeMarker();
    createMarker(lat, lng)
   
})

//创建marker事件
function createMarker(lat, lng) {
    if (!markers) {
    	markers = new TMap.MultiMarker({
            id: 'marker-layer',
            map: map,
            styles: {
                "marker": new TMap.MarkerStyle({
                    "src": "https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/marker-pink.png"
                })
            },
            geometries: [{
                "id": 'demo',
                "styleId": 'markers',
                "position": new TMap.LatLng(lat, lng),
                "properties": {
                    "title": "marker"
                }
            }]
        });
    }
}
//移除marker事件
function removeMarker() {
    if (markers) {
    	markers.setMap(null);
    	markers = null;
    }
}

function getSuggestions() {
  // 使用者在搜索框中输入文字时触发
  var suggestionListContainer = document.getElementById('suggestionList');
  suggestionListContainer.innerHTML = '';
  var keyword = document.getElementById('keyword').value;
  if (keyword) {
    suggest.getSuggestions({ keyword: keyword, location: map.getCenter() }).then((result) => {
        // 以当前所输入关键字获取输入提示
        suggestionListContainer.innerHTML = '';
        suggestionList = result.data;
        suggestionList.forEach((item, index) => {
          suggestionListContainer.innerHTML += '<li><a href="#" onclick="setSuggestion(' + index + ')">' + item.title + '<span class="item_info">' + item.address + '</span></a></li>';
        });
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

function setSuggestion(index) {
  // 点击输入提示后，于地图中用点标记绘制该地点，并显示信息窗体，包含其名称、地址等信息
  infoWindowList.forEach((infoWindow) => {
    infoWindow.close();
  });
  infoWindowList.length = 0;
  document.getElementById('keyword').value = suggestionList[index].title;
  document.getElementById('suggestionList').innerHTML = '';
  markers.setGeometries([]);
  markers.updateGeometries([
    {
      id: '0', // 点标注数据数组
      position: suggestionList[index].location,
    },
  ]);
  
  map.setCenter(suggestionList[index].location);
  
  $("#latitude").val(suggestionList[index].location.lat);
  $("#longitude").val(suggestionList[index].location.lng);
  
}

function searchByKeyword() {
  // 关键字搜索功能
  infoWindowList.forEach((infoWindow) => {
    infoWindow.close();
  });
  infoWindowList.length = 0;
  markers.setGeometries([]);
  search
    .searchRectangle({
      keyword: document.getElementById('keyword').value,
      bounds: map.getBounds(),
    })
    .then((result) => {
      result.data.forEach((item, index) => {
        var geometries = markers.getGeometries();
        var infoWindow = new TMap.InfoWindow({
          map: map,
          position: item.location,
          content: `<h3>${item.title}</h3><p>地址：${item.address}</p><p>电话：${item.tel}</p>`,
          offset: { x: 0, y: -50 },
        });
        infoWindow.close();
        infoWindowList[index] = infoWindow;
        geometries.push({
          id: String(index),
          position: item.location,
        });
        markers.updateGeometries(geometries);
        markers.on('click', (e) => {
          infoWindowList[Number(e.geometry.id)].open();
        });
      });
    });
}
</script>

<script type="text/javascript">

$(function(){
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
            ajaxForm("${ctx}/admin/01/store/add/done.html", function(data) {
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
	
	 $("#pic").change(function(e){
		var files = e.target.files;
		$("#pic").attr("name", "pic") ;
		var objUrl = getObjectURL(this.files[0]) ;
		if (objUrl) {
			$("#img0").attr("src", objUrl) ;
		}
		
		for(var i = 0; i < files.length; i++) {
  		   hiddenDiv();
  		   new html5ImgCompress(e.target.files[i], {
	        before: function(file) {
	        },
	        done: function (file, base64) {
	          $('#base64').val(base64);
	        },
	        fail: function(file) {
	        },
	        complate: function(file) {
	          ajaxFileUpload("cover");
	        },
	        notSupport: function(file) {
	          alert('浏览器不支持！');
	        }
	      });
  	   }
	});
});

function ajaxFileUpload(picInput) {
	$.ajax( {
        url : "${ctx}/uploadYs.html",
		type : 'post',
		data : {base64:$('#base64').val()},
		timeout : 60000,
		dataType: "json",
        success:function(data){
			console.log(data);
			$("#" + picInput).val(data.resDes);
            showDiv();
        },
        error:function(XmlHttpRequest,textStatus,errorThrown){
            console.log(XmlHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function delIMg(img,imgHid,file){
	$("#" + img).attr("src","${ctx}/images/photo_camera.png");
	$("#" + imgHid).val("");
	$("#" + file).val("");
	
}
</script>
</body>
</html>
