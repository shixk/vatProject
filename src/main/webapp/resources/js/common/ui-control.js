/**
 * Created by zhangyj21 on 2015/8/16.
 */


/* 左右 选择框 */
$(function () {
    //移到右边
    $('#add').click(function () {
        //先判断是否有选中
        if (!$("#select1 option").is(":selected")) {
            alert("请选择需要移动的选项")
        }
        //获取选中的选项，删除并追加给对方
        else {
            $('#select1 option:selected').appendTo('#select2');
        }
    });

    //移到左边
    $('#remove').click(function () {
        //先判断是否有选中
        if (!$("#select2 option").is(":selected")) {
            alert("请选择需要移动的选项")
        } else {
            $('#select2 option:selected').appendTo('#select1');
        }
    });

    //全部移到右边
    $('#add_all').click(function () {
        //获取全部的选项,删除并追加给对方
        $('#select1 option').appendTo('#select2');
    });

    //全部移到左边
    $('#remove_all').click(function () {
        $('#select2 option').appendTo('#select1');
    });

    //双击选项
    $('#select1').dblclick(function () { //绑定双击事件
        //获取全部的选项,删除并追加给对方
        $("option:selected", this).appendTo('#select2'); //追加给对方
    });

    //双击选项
    $('#select2').dblclick(function () {
        $("option:selected", this).appendTo('#select1');
    });

});