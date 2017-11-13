var Role = {
    id : "columnTable",//表格id
    prefix : "/sys/role"
};

$(function() {
	var defaultColunms = Role.initColumn();
    var table = new BSTable(Role.id, Role.prefix + "/list", defaultColunms);
   // table.setPaginationType("client");
    Role.table = table.init();
});

/**
 * 初始化表格列
 */
Role.initColumn = function(){
	var columns = [
		 {checkbox : true },
		 {title: '序号', formatter: Role.formatIndex},
	     {title: '角色名', field: 'name', align: 'center', valign: 'middle', sortable: true},
	     {title: '备注', field: 'remark', align: 'center', valign: 'middle', sortable: true},
	     {title: '创建时间', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
	     {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
	     {title: '操作', field: 'id', align: 'center', valign: 'middle', formatter: Role.formatOper}
	];
	
	return columns;
}

/**
 * 格式序号
 */
Role.formatIndex = function(value, row, index){
	return index + 1;
}

/**
 * 格式操作
 */
Role.formatOper = function(value, row, index){
	var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="Role.edit(\''
		+ row.id
		+ '\')"><i class="fa fa-edit"></i></a> ';
	var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="Role.remove(\''
		+ row.id
		+ '\')"><i class="fa fa-remove"></i></a> ';
	return e + d;
}

Role.reLoad = function() {
	$('#' + Role.id).bootstrapTable('refresh');
}

Role.add = function() {
	// iframe层
	layer.open({
		type : 2,
		title : '添加角色',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Role.prefix + '/add' // iframe的url
	});
}

Role.remove = function(id){
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : Role.prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code === 0) {
					layer.msg("删除成功");
					Role.reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})

}

Role.edit = function(id) {
	layer.open({
		type : 2,
		title : '角色修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Role.prefix + '/edit/' + id // iframe的url
	});
}

Role.batchRemove() = function{
	// var rows = $('#exampleTable').bootstrapTable('getSelections');

}