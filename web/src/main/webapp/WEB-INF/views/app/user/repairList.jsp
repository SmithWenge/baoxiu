<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <title>RepairList</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/repairlist/styles.css" type="text/css" rel="stylesheet"/>
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
  <script src="${contextPath}/static/plugin/jquery/jquery-3.1.1.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
  <script src="${contextPath}/static/axure/data/document.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
  <script src="${contextPath}/static/axure/files/repairlist/data.js"></script>
  <script type="text/javascript">
    $axure.utils.getTransparentGifPath = function() { return '${contextPath}/static/axure/resources/images/transparent.gif'; };
    $axure.utils.getOtherPath = function() { return '${contextPath}/static/axure/resources/Other.html'; };
    $axure.utils.getReloadPath = function() { return '${contextPath}/static/axure/resources/reload.html'; };
  </script>
</head>
<body>
<div id="base" class="">

  <!-- Unnamed (矩形) -->
  <div id="u176" class="ax_default box_1">
    <img id="u176_img" class="img " src="${contextPath}/static/axure/images/track/u160.png"/>
    <!-- Unnamed () -->
    <div id="u177" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u178" class="ax_default box_1">
    <img id="u178_img" class="img " src="${contextPath}/static/axure/images/repairlist/u178.png"/>
    <!-- Unnamed () -->
    <div id="u179" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u180" class="ax_default box_1">
    <img id="u180_img" class="img " src="${contextPath}/static/axure/images/track/u160.png"/>
    <!-- Unnamed () -->
    <div id="u181" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>



  <!-- Unnamed (矩形) -->
  <div id="u184" class="ax_default heading_3">
    <img id="u184_img" class="img " src="${contextPath}/static/axure/images/repairlist/u184.png"/>
    <!-- Unnamed () -->
    <div id="u185" class="text">
      <p><span>您的报修历史（${userTel.userTel}）</span></p>
    </div>
  </div>



   <a href="${contextPath}/app/user/index.action">
    <div id="u189" class="ax_default primary_button">
      <img id="u189_img" class="img " src="${contextPath}/static/axure/images/repairlist/u189.png"/>
      <!-- Unnamed () -->
      <div id="u190" class="text">
        <p><span>返回首页</span></p>
      </div>
    </div>
  </a>
  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u192" class="ax_default box_1">
    <img id="u192_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
    <!-- Unnamed () -->
    <div id="u193" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u195" class="ax_default box_1">
    <img id="u195_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
    <!-- Unnamed () -->
    <div id="u196" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>

  <!-- Unnamed (组合) -->
  <div id="u197" class="ax_default" selectiongroup="list" data-width="325" data-height="48">
    <div class="listNew">
      <c:forEach items="${maintenanceLists}" var="maintenance">
      <a href="${contextPath}/app/user/select/oneMaintenance/${maintenance.listNumber}.action">
        <div class="list_1">
          <button class="button-done">
            <c:if test="${maintenance.listState == 1}">已提交</c:if>
            <c:if test="${maintenance.listState == 2}">已派单</c:if>
            <c:if test="${maintenance.listState == 3}">已处理</c:if>
            <c:if test="${maintenance.listState == 4}">已评价</c:if>
          </button>
          <p class="listp">${maintenance.listNumber}</p>
          <p class="listp ">${maintenance.liststatetime}</p>
          <div class="listDetails" >
            <p class="listDetails_1">${maintenance.listDescription}</p>
          </div>
          <div class="xian"></div>
        </div>
      </a>
        </c:forEach>
    </div>
  </div>
  </div>
</body>
</html>


