<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/maintenance/list/manage/index.action">报修单管理</a>
              <a><cite>重新派单</cite></a>
            </span>
    </legend>
    <div style="width: 80%; margin-top: 15px;">
      <form action="${contextPath}/admin/maintenance/list/manage/reSend/do.action" method="post" onsubmit="return checkSubmit();">
        <div class="layui-form-item">
          <label class="layui-form-label">报修单编号</label>
          <div class="layui-input-block">
            <input type="text" value="${list.listNumber}" name="listNumber" class="layui-input" readonly>
          </div>
        </div>


        <div class="layui-form-item">
          <label class="layui-form-label">维修小组</label>
          <div class="layui-input-block">
            <select name="repairGroupId" id="repairGroupId" class="layui-select">
              <option value="-1">请选择维修小组</option>
             <c:forEach items="${repairGroups}" var="repairGroup">
               <option value="${repairGroup.repairGroupId}">${repairGroup.groupName}</option>
               </c:forEach>
            </select>
          </div>
        </div>

        <div class="layui-form-item">
          <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">派单</button>
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
    $("#five").attr("class", "layui-nav-item layui-nav-itemed");
    $("#maintenanceList").attr("class", "layui-this");
  });
</script>
<script type="text/javascript">
  function checkSubmit(){
    var value = document.getElementById("repairGroupId").value;
    if(value == -1){
      alert("请选择维修小组!");
      return false;
    }else{
      return true;
    }
  }
</script>
<%@ include file="/WEB-INF/include/footer.jsp"%>
