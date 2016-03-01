<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height: 400px"></div>
<script type="text/javascript"></script>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/javascript">
	$.post("getInfo.do", function(data) {

		// 路径配置
		require.config({
			paths : {
				echarts : 'http://echarts.baidu.com/build/dist'
			}
		});

		// 使用
		require([ 'echarts', 'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
		], function(ec) {
			// 基于准备好的dom，初始化echarts图表
			var myChart = ec.init(document.getElementById('main'));

			var option = {
				tooltip : {
					show : true
				},
				legend : {
					data : data["legend"]
				},
				xAxis : [ {
					type : 'category',
					data : data["category"]
				} ],
				yAxis : [ {
					type : 'value'
				} ],
				series : [ {
					"name" : data["legend"],
					"type" : "bar",
					"data" : data["bar"]
				} ]
			};

			// 为echarts对象加载数据 
			myChart.setOption(option);
		});

	}, "json");
</script>
</body>