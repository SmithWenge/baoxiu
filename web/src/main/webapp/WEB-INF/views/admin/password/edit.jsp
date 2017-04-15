<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/userInfo/changePassword/list.action">用户信息</a>
              <a><cite>管理员用户密码修改</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/userInfo/changePassword/edit/do.action" method="post" class="layui-form">
                <input name="userId" value="${adminUser.userId}" type="hidden">
                <div class="layui-form-item">
                    <label class="layui-form-label">登陆名</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" lay-verify="username" value="${adminUser.username}" autocomplete="off" class="layui-input" readonly="true" id="username">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-block">
                        <input type="text" name="password" lay-verify="password" placeholder="新密码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-block">
                        <input type="text" lay-verify="againPassword" placeholder="确认密码" autocomplete="off" class="layui-input">
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
