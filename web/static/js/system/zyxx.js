
layui.use(['common', 'jquery'], function(){
    var $ = layui.jquery;
    var common = layui.common;
    common.doService(ctx + '/system/zygl/queryAllZyList', {}, function(data) {
        $("#zyxx_tbody").empty();
        if (data.length <= 0) {
            var _empty_tr = '<tr colspan="5">暂无数据</tr>';
            $("#zyxx_tbody").append($(_empty_tr))
        } else {
            data.forEach(function(item, index) {
                var _tr = '<tr zyid="' + item.zyid + '">' +
                    '<td>' + zypxStr(item) + '</td>' +
                    '<td>' + zymcStr(item) + '</td>' +
                    '<td>' + item.zylj + '</td>' +
                    '<td>' + item.zylx + '</td>' +
                    '<td>' + czStr(item) + '</td>' +
                    '</tr>';
                $("#zyxx_tbody").append($(_tr))
            });

        }
    });

    function zypxStr(row) {
        var preStr = row.zyid.replace(/\d/g, '&nbsp;');
        return preStr + row.zypx;
    }

    function zymcStr(row) {
        var preStr = row.zyid.replace(/\d/g, '&nbsp;&nbsp;');
        if (row.xjzy_size <= 0) {
            return preStr + '<span class="layui-badge-dot layui-bg-black"></span> ' + row.zymc;
        } else {
            return preStr + '<i class="layui-icon" >&#xe623;</i> ' + row.zymc;
        }
    }

    function czStr(row) {
        return '<a href="javascript:edit(\'' + row.zyid + '\')">编辑</a>' +
            '<a href="javascript:del(\'' + row.zyid + '\')">修改</a>';
    }

    function eidt(zyid) {

    }

    function del(zyid) {

    }
});