<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/place/distinct/list.action">校区管理</a>
              <a><cite>校区修改</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/place/distinct/edit/do.action" method="post" class="layui-form">
                <input type="hidden" name="distinctId" value="${distinct.distinctId}">
                <!--
                <div class="layui-form-item">
                    <label class="layui-form-label">校区编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="distinctNumber" lay-verify="distinctNumber" value="${distinct.distinctNumber}" class="layui-input" id="distinctNumber">
                        <input type="hidden" name="hiddenDistinctNumber" value="${distinct.distinctNumber}" class="layui-input" id="hiddenDistinctNumber">
                    </div>
                </div>
                -->
                <div class="layui-form-item">
                    <label class="layui-form-label">校区名</label>
                    <div class="layui-input-block">
                        <input type="text" name="distinctName" lay-verify="distinctName" value="${distinct.distinctName}" autocomplete="off" class="layui-input">
                    </div>
                </div>
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

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    /**
     distinctNumber: function(value) {
            if(value.length > 4 || value.length < 2) {
                return "校区编号的长度为2到4";
            }

            if (!(/^[0-9]+$/.test(value))) {
                return "请填写数字序列";
            }

            var validateData = {
                "distinctNumber": $("#distinctNumber").val(),
                "hiddenDistinctNumber": $("#hiddenDistinctNumber").val()
            };
            var uniqueDistinctNumber = false;

            $.ajax({
                type: 'post',
                async: false,
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                data: validateData,
                url: '${contextPath}/admin/place/distinct/unique/distinctNumber.action',
                success: function (result) {
                    uniqueDistinctNumber = result;
                }
            });

            if (!uniqueDistinctNumber) {
                return "填写的校区编号已存在";
            }
        },
     */
    $(function () {
        // 导航栏选择
        $("#first").attr("class", "layui-nav-item layui-nav-itemed");
        $("#placeDistinct").attr("class", "layui-this");

        // 表单验证
        var form = layui.form();

        form.verify({

            distinctName: function (value) {
                if (value.length < 1) {
                    return "请输入校区名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>