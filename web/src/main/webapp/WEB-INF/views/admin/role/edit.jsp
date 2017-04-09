<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/role/list.action">角色管理</a>
              <a><cite>角色编辑</cite></a>
            </span>
        </legend>
        <div style="width: 98%; margin-top: 15px;">
            <form action="${contextPath}/admin/role/edit/save.action" method="post" class="layui-form" id="roleAddForm">
                <input type="hidden" value="${role.roleId}" name="roleId">
                <div class="layui-form-item">
                    <label class="layui-form-label">角色名</label>
                    <div class="layui-input-block">
                        <input type="text" id="inputRoleName" placeholder="输入" name="roleName" value="${role.roleName}" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">角色中文名</label>
                    <div class="layui-input-block">
                        <input type="text" id="inputRoleZHCNName" placeholder="输入" name="roleZHCNName" value="${role.roleZHCNName}" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="layui-form-label">角色已有权限</label>
                </div>
                <div class="fenduan" style="margin-left: 5%;">
                    <c:if test="${checkedDistinct.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">校区管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedDistinct}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedBuilding.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">地点管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedBuilding}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedRoom.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">位置管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedRoom}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedSet.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">设备组管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedSet}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedEquipment.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">设备管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedEquipment}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedPrinter.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">打印机管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedPrinter}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedWorkerInfo.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">工人信息管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedWorkerInfo}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedWorkerType.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">工种管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedWorkerType}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedRepairGroup.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">维修小组管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedRepairGroup}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${checkedMaintenanceList.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">报修单管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${checkedMaintenanceList}" var="maintenanceList">
                                    <input type="checkbox" name="editPermissions" value="${maintenanceList.permissionId}" title="${maintenanceList.permissionZHCNName}" checked>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="form-group">
                    <label class="layui-form-label">角色未有权限</label>
                </div>
                <div class="fenduan" style="margin-left: 5%;">
                    <c:if test="${distinct.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">校区管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${distinct}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${building.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">地点管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${building}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${room.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">位置管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${room}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${set.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">设备组管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${set}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${equipment.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">设备管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${equipment}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${printer.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">打印机管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${printer}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${workerInfo.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">工人信息管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${workerInfo}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${workerType.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">工种管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${workerType}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${repairGroup.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">维修小组管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${repairGroup}" var="permission">
                                    <input type="checkbox" name="editPermissions" value="${permission.permissionId}" title="${permission.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${maintenanceList.size() > 0}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">报修单管理权限</label>
                            <div class="layui-input-block">
                                <c:forEach items="${maintenanceList}" var="maintenanceList">
                                    <input type="checkbox" name="editPermissions" value="${maintenanceList.permissionId}" title="${maintenanceList.permissionZHCNName}">
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
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

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script type="text/javascript">
    $(function () {
        $("#userManageNav").trigger("click");
    });
</script>

<script type="text/javascript">
    // 中文验证
    jQuery.validator.addMethod("zhongwen", function(value, element) {
        return this.optional(element) || /^[\u4e00-\u9fa5]+$/.test(value);
    }, "请输入中文");

    jQuery.validator.addMethod("zimu", function(value, element) {
        return this.optional(element) || /^[a-zA-Z]+$/.test(value);
    }, "请输入字母");

    $(function () {
        $('#roleAddForm').validate({
            rules: {
                roleName: {
                    required: true,
                    zimu: true,
                    minlength: 2,
                    maxlength: 25
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
                    maxlength: "角色名的长度为2到25."
                },
                roleZHCNName: {
                    required: "请填写角色名的中文描述."
                }
            }
        });
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>