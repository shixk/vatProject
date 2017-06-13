<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common.jsp"%>
<script src="<c:url value='/resources/js/quartzcron/cron.js'/>" charset="utf-8"></script>
<body>
	<div id="tt" class="easyui-tabs">
		<div title="计划中的任务" data-options="refreshable: false">
			<div id="tb">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-standard-clock-add" onclick="add();">添加</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-standard-clock-pause" onclick="stop();">暂停</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-standard-clock" onclick="resume();">恢复</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-standard-clock-delete" onclick="del();">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-standard-clock-edit" style="width: 150px;"
					onclick="upd();">修改表达式</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-standard-clock-play"
					style="width: 150px;" onclick="startNow();">立即运行一次</a>
			</div>
			<table id="dg"></table>
		</div>
		<div title="运行中的任务" data-options="refreshable: false">
			<table id="dg_running"></table>
		</div>
	</div>

	<div id="dlg"></div>
	<script type="text/javascript">
		var dg;
		var dg_running;
		var d;
		$(function() {
			dg = $('#dg').datagrid({
				url : 'scheduleJob/json.do',
				fit : true,
				fitColumns : true,
				border : false,
				idField : 'id',
				striped : true,
				pagination : true,
				rownumbers : true,
				pageNumber : 1,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50 ],
				remoteSort : false,
				singleSelect : true,
				columns : [ [ {
					field : 'name',
					title : '任务名',
					sortable : true,
					width : 30
				}, {
					field : 'group',
					title : '任务组',
					sortable : true,
					width : 30
				}, {
					field : 'cronExpression',
					title : 'cron表达式',
					sortable : true,
					width : 70,
					editor : "text"
				}, {
					field : 'status',
					title : '状态',
					sortable : true,
					width : 30
				}, {
					field : 'className',
					title : '任务类',
					sortable : true
				}, {
					field : 'description',
					title : '描述',
					sortable : true
				} ] ],
				toolbar : '#tb',
				autoEditing : true, //该属性启用双击行时自定开启该行的编辑状态
				singleEditing : true,
				onAfterEdit : function(rowIndex, rowData, changes) {
					sureUpd(rowData);
				}
			});

		});

		$('#tt').tabs({
			fit : true,
			border : false,
			onSelect : function(title, index) {
				if (index == 1) {
					dg_running = $('#dg_running').datagrid({
						url : 'scheduleJob/running/json.do',
						fit : true,
						fitColumns : true,
						border : false,
						idField : 'id',
						striped : true,
						pagination : true,
						rownumbers : true,
						pageNumber : 1,
						pageSize : 20,
						pageList : [ 10, 20, 30, 40, 50 ],
						remoteSort : false,
						singleSelect : true,
						columns : [ [ {
							field : 'name',
							title : '任务名',
							sortable : true,
							width : 30
						}, {
							field : 'group',
							title : '任务组',
							sortable : true,
							width : 30
						}, {
							field : 'cronExpression',
							title : 'cron表达式',
							sortable : true,
							width : 70,
							editor : "text"
						}, {
							field : 'status',
							title : '状态',
							sortable : true,
							width : 30
						}, {
							field : 'className',
							title : '任务类',
							sortable : true
						}, {
							field : 'description',
							title : '描述',
							sortable : true
						} ] ]
					});
				}
			}
		});

		//添加
		function add() {
			d = $("#dlg").dialog({
				title : '添加定时任务',
				width : 300,
				height : 200,
				href : 'scheduleJob/add.do',
				modal : true,
				buttons : [ {
					text : '确认',
					handler : function() {
						$("#mainform").submit();
					}
				}, {
					text : '取消',
					handler : function() {
						d.panel('close');
					}
				} ]
			});
		}

		//暂停
		function stop() {
			var row = dg.datagrid('getSelected');
			if (rowIsNull(row))
				return;
			window.$.messager.confirm('提示', '确定要暂停任务？', function(data) {
				if (data) {
					$.ajax({
						type : 'get',
						url : "scheduleJob/" + row.name + "/" + row.group
								+ "/stop.do",
						success : function(data) {
							window.$.messager.show({
								title : "提示",
								msg : data,
								position : "bottomRight"
							});

						}
					});
				}
			});
		}

		//恢复
		function resume() {
			var row = dg.datagrid('getSelected');
			if (rowIsNull(row))
				return;
			window.$.messager.confirm('提示', '确定要恢复任务？', function(data) {
				if (data) {
					$.ajax({
						type : 'get',
						url : "scheduleJob/" + row.name + "/" + row.group
								+ "/resume.do",
						success : function(data) {
							window.$.messager.show({
								title : "提示",
								msg : data,
								position : "bottomRight"
							});

						}
					});
				}
			});
		}

		//删除
		function del() {
			var row = dg.datagrid('getSelected');
			if (rowIsNull(row))
				return;
			window.$.messager.confirm('提示', '删除后无法恢复您确定要删除？', function(data) {
				if (data) {
					$.ajax({
						type : 'get',
						url : "scheduleJob/" + row.name + "/" + row.group
								+ "/delete.do",
						success : function(data) {
							window.$.messager.show({
								title : "提示",
								msg : data,
								position : "bottomRight"
							});

						}
					});
				}
			});
		}

		//修改表达式
		function upd() {
			var row = dg.datagrid('getSelected');
			if (rowIsNull(row))
				return;
			var rowIndex = dg.datagrid('getRowIndex', row);
			dg.datagrid('beginEdit', rowIndex);
		}

		//确认修改
		function sureUpd(row) {
			window.$.messager.confirm('提示', '确定要修改任务？', function(data) {
				if (data) {
					$.ajax({
						type : 'get',
						url : "scheduleJob/" + row.name + "/" + row.group
								+ "/update.do",
						data : "cronExpression=" + row.cronExpression,
						success : function(data) {
							window.$.messager.show({
								title : "提示",
								msg : data,
								position : "bottomRight"
							});

						}
					});
					d.panel('close');
				} else {
					dg.datagrid('rejectChanges');
				}
			});
		}

		//立即运行一次
		function startNow() {
			var row = dg.datagrid('getSelected');
			if (rowIsNull(row))
				return;
			window.$.messager.confirm('提示', '确定要立即运行一次该任务？', function(data) {
				data = eval(data);
				if (data) {
					$.ajax({
						type : 'get',
						url : "scheduleJob/" + row.name + "/" + row.group
								+ "/startNow.do",
						success : function(data) {
							//dg.datagrid('reload');
							window.$.messager.show({
								title : "提示",
								msg : data,
								position : "bottomRight"
							});
						}
					});
				}
			});
		}
	</script>
</body>
</html>