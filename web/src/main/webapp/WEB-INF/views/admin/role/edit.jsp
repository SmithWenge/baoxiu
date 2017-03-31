<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/nav.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<link href="${contextPath}/static/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css">

<%--系管理>系添加--%>
<style>
    #role {
        background: whitesmoke;
        border-left: 4px solid #fed350;
        border-right: 4px solid #fed350;
        color: #444;
    }
</style>
<div class="panel panel-default" style="float: left; width: 90%; border-radius: 0px;">
    <div class="panel-heading" style="padding-bottom: 0px; padding-top: 0px; padding-left: 0px; border-radius: 0px;">
        <ol class="breadcrumb" style="padding-bottom: 8px;margin-bottom: 0px;">
            <li>
                <a href="${contextPath}/admin/role/list.action"><span class="glyphicon glyphicon-map-marker"></span> &nbsp;角色管理</a>
            </li>
            <li>角色编辑</li>
        </ol>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/role/edit/save.action" method="post" id="roleAddForm">
                    <input type="hidden" value="${role.roleId}" name="roleId">
                    <div class="form-group">
                        <label for="inputRoleName" class="col-sm-2 control-label">角色名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="inputRoleName" value="${role.roleName}" name="roleName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRoleZHCNName" class="col-sm-2 control-label">角色中文名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="inputRoleZHCNName" value="${role.roleZHCNName}" name="roleZHCNName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色已有权限</label>
                    </div>
                        <div class="fenduan" style="margin-left: 5%;">
                            <c:if test="${checkedDepartment.size() > 0}">
                                <div class="from-group" style="height: 30px;">
                                    <label class="col-sm-2 control-label">系管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedDepartment}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedcourse.size() > 0}">
                                <div class="from-group" style="height: 30px;">
                                    <label class="col-sm-2 control-label">课程管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedcourse}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedmajor.size() > 0}">
                                <div class="from-group" style="height: 30px;">
                                    <label class="col-sm-2 control-label">专业管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedmajor}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedteacher.size() > 0}">
                                <div class="from-group" style="height: 30px;">
                                    <label class="col-sm-2 control-label">教师管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedteacher}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedstudent.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">学生管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedstudent}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkeddisciplinary.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">违纪管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkeddisciplinary}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedscore.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">成绩管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedscore}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedmonitor.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">班级管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedmonitor}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedchangeCourse.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">换课管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedchangeCourse}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedmemberCount.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">人员统计管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedmemberCount}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkeddownSign.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">线下报名管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkeddownSign}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedbackcolorSet.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">背景色管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedbackcolorSet}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedmessageSet.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">基础信息管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedmessageSet}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedlog.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">日志管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedlog}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkeduser.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">用户管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkeduser}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedregister.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">新学员管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedregister}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedcharge.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">收费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedregister}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedfinanceCount.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">收费统计管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedfinanceCount}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedfinanceAnalysis.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">财务分析管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedfinanceAnalysis}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedbill.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">发票管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedbill}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedunpayment.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">未缴费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedunpayment}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedpayment.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">已缴费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedpayment}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedrefund.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">退课费用管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedrefund}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedfinanceChangeCourse.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">换课费用管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedfinanceChangeCourse}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${checkedroom.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">房间管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${checkedroom}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" checked value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色未有权限</label>
                    </div>
                        <div class="fenduan" style="margin-left: 5%">
                            <c:if test="${department.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">系管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${department}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${course.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">课程管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${course}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${major.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">专业管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${major}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${teacher.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">教师管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${teacher}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${student.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">学生管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${student}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${disciplinary.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">违纪管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${disciplinary}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${score.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">成绩管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${score}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${monitor.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">班级管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${monitor}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${changeCourse.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">换课管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${changeCourse}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${memberCount.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">人员统计管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${memberCount}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${downSign.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">线下报名管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${downSign}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${backcolorSet.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">背景色管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${backcolorSet}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${messageSet.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">基础信息管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${messageSet}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${log.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">日志管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${log}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${user.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">用户管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${user}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${register.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">新学员管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${register}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${charge.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">收费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${charge}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${financeCount.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">收费统计管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${financeCount}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${financeAnalysis.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">财务分析管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${financeAnalysis}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${bill.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">发票管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${bill}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${unpayment.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">未缴费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${unpayment}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${payment.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">已缴费管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${payment}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${refund.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">退课费用管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${refund}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${financeChangeCourse.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">换课费用管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${financeChangeCourse}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${room.size() > 0}">
                                <div class="from-group" style="height: 30px">
                                    <label class="col-sm-2 control-label">房间管理权限</label>
                                    <div class="col-sm-10">
                                        <c:forEach items="${room}" var="permission">
                                            <label class="checkbox-inline" style="margin-left: 0px">
                                                <input type="checkbox" value="${permission.permissionId}" name="editPermissions"> ${permission.permissionZHCNName}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">保存</button>
                            <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
                        </div>
                    </div>
                </form>
            </form>
        </div>
    </div>
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