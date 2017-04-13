<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>用户权限管理</legend>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>工号</td>
          <td>姓名</td>
          <td>角色</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="status">
          <td>${status.index + 1}</td>
          <td>${user.adminNumber}</td>
          <td>${user.adminName}</td>
          <td>
            <c:forEach items="${user.roleList}" var="role">
              ${role.roleName},
            </c:forEach>
          </td>
          <td>
            <div class="layui-btn-group">
              <a href="${contextPath}/admin/userInfo/editRole/route/${user.userId}.action">
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

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
  $(function () {
    //导航栏选择
    $("#six").attr("class", "layui-nav-item layui-nav-itemed");
    $("#userRole").attr("class", "layui-this");
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>

