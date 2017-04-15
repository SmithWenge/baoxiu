<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>报修单管理</legend>
    <div class="layui-form" id="maintenanceListQueryForm">
      <div class="layui-form-item elementAddAndQueryDiv">
        <div class="layui-input-inline" style="width:130px;">
          <select name="distinctId" id="distinctId" lay-filter="distinctId">
            <option value="">请选择校区</option>
          </select>
        </div>
        <div class="layui-input-inline" style="width:130px;">
          <select name="buildingId" id="buildingId" lay-filter="buildingId">
            <option value="">请选择地点</option>
          </select>
        </div>
        <div class="layui-input-inline" style="width:130px;">
          <select name="roomId" id="roomId" lay-filter="roomId">
            <option value="">请选择位置</option>
          </select>
        </div>
        <div class="layui-input-inline" style="width:130px;">
          <select name="equipmentId" id="equipmentId" lay-filter="equipmentId">
            <option value="">请选择设备</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <div class="layui-input-block queryDivBtn">
            <button class="layui-btn layui-btn-normal" id="querymaintenanceListBtn">查询</button>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-form" id="maintenanceListStateQueryForm">
      <div class="layui-form-item elementAddAndQueryDiv">
        <div class="layui-input-inline" style="width:130px;">
          <select name="listState" id="listState" lay-filter="listState">
            <option value=-1>请选择状态</option>
            <option value=1>已提交</option>
            <option value=2>已派单</option>
            <option value=3>延期</option>
            <option value=4>等待派单</option>
            <option value=5>正在备件</option>
            <option value=6>已催单</option>
            <option value=7>已评价</option>
            <option value=8>待评价</option>
            <option value=9>其他</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <div class="layui-input-block queryDivBtn">
            <button class="layui-btn layui-btn-normal" id="querymaintenanceListStateBtn">查询</button>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-form" id="maintenanceListGroupQueryForm">
      <div class="layui-form-item elementAddAndQueryDiv">
        <div class="layui-input-inline" style="width:130px;">
          <select name="repairGroupId" id="repairGroupId" lay-filter="repairGroupId">
            <option value="">请选择维修组</option>
          </select>
        </div>
        <div class="layui-input-inline">
          <div class="layui-input-block queryDivBtn">
            <button class="layui-btn layui-btn-normal" id="querymaintenanceGroupStateBtn">查询</button>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-form" id="maintenanceListDataQueryForm">
      <div class="layui-form-item elementAddAndQueryDiv">
        <div class="layui-form-pane" style="margin-top: 15px;">
          <div class="layui-form-item">
            <label class="layui-form-label">报修时间范围选择</label>
            <div class="layui-input-inline">
              <input class="layui-input" placeholder="开始日" name="startListTime" id="LAY_demorange_s">
            </div>
            <div class="layui-input-inline">
              <input class="layui-input" placeholder="截止日" name="stopListTime" id="LAY_demorange_e">
            </div>
            <div class="layui-input-inline">
              <button class="layui-btn layui-btn-normal" id="maintanceListDateBtn">查询</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-field-box">
      <table class="layui-table">
        <thead>
        <tr>
          <td>序号</td>
          <td>报修单号</td>
          <td>报修单状态</td>
          <td>设备名</td>
          <td>维修小组</td>
          <td>报修时间</td>
          <td>操作维修状态</td>
          <td>最新状态时间</td>
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
      "repairGroupId": '',
      "stopListTime": '',
      "startListTime": ''
    };

    // 查询数据分页显示
    $("#querymaintenanceListBtn").click(function () {
      loadPageData();
    });
    $("#querymaintenanceListStateBtn").click(function () {
      loadPageData();
    });
    $("#querymaintenanceGroupStateBtn").click(function () {
      loadPageData();
    });
    $("#maintanceListDateBtn").click(function () {
      condition.startListTime = $("#LAY_demorange_s").val();
      condition.stopListTime = $("#LAY_demorange_e").val();
      loadPageData();
    });

    // 加载状态信息
    function loadStateData() {
      var form = layui.form();

      form.on('select(listState)', function(data) {
        condition.listState = data.value;
      });
    }

    // 加载维修组信息
    function loadGroupData(result) {
      var $ = layui.jquery;
      var $form = $('#maintenanceListGroupQueryForm');
      var form = layui.form();

      var optionsData = result.groups;
      var optionsValue = '<option value="">请选择维修组</option>';

      for (var i = 0; i < optionsData.length; i++) {
        optionsValue += '<option value="' + optionsData[i].repairGroupId + '">' + optionsData[i].groupName + '</option>';
      }

      $form.find('select[id=repairGroupId]').empty();
      $form.find('select[id=repairGroupId]').append(optionsValue);
      form.render();

      form.on('select(repairGroupId)', function(data) {
        condition.repairGroupId = data.value;
      });
    }

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

    // 拼接操作字符转
    function createOpsBtnGroup(listNumber) {
      return '<div class="layui-btn-group">' +
              '<a href="${contextPath}/admin/maintenance/list/manage/status/dispatch/' + listNumber +'.action">' +
              ' <button class="layui-btn layui-btn-normal" >派单</button>&#xe642;</i>' +
              '</button> </a><a href="${contextPath}/admin/maintenance/list/manage/status/done/' + listNumber + '.action">' +
              ' <button class="layui-btn layui-btn-normal" >完成</button>&#xe640;</i></button>' +
              '</a></div>'

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
        "repairGroupId": condition.repairGroupId,
        "stopListTime": condition.stopListTime,
        "startListTime": condition.startListTime,
        "liststateStr": condition.listState
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
          loadGroupData(result);
          loadStateData();

          $.each(result.page.content, function (i, item) {

            var trData = "<tr><td>" + (i + 1) + "</td><td><a href=\"${contextPath}/admin/maintenance/list/manage/details/route/" + item.listNumber + ".action\">" + item.listNumber + "</a></td><td>" + item.liststateStr ;
            trData += "<td>" + item.equipmentName + "</td><td>" + item.groupName + "</td><td>"  + item.listTime + "</td>"+ "</td>"+"</td><td>" + createOpsBtnGroup(item.listNumber) + "</td>";

            if(item.equipmentName == null) {
              var maBtn = createOpsBtnGroup(item.listNumber);
            } else {
              var maBtn = "";
            }

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
        "repairGroupId": condition.repairGroupId,
        "stopListTime": condition.stopListTime,
        "startListTime": condition.startListTime,
        "liststateStr": condition.listState
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


    //日期选择框设置
    layui.use('laydate', function(){
      var laydate = layui.laydate;

      var start = {
        min: '2000-01-01 00:00:00'
        ,max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
          end.min = datas; //开始日选好后，重置结束日的最小日期
          end.start = datas //将结束日的初始值设定为开始日
        }
      };

      var end = {
        min: '2000-01-01 00:00:00'
        ,max: '2099-06-16 23:59:59'
        ,istoday: false
        ,choose: function(datas){
          start.max = datas; //结束日选好后，重置开始日的最大日期
        }
      };

      document.getElementById('LAY_demorange_s').onclick = function(){
        start.elem = this;
        laydate(start);
      }
      document.getElementById('LAY_demorange_e').onclick = function(){
        end.elem = this
        laydate(end);
      }

    });
  });
</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>