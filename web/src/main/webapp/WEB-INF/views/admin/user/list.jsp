<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>管理员用户信息管理</legend>
    <a href="${contextPath}/admin/userInfo/add/router.action.action">
      <button class="layui-btn layui-btn-normal elementAddBtn" id="addAdminUser">添加</button>
    </a>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>工号</td>
          <td>姓名</td>
          <td>性别</td>
          <td>邮箱</td>
          <td>身份证</td>
          <td>电话</td>
          <td>登陆名</td>
          <td>状态</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${user}" var="user" varStatus="status">
            <td>${status.index + 1}</td>
            <td>${user.adminNumber}</td>
            <td>${user.adminName}</td>
            <td>
              <c:if  test="${user.adminGender == 1}" >男</c:if>
              <c:if  test="${user.adminGender == 0}" >女</c:if>
            </td>
            <td>${user.adminEmail}</td>
            <td>${user.adminCard}</td>
            <td>${user.adminTelephone}</td>
            <td>${user.username}</td>
            <td>
              <c:if  test="${user.adminState == 0}" >未激活</c:if>
              <c:if  test="${user.adminState == 1}" >激活</c:if>
            </td>
            <td>
              <div class="layui-btn-group">
                <a href="${contextPath}/admin/userInfo/edit/route/${user.adminUserId}.action">
                  <button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i></button>
                </a>
                <a href="${contextPath}/admin/userInfo/delete/${user.adminUserId}.action">
                  <button class="layui-btn layui-btn-small layui-btn-danger"><i class="layui-icon">&#xe640;</i></button>
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
    $("#userInfo").attr("class", "layui-this");
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
