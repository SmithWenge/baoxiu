<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>报修单管理</legend>
    <div class="layui-form" id="maintenanceListQueryForm">
      <div class="layui-form-item elementAddAndQueryDiv">
        <div class="layui-input-inline">
          <select name="distinctId" id="distinctId" lay-filter="distinctId">
            <option value="">请选择校区</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <select name="buildingId" id="buildingId" lay-filter="buildingId">
            <option value="">请选择地点</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <select name="roomId" id="roomId" lay-filter="roomId">
            <option value="">请选择位置</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <select name="equipmentId" id="equipmentId" lay-filter="equipmentId">
            <option value="">请选择设备</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <div class="layui-input-block queryDivBtn">
            <button class="layui-btn layui-btn-normal" id="querymaintenanceListBtn">查询</button>
          </div>
        </div>
        <div class="layui-input-inline addBtnFloatRight">
          <div class="layui-input-block">
            <a href="${contextPath}/admin/equipment/add/route.action">
              <button class="layui-btn layui-btn-normal" id="addPlaceDistinct">添加</button>
            </a>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>设备名</td>
          <td>设备编号</td>
          <td>维修小组</td>
          <td>小组编号</td>
          <td>操作</td>
        </tr>
        </thead>
        <tbody id="pageTableBody">
        </tbody>
      </table>
    </div>
    <div class="layui-field-box">
      <div id="pageNav"></div>
    </div>
  </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
  $(function () {
    // 导航栏选择
    $("#five").attr("class", "layui-nav-item layui-nav-itemed");
    $("#maintenanceList").attr("class", "layui-this");

    var condition = {
      "roomId": '',
      "buildingId": '',
      "equipmentId":'',
      "listState": '',
      "repairGroupId": ''
    };

    // 查询数据分页显示
    $("#querymaintenanceListBtn").click(function () {
      loadPageData();
    });

    // 加载设备信息
    function loadEquipmentData(result) {
      var $ = layui.jquery;
      var $form = $('#maintenanceListQueryForm');
      var form = layui.form();

      var equipments = result.equipments;
      var optionsValue = '<option value="">请选择设备</option>';

      for (var i = 0; i < equipments.length; i++) {
        optionsValue += '<option value="' + equipments[i].equipmentId + '">' + equipments[i].equipmentName + '</option>';
      }

      $form.find('select[id=equipmentId]').empty();
      $form.find('select[id=equipmentId]').append(optionsValue);
      form.render();

      form.on('select(equipmentId)', function(data) {
        condition.equipmentId = data.value;
      });
    }

    // 加载位置信息
    function loadRoomData(result) {
      var $ = layui.jquery;
      var $form = $('#maintenanceListQueryForm');
      var form = layui.form();

      var rooms = result.rooms;
      var optionsValue = '<option value="">请选择位置</option>';

      for (var i = 0; i < rooms.length; i++) {
        optionsValue += '<option value="' + rooms[i].roomId + '">' + rooms[i].roomName + '</option>';
      }

      $form.find('select[id=roomId]').empty();
      $form.find('select[id=roomId]').append(optionsValue);
      form.render();

      form.on('select(roomId)', function(data) {
        var postData = {
          "roomId": data.value,
          "buildingId": condition.buildingId
        };

        // 位置条件
        condition.roomId = data.value;

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
      var $form = $('#maintenanceListQueryForm');
      var form = layui.form();

      var buildings = result.buildings;
      var optionsValue = '<option value="">请选择地点</option>';

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

        // 地点条件
        condition.buildingId = data.value;

        $.ajax({
          type: 'post',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          url: '${contextPath}/admin/maintenance/list/manage/rooms.action',
          data: postData,
          success: function (result) {
            loadRoomData(result);
          }
        });
      });
    }

    // 加载校区数据
    function loadDistinctData(result) {
      var $ = layui.jquery;
      var $form = $('#maintenanceListQueryForm');
      var form = layui.form();

      var distincts = result.distincts;

      var optionsValue = '<option value="">请选择校区</option>';
      for (var i = 0; i < distincts.length; i++) {
        optionsValue += '<option value="' + distincts[i].distinctId + '">' + distincts[i].distinctName + '</option>';
      }

      $form.find('select[id=distinctId]').empty();
      $form.find('select[id=distinctId]').append(optionsValue);
      form.render();

      form.on('select(distinctId)', function(data) {
        var postData = {
          "distinctId": data.value
        };

        $.ajax({
          type: 'post',
          contentType: 'application/x-www-form-urlencoded',
          dataType: 'json',
          url: '${contextPath}/admin/maintenance/list/manage/buildings.action',
          data: postData,
          success: function (result) {
            loadBuildingData(result);
          }
        });
      });
    }

    // 分页
    var laypage = layui.laypage;

    // 跳转不同分页数据渲染
    function jumpPage(curr) {
      if (curr <= 0) curr = 1;

      var pageData = {
        "page": curr - 1,
        "buildingId": condition.buildingId,
        "roomId": condition.roomId,
        "equipmentId": condition.equipmentId,
        "repairGroupId": condition.repairGroupId
      };
      $.ajax({
        type: 'post',
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        url: '${contextPath}/admin/maintenance/list/manage/route/page.action',
        data: pageData,
        success: function (result) {
          $("#pageTableBody").empty();

          loadDistinctData(result);

          $.each(result.page.content, function (i, item) {
            var trData = "<tr><td>" + (i + 1) + "</td><td>" + item.listNumber + "</td><td>" + item.listState + "</td>";
            trData += "<td>" + item.equipmentName + "</td><td>" + item.groupName + "</td><td>  + item.listTime + </td><td>";
            $("#pageTableBody").append(trData);
          });
        }
      });
    }

    // 初始化页面加载数据
    function loadPageData() {
      var pageData = {
        "page": 0,
        "buildingId": condition.buildingId,
        "roomId": condition.roomId,
        "equipmentId": condition.equipmentId,
        "repairGroupId": condition.repairGroupId
      };
      $.ajax({
        type: 'post',
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        url: '${contextPath}/admin/maintenance/list/manage/route/page.action',
        data: pageData,
        success: function (result) {
          laypage({
            cont: 'pageNav',
            pages: result.page.totalPages,
            skin: '#1E9FFF',
            prev: '<em><</em>',
            next: '<em>></em>',
            groups: 10,
            first: 1,
            last: result.page.totalPages,
            jump: function (obj) {
              jumpPage(obj.curr);
            }
          });
        }
      });
    }

    loadPageData();
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>