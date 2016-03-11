//namespace
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
			canvers.attr("width", $.fn.its.map.width - 3);
			canvers.attr("height", $.fn.its.map.height - 3);
			$.fn.its.stage = new createjs.Stage("its_map");
			$.fn.its.lightView = new createjs.Container();
			$.fn.its.carView = new createjs.Container();
			var socket = new WebSocket("ws://" + url);

			socket.onopen = function(event) {

				// send message to server
				socket.send("{" + //
				"'screenSize':" + //
				"{'screen_width':'" + $.fn.its.map.width + "',"
						+ "'screen_height':'" + $.fn.its.map.height + "'}" //
						+ "}");

				// receive message from the server
				socket.onmessage = function(event) {
					var entity = eval("(" + event.data + ")");
					var entityName = entity["entity"];
					switch (entityName) {
					case "roads":
						$.fn.its.loadRoad(canvers, entity["coordinate"]);
						break;
					case "lights":
						$.fn.its.drawLight(canvers, entity["coordinate"]);
						break;
					case "cars":
						$.fn.its.drawCar(canvers, entity["coordinate"]);
						break;
					default:
						$.info("error!")
					}
					;
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
		loadRoad : function(canvers, data) {
			var stage = $.fn.its.stage;
			var gameView = new createjs.Container();
			stage.addChild(gameView);
			var Line = new createjs.Shape();
			Line.graphics.beginStroke("#000000");
			Line.graphics.setStrokeStyle(0.5);
			data.forEach(function(da) {
				for (var i = 0; i < 4; i++) {
					Line.graphics.moveTo(da[i], da[i + 1]);
					Line.graphics.lineTo(da[i + 2], da[i + 3]);
				}
			});
			gameView.addChild(Line);
			stage.update();
		},
		drawCar : function(canvers, data) {
			var stage = $.fn.its.stage;
			var view = $.fn.its.carView;
			view.removeAllChildren();
			for ( var i=0; i < data.length; i++) {
				var carImage = new createjs.Bitmap("http://" + data[i]["image"]);
				carImage.x = data[i]["coor"][0];
				carImage.y = data[i]["coor"][1];
				carImage.rotation = data[i]["angle"];
				view.addChild(carImage);
			}
			stage.addChild(view);
			stage.update();
		},
		drawLight : function(canvers, data) {
			var stage = $.fn.its.stage;
			var view = $.fn.its.lightView;
			view.removeAllChildren();
			var Line = new createjs.Shape();
			Line.graphics.setStrokeStyle(2);
			for (var i = 0; i < data.length; i++) {
				var c = data[i]["coor"];

				if (data[i]["state"] == "0") {
					Line.graphics.beginStroke("#FF0000");
				} else {
					Line.graphics.beginStroke("#00FF00");
				}

				Line.graphics.moveTo(c[0], c[1]);
				Line.graphics.lineTo(c[2], c[3]);
				Line.graphics.moveTo(c[0], c[1]);
				Line.graphics.lineTo(c[4], c[5]);
			}
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
