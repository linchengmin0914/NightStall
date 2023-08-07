var goURL;
var isAutoHidden = true;

/** 
* date 2016-4-9 17:15:46
* author huangheqing
**/
(function($){
	//选择插件
	$.fn.mySelect = function(t) {
        var config = $.extend({
            title : '请选择',
			styletype : 0,
			option : ['1','2'],
			callback : function(othis,input){
				var val = $(othis).text().trim();
				$(input).val(val);
			}
        },t);
		
        this.each(function(index, element) {
         	 $(element).click(function(){
			 	var tempId = (Math.random() + '').split('.')[1],
					maskObj = createMaskFun(tempId),
					layerObj = createLayerFun(tempId,config,this);
				
			});
        })
		
		function createMaskFun(id){
			var bg = document.createElement('div');
			bg.id = 'bg' +id;
			bg.className = 'lightbox';
			
			document.body.appendChild(bg);
			
			return bg;
		}
		
		function createLayerFun(id,cf,othis){
			var layer = document.createElement('div');
			layer.id = 'layer' + id;
			
			if(cf.styletype === 0){
				layer.className = 'select-layer';
				
				var html = '<h3 class="t_c select-title"><a class="f_l active" id="close' + id + '">取消</a>' + cf.title + '</h3><ul class="select-list" id="select' + id + '">';
				
				for(var i = 0;i < cf.option.length;i++){
					html = html + '<li>' + cf.option[i] + '</li>'
				}
				
				html = html + '</ul>';
			}else if(cf.styletype === 1){
				layer.className = 'select-layer-1';
				
				var html = '<ul class="select-list-1 t_c" id="select' + id + '">';
				for(var i = 0;i < cf.option.length;i++){
					html = html + '<li>' + cf.option[i] + '</li>'
				}
				
				html = html + '</ul>';
				
				html = html + '<h3 class="t_c select-title-1"><a class="active" id="close' + id + '">取消</a></h3>';				
			}
			
			layer.innerHTML = html;
			
			document.body.appendChild(layer);
			
			//绑定关闭弹窗事件
			$('#close' + id).click(function(){
				closeFun(id);
			});
			
			//绑定选择事件
			$('#select' + id).find('li').click(function(){
				cf.callback(this,othis);
				closeFun(id)
			});
			
			return layer;
		}
		
		//关闭弹窗事件
		function closeFun(id){
			$('#bg' + id).remove();
			$('#layer' + id).remove();
		}
    }
})(jQuery);


//弹窗插件
function myLayer(t) {
	var that = this;
	this.config = $.extend({
		html : '操作成功',
		width : false,
		yesFun : function(id){ return false},
		noFun : function(id){ return false},
		noBtnText : '取消',
		yesBtnText : '确定',
		type : 1,
		btn : true //是否需要弹窗按钮
	},t);
	
	this.init(this.config);
}

myLayer.prototype = {
	init : function(config){
		var that = this;
		var tempId = (Math.random() + '').split('.')[1];
		this.id = tempId;
		
		this.maskObj = config.type === 2 ? null : that.createMaskFun(tempId);
		this.layerObj = that.createLayerFun(tempId,config,this);
	},
	createMaskFun : function(id){
		var bg = document.createElement('div');
		bg.id = 'bg' +id;
		bg.className = 'lightbox';
		document.body.appendChild(bg);
		
		return bg;
	},
	
	//创建弹窗
	createLayerFun : function(id,cf,othis){
		var that = this;
		var layer = document.createElement('div');
		layer.id = 'layer' + id;
		layer.className = 'layer';
		
		if(!!cf.width && !isNaN(cf.width)){
			layer.style.width = cf.width + 'px';
		}
		
		var html = '<div class="main_tit1">' + cf.html + '</div>' + 
		
		((cf.btn && cf.type ===1) ? ('<div class="main_tit">' +
			'<ul class="fix">' +
				'<li class="line"><a class="db" id="noBtn' + id + '">' + cf.noBtnText + '</a></li>' +
				'<li><a class="db" id="yesBtn' + id + '">' + cf.yesBtnText + '</a></li>' +
			'</ul>' +
		'</div>') : '') + 
		
		(cf.type ===3 ? ('<div class="main_tit">' +
			'<ul class="fix">' +
				'<li style="display:none"><a class="db" id="noBtn' + id + '">' + cf.noBtnText + '</a></li>' +
				'<li style="width: 100%"><a class="db" id="yesBtn' + id + '">' + cf.yesBtnText + '</a></li>' +
			'</ul>' +
		'</div>') : '');
		
		
		
		layer.innerHTML = html;
		
		document.body.appendChild(layer);
		
		//取消按钮一
		$('#noBtn' + id).click(function(){
			if(!cf.noFun(id)){
				that.closeFun();
			}
		});
		
		//确定按钮一
		$('#yesBtn' + id).click(function(){
			if(!cf.yesFun(id)){
				that.closeFun();
			}
		});
		
		if(cf.type === 2){
			if(isAutoHidden) {
				setTimeout(function(){
					$('#layer' + id).remove();
					if($.trim(goURL) != '') {
						 window.location.href = goURL;
					}
				}, 2000);
			}
		}
		
		return layer;
	},
	
	//关闭弹窗事件
	closeFun : function(){
		var id = this.id;
		$('#bg' + id).remove();
		$('#layer' + id).remove();
	}
};



