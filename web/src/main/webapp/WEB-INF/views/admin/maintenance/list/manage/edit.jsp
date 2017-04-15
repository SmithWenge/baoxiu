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
    <div style="width: 80%; margin-top: 15px;">
      <form action="${contextPath}/admin/maintenance/list/manage/edit/do.action" method="post" class="layui-form" id="editForm">
        <div class="layui-form-item">
          <label class="layui-form-label">报修图片</label>
          <div class="layui-input-block">
            <img src="${contextPath}/static/maintencePictures/${list.listPicture}" style="width: 20%; margin-top: 15px; ">
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">报修单编号</label>
          <div class="layui-input-block">
            <input type="text" value="${list.listNumber}" name="listNumber" class="layui-input" readonly>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">报修人电话</label>
          <div class="layui-input-block">
            <input type="text" value="${list.userTel}" name="userTel" class="layui-input" readonly>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">维修小组</label>
          <div class="layui-input-block">
            <select name="repairGroupId" lay-filter="aihao" id="repairGroupId">
              <c:forEach items="${groups}" var="group">
                <c:if test="${equipment.repairGroupId eq group.repairGroupId}">
                  <option value="${group.repairGroupId}" selected>${group.groupName}</option>
                </c:if>
                <c:if test="${equipment.repairGroupId ne group.repairGroupId}">
                  <option value="${group.repairGroupId}">${group.groupName}</option>
                </c:if>
              </c:forEach>
            </select>
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
            <select name="distinctId" id="distinctId" lay-filter="distinctId">
              <c:forEach items="${distincts}" var="distinct">
                <c:if test="${distinct.distinctId eq equipment.distinctId}">
                  <option value="${distinct.distinctId}" selected>${distinct.distinctName}</option>
                </c:if>
                <c:if test="${distinct.distinctId ne equipment.distinctId}">
                  <option value="${distinct.distinctId}">${distinct.distinctName}</option>
                </c:if>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">地点</label>
          <div class="layui-input-block">
            <select name="buildingId" id="buildingId" lay-filter="buildingId">
              <option value="${list.buildingId}">${list.buildingName}</option>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">位置</label>
          <div class="layui-input-block">
            <select name="roomId" id="roomId" lay-filter="roomId">
              <option value="${list.roomId}">${list.roomName}</option>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">设备</label>
          <div class="layui-input-block">
            <select name="equipmentId" id="equipmentId" lay-filter="equipmentId">
              <option value="${list.equipmentId}">${list.equipmentName}</option>
            </select>
          </div>
        </div>
        <div class="layui-form-item">
          <label class="layui-form-label">描述</label>
          <div class="layui-input-block">
            <textarea class="layui-textarea" name="listDescription">${list.listDescription}</textarea>
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
    $("#five").attr("class", "layui-nav-item layui-nav-itemed");
    $("#maintenanceList").attr("class", "layui-this");

    // 加载设备信息
    function loadEquipmentData(result) {
      var $ = layui.jquery;
      var $form = $('#editForm');
      var form = layui.form();

      var equipments = result.equipments;
      var optionsValue = '<option value="-100">请选择位置</option>';

      for (var i = 0; i < equipments.length; i++) {
        optionsValue += '<option value="' + equipments[i].equipmentId + '">' + equipments[i].equipmentName + '</option>';
      }

      $form.find('select[id=equipmentId]').empty();
      $form.find('select[id=equipmentId]').append(optionsValue);
      form.render();
    }

    // 加载位置信息
    function loadRoomData(result) {
      var $ = layui.jquery;
      var $form = $('#editForm');
      var form = layui.form();

      var rooms = result.rooms;
      var optionsValue = '<option value="-100">请选择位置</option>';

      for (var i = 0; i < rooms.length; i++) {
        optionsValue += '<option value="' + rooms[i].roomId + '">' + rooms[i].roomName + '</option>';
      }

      $form.find('select[id=roomId]').empty();
      $form.find('select[id=roomId]').append(optionsValue);
      form.render();

      form.on('select(roomId)', function(data) {
        var postData = {
          "roomId": data.value
        };

        $.ajax({
          type: 'post',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          url: '${contextPath}/admin/maintenance/list/manage/sets.action',
          data: postData,
          success: function (result) {
            loadEquipmentData(result);
          }
        });
      });
    }

    // 加载地点数据
    function loadBuildingData(result) {
      var $ = layui.jquery;
      var $form = $('#editForm');
      var form = layui.form();

      var buildings = result.buildings;
      var optionsValue = '<option value="-100">请选择地点</option>';

      for (var i = 0; i < buildings.length; i++) {
        optionsValue += '<option value="' + buildings[i].buildingId + '">' + buildings[i].buildingName + '</option>';
      }

      $form.find('select[id=buildingId]').empty();
      $form.find('select[id=buildingId]').append(optionsValue);
      form.render();

      form.on('select(buildingId)', function(data) {
        var postData = {
          "buildingId": data.value
        };

        $.ajax({
          type: 'post',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          url: '${contextPath}/admin/equipment/building/rooms.action',
          data: postData,
          success: function (result) {
            loadRoomData(result);
          }
        });
      });
    }

    // 加载校区数据
    function loadDistinctData() {
      var $ = layui.jquery;
      var $form = $('#editForm');
      var form = layui.form();

      form.on('select(distinctId)', function(data) {
        var postData = {
          "distinctId": data.value
        };

        $.ajax({
          type: 'post',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          url: '${contextPath}/admin/equipment/distinct/buildings.action',
          data: postData,
          success: function (result) {
            loadBuildingData(result);
          }
        });
      });
    }

    loadDistinctData();



  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>