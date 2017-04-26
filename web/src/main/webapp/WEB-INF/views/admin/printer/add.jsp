<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/printer/list.action">打印机管理</a>
              <a><cite>打印机添加</cite></a>
            </span>
    </legend>
    <div style="width: 30%; margin-top: 15px; ">
      <form action="${contextPath}/admin/printer/add/do.action" method="post" class="layui-form">
        <div class="layui-form-item">
          <label class="layui-form-label">打印机描述</label>
          <div class="layui-input-block">
            <input type="text" name="printerZHCNName" lay-verify="printerZHCNName" placeholder="请输入" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">打印机编号</label>
          <div class="layui-input-block">
            <input type="text" name="printerNumber" lay-verify="printerNumber" placeholder="请输入" autocomplete="off" class="layui-input" id="printerNumber">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">打印机IP</label>
          <div class="layui-input-block">
            <input type="text" name="printIp" lay-verify="printIp" placeholder="请输入" class="layui-input" id="printIp">
          </div>
        </div>
        <div class="layui-form-item">
          <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">新建</button>
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
    $("#printer").attr("class", "layui-this");

    // 表单验证
    var form = layui.form();

    form.verify({
      printerZHCNName: function (value) {
        if (value.length < 2) {
          return "请输入打印机描述";
        }

        if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
          return "请输入中文";
        }
      },
      printerNumber: function (value) {
        if (value.length < 2 || value.length > 4) {
          return "打印机编号大于2小于4";
        }

        if (!(/^[0-9]+$/.test(value))) {
          return "请填写数字序列";
        }
        var validateData = { "printerNumber": $("#printerNumber").val() };
        var uniquePrinterNumber = false;

        $.ajax({
          type: 'post',
          async: false,
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          data: validateData,
          url: '${contextPath}/admin/printer/unique/printerNumber.action',
          success: function (result) {
            uniquePrinterNumber = result;
          }
        });

        if (!uniquePrinterNumber) {
          return "打印机编号已存在";
        }
      },
      printIp: function (value) {
        var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        var reg=value.match(exp);
        if(reg==null)
        {
          return "请输入合法Ip";
        }


        var validateData = { "printIp": $("#printIp").val() };
        var uniquePrintIp = false;

        $.ajax({
          type: 'post',
          async: false,
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          data: validateData,
          url: '${contextPath}/admin/printer/unique/printIp.action',
          success: function (result) {
            uniquePrintIp = result;
          }
        });

        if (!uniquePrintIp) {
          return "打印机Ip已存在";
        }

      }

    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>