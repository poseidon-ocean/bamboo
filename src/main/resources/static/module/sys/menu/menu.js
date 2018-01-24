var Menu = {
    id : "columnTable",//表格id
    layerIndex : -1,
    prefix : "/sys/menu"
};

$(function() {
	var defaultColunms = Menu.initColumn();
    
    var table = new BSTreeTable(Menu.id, Menu.prefix +"/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});

/**
 * 初始化表格列
 */
Menu.initColumn = function(){
	var columns = [
		 {checkbox : true },
		 {title: '序号', formatter: Menu.formatIndex},
	     {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
	     {title: '图标', field: 'icon', align: 'center', valign: 'middle', formatter: Menu.formatIcon},
	     {title: '类型', field: 'type', align: 'center', valign: 'middle', formatter: Menu.formatType},
	     {title: '地址', field: 'url', align: 'center', valign: 'middle'},
	     {title: '权限标识', field: 'perms', align: 'center', valign: 'middle'},
	     {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
	     {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
	     {title: '操作', field: 'id', align: 'center', valign: 'middle', formatter: Menu.formatOper}
	];
	
	return columns;
}

/**
 * 格式化图标
 */
Menu.formatIcon = function (row, index){
	return row.icon == null ? ''
			: '<i class="' + row.icon + ' fa-lg"></i>';
}

/**
 * 格式化类型
 */
Menu.formatType = function(row, index){
	if (row.type === 0) {
		return '<span class="label label-primary">目录</span>';
	}
	if (row.type === 1) {
		return '<span class="label label-success">菜单</span>';
	}
	if (row.type === 2) {
		return '<span class="label label-warning">按钮</span>';
	}
}

/**
 * 格式序号
 */
Menu.formatIndex = function(row, index){
	return index + 1;
}

/**
 * 格式操作
 */
Menu.formatOper = function(row, index){
	var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="Menu.edit(\''
		+ row.id
		+ '\')"><i class="fa fa-edit"></i></a> ';
	
	var p = '<a class="btn btn-primary btn-sm '
		+ s_add_h
		+ '" href="#" mce_href="#" title="添加下级" onclick="Menu.addSub(\''
		+ row.id
		+ '\')"><i class="fa fa-plus"></i></a> ';
	
	var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="Menu.remove(\''
		+ row.id
		+ '\')"><i class="fa fa-remove"></i></a> ';
	return e + p + d;
}

/**
 * 重新加载
 */
Menu.reLoad = function() {
	Menu.table.refresh();
	//$('#' + Menu.id).bootstrapTable('refresh');
}

/**
 * 添加
 */
Menu.add = function(pId) {
	layer.open({
		type : 2,
		title : '增加菜单',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Menu.prefix + '/add/' + pId // iframe的url
	});
}

/**
 * 删除
 */
Menu.remove = function(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : Menu.prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(data) {
				if (data.code == 0) {
					layer.msg("删除成功");
					Menu.reLoad();
				} else {
					layer.msg(data.msg);
				}
			}
		});
	})
}

/**
 * 跳转修改
 */
Menu.edit = function(id) {
	layer.open({
		type : 2,
		title : '菜单修改',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Menu.prefix + '/edit/' + id // iframe的url
	});
}
function batchRemove() {
	// var rows = $('#exampleTable').bootstrapTable('getSelections');

}