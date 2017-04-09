<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>权限管理</legend>
        <a href="${contextPath}/admin/role/routeAdd.action">
            <button class="layui-btn layui-btn-normal elementAddBtn" id="addRole">添加</button>
        </a>
        <div class="layui-field-box">
            <table class="layui-table">
                <thead>
                <tr>
                    <td>序号</td>
                    <td>角色名</td>
                    <td>描述</td>
                    <td>权限</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
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
                            <div class="layui-btn-group">
                                <a href="${contextPath}/admin/role/edit/route/${role.roleId}.action">
                                    <button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i></button>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </fieldset>
</div>

<%@include file="/WEB-INF/include/javascript.jsp"%>

<script>
    $(function () {
        // 导航栏选择
        $("#six").attr("class", "layui-nav-item layui-nav-itemed");
        $("#role").attr("class", "layui-this");
    });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>