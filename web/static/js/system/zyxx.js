
layui.use(['common', 'table'], function(){
    var common = layui.common;
    common.doService(ctx + '/system/zygl/query', {}, function(data) {
       console.log(data);
    });

    layui.table.render({
        elem: '#zyxxTable',
        page: true,
        cols: [[
            {checkbox: true},
            {field: 'id', title: 'ID', width: 80},
            {field: 'name', title: '姓名', width: 120},
            {field: 'age', title: '年龄', width: 60},
            {field: 'address', title: '地址', width: 240}
        ]],
        data: [
            {id: 1, name: '张三', age: 18, address: '北京'},
            {id: 2, name: '李四', age: 28, address: '山西'},
            {id: 3, name: '王五', age: 24, address: '台湾'}
        ]
    });
});