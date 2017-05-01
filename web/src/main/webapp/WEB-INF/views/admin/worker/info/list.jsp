<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>工人信息管理</legend>
    <a href="${contextPath}/admin/worker/info/add/route.action">
      <button class="layui-btn layui-btn-normal elementAddBtn" id="addWorkerInfo">添加</button>
    </a>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>姓名</td>
          <td>电话</td>
          <td>单位</td>
          <td>部门</td>
          <td>职称</td>
          <td>维修小组名</td>
          <td>工人状态</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${workerInfos}" var="workerInfo" varStatus="status">
          <tr>
            <td>${status.index + 1}</td>
            <td>${workerInfo.workerName}</td>
            <td>${workerInfo.workerTel}</td>
            <td>${workerInfo.workerUnite}</td>
            <td>${workerInfo.typeName}</td>
            <td>${workerInfo.workerJob}</td>
            <td>${workerInfo.groupName}</td>
            <td>

              <c:if test="${workerInfo.workerState == 1}" >在职</c:if>
              <c:if test="${workerInfo.workerState == 2}" >离职</c:if>
              <c:if test="${workerInfo.workerState == 3}" >休假</c:if>
              <c:if test="${workerInfo.workerState == 4}" >其他</c:if>

            </td>
            <td>
              <div class="layui-btn-group">
                <a href="${contextPath}/admin/worker/info/edit/route/${workerInfo.userId}.action">
                  <button class="layui-btn layui-btn-small layui-btn-warm"><i class="layui-icon">&#xe642;</i></button>
                </a>
                <a href="${contextPath}/admin/worker/info/delete/${workerInfo.userId}.action">
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
    $("#three").attr("class", "layui-nav-item layui-nav-itemed");
    $("#workerInfo").attr("class", "layui-this");

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