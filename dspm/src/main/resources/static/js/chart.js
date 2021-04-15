function stockTarget(obj){
	var url = $(obj).find("#targetUrl").val();
	var id = $(obj).find("#targetId").val();
	if(url == "moveInWM"){
		window.location.href = "/"+url+"?kbn=1&hkbn=3&stockingId="+id;
	}else{
		window.location.href = "/"+url+"?kbn=1&hkbn=3&id="+id;
	}
}

function GetPercent(num, total) {
      num = parseFloat(num);
      total = parseFloat(total);
      if (isNaN(num) || isNaN(total)) {
          return "-";
     }
    return total <= 0 ? "0%" : (Math.round(num / total * 100));
}

$(function() {
	
	var peaple = new Array();
	var peapleSum = 0;
	peaple.push(infoList["man"]);
	peapleSum += Number(infoList["man"]);
	peaple.push(infoList["woman"]);
	peapleSum += Number(infoList["woman"]);
	peaple.push(infoList["aged"]);
	peapleSum += Number(infoList["aged"]);
	peaple.push(infoList["foreigners"]);
	peapleSum += Number(infoList["foreigners"]);
	peaple.push(infoList["disability"]);
	peapleSum += Number(infoList["disability"]);
	
	$(".totalPeaple").text(peapleSum + "人");
    
	//人口動態
	var ctx = document.getElementById("impressions-add").getContext('2d');
	
	var myChart = new Chart(ctx, {
		type : 'pie',
		data : {
			labels : [ "男性人数", "女性人数", "老人人数", "外国籍人数", "障害者人数" ],
			datasets : [ {
				backgroundColor : [ 'rgb(59, 89, 152)',
						'rgba(59, 89, 152, 0.80)', 'rgba(59, 89, 152, 0.60)',
						'rgba(59, 89, 152, 0.40)', 'rgba(59, 89, 152, 0.30)',
						'rgba(59, 89, 152, 0.20)' ],
				hoverBackgroundColor : [ 'rgb(59, 89, 152)',
						'rgba(59, 89, 152, 0.80)', 'rgba(59, 89, 152, 0.60)',
						'rgba(59, 89, 152, 0.40)', 'rgba(59, 89, 152, 0.30)',
						'rgba(59, 89, 152, 0.10)' ],
				data : peaple,
				borderWidth : [ 2, 2, 2, 2, 2 ]
			} ]
		},
		options : {
			legend : {
				position : 'right',
				display : true,
				labels : {
					boxWidth : 30
				}
			},
			tooltips : {
				displayColors : false,
			}
		}
	});
	
	var jsonDatas = [];
	var jsonData = {};
	for (var i in goodsNumList) {
		jsonData = {};
		jsonData.x = goodsNumList[i]["goodsName"];
		jsonData.y = GetPercent(Number(goodsNumList[i]["goodsSum"])
					,Number(peapleSum)*Number(goodsNumList[i]["amount"]));
		jsonData.z = Number(goodsNumList[i]["goodsSum"]);
		jsonData.a = Number(peapleSum)*Number(goodsNumList[i]["amount"]);
		jsonDatas.push(jsonData);
    }
	
	Morris.Bar({
		  element: 'bar-chart',
		  data: jsonDatas,
		  xkey: 'x',
		  ykeys: ['y'],
		  labels: ['差', '備品数', '必要数'],
		  units:'%',
		  barColors: ['#3b5998'],
		  gridTextColor : "#585757",
		  barSizeRatio: 0.1,
		  resize: false,
		  hideHover:true
	});
	
	$("#bar-chart").find("[text-anchor='middle']").find("tspan").hide();
	
	
	$('.women-easy-chart').easyPieChart({
		easing : 'easeOutBounce',
		barColor : '#3b5998',
		lineWidth : 12,
		trackColor : 'rgba(0, 0, 0, 0.08)',
		scaleColor : false,
		onStep : function(from, to, percent) {
			$(this.el).find('.w_percent').text(Math.round(percent));
		}
	});

	$('.men-easy-chart').easyPieChart({
		easing : 'easeOutBounce',
		barColor : '#55acee',
		lineWidth : 12,
		trackColor : 'rgba(0, 0, 0, 0.08)',
		scaleColor : false,
		onStep : function(from, to, percent) {
			$(this.el).find('.w_percent').text(Math.round(percent));
		}
	});


});
