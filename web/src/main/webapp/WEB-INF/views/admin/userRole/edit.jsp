<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/userInfo/userRole/list.action">用户权限管理</a>
              <a><cite>用户角色编辑</cite></a>
            </span>
    </legend>
    <div style="width: 98%; margin-top: 15px;">
      <form action="${contextPath}/admin/userInfo/userRole/save.action" method="post" class="layui-form" id="roleAddForm">
        <input type="hidden" value="${user.userId}" name="userId">
        <div class="fenduan" style="margin-left: 5%;">
          <div class="form-group">
            <label class="layui-form-label">用户已有的角色</label>
          </div>
          <c:if test="${exitUsers.size() > 0}">
            <div class="layui-form-item">
              <div class="layui-input-block">
                <c:forEach items="${exitUsers}" var="exitUser">
                  <input type="checkbox" name="editRoles" value="${exitUser.roleId}" title="${exitUser.roleName}" checked>
                </c:forEach>
              </div>
            </div>
          </c:if>
          <c:if test="${exitUsers.size() == 0}">
            <div class="layui-form-item">
              <div class="layui-input-block">
                <label class="layui-form-label">暂无勾选角色</label>
              </div>
            </div>
          </c:if>
          <div class="form-group">
            <label class="layui-form-label">用户没有的角色</label>
          </div>
          <c:if test="${users.size() > 0}">
            <div class="layui-form-item">
              <div class="layui-input-block">
                <c:forEach items="${users}" var="user">
                  <input type="checkbox" name="editRoles" value="${user.roleId}" title="${user.roleName}">
                </c:forEach>
              </div>
            </div>
          </c:if>
          <c:if test="${users.size() == 0}">
            <div class="layui-form-item">
              <div class="layui-input-block">
                <label class="layui-form-label">用户已有全部角色</label>
              </div>
            </div>
          </c:if>
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
    //导航栏选择
    $("#six").attr("class", "layui-nav-item layui-nav-itemed");
    $("#userRole").attr("class", "layui-this");
  });
</script>

<%@include file="/WEB-INF/include/footer.jsp"%>