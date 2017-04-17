<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>index</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="${contextPath}/static/worker/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/files/home/styles.css" type="text/css" rel="stylesheet"/>
    <script src="${contextPath}/static/worker/resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/axQuery.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/globals.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axutils.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/annotation.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/axQuery.std.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/doc.js"></script>
    <script src="${contextPath}/static/worker/data/document.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/messagecenter.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/events.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/recording.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/action.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/expr.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/geometry.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/flyout.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/ie.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/model.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/repeater.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/sto.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/utils.temp.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/variables.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/drag.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/move.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/visibility.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/style.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/adaptive.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/tree.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/init.temp.js"></script>
    <script src="${contextPath}/static/worker/files/home/data.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/legacy.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/viewer.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/math.js"></script>
    <script type="text/javascript">
        $axure.utils.getTransparentGifPath = function() { return '${contextPath}/static/worker/resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return '${contextPath}/static/worker/resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return '${contextPath}/static/worker/resources/reload.html'; };
    </script>
</head>
<body>

<div id="base" class="">

    <!-- list (动态面板) -->
    <div id="u20" class="ax_default" data-label="list">
        <div id="u20_state0" class="panel_state" data-label="State1">
            <div id="u20_state0_content" class="panel_state_content">
                <div class="listNew">
                    <c:forEach items="${lists}" var="list">
                        <a href="${contextPath}/app/worker/routeDetails/${list.listNumber}.action">
                            <div class="list_1">
                                <button class="button-await">已接单</button>
                                <p class="listp">${list.listNumber}</p>
                                <p class="listp ">${list.liststatetime}</p>
                                <div class="listDetails" >
                                    <p class="listDetails_1">${list.listDescription}</p>
                                </div>
                                <div class="xian"></div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Unnamed (Bottom) -->

<!-- Unnamed (组合) -->
<div id="u166" class="ax_default">

    <!-- Unnamed (矩形) -->
    <div id="u167" class="ax_default box_1">
        <div id="u167_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u168" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- all (形状) -->
    <a href="${contextPath}/app/worker/routeLatestList.action">
        <div id="u169" class="ax_default icon" data-label="all">
            <img id="u169_img" class="img " src="${contextPath}/static/worker/images/home/all_u169.png"/>
            <!-- Unnamed () -->
            <div id="u170" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </a>

    <!-- todo (形状) -->
    <a href="${contextPath}/app/worker/routeWaitingList.action">
        <div id="u171" class="ax_default icon" data-label="todo">
            <img id="u171_img" class="img " src="${contextPath}/static/worker/images/home/todo_u171.png"/>
            <!-- Unnamed () -->
            <div id="u172" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </a>

    <a href="${contextPath}/app/worker/routeDoingList.action">
        <div id="u173" class="ax_default icon" data-label="home">
            <img id="u174_img" class="img " src="${contextPath}/static/worker/images/home/home_u173.png"/>
            <div id="u174" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </a>
</div>

<div id="u175" class="ax_default icon">
    <div id="u176" class="text" style="display: none; visibility: hidden">
        <p><span></span></p>
    </div>
</div>

<div id="u178" class="ax_default box_1">
    <div id="u178_div" class=""></div>
    <div id="u179" class="text" style="visibility: visible;">
        <p><span>大连交通大学后勤维修处理系统</span></p>
    </div>
</div>

<div id="u180" class="ax_default">
    <div id="u181" class="ax_default ellipse">
        <div id="u182" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <div id="u183" class="ax_default icon">
        <div id="u184" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>
</div>

<div id="u185" class="ax_default box_1">
    <div id="u185_div" class=""></div>
    <div id="u186" class="text" style="visibility: visible;">
        <p><span>在办工作</span></p>
    </div>
</div>

</body>
</html>

