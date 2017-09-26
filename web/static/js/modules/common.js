layui.define(['layer', 'jquery'], function(exports) {
    var layer = layui.layer
        ,$ = layui.jquery;


    function qyAlert(msg,fn){
        layer.msg(msg, {
            time:1000
        },fn);
    }

    function qyAlertByTime(msg,time,fn){
        layer.msg(msg, {
            time:time
        },fn);
    }

    function qyError(msg,fn){
        layer.msg(msg, {
            time:2000,
            shift:6
        },fn);
    }

    function qyErrorByTime(msg,time,fn){
        layer.msg(msg, {
            time:time,
            shift:6
        },fn);
    }

    function doService(url, data, callback) {
        $.ajax({
            url: url,
            data: data,
            method: 'POST',
            dataType: 'JSON',
            success: function(json) {
                if (json.success) {
                    callback(json.message);
                } else {
                    qyError(json.message);
                }
            },
            error: function() {
                qyError("系统错误");
            }
        });
    }

    exports('common', {
        qyAlert: qyAlert,
        qyAlertByTime: qyAlertByTime,
        qyError: qyError,
        qyErrorByTime: qyErrorByTime,
        doService: doService
    });
});
