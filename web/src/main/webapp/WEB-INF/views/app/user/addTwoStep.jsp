<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <title>保修单添加</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/confirm/styles.css" type="text/css" rel="stylesheet"/>
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
  <script src="${contextPath}/static/axure/files/index/data.js"></script>
  <script type="text/javascript">
    $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
    $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
    $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
  </script>
  <script type="text/javascript">
    function checkSubmit(){
      var tel = document.getElementById("userTel");
      if (!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(tel.value))) {
        alert("请输入合法手机号");
        return false;
      }else{
        return true;
      }

    }
    </script>
</head>
<body>
<div id="base" class="">

  <!-- Unnamed (矩形) -->
  <div id="u75" class="ax_default box_1">
    <img id="u75_img" class="img " src="${contextPath}/static/axure/images/confirm/u75.png"/>
    <!-- Unnamed () -->
    <div id="u76" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u77" class="ax_default box_1">
    <img id="u77_img" class="img " src="${contextPath}/static/axure/images/confirm/u77.png"/>
    <!-- Unnamed () -->
    <div id="u78" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>
  <form action="${contextPath}/app/user/add/do.action" method="post" onsubmit="return checkSubmit();">
  <!-- Unnamed (矩形) -->
  <div id="u79" class="ax_default box_1">
    <img id="u79_img" class="img " src="${contextPath}/static/axure/images/confirm/u79.png"/>
    <!-- Unnamed () -->
    <div id="u80" class="text">
      <p><span>请确认您的报修信息</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u81" class="ax_default primary_button">
    <%--<img id="u81_img" class="img " src="${contextPath}/static/axure/images/confirm/u81.png"/>--%>
    <%--<!-- Unnamed () -->--%>
    <%--<div id="u82" class="text">--%>
      <input type="submit" value="报修">
    </div>

<a href="${contextPath}/app/user/index.action">
  <div id="u83" class="ax_default button">
    <img id="u83_img" class="img " src="${contextPath}/static/axure/images/confirm/u83.png"/>
    <!-- Unnamed () -->
    <div id="u84" class="text">
      <p><span>放弃</span></p>
    </div>
  </div>
</a>
  <!-- Unnamed (矩形) -->
  <div id="u85" class="ax_default label">
    <img id="u85_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u86" class="text">
      <p><span>校区</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u87" class="ax_default label">
    <img id="u87_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u88" class="text">
      <p><span>地点</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u89" class="ax_default label">
    <img id="u89_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u90" class="text">
      <p><span>位置</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u91" class="ax_default label">
    <img id="u91_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u92" class="text">
      <p><span>设备</span></p>
    </div>
  </div>

  <!-- Unnamed (组合) -->
  <div id="u93" class="ax_default" data-width="156" data-height="27">

    <!-- Unnamed (矩形) -->
    <div id="u94" class="ax_default box_1">
      <img id="u94_img" class="img " src="${contextPath}/static/axure/images/confirm/u94.png"/>
      <!-- Unnamed () -->
      <div id="u95" class="text" style="display:none; visibility: hidden">
        <p><span></span></p>
      </div>
    </div>

    <!-- Unnamed (文本框) -->
    <div id="u96" class="ax_default text_field">
      <input id="userTel" name="userTel" type="text">
      <input type="hidden" name="distinctId" value="${maintenance.distinctId}">
      <input type="hidden" name="buildingId" value="${maintenance.buildingId}">
      <input type="hidden" name="roomId" value="${maintenance.roomId}">
      <input type="hidden" name="equipmentId" value="${maintenance.equipmentId}">
      <input type="hidden" name="listDescription" value="${maintenance.listDescription}">
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u97" class="ax_default label">
    <img id="u97_img" class="img " src="${contextPath}/static/axure/images/confirm/u97.png"/>
    <!-- Unnamed () -->
    <div id="u98" class="text">
      <p><span>故障描述</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u99" class="ax_default label">
    <img id="u99_img" class="img " src="${contextPath}/static/axure/images/confirm/u99.png"/>
    <!-- Unnamed () -->
    <div id="u100" class="text">
      <p><span>请输入您的手机号：</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u101" class="ax_default paragraph">
    <img id="u101_img" class="img " src="${contextPath}/static/axure/images/confirm/u101.png"/>
    <!-- Unnamed () -->
    <div id="u102" class="text">
      <p><span>数据使用说明：</span></p><p><span>手机号用来识别您的报修，务必准确输入。今后您查询报修进度需要输入您的手机号，并在后勤部门无法确定故障问题时用来与您联系，仅后勤处维修管理员可以查看您的联系方式，其他人无法获取。</span></p>
    </div>
  </div>
</form>
  <a href="${contextPath}/app/user/maintenance/add/router.action">
  <!-- Unnamed (矩形) -->
  <div id="u103" class="ax_default button">
    <img id="u103_img" class="img " src="${contextPath}/static/axure/images/confirm/u103.png"/>
    <!-- Unnamed () -->
    <div id="u104" class="text">
     <p><span>上一步</span></p>
    </div>
  </div>
  </a>
  <!-- Unnamed (矩形) -->
  <div id="u105" class="ax_default label">
    <img id="u105_img" class="img " src="${contextPath}/static/axure/images/confirm/u105.png"/>
    <!-- Unnamed () -->
    <div id="u106" class="text">
      <p><span>${maintenance.distinctName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u107" class="ax_default label">
    <img id="u107_img" class="img " src="${contextPath}/static/axure/images/index/u14.png"/>
    <!-- Unnamed () -->
    <div id="u108" class="text">
      <p><span>${maintenance.buildingName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u109" class="ax_default label">
    <img id="u109_img" class="img " src="${contextPath}/static/axure/images/index/u14.png"/>
    <!-- Unnamed () -->
    <div id="u110" class="text">
      <p><span>${maintenance.roomName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u111" class="ax_default label">
    <img id="u111_img" class="img " src="${contextPath}/static/axure/images/confirm/u111.png"/>
    <!-- Unnamed () -->
    <div id="u112" class="text">
      <p><span>${maintenance.equipmentName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u113" class="ax_default paragraph">
    <img id="u113_img" class="img " src="${contextPath}/static/axure/images/confirm/u113.png"/>
    <!-- Unnamed () -->
    <div id="u114" class="text">
      <p><span>${maintenance.listDescription}</span></p>
    </div>
  </div>

  <!-- Unnamed (垂直线) -->
  <div id="u115" class="ax_default line">
    <img id="u115_img" class="img " src="${contextPath}/static/axure/images/confirm/u115.png"/>
    <!-- Unnamed () -->
    <div id="u116" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u118" class="ax_default box_1">
    <img id="u118_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
    <!-- Unnamed () -->
    <div id="u119" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u121" class="ax_default box_1">
    <img id="u121_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
    <!-- Unnamed () -->
    <div id="u122" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>
</div>
</body>
</html>
