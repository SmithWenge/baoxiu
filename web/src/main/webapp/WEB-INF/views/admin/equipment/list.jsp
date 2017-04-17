<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>设备管理</legend>
        <div class="layui-form" id="equipmentQueryForm">
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
                    <select name="roomId" id="roomId" lay-filter="roomId">
                        <option value="">请选择位置</option>
                    </select>
                </div>
                <div class="layui-input-inline">
                    <div class="layui-input-block queryDivBtn">
                        <button class="layui-btn layui-btn-normal" id="queryEquipmentBtn">查询</button>
                    </div>
                </div>
                <div class="layui-input-inline addBtnFloatRight">
                    <div class="layui-input-block">
                        <a href="${contextPath}/admin/equipment/add/route.action">
                            <button class="layui-btn layui-btn-normal" id="addPlaceDistinct">添加</button>
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
                    <td>地点</td>
                    <td>位置</td>
                    <td>设备名</td>
                    <td>设备编号</td>
                    <td>维修小组</td>
                    <td>小组编号</td>
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
        $("#two").attr("class", "layui-nav-item layui-nav-itemed");
        $("#equipment").attr("class", "layui-this");

        var condition = {
            "roomId": '',
            "buildingId": ''
        };

        // 查询数据分页显示
        $("#queryEquipmentBtn").click(function () {
            loadPageData();
        });

        // 加载位置信息
        function loadRoomData(result) {
            var $ = layui.jquery;
            var $form = $('#equipmentQueryForm');
            var form = layui.form();

            var rooms = result.rooms;
            var optionsValue = '<option value="-100">请选择位置</option>';

            for (var i = 0; i < rooms.length; i++) {
                optionsValue += '<option value="' + rooms[i].roomId + '">' + rooms[i].roomName + '</option>';
            }

            $form.find('select[id=roomId]').empty();
            $form.find('select[id=roomId]').append(optionsValue);
            form.render();

            form.on('select(roomId)', function(data) {
                // 位置条件
                console.log(data);
                condition.roomId = data.value;
                console.log(condition);
            });
        }

        // 加载地点数据
        function loadBuildingData(result) {
            var $ = layui.jquery;
            var $form = $('#equipmentQueryForm');
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
                var postData = {
                    "buildingId": data.value
                };

                // 地点条件
                condition.buildingId = data.value;

                $.ajax({
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    url: '${contextPath}/admin/equipment/building/rooms.action',
                    data: postData,
                    success: function (result) {
                        loadRoomData(result);
                    }
                });
            });
        }

        // 加载校区数据
        function loadDistinctData(result) {
            var $ = layui.jquery;
            var $form = $('#equipmentQueryForm');
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

                $.ajax({
                    type: 'post',
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    url: '${contextPath}/admin/equipment/distinct/buildings.action',
                    data: postData,
                    success: function (result) {
                        loadBuildingData(result);
                    }
                });
            });
        }

        // 拼接操作字符转
        function createOpsBtnGroup(equipmentId) {
            return '<div class="layui-btn-group">' +
                    '<a href="${contextPath}/admin/equipment/edit/route/' + equipmentId +'.action">' +
                    '<button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i>' +
                    '</button> </a><a href="${contextPath}/admin/equipment/delete/' + equipmentId + '.action">' +
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
                "roomId": condition.roomId
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/equipment/route/page.action',
                data: pageData,
                success: function (result) {
                    $("#pageTableBody").empty();

                    loadDistinctData(result);

                    $.each(result.page.content, function (i, item) {
                        var trData = "<tr><td>" + (i + 1) + "</td><td>" + item.buildingName + "</td><td>" + item.roomName + "</td><td>" + item.equipmentName + "</td><td>" + item.equipmentNumber + "</td>";
                        trData += "<td>" + item.repairGroupName + "</td><td>" + item.repairGroupNumber + "</td><td>" + createOpsBtnGroup(item.equipmentId) + "</td></tr>";
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
                "roomId": condition.roomId
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/equipment/route/page.action',
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