<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/place/room/index.action">位置管理</a>
              <a><cite>位置添加</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/place/room/edit/do.action" method="post" class="layui-form" id="placeRoomForm">
                <input type="hidden" name="roomId" value="${room.roomId}">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择地点</label>
                    <div class="layui-input-block">
                        <select name="buildingId" id="buildingId">
                            <c:forEach items="${buildings}" var="building">
                                <c:if test="${room.buildingId == building.buildingId}">
                                    <option value="${building.buildingId}" selected>${building.buildingName}</option>
                                </c:if>
                                <c:if test="${room.buildingId != building.buildingId}">
                                    <option value="${building.buildingId}">${building.buildingName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <!--
                <div class="layui-form-item">
                    <label class="layui-form-label">位置编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="roomNumber" lay-verify="roomNumber" value="${room.roomNumber}" class="layui-input" id="roomNumber">
                        <input type="hidden" name="hiddenRoomNumber" lay-verify="hiddenRoomNumber" value="${room.roomNumber}" class="layui-input" id="hiddenRoomNumber">
                    </div>
                </div>
                -->
                <div class="layui-form-item">
                    <label class="layui-form-label">位置名</label>
                    <div class="layui-input-block">
                        <input type="text" name="roomName" lay-verify="roomName" value="${room.roomName}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    /**
     roomNumber: function(value) {
            if(value.length > 4 || value.length < 2) {
                return "位置编号的长度为2到4";
            }

            if (!(/^[0-9]+$/.test(value))) {
                return "请填写数字序列";
            }

            var validateData = {
                "roomNumber": $("#roomNumber").val() ,
                "hiddenRoomNumber" : $("#hiddenRoomNumber").val()
            };
            var uniqueRoomNumber = false;

            $.ajax({
                type: 'post',
                async: false,
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                data: validateData,
                url: '${contextPath}/admin/place/room/unique/roomNumber.action',
                success: function (result) {
                    uniqueRoomNumber = result;
                }
            });

            if (!uniqueRoomNumber) {
                return "填写的位置编号已存在";
            }
        },
     */
    $(function () {
        // 导航栏选择
        $("#first").attr("class", "layui-nav-item layui-nav-itemed");
        $("#placeRoom").attr("class", "layui-this");
        var form = layui.form();

        // 加载地点数据
        function loadBuildingData(result) {
            var $ = layui.jquery;
            var $form = $('#placeRoomForm');
            var form = layui.form();
            var optionsValue = '';

            var buildings = result.buildings;

            if(buildings.length > 0) {
                for (var i = 0; i < buildings.length; i++) {
                    optionsValue += '<option value="' + buildings[i].buildingId + '">' + buildings[i].buildingName + '</option>';
                }
            } else {
                optionsValue = '<option value="0">该下拉菜单为空</option>';
            }

            $form.find('select[id=buildingId]').empty();
            $form.find('select[id=buildingId]').append(optionsValue);
            form.render();
        }

        form.on('select(distinctId)', function(data) {
            var postData = {
                "distinctId": data.value
            };

            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/place/room/buildings.action',
                data: postData,
                success: function (result) {
                    // 配置校区查询
                    loadBuildingData(result);
                }
            });
        });

        form.verify({
            roomName: function (value) {
                if (value.length < 1) {
                    return "请输入位置名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            }
        });
    });
</script>


<%@ include file="/WEB-INF/include/footer.jsp"%>