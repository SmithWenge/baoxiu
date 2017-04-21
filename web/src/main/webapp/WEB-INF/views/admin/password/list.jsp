<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>管理员用户信息管理</legend>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>工号</td>
          <td>姓名</td>
          <td>登陆名</td>
          <td>密码修改</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${user}" var="user" varStatus="status">
          <td>${status.index + 1}</td>
          <td>${user.adminNumber}</td>
          <td>${user.adminName}</td>
          <td>${user.username}</td>
          <td>
            <div class="layui-btn-group">
              <a href="${contextPath}/admin/userInfo/changePassword/edit/route/${user.adminUserId}.action">
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
    $("#password").attr("class", "layui-this");

    // 删除提示弹出提示
    function deleteConfirm($deleteBtn) {
      event.preventDefault();

      layer.confirm('确定要删除 ?', {
        btn: ['确定', '取消']
      }, function(index, layero) {
        layer.closeAll('dialog');
        $deleteBtn.unbind('click');
        $deleteBtn.trigger('click');
      }, function(index) {

      });
    }

    $(":button.layui-btn-danger").click(function () {
      var $deleteBtn = $(this);
      deleteConfirm($deleteBtn);
    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>

