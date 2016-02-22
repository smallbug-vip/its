/**
 * 注册菜单按钮事件
 */
function menu_but_event() {
	var $buttons = $('#back_manu_admin a[class="easyui-linkbutton"]');
	$buttons.each(function() {
		$(this).on('click', function() {
			$('#back_main_tabs').tabs('add', {
				title : $(this).text(),
				closable : true,
				href : $('#application_name').val() + "/admin/"//
						+ $(this).attr('dir') + ".do"
			});
		});
	});
}
