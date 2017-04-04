<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>位置管理</legend>
        <div class="layui-form" id="roomQueryForm">
            <div class="layui-form-item elementAddAndQueryDiv">
                <div class="layui-input-inline">
                <select name="distinctId" id="distinctId" lay-filter="distinctId">
                    <option value="">请选择校区</option>
                </select>
            </div>
                <div class="layui-input-inline">
                    <select name="buildingId" id="buildingId" lay-filter="buildingId">
                        <option value="">请选择地点</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <div class="layui-input-block queryDivBtn">
                        <button class="layui-btn layui-btn-normal" id="queryRoomBtn">查询</button>
                    </div>
                </div>
                <div class="layui-input-inline addBtnFloatRight">
                    <div class="layui-input-block">
                        <a href="${contextPath}/admin/place/room/add/route.action">
                            <button class="layui-btn layui-btn-normal" id="addPlaceRoom">添加</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-field-box">
            <table class="layui-table">
                <thead>
                <tr>
                    <td>序号</td>
                    <td>位置编号</td>
                    <td>位置名</td>
                    <td>所属校区</td>
                    <td>所属维修区域</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="pageTableBody">
                </tbody>
            </table>
        </div>
        <div class="layui-field-box">
            <div id="pageNav"></div>
        </div>
    </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    $(function () {
        // 导航栏选择
        $("#first").attr("class", "layui-nav-item layui-nav-itemed");
        $("#placeRoom").attr("class", "layui-this");

        var condition = {
            "distinctId": '',
            "buildingId": ''
        };

        // 查询数据分页显示
        $("#queryRoomBtn").click(function () {
            loadPageData();
        });

        // 加载地点数据
        function loadBuildingData(result) {
            var $ = layui.jquery;
            var $form = $('#roomQueryForm');
            var form = layui.form();

            var buildings = result.buildings;
            var optionsValue = '<option value="-100">请选择地点</option>';

            for (var i = 0; i < buildings.length; i++) {
                optionsValue += '<option value="' + buildings[i].buildingId + '">' + buildings[i].buildingName + '</option>';
            }

            $form.find('select[id=buildingId]').empty();

            $form.find('select[id=buildingId]').append(optionsValue);
            form.render();

            form.on('select(buildingId)', function(data) {

                // 地点条件
                condition.buildingId = data.value;
            });
        }

        // 加载校区数据
        function loadDistinctData(result) {
            var $ = layui.jquery;
            var $form = $('#roomQueryForm');
            var form = layui.form();

            var distincts = result.distincts;

            var optionsValue = '<option>请选择地点</option>';
            for (var i = 0; i < distincts.length; i++) {
                optionsValue += '<option value="' + distincts[i].distinctId + '">' + distincts[i].distinctName + '</option>';
            }

            $form.find('select[id=distinctId]').empty();
            $form.find('select[id=distinctId]').append(optionsValue);
            form.render();

            form.on('select(distinctId)', function(data) {
                var postData = {
                    "distinctId": data.value
                };

                // 校区条件
                condition.distinctId = data.value;

                $.ajax({
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    url: '${contextPath}/admin/place/room/buildings.action',
                    data: postData,
                    success: function (result) {
                        loadBuildingData(result);
                    }
                });
            });
        }

        // 拼接操作字符转
        function createOpsBtnGroup(roomId) {
            return '<div class="layui-btn-group">' +
                    '<a href="${contextPath}/admin/place/room/edit/route/' + roomId +'.action">' +
                    '<button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i>' +
                    '</button> </a><a href="${contextPath}/admin/place/room/delete/' + roomId + '.action">' +
                    '<button class="layui-btn layui-btn-small layui-btn-danger"><i class="layui-icon">&#xe640;</i></button>' +
                    '</a></div>'
        }
        // 分页
        var laypage = layui.laypage;

        // 跳转不同分页数据渲染
        function jumpPage(curr) {
            if (curr <= 0) curr = 1;

            var pageData = {
                "page": curr - 1,
                "buildingId": condition.buildingId,
                "distinctId": condition.distinctId
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/place/room/route/page.action',
                data: pageData,
                success: function (result) {
                    $("#pageTableBody").empty();

                    loadDistinctData(result);

                    $.each(result.page.content, function (i, item) {
                        var trData = "<tr><td>" + (i + 1) + "</td><td>" + item.roomNumber + "</td><td>" + item.roomName + "</td><td>" + item.distinctName + "</td><td>" + item.buildingName + "</td><td>" + createOpsBtnGroup(item.roomId) + "</td>";
                        $("#pageTableBody").append(trData);
                    });

                    deleteBtnConfirmListener();
                }
            });
        }

        // 初始化页面加载数据
        function loadPageData() {
            var pageData = {
                "page": 0,
                "buildingId": condition.buildingId,
                "distinctId": condition.distinctId
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/place/room/route/page.action',
                data: pageData,
                success: function (result) {
                    laypage({
                        cont: 'pageNav',
                        pages: result.page.totalPages,
                        skin: '#1E9FFF',
                        prev: '<em><</em>',
                        next: '<em>></em>',
                        groups: 10,
                        first: 1,
                        last: result.page.totalPages,
                        jump: function (obj) {
                            jumpPage(obj.curr);
                        }
                    });
                }
            });
        }

        loadPageData();

        // 删除提示弹出提示
        function deleteConfirm($deleteBtn) {
            event.preventDefault();

            layer.confirm('确定要删除 ?', {
                btn: ['确定', '取消']
            }, function(index, layero) {
                layer.closeAll('dialog');
                $deleteBtn.unbind('click');
                $deleteBtn.trigger('click');
            }, function(index) {

            });
        }

        // 添加删除的信息提示
        function deleteBtnConfirmListener() {
            $(":button.layui-btn-danger").click(function () {
                var $deleteBtn = $(this);
                deleteConfirm($deleteBtn);
            });
        }
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>