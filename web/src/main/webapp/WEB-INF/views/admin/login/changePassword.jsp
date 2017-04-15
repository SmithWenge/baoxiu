<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <div style="width: 30%; margin-top: 15px; ">
      <form action="${contextPath}/login/changePassword/do.action" method="post" class="layui-form">
        <input type="hidden" name="userId" value="${adminUser.userId}">
        <div class="layui-form-item">
          <label class="layui-form-label">登录名</label>
          <div class="layui-input-block">
            <input type="text" name="username" lay-verify="username" value="${adminUser.username}" readonly="true"  autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">新密码</label>
          <div class="layui-input-block">
            <input type="text" name="password" lay-verify="password" placeholder="请输入密码"   autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">确认密码</label>
          <div class="layui-input-block">
            <input type="text"  lay-verify="againPassword" id="againPassword" placeholder="确认密码"  autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceBuilding">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
          </div>
        </div>
      </form>
    </div>
  </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
  $(function () {
    var againPassword;
    var form = layui.form();
    form.verify({
      password: function (value) {

        againPassword = value
      },
      againPassword: function (value) {
        if (value != againPassword){
          return "两次输入不相同"
        }
      }

    });
  });
</script>


<%@ include file="/WEB-INF/include/footer.jsp"%>
