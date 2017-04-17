<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>用户自助报修系统</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link rel="shortcut icon" href="${contextPath}/static/images/djtu.ico" />
    <link href="${contextPath}/static/axure/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/axure/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/axure/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/axure/files/index/styles.css" type="text/css" rel="stylesheet"/>
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
    <script src="${contextPath}/static/axure/resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="${contextPath}/static/axure/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="${contextPath}/static/axure/resources/scripts/prototypePre.js"></script>
    <script src="${contextPath}/static/axure/data/document.js"></script>
    <script src="${contextPath}/static/axure/resources/scripts/prototypePost.js"></script>
    <script src="${contextPath}/static/axure/files/index/data.js"></script>
    <script type="text/javascript">
        $axure.utils.getTransparentGifPath = function() { return '${contextPath}/static/axure/resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return '${contextPath}/static/axure/resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return '${contextPath}/static/axure/resources/reload.html'; };
    </script>
</head>
<body>
<a id="base" class="">

    <div id="u0" class="ax_default box_1">
        <img id="u0_img" class="img " src="${contextPath}/static/axure/images/index/u0.png"/>
        <div id="u1" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u2" class="ax_default image">
        <img id="u2_img" class="img " src="${contextPath}/static/axure/images/index/u2.png"/>
        <div id="u3" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u4" class="ax_default heading_3">
        <img id="u4_img" class="img " src="${contextPath}/static/axure/images/index/u4.png"/>
        <div id="u5" class="text">
            <p><span>紧急报修方式</span></p>
        </div>
    </div>

    <div id="u6" class="ax_default box_1">
        <img id="u6_img" class="img " src="${contextPath}/static/axure/images/index/u6.png"/>
        <div id="u7" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u8" class="ax_default line">
        <img id="u8_img" class="img " src="${contextPath}/static/axure/images/index/u8.png"/>
        <div id="u9" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>
    <a href="${contextPath}/app/user/maintenance/add/router.action">
    <div id="u10" class="ax_default label">
        <img id="u10_img" class="img " src="${contextPath}/static/axure/images/index/u10.png"/>
        <div id="u11" class="text">
          <p><span>我要报修</span></p>
        </div>
    </div>
    </a>
<a href="${contextPath}/app/user/my/maintenance/router.action">
    <div id="u12" class="ax_default label">
        <img id="u12_img" class="img " src="${contextPath}/static/axure/images/index/u10.png"/>
        <div id="u13" class="text">
            <p><span>我的报修</span></p>
        </div>
    </div>
</a>
    <div id="u14" class="ax_default label">
        <img id="u14_img" class="img " src="${contextPath}/static/axure/images/index/u14.png"/>
        <div id="u15" class="text">
            <p><span>Repair</span></p>
        </div>
    </div>

    <div id="u16" class="ax_default label">
        <img id="u16_img" class="img " src="${contextPath}/static/axure/images/index/u16.png"/>
        <div id="u17" class="text">
            <p><span>Track</span></p>
        </div>
    </div>

    <div id="u18" class="ax_default label">
        <img id="u18_img" class="img " src="${contextPath}/static/axure/images/index/u18.png"/>
        <div id="u19" class="text">
            <p><span>&nbsp;&nbsp; 自来水管线爆裂等需要紧急维修的情况，请直接拨打后勤处维修科24小时报修电话：84108410。</span></p>
        </div>
    </div>

    <div id="u20" class="ax_default box_1">
        <img id="u20_img" class="img " src="${contextPath}/static/axure/images/index/u20.png"/>
        <div id="u21" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u22" class="ax_default heading_3">
        <img id="u22_img" class="img " src="${contextPath}/static/axure/images/index/u22.png"/>
        <div id="u23" class="text">
            <p><span>后勤风采</span></p>
        </div>
    </div>

    <div id="u24" class="ax_default label">
        <img id="u24_img" class="img " src="${contextPath}/static/axure/images/index/u24.png"/>
        <div id="u25" class="text">
            <p><span>&nbsp;&nbsp; &nbsp;&nbsp; 近日，后勤处精心购置了一批树苗，于3月25日在教学南区开展了植树活动。</span></p>
        </div>
    </div>

    <div id="u26" class="ax_default label">
        <img id="u26_img" class="img " src="${contextPath}/static/axure/images/index/u26.png"/>
        <div id="u27" class="text">
            <p><span>更多&gt;&gt;</span></p>
        </div>
    </div>

    <div id="u28" class="ax_default image">
        <img id="u28_img" class="img " src="${contextPath}/static/axure/images/index/u28.jpg"/>
        <div id="u29" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u30" class="ax_default icon">
        <img id="u30_img" class="img " src="${contextPath}/static/axure/images/index/u30.png"/>
        <div id="u31" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u32" class="ax_default icon">
        <img id="u32_img" class="img " src="${contextPath}/static/axure/images/index/u32.png"/>
        <div id="u33" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <%--<div id="u34" class="ax_default">--%>
    <%--</div>--%>

    <%--<div id="u35" class="ax_default">--%>
    <%--</div>--%>

    <div id="u37" class="ax_default box_1">
        <img id="u37_img" class="img " src="${contextPath}/static/axure/images/index/u37.png"/>
        <div id="u38" class="text">
            <p><span>大连交通大学后勤报修系统</span></p>
        </div>
    </div>

    <div id="u40" class="ax_default box_1">
        <img id="u40_img" class="img " src="${contextPath}/static/axure/images/index/u40.png"/>
        <div id="u41" class="text">
            <p><span>程序开发：大连交通大学56工作室</span></p>
        </div>
    </div>
</div>
</body>
</html>
