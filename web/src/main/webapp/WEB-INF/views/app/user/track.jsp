<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <title>Track</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta name="apple-mobile-web-app-capable" content="yes"/>
  <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
  <link href="${contextPath}/static/axure/files/track/styles.css" type="text/css" rel="stylesheet"/>
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
  <script src="${contextPath}/static/plugin/jquery/jquery-3.1.1.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
  <script src="${contextPath}/static/axure/data/document.js"></script>
  <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
  <script src="${contextPath}/static/axure/files/track/data.js"></script>
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
  <div id="u0" class="ax_default box_1">
    <img id="u0_img" class="img " src="${contextPath}/static/axure/images/track/regen/u0.png"/>
    <!-- Unnamed () -->
    <div id="u1" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>
  <form action="${contextPath}/app/user/select/all/maintenance.action" method="post" onsubmit="return checkSubmit()">
  <!-- Unnamed (矩形) -->
  <div id="u2" class="ax_default primary_button">
    <%--<img id="u2_img" class="img " src="${contextPath}/static/axure/images/track/regen/u2.png"/>--%>
    <%--<!-- Unnamed () -->--%>
    <%--<div id="u3" class="text">--%>
     <input  type="submit" value="查询">
    <%--</div>--%>
  </div>

<a href="${contextPath}/app/user/index.action">
  <div id="u4" class="ax_default button">
    <img id="u4_img" class="img " src="${contextPath}/static/axure/images/track/regen/u4.png"/>
    <!-- Unnamed () -->
    <div id="u5" class="text">
      <p><span>返回</span></p>
    </div>
  </div>
</a>
  <!-- Unnamed (矩形) -->
  <div id="u6" class="ax_default box_1">
    <img id="u6_img" class="img " src="${contextPath}/static/axure/images/track/regen/u6.png"/>
    <!-- Unnamed () -->
    <div id="u7" class="text">
      <p><span>查看报修和维修进度查询</span></p>
    </div>
  </div>

  <!-- Unnamed (矩形) -->
  <div id="u8" class="ax_default box_1">
    <img id="u8_img" class="img " src="${contextPath}/static/axure/images/track/regen/u8.png"/>
    <!-- Unnamed () -->
    <div id="u9" class="text" style="display:none; visibility: hidden">
      <p><span></span></p>
    </div>
  </div>

  <!-- Unnamed (组合) -->
  <div id="u10" class="ax_default" data-width="269" data-height="37">

    <!-- Unnamed (矩形) -->
    <div id="u11" class="ax_default box_1">
      <img id="u11_img" class="img " src="${contextPath}/static/axure/images/track/regen/u11.png"/>
      <!-- Unnamed () -->
      <div id="u12" class="text" style="display:none; visibility: hidden">
        <p><span></span></p>
      </div>
    </div>

    <!-- Unnamed (文本框) -->
    <div id="u13" class="ax_default text_field">
      <input id="userTel" name="userTel" type="text" >
    </div>
  </div>
</form>
  <!-- Unnamed (矩形) -->
  <div id="u14" class="ax_default label">
    <img id="u14_img" class="img " src="${contextPath}/static/axure/images/track/regen/u14.png"/>
    <!-- Unnamed () -->
    <div id="u15" class="text">
      <p><span>近一月全校报修数量：</span></p>
    </div>
  </div>

  <!-- Unnamed (Top) -->

  <!-- Unnamed (矩形) -->
  <div id="u17" class="ax_default box_1">
    <img id="u17_img" class="img " src="${contextPath}/static/axure/images/track/regen/u17.png"/>
    <!-- Unnamed () -->
    <div id="u18" class="text">
      <p><span>大连交通大学后勤报修系统</span></p>
    </div>
  </div>

  <!-- Unnamed (Bottom) -->

  <!-- Unnamed (矩形) -->
  <div id="u20" class="ax_default box_1">
    <img id="u20_img" class="img " src="${contextPath}/static/axure/images/track/regen/u20.png"/>
    <!-- Unnamed () -->
    <div id="u21" class="text">
      <p><span>程序设计：大连交通大学56工作室</span></p>
    </div>
  </div>
</div>
</body>
</html>
