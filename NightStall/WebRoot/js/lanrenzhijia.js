/*
	Based on AjaxLoad.info
	By Justin Svendsen
	contact:
		nonbreakingspace@gmail.com
		or
		justin.s@markerstudio.com
		
		
	v0.0	-	First build
				- massive fail!
				- making progress
				- works enough to deply to ABF, refactor variables first, so they're more relvants
				
	v0.1	-	TODO
				- if a visualSelect is showing, hide it when another is clicked on.
				- add options and cases to allow container to be different width than the select, currently the select width determines the container width.
				- add fix for the ie background required bug.
			-	Changes
				- removed attr:onclick and added a previewImage.click, using multi-level arrays for IE
				
	v0.2	-	TODO
				- omg, stupid IE6
			-	Changes
				- added iframe shim, testing required
				- made the select overlay work more like a toggle, rather than an on switch
				- added code to hide any visual select dropdowns that are showing
				- renamed plugin init from jVisualSelect() to visualSelect()
	v0.3	-	TODO
				- the iframes aren't showing as transparent correctly for some reason, even using an
					alpha filter. must find a fix for this
				- refactor
				- working on making plugins configurable
			-	Changes
	v0.4	-	TODO
				- the iframes aren't showing as transparent correctly for some reason, even using an
					alpha filter. must find a fix for this
				- refactor

*/
(function(jQuery) {
jQuery.fn.extend({
	visualSelect: function(options) {
		return this.each(function() {
			new jQuery.visualSelect(this, options);
		});
	}
});


var defaults = {	
//	Related to the image container
	previewContainerClass: "previewContainer",
	previewContainerHeight: "200px",
	previewContainerOverflow: "auto",
	
//	Related to the image
	imagePath: "images/",
	imagePrefix: "bg-wall-",
	imageSuffix: "-th",
	imageExtension: ".jpg"
};

var vsMultiArray = new Array();
var vsArrayCount = 0;
	
jQuery.visualSelect = function(obj, options) {
	defaults = $.extend({}, defaults, options || {});
	
	//	Determine the width and height of our select
	var oSelect = jQuery(obj);
	var oSelectID = oSelect.attr("id");
	var oSelectWidth = oSelect.attr("offsetWidth");
	var oSelectHeight = oSelect.attr("offsetHeight");
	
	
	//	Set our generated / random ID to use for all functions
	var visualSelectID = jRandomID(oSelectID);
	
	
	//	Create a container around the select, because the following
	//	div and images are added after the select, they will now be
	//	inside our container.
	var selectContainer = jQuery("<div>");
	selectContainer.attr("id", visualSelectID + "Container");
	selectContainer.css("position", "relative");
	
	oSelect.wrap(selectContainer);
	
	
	//	Create our link / thumb container
	var previewContainer = jQuery("<div>");
	previewContainer.attr("id", visualSelectID);
	previewContainer.attr("class", defaults.previewContainerClass);
	previewContainer.css({
					  	width: oSelectWidth,
						height: defaults.previewContainerHeight,
						position: "absolute",
						top: oSelectHeight,
						left: 0,
						zIndex: "999",
						overflow: defaults.previewContainerOverflow,
						display: "none"
					  });
	
	
	//	Get our options and append thumbs for each one
	var oSelectOptions = jQuery("option", obj);
	var oSelectOptionsLength = oSelectOptions.length;
	
	for (i = 0; i < oSelectOptionsLength; i++) {
		if (jQuery(oSelectOptions[i]).attr("value")) {
			vsMultiArray[vsArrayCount] = new Array('#' + (visualSelectID + "Container"), '.' + defaults.previewContainerClass, jQuery(oSelectOptions[i]).attr("value"));
			
			var previewImage = jQuery("<img>");
			previewImage.attr({
							 src: defaults.imagePath + defaults.imagePrefix + jQuery(oSelectOptions[i]).attr("value") + defaults.imageSuffix + defaults.imageExtension,
							 alt: $(oSelectOptions[i]).html(),
							 rel: vsArrayCount
							 });
			previewImage.click(function() {
											jQuery.visualSelect.makeSelection(vsMultiArray[jQuery(this).attr("rel")][0], vsMultiArray[jQuery(this).attr("rel")][1], vsMultiArray[jQuery(this).attr("rel")][2]);
											});
			
			previewContainer.append("\r\n");
			previewContainer.append(previewImage);
			
			vsArrayCount++;
		}
	}

	oSelect.after(previewContainer);

	if (jQuery.browser.msie && jQuery.browser.version.substr(0,1)<7) {
		//	Create an iframe shim for IE6, wonderful browser that it is
		var oSelectUnderlay = jQuery("<iframe>");
		oSelectUnderlay.attr("id", visualSelectID + "Underlay");
		oSelectUnderlay.attr("src", "j avascript:'<html></html>';");
		oSelectUnderlay.attr("frameBorder", 0);
		oSelectUnderlay.attr("scrolling", "no");
		oSelectUnderlay.attr("allowtransparency", "true");
		oSelectUnderlay.css({
							 width: oSelectWidth,
							 height: oSelectHeight,
							 position: "absolute",
							 display: "block",
							 top: 0,
							 left: 0,
							 zIndex: 1,
							 "filter": "alpha(opacity=50)"
							});
		
		oSelect.before(oSelectUnderlay);
	}
	
	//	Create an invisible overlay ontop of the select
	var oSelectOverlay = jQuery("<div>");
	oSelectOverlay.attr("id", visualSelectID + "Overlay");
	oSelectOverlay.css({
						width: oSelectWidth,
						height: oSelectHeight,
						position: "absolute",
						top: 0,
						left: 0,
						zIndex: 100
					  });
	oSelectOverlay.bind("click", function() {
		var displayState = $("#" + visualSelectID).css("display");
		if (displayState == "none") {
			$("." + defaults.previewContainerClass).css("display", "none");
			$("#" + visualSelectID).css("display", "block");
		} else {
			$("#" + visualSelectID).css("display", "none");
				
		}
	});
	
	oSelect.after(oSelectOverlay);
};

jQuery.visualSelect.makeSelection = function (container, containerClass, option) {
	jQuery(container + " " + containerClass).css("display", "none");
	jQuery.visualSelect.setOption(container, option);
}

jQuery.visualSelect.setOption = function (container, option) {
//	alert($(container + " option[value='02']"));
	jQuery(container + " option[value='" + option + "']").attr("selected", true);
}
	
function jRandomID(oSelectID) {
	if (oSelectID) {
		return "selectContainer" + oSelectID;
	} else {
		return "selectContainer" + jRandomIDGenerator();
	}
};

function jRandomIDGenerator() {
	var chars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var string_length = 8;
	var randomString = '';
	for (var i=0; i<string_length; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		randomString += chars.substring(rnum,rnum+1);
	}
	
	return randomString;
};

})(jQuery);