var Dept = {
    id : "columnTable",//表格id
    layerIndex : -1,
    prefix : "/sys/dept"
};

$(function() {
    var defaultColunms = Dept.initColumn();
    
    var table = new BSTreeTable(Dept.id, Dept.prefix +"/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    Dept.table = table;
});

/**
 * 初始化表格列
 */
Dept.initColumn = function(){
	var columns = [
		 {checkbox : true },
		 {title: '序号', formatter: Dept.formatIndex},
	     {title: '部门名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
	     {title: '排序', field: 'orderNum', align: 'center', valign: 'middle', sortable: true},
	     {title: '创建人', field: 'createBy', align: 'center', valign: 'middle', sortable: true},
	     {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
	     {title: '操作', field: 'id', align: 'center', valign: 'middle', formatter: Dept.formatOper}
	];
	
	return columns;
}

/**
 * 格式序号
 */
Dept.formatIndex = function(row, index){
	return index + 1;
}

/**
 * 格式操作
 */
Dept.formatOper = function(row, index){
	var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="Dept.edit(\''
		+ row.id
		+ '\')"><i class="fa fa-edit"></i></a> ';
	var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="增加下級"  mce_href="#" onclick="Dept.add(\''
	+ row.id
	+ '\')"><i class="fa fa-plus"></i></a> ';
	var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="Dept.remove(\''
		+ row.id
		+ '\')"><i class="fa fa-remove"></i></a> ';
	return e + a + d;
}


Dept.reLoad = function() {
	load();
}

Dept.add = function(pId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Dept.prefix + '/add/' + pId
	});
}

Dept.edit = function(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : Dept.prefix + '/edit/' + id // iframe的url
	});
}
 
Dept.remove = function(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : Dept.prefix + "/remove",
			type : "post",
			data : {
				'deptId' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					Dept.reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}


Dept.batchRemove = function() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['deptId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : Dept.prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					Dept.reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}

