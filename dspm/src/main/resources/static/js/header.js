
function loadImg(img,div_w,div_h){
	var cimg=img;
	if(div_w == ""){
		var div_w = 206;
	}
	if(div_h == ""){
		var div_h = 206;
	}
	var img_w=cimg.width;
	var img_h=cimg.height;
	
	if (img_w>div_w) {
		var w_original=img_w;
		var h_original=img_h;
		
		img_h = img_h * (div_w / img_w);
		img_w = div_w;
			if (img_h > div_h) {
			  img_w = w_original * (div_h / h_original);
			  img_h = div_h;
			} 
		if(img_w < div_w){
			let css_w=(div_w-img_w)/2;
			$(cimg).css("margin-left",css_w);
		}
	}else if(img_h>div_h){
		var w_original=img_w;
		var h_original=img_h;
		
		img_w=img_w*(div_h/img_h);
		img_h=div_h
		if (img_w > div_w) { 
		  img_h = h_original * (div_w / w_original);
		  img_w = div_w;
		}
		if(img_w < div_w){
			let css_w=(div_w-img_w)/2;
			$(cimg).css("margin-left",css_w);
		}
	}
	img_w=img_w - 2;
	img_h=img_h - 2;
	$(cimg).attr({width:img_w,height:img_h});
	$(cimg).removeAttr("onload");
	$(cimg).css("display","");
}