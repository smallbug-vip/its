<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="easyui-accordion" style="width: 100%;margin:0" id="back_manu_admin">
	<div title="地图管理" data-options="iconCls:'icon-ok'"
		style="overflow: auto;">
		<div style="padding:2px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="area">区域管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="cross">路口管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="light">信号灯管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="road">道路管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="lane">车道管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="car">这辆管理</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="routs">行程管理</a>
		</div>
	</div>
	<div title="信息统计" data-options="iconCls:'icon-chart'"
		style="overflow: auto;">
		<div style="padding:2px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width:100%" dir="info">信息统计</a>
		</div>
	</div>
</div>

<script type="text/javascript">
menu_but_event();//如果需要ajax加载菜单项，在回调函数最后这个方法。
</script>

