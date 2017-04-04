<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>打印机管理</legend>
    <a href="${contextPath}/admin/printer/add/route.action">
      <button class="layui-btn layui-btn-normal elementAddBtn" id="addWorkerInfo">添加</button>
    </a>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>打印机描述</td>
          <td>打印机编号</td>
          <td>打印机IP</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${printers}" var="printers" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${printers.printerZHCNName}</td>
            <td>${printers.printerNumber}</td>
            <td>${printers.printIp}</td>
            <td>
              <div class="layui-btn-group">
                <a href="${contextPath}/admin/printer/edit/route/${printers.printerId}.action">
                  <button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i></button>
                </a>
                <a href="${contextPath}/admin/printer/delete/${printers.printerId}.action">
                  <button class="layui-btn layui-btn-small layui-btn-danger"><i class="layui-icon">&#xe640;</i></button>
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
    // 导航栏选择
    $("#two").attr("class", "layui-nav-item layui-nav-itemed");
    $("#printer").attr("class", "layui-this");

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
