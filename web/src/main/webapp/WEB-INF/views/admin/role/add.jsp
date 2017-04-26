<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/role/list.action">角色管理</a>
              <a><cite>角色添加</cite></a>
            </span>
        </legend>
        <div style="width: 98%; margin-top: 15px;">
            <form action="${contextPath}/admin/role/add.action" method="post" class="layui-form" id="roleAddForm">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名</label>
                    <div class="layui-input-block">
                        <input type="text" id="inputRoleName" placeholder="输入" name="roleName" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色中文名</label>
                    <div class="layui-input-block">
                        <input type="text" id="inputRoleZHCNName" placeholder="输入" name="roleZHCNName" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">校区管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${distinct}" var="distinct">
                            <input type="checkbox" name="addPermissions" value="${distinct.permissionId}" title="${distinct.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">地点管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${building}" var="building">
                            <input type="checkbox" name="addPermissions" value="${building.permissionId}" title="${building.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">位置管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${room}" var="room">
                            <input type="checkbox" name="addPermissions" value="${room.permissionId}" title="${room.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备组管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${set}" var="set">
                            <input type="checkbox" name="addPermissions" value="${set.permissionId}" title="${set.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${equipment}" var="equipment">
                            <input type="checkbox" name="addPermissions" value="${equipment.permissionId}" title="${equipment.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">打印机管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${printer}" var="printer">
                            <input type="checkbox" name="addPermissions" value="${printer.permissionId}" title="${printer.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">工人信息管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${workerInfo}" var="workerInfo">
                            <input type="checkbox" name="addPermissions" value="${workerInfo.permissionId}" title="${workerInfo.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">工种管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${workerType}" var="workerType">
                            <input type="checkbox" name="addPermissions" value="${workerType.permissionId}" title="${workerType.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">维修小组管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${repairGroup}" var="repairGroup">
                            <input type="checkbox" name="addPermissions" value="${repairGroup.permissionId}" title="${repairGroup.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">报修单管理权限</label>
                    <div class="layui-input-block">
                        <c:forEach items="${maintenanceList}" var="maintenanceList">
                            <input type="checkbox" name="addPermissions" value="${maintenanceList.permissionId}" title="${maintenanceList.permissionZHCNName}">
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">新建</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>
<%@include file="/WEB-INF/include/javascript.jsp"%>
<script type="text/javascript" src="${contextPath}/static/plugin/jquery/jquery.validate.min.js"></script>

<script type="text/javascript">
    $(function () {
        // 导航栏选择
        $("#six").attr("class", "layui-nav-item layui-nav-itemed");
        $("#role").attr("class", "layui-this");
    });

    // 中文验证
    jQuery.validator.addMethod("zhongwen", function(value, element) {
        return this.optional(element) || /^[\u4e00-\u9fa5]+$/.test(value);
    }, "请输入中文");

    jQuery.validator.addMethod("zimu", function(value, element) {
        return this.optional(element) || /^[a-zA-Z]+$/.test(value);
    }, "请输入字母");

    $(function () {
        // 导航栏选择
        $("#six").attr("class", "layui-nav-item layui-nav-itemed");
        $("#role").attr("class", "layui-this");

        $('#roleAddForm').validate({
            rules: {
                roleName: {
                    required: true,
                    zimu: true,
                    minlength: 2,
                    maxlength: 25,
                    remote: {
                        url : "${contextPath}/admin/role/unique/roleName.action",
                        type : "post",
                        dataType : "json",
                        data : {
                            roleName : function() {
                                return $("#inputRoleName").val();
                            }
                        }
                    }
                },
                roleZHCNName: {
                    required: true,
                    zhongwen: true
                }
            },
            messages: {
                roleName: {
                    required: "请填写角色名.",
                    zimu: "请保证角色名为字母",
                    minlength: "角色名的长度为2到25.",
                    maxlength: "角色名的长度为2到25.",
                    remote: "请填写不存在的角色名."
                },
                roleZHCNName: {
                    required: "请填写角色名的中文描述."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>