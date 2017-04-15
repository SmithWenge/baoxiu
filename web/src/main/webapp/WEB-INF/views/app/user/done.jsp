<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
  <title>Done</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/done/styles.css" type="text/css" rel="stylesheet"/>
  <style type="text/css">
    @font-face {
      font-family: 'Iaxurefont';
      src: url("http://at.alicdn.com/t/font_1463732837_010477.eot");
      src: url("http://at.alicdn.com/t/font_1463732837_010477.eot?#iefix") format('embedded-opentype'),
      url("http://at.alicdn.com/t/font_1463732837_010477.woff") format('woff'),
      url("http://at.alicdn.com/t/font_1463732837_010477.ttf") format('truetype'),
      url("http://at.alicdn.com/t/font_1463732837_010477.svg#Iaxurefont") format('svg')
      }
  </style>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-1.7.1.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
  <script src="${contextPath}/static/axure/data/document.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
  <script src="${contextPath}/static/axure/files/done/data.js"></script>
  <script type="text/javascript">
    $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
    $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
    $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
  </script>
</head>
<body>
<div id="base" class="">

  <!-- Unnamed (矩形) -->
  <div id="u123" class="ax_default box_1">
    <img id="u123_img" class="img " src="${contextPath}/static/axure/images/done/u123.png"/>
    <!-- Unnamed () -->
    <div id="u124" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u125" class="ax_default heading_2">
    <img id="u125_img" class="img " src="${contextPath}/static/axure/images/done/u125.png"/>
    <!-- Unnamed () -->
    <div id="u126" class="text">
      <p><span>我们已经受理您的报修</span></p>
    </div>
  </div>

  <a href="${contextPath}/app/user/index.action">
  <div id="u127" class="ax_default primary_button">
    <img id="u127_img" class="img " src="${contextPath}/static/axure/images/done/u127.png"/>
    <!-- Unnamed () -->
    <div id="u128" class="text">
      <p><span>返回首页</span></p>
    </div>
  </div>
  </a>

  <!-- Unnamed (矩形) -->
  <div id="u129" class="ax_default label">
    <img id="u129_img" class="img " src="${contextPath}/static/axure/images/done/u129.png"/>
    <!-- Unnamed () -->
    <div id="u130" class="text">
      <p><span>报修单号：${maintenance.listNumber}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u131" class="ax_default label">
    <img id="u131_img" class="img " src="${contextPath}/static/axure/images/done/u131.png"/>
    <!-- Unnamed () -->
    <div id="u132" class="text">
      <p><span>报修报修时间：${maintenance.liststatetime}</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u133" class="ax_default label">
    <img id="u133_img" class="img " src="${contextPath}/static/axure/images/done/u133.png"/>
    <!-- Unnamed () -->
    <div id="u134" class="text">
      <p><span>报修人手机：${maintenance.userTel}</span></p>
    </div>
  </div>

  <!-- Unnamed (组合) -->
  <div id="u135" class="ax_default" data-width="262" data-height="17">

    <!-- Unnamed (矩形) -->
    <div id="u136" class="ax_default label">
      <img id="u136_img" class="img " src="${contextPath}/static/axure/images/confirm/u105.png"/>
      <!-- Unnamed () -->
      <div id="u137" class="text">
        <p><span>您可以在</span></p>
      </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u138" class="ax_default label">
      <img id="u138_img" class="img " src="${contextPath}/static/axure/images/confirm/u105.png"/>
      <!-- Unnamed () -->
      <div id="u139" class="text">
        <p><span>我的报修</span></p>
      </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u140" class="ax_default label">
      <img id="u140_img" class="img " src="${contextPath}/static/axure/images/done/u129.png"/>
      <!-- Unnamed () -->
      <div id="u141" class="text">
        <p><span>页面跟踪维修进度。</span></p>
      </div>
    </div>
  </div>

  <!-- Unnamed (形状) -->
  <div id="u142" class="ax_default icon">
    <img id="u142_img" class="img " src="${contextPath}/static/axure/images/done/u142.png"/>
    <!-- Unnamed () -->
    <div id="u143" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u144" class="ax_default label">
    <img id="u144_img" class="img " src="${contextPath}/static/axure/images/done/u144.png"/>
    <!-- Unnamed () -->
    <div id="u145" class="text">
      <p><span>系统当前队列中还有4项维修任务正在进行</span></p>
    </div>
  </div>

  <!-- Unnamed (水平线) -->
  <div id="u146" class="ax_default line">
    <img id="u146_img" class="img " src="${contextPath}/static/axure/images/done/u146.png"/>
    <!-- Unnamed () -->
    <div id="u147" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u149" class="ax_default box_1">
    <img id="u149_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
    <!-- Unnamed () -->
    <div id="u150" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u152" class="ax_default box_1">
    <img id="u152_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
    <!-- Unnamed () -->
    <div id="u153" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>
</div>
</body>
</html>
