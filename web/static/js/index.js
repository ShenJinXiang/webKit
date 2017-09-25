//JavaScript代码区域
layui.use('element', function(){
    var element = layui.element;
    initMainFrameHeight();

    $(window).resize(initMainFrameHeight);

    $("#menu-nav a").on("click", function() {
        debugger
        var zyid = $(this).attr('zyid');
        var zylj = $(this).attr('zylj');
        var zymc = $(this).attr("zymc");
        if (!!zylj && zylj != '#') {
            addMainTab(zyid, zymc, ctx + zylj);
        }

        return false;
    });

    $("#lay-main-title").on('click', '.layui-tab-close', function() {
        var _index = $(this).parent().index();
        $(this).parent().remove();
        $("#lay-main-content .layui-tab-item").eq(_index).remove();
        if ($("#lay-main-title li.layui-this").size() <= 0) {
            $("#lay-main-title li").eq(_index - 1).addClass("layui-this");
            $("#lay-main-content .layui-tab-item").eq(_index - 1).addClass("layui-show");
        }
        setSelectMenu();
    });

    $("#lay-main-title").on('click', 'li', function() {
        setSelectMenu();
    });

    function setSelectMenu() {
        var zyid = $("#lay-main-title .layui-this").attr('zyid');
        $("#menu-nav .layui-this").removeClass("layui-this");
        if ($("#menu-nav [zyid='" + zyid + "']").size() > 0) {
            $("#menu-nav [zyid='" + zyid + "']").addClass("layui-this");
        }
    }

    function addMainTab(zyid, zymc, zylj) {
        if ($("#lay-main-title li[zyid=" + zyid + "]").size() <= 0) {
            var _title = '<li class="layui-this" zyid="' + zyid + '">' + zymc + '' +
                '<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>' +
                '</li>';
            $("#lay-main-title").children("li").removeClass("layui-this").end().append($(_title));
            var _item = '<div class="layui-tab-item layui-show" zyid="' + zyid + '">' +
                '<iframe src="' + zylj + '" class="lay-webkit-main-frame" frameborder="0" ></iframe>' +
                '</div>';
            $("#lay-main-content").children(".layui-tab-item").removeClass("layui-show").end().append($(_item));
            initMainFrameHeight();
        } else {
            $("#lay-main-title li[zyid=" + zyid + "]").trigger("click");
        }
        setSelectMenu();
    }

    function initMainFrameHeight() {
        $(".lay-webkit-main-frame").height($(".lay-webkit-main-frame").closest(".layui-body").height() - 66);
    }
});