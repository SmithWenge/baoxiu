<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/maintenance/list/manage/index.action">报修单管理</a>
              <a><cite>查看详情</cite></a>
            </span>
    </legend>
    <div style="width: 80%; margin-top: 15px; ">
      <div class="layui-form-item">
        <label class="layui-form-label">报修图片</label>
        <div class="layui-input-block">
          <img src="${contextPath}/static/maintencePictures/${list.listPicture}" style="width: 20%; margin-top: 15px; ">
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">报修单编号</label>
        <div class="layui-input-block">
          <input type="text" value="${list.listNumber}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">报修人电话</label>
        <div class="layui-input-block">
          <input type="text" value="${list.userTel}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">维修小组</label>
        <div class="layui-input-block">
          <input type="text" value="${list.groupName}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">当前状态</label>
        <div class="layui-input-block">
          <input type="text" value="${list.liststateStr}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">校区</label>
        <div class="layui-input-block">
          <input type="text" value="${list.distinctName}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">地点</label>
        <div class="layui-input-block">
          <input type="text" value="${list.buildingName}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">位置</label>
        <div class="layui-input-block">
          <input type="text" value="${list.roomName}" class="layui-input" readonly>
        </div>
      </div>
      <div class="layui-form-item">
        <label class="layui-form-label">设备名</label>
        <div class="layui-input-block">
          <input type="text" value="${list.equipmentName}" class="layui-input" readonly>
        </div>
      </div>
      <div class="form-group">
        <label class="layui-form-label">状态变更表</label>
      </div>
      <table class="layui-table" style="margin-left: 110px; width: 88%">
        <colgroup>
          <col width="200">
          <col>
        </colgroup>
        <thead>
        <tr>
          <th>报修单状态</th>
          <th>更改时间</th>
          <th>描述</th>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${list.lists}" var="model">
            <tr>
              <td>${model.liststateStr}</td>
              <td>${model.liststatetime}</td>
              <td>${model.listDescription}</td>
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
    $("#five").attr("class", "layui-nav-item layui-nav-itemed");
    $("#maintenanceList").attr("class", "layui-this");
  });
</script>


<%@ include file="/WEB-INF/include/footer.jsp"%>