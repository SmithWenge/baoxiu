<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Details</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/details/styles.css" type="text/css" rel="stylesheet"/>
  <style type="text/css">
    @font-face {
      font-family: 'Iaxurefont';
      src: url(http://at.alicdn.com/t/font_1463732837_010477.eot');
      src: url(http://at.alicdn.com/t/font_1463732837_010477.eot?#iefix') format('embedded-opentype'),
      url(http://at.alicdn.com/t/font_1463732837_010477.woff') format('woff'),
      url(http://at.alicdn.com/t/font_1463732837_010477.ttf') format('truetype'),
      url(http://at.alicdn.com/t/font_1463732837_010477.svg#Iaxurefont') format('svg');
      }
  </style>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-1.7.1.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
  <script src="${contextPath}/static/axure/data/document.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
  <script src="${contextPath}/static/axure/files/details/data.js"></script>
  <script type="text/javascript">
    $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
    $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
    $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
  </script>
</head>
<body>
<div id="base" class="">

  <!-- Unnamed (矩形) -->
  <div id="u245" class="ax_default box_1" style="height: 270px;background-color: #fff;">
    <c:forEach items="${allStates}" var="state">
    <div class="details_1" >
      <p>${state.liststatetime}</p>
    </div>
    <div class="details_2" >
      <p>
        <c:if test="${state.listState == 1}">已提交</c:if>
        <c:if test="${state.listState == 2}">已派单</c:if>
        <c:if test="${state.listState == 3}">延期</c:if>
        <c:if test="${state.listState == 4}">等待派单</c:if>
        <c:if test="${state.listState == 5}">正在备件</c:if>
        <c:if test="${state.listState == 6}">已催单</c:if>
        <c:if test="${state.listState == 7}">以评价</c:if>
        <c:if test="${state.listState == 8}">待评价</c:if>
        <c:if test="${state.listState == 9}">其他</c:if>
      </p>
    </div>
      </c:forEach>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u248" class="ax_default box_1">
    <img id="u248_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
    <!-- Unnamed () -->
    <div id="u249" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>

  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u251" class="ax_default box_1">
    <img id="u251_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
    <!-- Unnamed () -->
    <div id="u252" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u253" class="ax_default box_1">
    <img id="u253_img" class="img " src="${contextPath}/static/axure/images/track/u160.png"/>
    <!-- Unnamed () -->
    <div id="u254" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u255" class="ax_default heading_3">
    <img id="u255_img" class="img " src="${contextPath}/static/axure/images/details/u255.png"/>
    <!-- Unnamed () -->
    <div id="u256" class="text">
      <p><span>报修单详情（00000000）</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u257" class="ax_default box_1">
    <img id="u257_img" class="img " src="${contextPath}/static/axure/images/track/u160.png"/>
    <!-- Unnamed () -->
    <div id="u258" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u259" class="ax_default box_1">
    <img id="u259_img" class="img " src="${contextPath}/static/axure/images/details/u259.png"/>
    <!-- Unnamed () -->
    <div id="u260" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

<a href="${contextPath}/app/user/turn/repairList/router/${maintenanceList.userTel}.action">
  <div id="u261" class="ax_default primary_button">
    <img id="u261_img" class="img " src="${contextPath}/static/axure/images/details/u261.png"/>
    <!-- Unnamed () -->
    <div id="u262" class="text">
      <p><span>前往我的报修</span></p>
    </div>
  </div>
</a>
  <!-- Unnamed (矩形) -->
  <div id="u263" class="ax_default label">
    <img id="u263_img" class="img " src="${contextPath}/static/axure/images/done/u133.png"/>
    <!-- Unnamed () -->
    <div id="u264" class="text">
      <p><span>报修人手机：${maintenanceList.userTel}</span></p>
    </div>
  </div>


  <!-- Unnamed (矩形) -->
  <div id="u267" class="ax_default label">
    <img id="u267_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u268" class="text">
      <p><span>校区</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u269" class="ax_default label">
    <img id="u269_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u270" class="text">
      <p><span>地点</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u271" class="ax_default label">
    <img id="u271_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u272" class="text">
      <p><span>位置</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u273" class="ax_default label">
    <img id="u273_img" class="img " src="${contextPath}/static/axure/images/confirm/u85.png"/>
    <!-- Unnamed () -->
    <div id="u274" class="text">
      <p><span>设备</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u275" class="ax_default label">
    <img id="u275_img" class="img " src="${contextPath}/static/axure/images/confirm/u97.png"/>
    <!-- Unnamed () -->
    <div id="u276" class="text">
      <p><span>故障描述</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u277" class="ax_default label">
    <img id="u277_img" class="img " src="${contextPath}/static/axure/images/confirm/u105.png"/>
    <!-- Unnamed () -->
    <div id="u278" class="text">
      <p><span>${maintenanceList.distinctName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u279" class="ax_default label">
    <img id="u279_img" class="img " src="${contextPath}/static/axure/images/index/u14.png"/>
    <!-- Unnamed () -->
    <div id="u280" class="text">
      <p><span>${maintenanceList.buildingName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u281" class="ax_default label">
    <img id="u281_img" class="img " src="${contextPath}/static/axure/images/index/u14.png"/>
    <!-- Unnamed () -->
    <div id="u282" class="text">
      <p><span>${maintenanceList.roomName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u283" class="ax_default label">
    <img id="u283_img" class="img " src="${contextPath}/static/axure/images/confirm/u111.png"/>
    <!-- Unnamed () -->
    <div id="u284" class="text">
      <p><span>${maintenanceList.equipmentName}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u285" class="ax_default paragraph">
    <img id="u285_img" class="img " src="${contextPath}/static/axure/images/confirm/u113.png"/>
    <!-- Unnamed () -->
    <div id="u286" class="text">
      <p><span>${maintenanceList.listDescription}</span></p>
    </div>
  </div>

  <!-- Unnamed (垂直线) -->
  <div id="u287" class="ax_default line">
    <img id="u287_img" class="img " src="${contextPath}/static/axure/images/confirm/u115.png"/>
    <!-- Unnamed () -->
    <div id="u288" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>






</div>
</body>
</html>

