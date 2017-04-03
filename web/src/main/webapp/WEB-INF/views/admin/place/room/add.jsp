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
            <form action="${contextPath}/admin/place/distinct/add/do.action" method="post" class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">选择校区</label>
                    <div class="layui-input-block">
                        <select name="distinctId" id="distinctId">
                            <c:forEach items="${distincts}" var="distinct">
                                <option value="${distinct.distinctId}">${distinct.distinctName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择地点</label>
                    <div class="layui-input-block">
                        <select name="buildingId" id="buildingId">

                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">位置编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="roomNumber" lay-verify="roomNumber" placeholder="请输入编号" class="layui-input" id="roomNumber">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">位置名</label>
                    <div class="layui-input-block">
                        <input type="text" name="roomName" lay-verify="roomName" placeholder="请输入" autocomplete="off" class="layui-input">
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
    $(function () {
        var form = layui.form();

        form.verify({
            roomNumber: function(value) {
                if(value.length > 4 || value.length < 2) {
                    return "位置编号的长度为2到4";
                }

                if (!(/^[0-9]+$/.test(value))) {
                    return "请填写数字序列";
                }

                var validateData = { "roomNumber": $("#roomNumber").val() };
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
            roomName: function (value) {
                if (value.length < 2) {
                    return "请输入位置名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            }
        });
    });

    $('#distinctId').trigger('change');

    $('#distinctId').on('change', function () {
        var distinctId = $('#distinctId').val();
        var building = document.getElementById("buildingId");
        $.ajax({
            type: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '${contextPath}/admin/place/room/buildings/' + distinctId + '.action',
            success: function (result) {
                building.options.length = 0;
                $.each(result.buildings, function (i, item) {
                    building.options.add(new Option(item.buildingName, item.buildingId));
                });
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>