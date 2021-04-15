$(".checkDate").bind("input propertychange", function() {
	$(this).val(formatDate($(this).val()), 0);
});
$(".checkDate").blur(function() {
	checkDate($(this));
});

function formatNumber(number, places) {
	var number = number.replace(/[^0-9]/ig, "");
	return number;
}

function checkDate(obj){
	var wDate = formatNumber($(obj).val(), 0);
	if(wDate.length != 8){
		 var now = new Date();
		 var year = now.getFullYear();
	     var month = now.getMonth()+1;
	     var date = now.getDate();
	     if (month >= 1 && month <= 9) {
            month = "0" + month;
	     }
	     if (date >= 0 && date <= 9) {
	    	 date = "0" + date;
	     }
		 var time = year + "-" + month + "-" + date;
		 $(obj).val(time);
	}
}

function formatDate(number, places) {
	var number = number.replace(/[^0-9-]/g, "");
	return number;
}

function getGoodsList(url, form) {
	$.ajax({
		type : "POST",
		url : url,
		data : $("#" + form).serialize(),
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			$("#goodsCode").html($(data).find("#goodsCode").html());
		}
	});
}

function ajax(action, url, form) {
	$.ajax({
		type : action,
		url : url,
		data : $("#" + form).serialize(),
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			$("#content").html($(data).find("#content").html());
		}
	});
}

window.Ewin = function() {
	var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel" style="padding-top: 10%;">'
			+ '<div class="modal-dialog modal-sm">'
			+ '<div class="modal-content">'
			+ '<div class="modal-header">'
			+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
			+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>'
			+ '</div>'
			+ '<div class="modal-body">'
			+ '<p>[Message]</p>'
			+ '</div>'
			+ '<div class="modal-footer">'
			+ '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>'
			+ '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>'
			+ '</div>' + '</div>' + '</div>' + '</div>';

	var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">'
			+ '<div class="modal-dialog">'
			+ '<div class="modal-content">'
			+ '<div class="modal-header">'
			+ '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>'
			+ '<h4 class="modal-title" id="modalLabel">[Title]</h4>'
			+ '</div>'
			+ '<div class="modal-body">'
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '</div>';
	var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
	var generateId = function() {
		var date = new Date();
		return 'mdl' + date.valueOf();
	}
	var init = function(options) {
		options = $.extend({}, {
			title : "メッセージ",
			message : "操作内容",
			btnok : "確認",
			btncl : "キャンセル",
			width : 200,
			auto : false
		}, options || {});
		var modalId = generateId();
		var content = html.replace(reg, function(node, key) {
			return {
				Id : modalId,
				Title : options.title,
				Message : options.message,
				BtnOk : options.btnok,
				BtnCancel : options.btncl
			}[key];
		});
		$('body').append(content);
		$('#' + modalId).modal({
			width : options.width,
			backdrop : 'static'
		});
		$('#' + modalId).on('hide.bs.modal', function(e) {
			$('body').find('#' + modalId).remove();
		});
		return modalId;
	}

	return {
		alert : function(options) {
			if (typeof options == 'string') {
				options = {
					message : options
				};
			}
			var id = init(options);
			var modal = $('#' + id);
			modal.find('.ok').removeClass('btn-success')
					.addClass('btn-primary');
			modal.find('.cancel').hide();

			return {
				id : id,
				on : function(callback) {
					if (callback && callback instanceof Function) {
						modal.find('.ok').click(function() {
							callback(true);
						});
					}
				},
				hide : function(callback) {
					if (callback && callback instanceof Function) {
						modal.on('hide.bs.modal', function(e) {
							callback(e);
						});
					}
				}
			};
		},
		confirm : function(options) {
			var id = init(options);
			var modal = $('#' + id);
			modal.find('.ok').removeClass('btn-primary')
					.addClass('btn-success');
			modal.find('.cancel').show();
			return {
				id : id,
				on : function(callback) {
					if (callback && callback instanceof Function) {
						modal.find('.ok').click(function() {
							callback(true);
						});
						modal.find('.cancel').click(function() {
							callback(false);
						});
					}
				},
				hide : function(callback) {
					if (callback && callback instanceof Function) {
						modal.on('hide.bs.modal', function(e) {
							callback(e);
						});
					}
				}
			};
		},
		dialog : function(options) {
			options = $.extend({}, {
				title : 'title',
				url : '',
				width : 800,
				height : 550,
				onReady : function() {
				},
				onShown : function(e) {
				}
			}, options || {});
			var modalId = generateId();

			var content = dialogdHtml.replace(reg, function(node, key) {
				return {
					Id : modalId,
					Title : options.title
				}[key];
			});
			$('body').append(content);
			var target = $('#' + modalId);
			target.find('.modal-body').load(options.url);
			if (options.onReady())
				options.onReady.call(target);
			target.modal();
			target.on('shown.bs.modal', function(e) {
				if (options.onReady(e))
					options.onReady.call(target, e);
			});
			target.on('hide.bs.modal', function(e) {
				$('body').find(target).remove();
			});
		}
	}
}();

Date.prototype.format = function (fmt) {
    var o = {
        "y+": this.getFullYear,
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
setInterval("document.getElementById('dateTimeSet').innerHTML = (new Date()).format('MM-dd hh:mm');", 1000);

