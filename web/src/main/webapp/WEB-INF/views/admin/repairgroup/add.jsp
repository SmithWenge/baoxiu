
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/repairgroup/list.action">维修小组管理</a>
              <a><cite>维修小组添加</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/admin/repairgroup/add/do.action" method="post" class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">小组编号</label>
                    <div class="layui-input-block">
                        <input type="text" name="groupNumber" lay-verify="groupNumber" placeholder="请输入编号" class="layui-input" id="groupNumber">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">小组名</label>
                    <div class="layui-input-block">
                        <input type="text" name="groupName" lay-verify="groupName" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">打印机编号</label>
                    <div class="layui-input-block">
                        <select name="groupPrinterIp" id="groupPrinterIp" lay-verify="groupPrinterIp">
                            <option value="">无</option>
                            <c:forEach items="${printers}" var="printers">
                                <option  value="${printers.printerZHCNName}" >${printers.printerZHCNName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addRepairGroup">保存</button>
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
        $("#first").attr("class", "layui-nav-item layui-nav-itemed");
        $("#repairGroup").attr("class", "layui-this");

        // 表单验证
        var form = layui.form();

        form.verify({
            groupNumber: function(value) {
                if(value.length > 3 || value.length < 1) {
                    return "维修小组编号的长度为1到3";
                }

                if (!(/^[0-9]+$/.test(value))) {
                    return "请填写数字序列";
                }

                var validateData = { "groupNumber": $("#groupNumber").val() };
                var uniqueGroupNumber = false;

                $.ajax({
                    type: 'post',
                    async: false,
                    contentType: 'application/x-www-form-urlencoded',
                    dataType: 'json',
                    data: validateData,
                    url: '${contextPath}/admin/repairgroup/unique/groupNumber.action',
                    success: function (result) {
                        uniqueGroupNumber = result;
                    }
                });

                if (!uniqueGroupNumber) {
                    return "填写的维修小组编号已存在";
                }
            },
            groupName: function (value) {
                if (value.length < 2) {
                    return "请输入维修小组名";
                }

                if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
                    return "请输入中文";
                }
            }
        });
    });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>