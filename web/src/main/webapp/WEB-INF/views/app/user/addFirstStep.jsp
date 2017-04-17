<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Report</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/report/styles.css" type="text/css" rel="stylesheet"/>
  <style type="text/css">
    @font-face {
      font-family: 'Iaxurefont';
      src: url("http://at.alicdn.com/t/font_1463732837_010477.eot");
      src: url("http://at.alicdn.com/t/font_1463732837_010477.eot?#iefix") format('embedded-opentype'),
      url("http://at.alicdn.com/t/font_1463732837_010477.woff") format('woff'),
      url("http://at.alicdn.com/t/font_1463732837_010477.ttf") format('truetype'),
      url("http://at.alicdn.com/t/font_1463732837_010477.svg#Iaxurefont") format('svg');
      }
  </style>
  <%--<script src="${contextPath}/static/axure/resources/scripts/jquery-1.7.1.min.js"></script>--%>
  <script src="${contextPath}/static/plugin/jquery/jquery-3.1.1.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
  <script src="${contextPath}/static/axure/data/document.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
  <script src="${contextPath}/static/axure/files/report/data.js"></script>
  <script type="text/javascript">
    $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
    $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
    $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
  </script>
</head>
<body>
<div id="base" class="">

  <!-- Unnamed (矩形) -->
  <div id="u42" class="ax_default box_2">
    <img id="u42_img" class="img " src="${contextPath}/static/axure/images/report/u42.png"/>
    <!-- Unnamed () -->
    <div id="u43" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u44" class="ax_default box_2">
    <img id="u44_img" class="img " src="${contextPath}/static/axure/images/report/u44.png"/>
    <!-- Unnamed () -->
    <div id="u45" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>
<form action="${contextPath}/app/user/add/tel/router.action" method="post">
  <!-- Unnamed (矩形) -->
  <div id="u46" class="ax_default label">
    <img id="u46_img" class="img " src="${contextPath}/static/axure/images/report/u46.png"/>
    <!-- Unnamed () -->
    <div id="u47" class="text">
      <p><span>校区</span></p>
    </div>
  </div>

  <!-- Unnamed (下拉列表框) -->
  <div id="u48" class="ax_default droplist">
    <select id="distinctId" name="distinctId">
      <c:forEach items="${placeDistincts}" var="placeDistincts">
      <option value="${placeDistincts.distinctId}">${placeDistincts.distinctName}</option>
        </c:forEach>
    </select>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u49" class="ax_default label">
    <img id="u49_img" class="img " src="${contextPath}/static/axure/images/report/u46.png"/>
    <!-- Unnamed () -->
    <div id="u50" class="text">
      <p><span>地点</span></p>
    </div>
  </div>

  <!-- Unnamed (下拉列表框) -->
  <div id="u51" class="ax_default droplist">
    <select id="buildingId" name="buildingId">
    </select>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u52" class="ax_default label">
    <img id="u52_img" class="img " src="${contextPath}/static/axure/images/report/u46.png"/>
    <!-- Unnamed () -->
    <div id="u53" class="text">
      <p><span>位置</span></p>
    </div>
  </div>

  <!-- Unnamed (下拉列表框) -->
  <div id="u54" class="ax_default droplist">
    <select id="roomId" name="roomId">
    </select>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u55" class="ax_default label">
    <img id="u55_img" class="img " src="${contextPath}/static/axure/images/report/u46.png"/>
    <!-- Unnamed () -->
    <div id="u56" class="text">
      <p><span>设备</span></p>
    </div>
  </div>

  <!-- Unnamed (下拉列表框) -->
  <div id="u57" class="ax_default droplist">
    <select id="equipmentId" name="equipmentId">
    </select>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u58" class="ax_default label">
    <img id="u58_img" class="img " src="${contextPath}/static/axure/images/report/u46.png"/>
    <!-- Unnamed () -->
    <div id="u59" class="text">
      <p><span>描述</span></p>
    </div>
  </div>

  <!-- Unnamed (多行文本框) -->
  <div id="u60" class="ax_default text_area">
    <textarea id="listDescription" name="listDescription"></textarea>
  </div>

  <div id="u61" class="ax_default label">
    <img id="u61_img" class="img " src="${contextPath}/static/axure/images/report/u61.png"/>
    <div id="u62" class="text">
      <p><span>请填选下列内容，如列表中没有，则选择“其他”</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u63" class="ax_default primary_button">
    <img id="u63_img" class="img " src="${contextPath}/static/axure/images/report/u63.png"/>
    <div id="u64" class="text">
      <p><input type="submit" value="下一步"></p>
    </div>

  </div>
</form>
  <!-- Unnamed (矩形) -->
  <div id="u65" class="ax_default button">
    <%--<img id="u65_img" class="img " src="${contextPath}/static/axure/images/report/u65.png"/>--%>
    <%--<!-- Unnamed () -->--%>
    <%--<div id="u66" class="text">--%>
      <a href="${contextPath}/app/user/index.action">放弃</a>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u67" class="ax_default box_2">
    <img id="u67_img" class="img " src="${contextPath}/static/axure/images/report/u67.png"/>
    <!-- Unnamed () -->
    <div id="u68" class="text">
      <p><span>故障报修</span></p>
    </div>
  </div>

  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u70" class="ax_default box_1">
    <img id="u70_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
    <!-- Unnamed () -->
    <div id="u71" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u73" class="ax_default box_1">
    <img id="u73_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
    <!-- Unnamed () -->
    <div id="u74" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(function(){
      $('#distinctId').on('change',function(){
          var postData = {
            "distinctId": $("#distinctId").val()
          };
          $.ajax({
              url:"${contextPath}/app/user/buildings.action",
              data: postData,
              dataType:"json",
              type:"post",
              contentType:"application/x-www-form-urlencoded; charset=utf-8",
              success: function(result) {
                $("#buildingId").empty();

                  $.each(result.buildings,function(item,value) {
                    $("#buildingId").append(new Option(value.buildingName,value.buildingId));
                  });
                  $("#buildingId").append(new Option("其他", 0));
                $("#buildingId").trigger("change");
              },
              error: function (e) {
                  console.log("error information");
              }
          });
      });
      $('#buildingId').on('change',function(){
        var postData = {
          "buildingId": $("#buildingId").val()

         };
      $.ajax({
        url:"${contextPath}/app/user/placeRoom.action",
        data: postData,
        dataType:"json",
        type:"post",
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        success: function(result) {
          $("#roomId").empty();

          $.each(result.rooms,function(item,value) {
            $("#roomId").append(new Option(value.roomName,value.roomId));
          });
            $("#roomId").append(new Option("其他", 0));
            $('#roomId').trigger("change");

        },
        error: function (e) {
          console.log("error information");
        }
      })

    } );
    $('#roomId').on('change',function(){
      var postData = {
        "roomId": $("#roomId").val()

      };
      $.ajax({
        url:"${contextPath}/app/user/equipment.action",
        data: postData,
        dataType:"json",
        type:"post",
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        success: function(result) {
          $("#equipmentId").empty();

          $.each(result.equipments,function(item,value) {
            $("#equipmentId").append(new Option(value.equipmentName,value.equipmentId));
          });
          $("#equipmentId").append(new Option("其他", 0));
          $('#equipmentId').trigger("change");

        },
        error: function (e) {
          console.log("error information");
        }
      })

    } );

      $('#distinctId').trigger("change");
    });

  </script>
</body>
</html>



