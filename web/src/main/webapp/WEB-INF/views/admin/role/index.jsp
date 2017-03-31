<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/nav.jsp"%>
<%@include file="/WEB-INF/include/message.jsp"%>

<%--课程管理--%>
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
        <ul class="nav nav-pills">
            <li role="presentation">
                <a href="${contextPath}/admin/role/list.action">
                    <span class="glyphicon glyphicon-map-marker"></span> &nbsp;角色管理
                </a>
            </li>
            <li role="presentation" style="float: right;">
                <a href="${contextPath}/admin/role/routeAdd.action">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </li>
        </ul>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top: 5px;">
            <div class="col-md-12">
                <table class="table" id="paginationTable" align="center">
                    <tr style="background-color: #3767b1; color: #dbdbdb;">
                        <th>序号</th>
                        <th>角色名</th>
                        <th>描述</th>
                        <th>权限</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${roles}" var="role" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${role.roleName}</td>
                            <td>${role.roleZHCNName}</td>
                            <td>
                                <c:forEach items="${role.permissions}" var="permission">
                                    ${permission.permissionZHCNName},
                                </c:forEach>
                            </td>
                            <td>
                                <shiro:hasRole name="super">
                                    <a href="${contextPath}/admin/role/edit/route/${role.roleId}.action">
                                        <button type="button" class="btn btn-warning">修改</button>
                                    </a>
                                </shiro:hasRole>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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
    $(function() {
        // 设置table表格中的行高
        var $height = $('#paginationTable td').height() + 'px';
        $('#paginationTable td').css('line-height', $height);
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>