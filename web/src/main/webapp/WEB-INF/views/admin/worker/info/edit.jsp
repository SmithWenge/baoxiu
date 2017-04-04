<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field">
    <legend>
            <span class="layui-breadcrumb">
              <a href="${contextPath}/admin/worker/info/list.action">工人管理</a>
              <a><cite>工人信息添加</cite></a>
            </span>
    </legend>
    <div style="width: 30%; margin-top: 15px; ">
      <form action="${contextPath}/admin/worker/info/edit/do.action" method="post" class="layui-form">
        <input type="hidden" value="${infos.userId}" name="userId">
        <div class="layui-form-item">
          <label class="layui-form-label">姓名</label>
          <div class="layui-input-block">
            <input type="text" value="${infos.workerName}" name="workerName" lay-verify="workerName" placeholder="${infos.workerName}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">单位</label>
          <div class="layui-input-block">
            <input type="text" name="workerUnite" lay-verify="workerUnite" placeholder="${infos.workerUnite}" autocomplete="off" class="layui-input">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">部门</label>
          <div class="layui-input-block">
            <select name="workerDepartment" lay-verify="workerDepartment" id="workerDepartment">
              <option value="${infos.workerDepartment}">不修改
              </option>
              <option value="1">才艺部</option>
              <option value="2">园艺部</option>
              <option value="3">工程部</option>
              <option value="4">设备部</option>
              <option value="5">其他</option>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">职称</label>
          <div class="layui-input-block">
            <input type="text" name="workerJob" lay-verify="workerJob" placeholder="${infos.workerJob}" class="layui-input" id="workerJob">
          </div>
        </div>

        <div class="layui-form-item">
          <label class="layui-form-label">组名</label>
          <div class="layui-input-block">
            <select name="repairGroupId" id="repairGroupId" lay-verify="repairGroupId">
              <option value="${infos.repairGroupId}">${infos.groupName}</option>
              <c:forEach items="${repairGroups}" var="repairGroups">
                <option  value="${repairGroups.repairGroupId}" >${repairGroups.groupName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">工种</label>
          <div class="layui-input-block">
            <select name="typeId" id="typeId" lay-verify="typeId">
              <option value="${infos.typeId}">${infos.typeName}</option>
              <c:forEach items="${workerTypes}" var="workerTypes">
                <option   value="${workerTypes.typeId}" >${workerTypes.typeName}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">状态</label>
          <div class="layui-input-block">
            <select name="workerState" id="workerState" lay-verify="workState">
              <option value="${infos.workerState}">不修改</option>
              <option  value="1" >在岗</option>
              <option  value="2" >离职</option>
              <option  value="3" >休假</option>
              <option  value="4" >其它</option>
            </select>
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

    // 表单验证
    var form = layui.form();

    form.verify({
      workerName: function (value) {
        if (value.length < 2) {
          return "请输入工人名字";
        }

        if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
          return "请输入中文";
        }
      },
      workerUnite: function (value) {
        if (value.length < 2) {
          return "请输入单位名";
        }

        if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
          return "请输入中文";
        }
      },
      workerJob: function (value) {
        if (value.length < 2) {
          return "请输入职称名";
        }

        if (!(/^[\u4e00-\u9fa5]+$/.test(value))) {
          return "请输入中文";
        }
      }

    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>
