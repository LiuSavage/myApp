var IMG_LENGTH = 3;
var IMG_MAXCOUNT = 5;
var UP_IMGCOUNT = 0;

$("#div_imgfile").click(function () {
	if ($(".lookimg img").length >= IMG_MAXCOUNT) {
		alert("写真は" + IMG_MAXCOUNT + "枚までです。");
		return;
	}

	let _CRE_FILE = document.createElement("input");
	if ($(".imgfile").length <= $(".lookimg").length) {
		_CRE_FILE.setAttribute("type", "file");
		_CRE_FILE.setAttribute("name", "files");
		_CRE_FILE.setAttribute("class", "imgfile");
		_CRE_FILE.setAttribute("multiple", "multiple");
		_CRE_FILE.setAttribute("accept", ".png,.jpg,.jpeg");
		_CRE_FILE.setAttribute("num", UP_IMGCOUNT);
		$("#div_imgfile").after(_CRE_FILE);
	}
	else {
		_CRE_FILE = $(".imgfile").eq(0).get(0);
	}
	return $(_CRE_FILE).click();
});
$(document).on("change",".imgfile", function () {
	if ($(this).val().length > 0) {


		let FORMAT = $(this).val().substr($(this).val().length - 3, 3);
		if (FORMAT != "png" && FORMAT != "jpg" && FORMAT != "peg") {
			alert("ファイルフォーマットが不正です！！！");
			return;
		}

		var _prevdiv = "";
		let img_count = this.files.length + $(".lookimg img").length;
		if(img_count>IMG_MAXCOUNT){
			alert("写真は" + IMG_MAXCOUNT + "枚までです");
			$(this).remove();
			return;
		}
		for(let i=0;i<this.files.length;i++){
			var file = this.files[i];
			if (file.size > (IMG_LENGTH * 1024 * 1024)) {
				alert("写真のサイズは" + IMG_LENGTH + "MBまでです。");
				$(this).val("");
				return;
			}
			if(i==0){
				_prevdiv = document.createElement("div");
				_prevdiv.setAttribute("class", "lookimg");
				let IMG_DELBTN = document.createElement("div");
				IMG_DELBTN.setAttribute("class", "lookimg_delBtn");
				IMG_DELBTN.innerHTML = "キャンセル";
				$(_prevdiv).append(IMG_DELBTN);
				let IMG_PROGRESS = document.createElement("div");
				IMG_PROGRESS.setAttribute("class", "lookimg_progress");
				$(IMG_PROGRESS).append(document.createElement("div"));
				$(_prevdiv).append(IMG_PROGRESS);
				_prevdiv.setAttribute("num", $(this).attr("num"));
				$("#div_imglook").children("div:last").before(_prevdiv);
				UP_IMGCOUNT++;
			}
			let preview = document.createElement("img");
			$(preview).attr("onload","loadImg(this,130,130)");
			$(_prevdiv).append(preview);
			let reader = new FileReader();
			reader.onloadend = function () {
				preview.src = reader.result;
			}
			if (file) {
				reader.readAsDataURL(file);
			} else {
				preview.src = "";
			}
		}

	}
});

$(document).on("click",".lookimg_delBtn", function () {
	if($(this).prev().val()){
		var delFile = $(this).prev().val();
		var _CRE_FILE = document.createElement("input");
			_CRE_FILE.setAttribute("type", "hidden");
			_CRE_FILE.setAttribute("name", "delFile");
			_CRE_FILE.setAttribute("value", delFile);
			$("#div_imgfile").after(_CRE_FILE);

			$(this).parent().remove();
	}else{
		$(".imgfile[num=" + $(this).parent().attr("num") + "]").remove();
		$(this).parent().remove();
	}
});

$(document).on("mouseover",".lookimg", function () {
	if ($(this).attr("ISUP") != "1")
		$(this).children(".lookimg_delBtn").eq(0).css("display", "block");;
});

$(document).on("mouseout",".lookimg", function () {
	$(this).children(".lookimg_delBtn").eq(0).css("display", "none");;
});

lightGallery(document.getElementById('lightgallery'));
