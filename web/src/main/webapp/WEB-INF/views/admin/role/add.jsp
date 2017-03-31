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
            <li>角色添加</li>
        </ol>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/role/add.action" method="post" id="roleAddForm">
                    <div class="form-group">
                        <label for="inputRoleName" class="col-sm-2 control-label">角色名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="inputRoleName" placeholder="输入" name="roleName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRoleZHCNName" class="col-sm-2 control-label">角色中文名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="inputRoleZHCNName" placeholder="输入" name="roleZHCNName">
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label" style="float: left">系管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top: 6px">
                            <c:forEach items="${department}" var="department">
                                <input type="checkbox" value="${department.permissionId}" name="addPermissions"> ${department.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">课程管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;" >
                            <c:forEach items="${course}" var="course">
                                <input type="checkbox" value="${course.permissionId}" name="addPermissions"> ${course.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">专业管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${major}" var="major">
                                <input type="checkbox" value="${major.permissionId}" name="addPermissions"> ${major.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">教师管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${teacher}" var="teacher">
                                <input type="checkbox" value="${teacher.permissionId}" name="addPermissions"> ${teacher.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">学生管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${student}" var="student">
                                <input type="checkbox" value="${student.permissionId}" name="addPermissions"> ${student.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">违纪管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${disciplinary}" var="disciplinary">
                                <input type="checkbox" value="${disciplinary.permissionId}" name="addPermissions"> ${disciplinary.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">成绩管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${score}" var="score">
                                <input type="checkbox" value="${score.permissionId}" name="addPermissions"> ${score.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">班长管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${monitor}" var="monitor">
                                <input type="checkbox" value="${monitor.permissionId}" name="addPermissions"> ${monitor.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">换课管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${changeCourse}" var="changeCourse">
                                <input type="checkbox" value="${changeCourse.permissionId}" name="addPermissions"> ${changeCourse.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">人员统计权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${memberCount}" var="memberCount">
                                <input type="checkbox" value="${memberCount.permissionId}" name="addPermissions"> ${memberCount.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">线下报名管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${downSign}" var="downSign">
                                <input type="checkbox" value="${downSign.permissionId}" name="addPermissions"> ${downSign.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">背景色设置权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${backcolorSet}" var="backcolorSet">
                                <input type="checkbox" value="${backcolorSet.permissionId}" name="addPermissions"> ${backcolorSet.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">基础信息设置权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${messageSet}" var="messageSet">
                                <input type="checkbox" value="${messageSet.permissionId}" name="addPermissions"> ${messageSet.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">日志管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${log}" var="log">
                                <input type="checkbox" value="${log.permissionId}" name="addPermissions"> ${log.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">用户管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${user}" var="user">
                                <input type="checkbox" value="${user.permissionId}" name="addPermissions"> ${user.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">新学员管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${register}" var="register">
                                <input type="checkbox" value="${register.permissionId}" name="addPermissions"> ${register.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">收费管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${charge}" var="charge">
                                <input type="checkbox" value="${charge.permissionId}" name="addPermissions"> ${charge.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">收费统计查看权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${financeCount}" var="financeCount">
                                <input type="checkbox" value="${financeCount.permissionId}" name="addPermissions"> ${financeCount.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">财务分析管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${financeAnalysis}" var="financeAnalysis">
                                <input type="checkbox" value="${financeAnalysis.permissionId}" name="addPermissions"> ${financeAnalysis.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">发票管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${bill}" var="bill">
                                <input type="checkbox" value="${bill.permissionId}" name="addPermissions"> ${bill.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">未缴费管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${unpayment}" var="unpayment">
                                <input type="checkbox" value="${unpayment.permissionId}" name="addPermissions"> ${unpayment.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">已缴费管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${payment}" var="payment">
                                <input type="checkbox" value="${payment.permissionId}" name="addPermissions"> ${payment.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">退课费用管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${refund}" var="refund">
                                <input type="checkbox" value="${refund.permissionId}" name="addPermissions"> ${refund.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">换课费用管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${financeChangeCourse}" var="financeChangeCourse">
                                <input type="checkbox" value="${financeChangeCourse.permissionId}" name="addPermissions"> ${financeChangeCourse.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <div class="from-group" style="height: 30px">
                        <label class="col-sm-2 control-label">教室管理权限</label>
                        <div class="col-sm-10" style="float: right;margin-top:6px;">
                            <c:forEach items="${room}" var="room">
                                <input type="checkbox" value="${room.permissionId}" name="addPermissions"> ${room.permissionZHCNName}
                            </c:forEach>
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">角色权限</label>--%>
                        <%--<div class="col-sm-10">--%>
                            <%--<c:forEach items="${permissions}" var="permission">--%>
                                <%--<label class="checkbox-inline" style="margin-left: 0px;">--%>
                                    <%--<input type="checkbox" value="${permission.permissionId}" name="addPermissions"> ${permission.permissionZHCNName}--%>
                                <%--</label>--%>
                            <%--</c:forEach>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success">添加</button>
                            <button type="button" id="backMark" class="btn btn-default" style="margin-left: 100px">返回</button>
                        </div>
                    </div>
                </form>
            </div>
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