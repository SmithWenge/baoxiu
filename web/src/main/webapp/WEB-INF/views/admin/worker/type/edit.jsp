<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/worker/type/list.action">工种管理</a>
              <a><cite>工种修改</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/worker/type/edit/do.action" method="post" class="layui-form">
                <input type="hidden" name="typeId" value="${type.typeId}">

                <div class="layui-form-item">
                    <label class="layui-form-label">工种名</label>
                    <div class="layui-input-block">
                        <input type="text" name="typeName" lay-verify="typeName" value="${type.typeName}" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addWorkerType">保存</button>
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
        var form = layui.form();

        form.verify({

            typeName: function (value) {
                if (value.length < 2) {
                    return "请输入工种名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
