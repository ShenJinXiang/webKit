// $(function(){
   // layui.layer.msg('shenjinxiang');
   initTable();
// });

function initTable() {
   var table = layui.table;
   table.render({
       elem: '#zyxxTable',
       page: true,
       cols: [[
           {checkbox: true},
           {field: 'id', title: 'ID', width: 80},
           {field: 'username', title: '用户名', width: 120},
           {field: 'age', title: '年龄', width: 40},
           {field: 'address', title: '地址', width: 200}
       ]],
       data: [
           {id: 1, username: '张三', age: 18, address: '北京'},
           {id: 2, username: '李四', age: 28, address: '天津'},
           {id: 3, username: '王五', age: 38, address: '太原'}
       ]
   });
}
