<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/set/index.action">设备组管理</a>
              <a><cite>设备组添加</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/set/add/do.action" method="post" class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">设备组名</label>
                    <div class="layui-input-block">
                        <input type="text" name="setName" lay-verify="setName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">设备编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="setNumber" id="setNumber" lay-verify="setNumber" placeholder="请输入" autocomplete="off" class="layui-input">
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
    $(function () {
        // 导航栏选择
        $("#two").attr("class", "layui-nav-item layui-nav-itemed");
        $("#set").attr("class", "layui-this");

        // 表单验证
        var form = layui.form();

        form.verify({
            setName: function (value) {
                if (value.length < 1) {
                    return "请输入设备组名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            },
            setNumber: function (value) {
                if (value.length < 2) {
                    return "请输入合法长度,最小为2";
                }

                if (!(/^[0-9]+$/.test(value))) {
                    return "请输入数字序列";
                }

                var validateData = { "setNumber": $("#setNumber").val() };
                var uniqueSetNumber = false;

                $.ajax({
                    type: 'post',
                    async: false,
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    data: validateData,
                    url: '${contextPath}/admin/set/unique/setNumber.action',
                    success: function (result) {
                        uniqueSetNumber = result;
                    }
                });

                if (!uniqueSetNumber) {
                    return "填写的设备组编号已存在";
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>