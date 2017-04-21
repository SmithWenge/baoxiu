<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/userInfo/list.action">用户信息</a>
              <a><cite>管理员用户信息修改</cite></a>
            </span>
    </legend>
    <div style="width: 30%; margin-top: 15px; ">
      <form action="${contextPath}/admin/userInfo/edit/do.action" method="post" class="layui-form">
        <input name="adminUserId" value="${adminUser.adminUserId}" type="hidden">
        <input name="userId" value="${adminUser.userId}" type="hidden">
        <div class="layui-form-item">
          <label class="layui-form-label">工号</label>
          <div class="layui-input-block">
            <input type="text" name="adminNumber" lay-verify="adminNumber" value="${adminUser.adminNumber}" autocomplete="off" class="layui-input" id="adminNumber">
            <input type="hidden" name="hiddenAdminNumber" lay-verify="hiddenAdminNumber" value="${adminUser.adminNumber}" autocomplete="off" class="layui-input" id="hiddenAdminNumber">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">姓名</label>
          <div class="layui-input-block">
            <input type="text" name="adminName" lay-verify="adminName" value="${adminUser.adminName}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">性别</label>
          <div class="layui-input-block" id="adminGender" lay-verify="adminGender">
            <input  name="adminGender"  value="1" title="男"  checked="" type="radio">
            <input   name="adminGender"  value="0" title="女" type="radio">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">邮箱</label>
          <div class="layui-input-block">
            <input type="text" name="adminEmail" lay-verify="adminEmail" value="${adminUser.adminEmail}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">身份证</label>
          <div class="layui-input-block">
            <input type="text" name="adminCard" lay-verify="adminCard" value="${adminUser.adminCard}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">登陆名</label>
          <div class="layui-input-block">
            <input type="text" name="username" lay-verify="username" value="${adminUser.username}" autocomplete="off" class="layui-input" id="username">
            <input type="hidden" name="hiddenUsername" lay-verify="hiddenUsername" value="${adminUser.username}" autocomplete="off" class="layui-input" id="hiddenUsername">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">电话</label>
          <div class="layui-input-block">
            <input type="text" name="adminTelephone" lay-verify="adminTelephone" value="${adminUser.adminTelephone}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">状态</label>
          <div class="layui-input-block" id="adminState"  lay-verify="adminState">
          <input  value=1 name="adminState" title="激活" checked="" type="radio">
          <input  value=0 name="adminState" title="未激活" type="radio">
        </div>
          </div>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addAdminUser">保存</button>
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

    // 表单验证
    var form = layui.form();

    form.verify({
      adminNumber: function (value) {
        if (value.length <2 || value.length>4) {
          return "请输入大于2小于4的编号";
        }

        if (!(/^[0-9]+$/.test(value))) {
          return "请填写数字序列";
        }

        var validateData = {
          "adminNumber": $("#adminNumber").val(),
          "hiddenAdminNumber": $("#hiddenAdminNumber").val()
        };
        var uniqueAdminNumber = false;

        $.ajax({
          type: 'post',
          async: false,
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          data: validateData,
          url: '${contextPath}/admin/userInfo/unique/adminNumber.action',
          success: function (result) {
            uniqueAdminNumber = result;
          }
        });

        if (!uniqueAdminNumber) {
          return "填写的工号已存在";
        }
      },
      username: function (value) {
        if (value.length <2 ) {
          return "请输入用户登陆名";
        }

        var validateData = {
          "username": $("#username").val(),
          "hiddenUsername": $("#hiddenUsername").val()
        };
        var uniqueUsername = false;

        $.ajax({
          type: 'post',
          async: false,
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          data: validateData,
          url: '${contextPath}/admin/userInfo/unique/username.action',
          success: function (result) {
            uniqueUsername = result;
          }
        });

        if (!uniqueUsername) {
          return "填写的用户名已存在";
        }
      },
      adminName: function (value) {
        if (value.length < 2) {
          return "请输入用户名";
        }

        if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
          return "请输入中文";
        }
      },
      adminEmail: function (value) {
        if (!(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(value))) {
          return "请输入合法邮箱";
        }
      },
      adminCard: function (value) {
        if (!(/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value))) {
          return "请输入合法身份证号";
        }
      },
      adminTelephone: function (value) {
        if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(value))) {
          return "请合法手机号";
        }
      }
    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>

