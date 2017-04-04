<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/equipment/index.action">设备管理</a>
              <a><cite>设备编辑</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/equipment/edit/do.action" method="post" class="layui-form">
                <input type="hidden" name="equipmentId" value="${equipment.equipmentId}">
                <div class="layui-form-item">
                    <label class="layui-form-label">设备名</label>
                    <div class="layui-input-block">
                        <input type="text" name="equipmentName" lay-verify="equipmentName" id="equipmentName" value="${equipment.equipmentName}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="equipmentNumber" lay-verify="equipmentNumber" id="equipmentNumber" value="${equipment.equipmentNumber}" autocomplete="off" class="layui-input">
                        <input type="hidden" name="hiddenEquipmentNumber" id="hiddenEquipmentNumber" value="${equipment.equipmentNumber}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备组</label>
                    <div class="layui-input-block">
                        <select name="setId" lay-filter="aihao" id="setIdSelect">
                            <c:forEach items="${sets}" var="set">
                                <c:if test="${equipment.setId eq set.setId}">
                                    <option value="${set.setId}" selected>${set.setName}</option>
                                </c:if>
                                <c:if test="${equipment.setId ne set.setId}">
                                    <option value="${set.setId}">${set.setName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">维修小组</label>
                    <div class="layui-input-block">
                        <select name="repairGroupId" lay-filter="aihao">
                            <c:forEach items="${groups}" var="group">
                                <c:if test="${equipment.repairGroupId eq group.repairGroupId}">
                                    <option value="${group.repairGroupId}" selected>${group.groupName}</option>
                                </c:if>
                                <c:if test="${equipment.repairGroupId ne group.repairGroupId}">
                                    <option value="${group.repairGroupId}">${group.groupName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
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
        // 导航栏选择
        $("#two").attr("class", "layui-nav-item layui-nav-itemed");
        $("#equipment").attr("class", "layui-this");

        // 表单验证
        var form = layui.form();

        form.verify({
            equipmentName: function (value) {
                if (value.length < 2) {
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

                var validateData = {
                    "equipmentNumber": $("#equipmentNumber").val(),
                    "hiddenEquipmentNumber": $("#hiddenEquipmentNumber").val()
                };
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