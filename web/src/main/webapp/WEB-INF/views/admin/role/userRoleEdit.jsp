<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/nav.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<link href="${contextPath}/static/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css">

<%--系管理>系修改--%>
<style>
    #user_manage {
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
                <a href="${contextPath}/admin/user/page.action"><span class="glyphicon glyphicon-map-marker"></span> &nbsp;用户管理</a>
            </li>
            <li>角色修改</li>
            <li>${user.username}</li>
        </ol>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <form class="form-horizontal" action="${contextPath}/admin/role/user/edit.action" method="post" id="departmentEditForm">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <input type="hidden" name="adminUserId" value="${user.adminUserId}">
                    <div class="row">
                        <div class="col-md-6">
                            <c:forEach items="${userRoles}" var="role">
                                <div class="checkbox">
                                    <label>
                                        ${role.roleZHCNName}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-6">
                            <c:forEach items="${allRoles}" var="role">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="roles" value="${role.roleId}"> ${role.roleZHCNName}
                                    </label>
                                </div>
                            </c:forEach>
                            <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-10">
                                    <button type="submit" class="btn btn-warning">更改角色</button>
                                </div>
                            </div>
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

<%@include file="/WEB-INF/include/footer.jsp"%>