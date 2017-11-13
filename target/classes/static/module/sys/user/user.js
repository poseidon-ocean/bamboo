/**
 * 系统管理--用户管理的单例对象
 */
var MgrUser = {
    id : "columnTable",//表格id
    seItem : null,		//选中的条目
    table : null,
    layerIndex : -1,
    prefix : "/sys/user",
    deptid : ''
};

$(function() {
	MgrUser.getTreeData();
	var defaultColunms = MgrUser.initColumn();
    var table = new BSTable(MgrUser.id, MgrUser.prefix + "/list", defaultColunms);
   // table.setPaginationType("client");
    MgrUser.table = table.init();
});

/**
 * 初始化表格列
 */
MgrUser.initColumn = function(){
	var columns = [
		 {checkbox : true },
		 {title: '序号', formatter: MgrUser.formatIndex},
	     {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
	     {title: '用户名', field: 'username', align: 'center', valign: 'middle', sortable: true},
	     {title: '邮箱', field: 'email', align: 'center', valign: 'middle', sortable: true},
	     {title: '状态', field: 'status', align: 'center', valign: 'middle', formatter: MgrUser.formatStatus},
	     {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle'},
	     {title: '操作', field: 'id', align: 'center', valign: 'middle', formatter: MgrUser.formatOper}
	];
	
	return columns;
}

/**
 * 格式序号
 */
MgrUser.formatIndex = function(value, row, index){
	return index + 1;
}

/**
 * 格式状态
 */
MgrUser.formatStatus = function(value, row, index){
	if (value == '0') {
		return '<span class="label label-danger">禁用</span>';
	} else if (value == '1') {
		return '<span class="label label-primary">正常</span>';
	}
}

/**
 * 格式操作
 */
MgrUser.formatOper = function(value, row, index){
	var s_edit_h='', s_remove_h='', s_resetPwd_h='';
	var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="MgrUser.edit(\''
	+ row.id
	+ '\')"><i class="fa fa-edit "></i></a> ';
	var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="MgrUser.remove(\''
		+ row.id
		+ '\')"><i class="fa fa-remove"></i></a> ';
	var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="MgrUser.resetPwd(\''
		+ row.id
		+ '\')"><i class="fa fa-key"></i></a> ';
	return e + d + f;
}

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        alert("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
    }
};


/**
 * 重新加载
 */
MgrUser.reLoad = function() {
	$('#' + MgrUser.id).bootstrapTable('refresh');
}

/**
 * 跳转新增
 */
MgrUser.add = function() {
	// iframe层
	layer.open({
		type : 2,
		title : '增加用户',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : MgrUser.prefix + '/add'
	});
}

/**
 * 移除选中的记录
 */
MgrUser.remove = function(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : MgrUser.prefix + "/remove",
			type : "POST",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					MgrUser.reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

/**
 * 跳转编辑
 */
MgrUser.edit = function(id) {
	layer.open({
		type : 2,
		title : '用户修改',
		maxmin : true,
		shadeClose : true, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : MgrUser.prefix + '/edit/' + id // iframe的url
	});
}

/**
 * 重置密码
 */
MgrUser.resetPwd = function(id) {
	layer.open({
		type : 2,
		title : '重置密码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '400px', '260px' ],
		content : MgrUser.prefix + '/resetPwd/' + id // iframe的url
	});
}

MgrUser.batchRemove = function() {
	var rows = $('#'+MgrUser.id).bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : MgrUser.prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					MgrUser.reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}

/**
 * 获取部门数据
 */
MgrUser.getTreeData = function() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		success : function(tree) {
			MgrUser.loadTree(tree);
		}
	});
}

/**
 * 加载部门数据
 */
MgrUser.loadTree = function(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}

/**
 * 点击部门切换数据
 * @param e
 * @param data
 * @returns
 */
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#' + MgrUser.id).bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				deptId : data.selected[0],
			}
		}
		$('#' + MgrUser.id).bootstrapTable('refresh',opt);
	}

});