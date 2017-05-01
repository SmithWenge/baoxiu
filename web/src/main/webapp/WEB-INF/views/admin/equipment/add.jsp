<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/equipment/index.action">设备管理</a>
              <a><cite>设备添加</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/equipment/add/do.action" method="post" class="layui-form" id="equipmentAddForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">校区</label>
                    <div class="layui-input-block">
                        <select name="distinctId" lay-filter="distinctId" id="distinctId">
                            <c:forEach items="${distincts}" var="distinct">
                                <option value="${distinct.distinctId}">${distinct.distinctName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">地点</label>
                    <div class="layui-input-block">
                        <select name="buildingId" id="buildingId" lay-filter="buildingId">
                            <option value="">请选择地点</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">位置</label>
                    <div class="layui-input-block">
                        <select name="roomId" id="roomId" lay-filter="roomId">
                            <option value="">请选择位置</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备名</label>
                    <div class="layui-input-block">
                        <input type="text" name="equipmentName" lay-verify="equipmentName" id="equipmentName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="equipmentNumber" lay-verify="equipmentNumber" id="equipmentNumber" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">维修小组</label>
                    <div class="layui-input-block">
                        <select name="repairGroupId" lay-filter="aihao">
                            <c:forEach items="${groups}" var="group">
                                <option value="${group.repairGroupId}">${group.groupName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">添加</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    $(function () {
        // 导航栏选择
        // 导航栏选择
        $("#first").attr("class", "layui-nav-item layui-nav-itemed");
        $("#equipment").attr("class", "layui-this");

        // 加载位置信息
        function loadRoomData(result) {
            var $ = layui.jquery;
            var $form = $('#equipmentAddForm');
            var form = layui.form();

            var rooms = result.rooms;
            var optionsValue = '<option value="-100">请选择位置</option>';

            for (var i = 0; i < rooms.length; i++) {
                optionsValue += '<option value="' + rooms[i].roomId + '">' + rooms[i].roomName + '</option>';
            }

            $form.find('select[id=roomId]').empty();
            $form.find('select[id=roomId]').append(optionsValue);
            form.render();
        }

        // 加载地点数据
        function loadBuildingData(result) {
            var $ = layui.jquery;
            var $form = $('#equipmentAddForm');
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
        function loadDistinctData() {
            var $ = layui.jquery;
            var $form = $('#equipmentAddForm');
            var form = layui.form();

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

        loadDistinctData();

        // 表单验证
        var form = layui.form();

        form.verify({
            equipmentName: function (value) {
                if (value.length < 1) {
                    return "请输入设备名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            },
            equipmentNumber: function (value) {
                if(value.length > 4 || value.length < 2) {
                    return "设备编号的长度为2到4";
                }

                if (!(/^[0-9]+$/.test(value))) {
                    return "请填写数字序列";
                }

                var validateData = { "equipmentNumber": $("#equipmentNumber").val() };
                var uniqueEquipmentNumber = false;

                $.ajax({
                    type: 'post',
                    async: false,
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    data: validateData,
                    url: '${contextPath}/admin/equipment/unique/equipmentNumber.action',
                    success: function (result) {
                        uniqueEquipmentNumber = result;
                    }
                });

                if (!uniqueEquipmentNumber) {
                    return "填写的设备编号已存在";
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>