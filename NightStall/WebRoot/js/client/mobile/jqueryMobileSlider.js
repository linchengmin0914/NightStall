/*
 *bruinHu  
 *20150414
 *PC自动和点击滑动和手机端滑动
 * $(".XX").mobileSlider();
 */
(function($){
	
    $.fn.mobileSlider = function(options){
		
        var defaultSettings = {
            width: 640,        //容器宽度
            height:295,        //容器高度
            during:5000,       //间隔时间
            speed:30,          //滑动速度
			scale:1,           //收缩
			lunbo:true          //轮播
        }
		
        options = $.extend(defaultSettings, options);
		
        return this.each(function(){
			
            var _this = $(this), s = options;     //$this就是 $(".XX") 
            var startX = 0, startY = 0;           //触摸开始时手势横纵坐标 
            var temPos;                           //滚动元素当前位置
            var iCurr = 0;                        //当前滚动屏幕数
            var timer = null;                    //计时器
            var oMover = $("ul",_this);          //滚动元素  oMover=$("ul",$(this))等价于oMover=$(this).find("ul")  oMove= -this.find("ul");  
            var oLi = $("li", oMover);           //滚动单元
            var num = oLi.length;                //滚动屏幕数
            var oPosition = {};                  //触点位置
            var moveWidth =  $(window).width(); //屏幕的宽
			var moveHeight = 0;                  //获取高
			var oFocus  = 0;
			
			
			initDiv(); //初始化宽高
		    initButton();//初始化按钮
            autoMove();//开启定时器
			
			//缩放屏幕
            $(window).bind('resize', function(){
				moveWidth =  $(window).width(); 
				initDiv();
            });
			
			
			 //判断设备
			 if(isMobile()){
				   //移动设备
                   touchEvent();
             }else{
				//PC端
			    oFocus.hover(function(){
                    iCurr = $(this).index() - 1;
                    stopMove();
                    doMove();
                }, function(){
                    autoMove();
                }) 
			 }
			  
			
			//初始化宽高
			function initDiv(){
				//设置宽	
				if(moveWidth >= s.width-0){
				 moveWidth= s.width;
				 _this.width(moveWidth)
				}else if(moveWidth <= 320){
					moveWidth=320;
					 _this.width(moveWidth)
				}else{
					moveWidth=moveWidth-0;
				}
				//设置高
				moveHeight = Math.round(moveWidth/s.scale);
				
				_this.width(moveWidth).height(moveHeight);
				
				 oLi.width(_this.width()).height(_this.height());
				 
				 oMover.width(num * oLi.width());
				 
                 _this.fadeIn(320);
					
			}	
			//按钮
			function initButton(){ 
				_this.append('<div class="focus"></div>');
				var oFocusContainer = $(".focus");
				for (var i = 0; i < num; i++) {
					oFocusContainer.append("<span></span>");
				}
				oFocus = $("span", oFocusContainer);
				oFocus.first().addClass("current");
			}
			//判断是否是移动设备
            function isMobile(){
                if (navigator.userAgent.match(/Android/i) || navigator.userAgent.indexOf('iPhone') != -1 || navigator.userAgent.indexOf('iPod') != -1 || navigator.userAgent.indexOf('iPad') != -1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            //自动运动
            function autoMove(){ 
				if(s.lunbo){
					timer = setInterval(doMove, s.during);
				}else{
					//doMove();
				}
				
			}
            //停止自动运动
            function stopMove(){ 
				clearInterval(timer); 
			}
			//动画效果
            function doAnimate(iTarget, fn){
                oMover.stop().animate({
					left: iTarget},
					_this.speed ,
					function(){if(fn){fn()};}
				);
            }
			//运动效果
            function doMove(){
                iCurr = iCurr >= num - 1 ? 0 : iCurr + 1;
                doAnimate(-moveWidth * iCurr);
                oFocus.eq(iCurr).addClass("current").siblings().removeClass("current");
            }
            //绑定触摸事件
            function touchEvent(){
				 // oMover jquer对象
				 //oMover[0]或oMover.get(0) DOM 对象
                oMover[0].addEventListener('touchstart', touchStartFun, false);
                oMover[0].addEventListener('touchmove', touchMoveFun, false);
                oMover[0].addEventListener('touchend', touchEndFun, false);
            }
            //获取触点位置
            function touchPos(event){
				
                var touchList = event.changedTouches;//获取接触对象列表
				var touchObject=0,tagX=0,tagY=0;
				
                for (var i = 0; i < touchList.length; i++) {
					
                    touchObject = touchList[i];//获取接触对象
                    tagX = touchObject.clientX;
                    tagY = touchObject.clientY;
                }
                oPosition.x = tagX;
                oPosition.y = tagY;
				
                return oPosition;
            }
            //触摸开始
            function touchStartFun(event){
                stopMove();
                touchPos(event);
                startX = oPosition.x;
                startY = oPosition.y;
                temPos = oMover.position().left;
            }
            //触摸移动 
            function touchMoveFun(event){
                touchPos(event);
                var moveX = oPosition.x - startX;
                var moveY = oPosition.y - startY;
                if(Math.abs(moveX) > Math.abs(moveY)){
                    event.preventDefault();
                    oMover.css({left: temPos + moveX});
                }
            }
            //触摸结束
            function touchEndFun(event){
                touchPos(event);
                var moveX = oPosition.x - startX;
                var moveY = oPosition.y - startY;
                if (Math.abs(moveX) > Math.abs(moveY)) {
					//左向右移动 前面一页
                    if (moveX > 0) {
                        iCurr--;
                        if (iCurr >= 0) {
                            var moveX = iCurr * moveWidth;
                            doAnimate(-moveX, autoMove);
                        }
                        else {
                            doAnimate(0, autoMove);
                            iCurr = 0;
                        }
                    }
					//右向左移动 后面一页
                    else {
                        iCurr++;
                        if (iCurr < num && iCurr >= 0) {
                            var moveX = iCurr * moveWidth;
                            doAnimate(-moveX, autoMove);
                        }
                        else {
                            iCurr = num - 1;
                            doAnimate(-(num - 1) * moveWidth, autoMove);
                        }
                    }
                    oFocus.eq(iCurr).addClass("current").siblings().removeClass("current");
                }
            }
         
        });
    }
})(jQuery);

function slideTouch(e, t) {
	var n, i = 0,
	r = 0,
	o = 0,
	a = 0,
	l = 0,
	c = 0,
	s = 0,
	u = 0,
	d = 0,
	g = 0,
	f = function() {
		g = e.length;
		for (var n = "",
		i = "tempId" + (new Date).getTime(), r = 0; g > r; r++) {
			var o = e.eq(r).attr("src");
			o === t ? (d = r, n += '<div class="slide_wrap" style="left:' + 100 * r + '%"><img id="img' + i + '" src="" data-src="' + o + '" style="-webkit-transform: translate3d(0, 0, 0);transform: translate3d(0, 0, 0);"/></div>') : n += '<div class="slide_wrap" style="left:' + 100 * r + '%"><img src="images/album_loading.gif" data-src="' + o + '" style="-webkit-transform: translate3d(0, 0, 0);transform: translate3d(0, 0, 0);"/></div>'
		}
		n = '<div class="media_stage"><section class="gallery_holder" id="' + i + '" style="-webkit-transform:scale(1) translate3d(-' + 100 * d + "%, 0, 0);transform:scale(1) translate3d(-" + 100 * d + '%, 0, 0);">' + n + '</section></div>',
		
		$('body').append(n);
		var a = new Image;
		a.onload = function() {
			null !== document.getElementById("img" + i) && (document.getElementById("img" + i).src = a.src, document.getElementById("img" + i).style.width = (a.width < document.getElementById(i).clientWidth - 10 ? a.width: document.getElementById(i).clientWidth - 10) + "px")
		},
		a.src = t;
		console.log(i)
		return document.getElementById(i)
	} (),
	m = function(e, t, n) {
		console.log(e);
		e.attachEvent ? e.attachEvent("on" + t, n) : e.addEventListener(t, n, !1)
	},
	h = function(e) {
		e && e.preventDefault ? e.preventDefault() : window.event.returnValue = !1
	},
	v = function(e) {
		for (var t = e.style.webkitTransform || e.style.transform,
		n = t.match(/translate3d\([^\)]+\)/)[0], i = n.replace("translate3d", "").replace(/[\(,\)]/g, "").split(" "), r = [], o = 0; o < i.length; o++) {
			var a = i[o];
			a = i[o].indexOf("%") > -1 ? Math.floor(parseInt(a) / 100 * e.clientWidth) : parseInt(a),
			r[o] = a
		}
		return r
	},
	p = "createTouch" in document,
	y = p ? "touchstart": "mousedown",
	w = p ? "touchmove": "mousemove",
	b = p ? "touchend": "mouseup",
	x = !1,
	T = 0,
	E = 0,
	k = 300,
	M = !1,
	D = null;
	m(f, y,
	function(e) {
		var e = e || window.event;
		if (! (p && e.touches.length > 1)) return T = (new Date).getTime(),
		300 > T - E ? (clearTimeout(S), M = !0, void(k = 10)) : (D = $(f).find("img").eq(d).css({
			transition: "0s",
			"-webkit-transition": "0s"
		}), k = 300, M = !1, E = T, $(f).removeClass("slide"), i = p ? e.touches[0].pageX: e.pageX || e.clientX, o = 0, l = 0, r = p ? e.touches[0].pageY: e.pageY || e.clientY, a = 0, c = 0, I ? s = v(f)[0] : (s = v(D[0])[0], u = v(D[0])[1]), clearTimeout(n), x = !0, !1)
	});
	var _ = function(e, t, n, i) {
		e.style.webkitTransform = "scale(" + t + ") translate3d(" + n + "px, " + i + "px, 0)",
		e.style.transform = "scale(" + t + ") translate3d(" + n + "px, " + i + "px, 0)"
	};
	m(f, w,
	function(e) {
		var e = e || window.event;
		p && e.touches.length > 1 || !x || (l = p ? e.touches[0].pageX: e.pageX || e.clientX, o = Math.ceil(l - i), c = p ? e.touches[0].pageY: e.pageY || e.clientY, a = Math.ceil(c - r), I ? _(f, 1, s + o, 0) : _(D[0], j, s + o / j, u + a / j), h(e))
	});
	var I = !0,
	S = null,
	j = 1;
	m(f, b,
	function(e) {
		if (! (p && e.touches.length > 1)) {

			if (x = !1, D.css({
				transition: "0.2s",
				"-webkit-transition": "0.2s"
			}), Math.abs(o) < 5 && Math.abs(a) < 5) return clearTimeout(S),
			S = setTimeout(function() {
				if (M) {
					var e = new Image;
					e.onload = function() {
						j = e.width / D.width(),
						j = 1.5 > j ? 1.5 : j,
						I ? _(D[0], j, 0, 0) : _(D[0], 1, 0, 0),
						I = !I
					},
					e.src = D.attr("src")
				} else $(f).parent().remove(),
				$(".lockpopup").hide()
			},
			k),
			h(e),
			!1;
			if (!I) {
				var t = f.clientWidth,
				i = D.width(),
				r = f.clientHeight,
				l = D.height(),
				c = (i * j - t) / 2 / j,
				c = 0 > c ? 0 : c,
				s = (l * j - r) / 2 / j,
				s = 0 > s ? 0 : s,
				u = v(D[0])[0],
				m = v(D[0])[1];
				if (! (Math.abs(u) > c && Math.abs(u) - c > 60)) return void(o > 0 && u > c ? a > 0 && m > s ? _(D[0], j, c, s) : 0 > a && -s > m ? _(D[0], j, c, -s) : _(D[0], j, c, m) : 0 > o && -c > u ? a > 0 && m > s ? _(D[0], j, -c, s) : 0 > a && -s > m ? _(D[0], j, -c, -s) : _(D[0], j, -c, m) : a > 0 && m > s ? _(D[0], j, u, s) : 0 > a && -s > m && _(D[0], j, u, -s));
				_(D[0], 1, 0, 0),
				I = !I
			}
			return d = Math.abs(o) < 50 ? d: 0 > o ? d === g - 1 ? g - 1 : d + 1 : 0 === d ? 0 : d - 1,
			$(f).addClass("slide"),
			setTimeout(function() {
				var e = f.clientWidth;
				_(f, 1, -d * e, 0);
				var t = $(f).find("img").eq(d);
				t.attr("src", t.data("src"))
			},
			0),
			n = setTimeout(function() {
				f.style.webkitTransform = "translate3d(-" + 100 * d + "%, 0, 0)",
				f.style.transform = "translate3d(-" + 100 * d + "%, 0, 0)"
			},
			500),
			h(e),
			!1
		}
	})
}
