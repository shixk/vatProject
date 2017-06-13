/**
 * Created by luyi1 on 2015/7/23.
 */

var url = '';
function getData(data){
    var rows = [];
    var total = data.length;
    for(var i=0; i<total; i++){
        rows.push({
            id:data[i].id,
            name:data[i].name,
            age: data[i].age
        });
    }
    var newData={"total":total,"rows":rows};
    return newData;
}

$('#userlist').datagrid({
    url: 'demo/list.do',
    title: '用户列表',
    fit: true,
    fixed: true,
    loadMsg: '数据加载中...',
    fitColumns: true,
    pageSize: 10,
    pagination: true,
    rownumbers: true,
    sortOrder: 'asc',
    striped: true,
    nowrap: true,
    align: 'center',
    singleSelect: true,
    showFooter: true,
    toolbar: "#toolbar",
    loadFilter: function (data) {
        return getData(data);
    }
});

function newUser(){
    $('#dlg').dialog('open').dialog('setTitle','新建用户');
    $('#fm').form('clear');
    url = 'demo/add.do';
}

function editUser(){
    var row = $('#userlist').datagrid('getSelected');
    if (row){
        $('#dlg').dialog('open').dialog('setTitle','编辑用户');
        $('#fm').form('load',row);
        url = 'demo/update.do?id=' + row.id;
    }
}

function saveUser(){
    $('#fm').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#dlg').dialog('close');		// close the dialog
                $('#userlist').datagrid('reload');	// reload the user data
            }
        }
    });
}


function destroyUser(){
    var row = $('#userlist').datagrid('getSelected');
    if (row){
        $.messager.confirm('确认','确认删除用户?',function(r){
            if (r){
                $.post('demo/delete.do',{id:row.id},function(result){
                    if (result.errorMsg){
                        $.messager.show({	// show error message
                            title: '错误',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#userlist').datagrid('reload');	// reload the user data
                    }
                },'json');
            }
        });
    }
}




$("#dlg").dialog({
    closed: true,
    buttons: "#dlg-buttons"
});


