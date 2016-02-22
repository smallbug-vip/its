//名称空间
(function($) {
	$.namespace = function(namespaceString) {
		var temp = [];
		var array = namespaceString.split(".");
		for (var i = 0; i < array.length; i++) {
			temp.push(array[i]);
			eval("window." + temp.join(".") + "={}")
		}
	}
})($);

(function($) {

	$.fn.its = {
		stage : null,
		lightView : null,
		carView : null,
		map : {
			width : "0",
			height : "0"
		},
		init : function(canvers, url) {
			// init stage
			$.fn.its.stage = new createjs.Stage("its_map");
			$.fn.its.lightView = new createjs.Container();
			// webService send commaind to load data
			var socket = new WebSocket("ws://" + url);

			socket.onopen = function(event) {
				// send message to server
				socket.send("[{'command':'areaChoose','value':['NUM001']}]");

				// receive message from the server
				socket.onmessage = function(event) {
					var entity = eval("(" + event.data + ")");
					if ("cars" == entity["entity"]) {

					} else if ("lights" == entity["entity"]) {
						$.fn.its.loadLight(canvers, entity["coordinate"], socket);
					} else if ("lanes" == entity["entity"]) {
						$.fn.its.loadLane(canvers, entity["coordinate"], socket);
						socket.send("[{'command':'loadLightData'}]");
					} else if ("roads" == entity["entity"]) {
						$.fn.its
								.loadRoad(canvers, entity["coordinate"], socket);
						socket.send("[{'command':'loadLaneData'}]");
					} else if ("areas" == entity["entity"]) {
						$.fn.its.loadArea(canvers, entity["coordinate"][0],
								socket);
						socket.send("[{'command':'loadRoadData'}]");
					}
				};

				// close
				socket.onclose = function(event) {
					$.info("Client notified socket has closed", event);
				};

				// error
				socket.onerror = function(event) {
					$.info("Client notified socket has error", event);
				};
			}
		},
		loadArea : function(canvers, data, socket) {
			canvers.attr("width", data["width"]);
			canvers.attr("height", data["length"]);
		},
		loadRoad : function(canvers, data, socket) {
			var stage = $.fn.its.stage;
			var gameView = new createjs.Container();
			stage.addChild(gameView);
			var Line = new createjs.Shape();
			Line.graphics.beginStroke("#000000");
			Line.graphics.setStrokeStyle(1);
			// $.info(data)
			data.forEach(function(da) {
				Line.graphics.moveTo(da["xxOne"], da["yyOne"]);
				Line.graphics.lineTo(da["xxOther"], da["yyOther"]);
			});
			gameView.addChild(Line);
			stage.update();
		},
		loadLane : function(canvers, data, socket) {
			var stage = $.fn.its.stage;
			var gameView = new createjs.Container();
			stage.addChild(gameView);
			var Line = new createjs.Shape();
			Line.graphics.beginStroke("#000000");
			Line.graphics.setStrokeStyle(0.5);
			// $.info(data)
			data.forEach(function(da) {
				Line.graphics.moveTo(da["xxOne"], da["yyOne"]);
				Line.graphics.lineTo(da["xxOther"], da["yyOther"]);
			});
			gameView.addChild(Line);
			stage.update();
		},
		loadLight : function(canvers, data, socket) {
//			$.info(data);
			var stage = $.fn.its.stage;
			var view = $.fn.its.lightView;
			view.removeAllChildren();
			var Line = new createjs.Shape();
			Line.graphics.setStrokeStyle(2);
			data.forEach(function(da){
				if (da["lightState"] == "0") {
					Line.graphics.beginStroke("#FF0000");
				} else {
					Line.graphics.beginStroke("#00FF00");
				}
				Line.graphics.moveTo(da["oneX"], da["oneY"]);
				Line.graphics.lineTo(da["otherX"], da["otherY"]);
			});
			view.addChild(Line);
			stage.addChild(view);
			stage.update();
		}
	};

	$.info = function(string) {
		console.info(string);
	};

	$.debug = function(string) {
		console.debug(string);
	};
})($);
