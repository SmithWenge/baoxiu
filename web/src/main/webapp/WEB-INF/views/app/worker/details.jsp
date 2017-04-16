<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Accept</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="${contextPath}/static/worker/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="${contextPath}/static/worker/files/accept/styles.css" type="text/css" rel="stylesheet"/>
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
    <script src="${contextPath}/static/worker/files/accept/data.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/legacy.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/viewer.js"></script>
    <script src="${contextPath}/static/worker/resources/scripts/axure/math.js"></script>
    <script type="text/javascript">
        $axure.utils.getTransparentGifPath = function() { return 'resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return 'resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return 'resources/reload.html'; };
    </script>
</head>
<body>
<div id="base" class="">

    <!-- Unnamed (动态面板) -->
    <div id="u447" class="ax_default ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u447_state0" class="panel_state" data-label="State1">
            <div id="u447_state0_content" class="panel_state_content">

                <!-- Unnamed (矩形) -->
                <div id="u448" class="ax_default box_1">
                    <div id="u448_div" class=""></div>
                    <!-- Unnamed () -->
                    <div id="u449" class="text" style="display: none; visibility: hidden">
                        <p><span></span></p>
                    </div>
                </div>

                <!-- Unnamed (矩形) -->
                <div id="u450" class="ax_default _二级标题">
                    <div id="u450_div" class=""></div>
                    <!-- Unnamed () -->
                    <div id="u451" class="text" style="visibility: visible;">
                        <p><span>已修改</span></p>
                    </div>
                </div>

                <!-- Unnamed (矩形) -->
                <div id="u452" class="ax_default label">
                    <div id="u452_div" class=""></div>
                    <!-- Unnamed () -->
                    <div id="u453" class="text" style="visibility: visible;">
                        <p><span>报修单号：${list.listNumber}</span></p>
                    </div>
                </div>

                <!-- Unnamed (形状) -->
                <div id="u456" class="ax_default icon">
                    <img id="u456_img" class="img " src="${contextPath}/static/worker/images/operate/u196.png"/>
                    <!-- Unnamed () -->
                    <div id="u457" class="text" style="display: none; visibility: hidden">
                        <p><span></span></p>
                    </div>
                </div>

                <!-- Unnamed (水平线) -->
                <div id="u458" class="ax_default line">
                    <img id="u458_img" class="img " src="${contextPath}/static/worker/images/operate/u198.png"/>
                    <!-- Unnamed () -->
                    <div id="u459" class="text" style="display: none; visibility: hidden">
                        <p><span></span></p>
                    </div>
                </div>

                <!-- Unnamed (矩形) -->
                <div id="u460" class="ax_default primary_button">
                    <div id="u460_div" class=""></div>
                    <!-- Unnamed () -->
                    <div id="u461" class="text" style="visibility: visible;">
                        <p><span>确 定</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Unnamed (Bottom) -->

    <!-- Unnamed (组合) -->
    <div id="u463" class="ax_default">

        <!-- Unnamed (矩形) -->
        <div id="u464" class="ax_default box_1">
            <div id="u464_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u465" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- all (形状) -->
        <div id="u466" class="ax_default icon" data-label="all">
            <img id="u466_img" class="img " src="${contextPath}/static/worker/images/home/all_u169.png"/>
            <!-- Unnamed () -->
            <div id="u467" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- todo (形状) -->
        <div id="u468" class="ax_default icon" data-label="todo">
            <img id="u468_img" class="img " src="${contextPath}/static/worker/images/home/todo_u171.png"/>
            <!-- Unnamed () -->
            <div id="u469" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- home (形状) -->
        <div id="u470" class="ax_default icon" data-label="home">
            <img id="u470_img" class="img " src="${contextPath}/static/worker/images/home/home_u173.png"/>
            <!-- Unnamed () -->
            <div id="u471" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <!-- Unnamed (形状) -->
    <div id="u472" class="ax_default icon">
        <img id="u472_img" class="img " src="${contextPath}/static/worker/images/home/u175.png"/>
        <!-- Unnamed () -->
        <div id="u473" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (Top) -->

    <!-- Unnamed (矩形) -->
    <div id="u475" class="ax_default box_1">
        <div id="u475_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u476" class="text" style="visibility: visible;">
            <p><span>大连交通大学后勤维修处理系统</span></p>
        </div>
    </div>

    <!-- Unnamed (组合) -->
    <div id="u477" class="ax_default">

        <!-- Unnamed (椭圆形) -->
        <div id="u478" class="ax_default ellipse">
            <img id="u478_img" class="img " src="${contextPath}/static/worker/images/home/u181.png"/>
            <!-- Unnamed () -->
            <div id="u479" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (形状) -->
        <div id="u480" class="ax_default icon">
            <img id="u480_img" class="img " src="${contextPath}/static/worker/images/home/u183.png"/>
            <!-- Unnamed () -->
            <div id="u481" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <div >
        <label class="layui-form-label">状态变更表</label>
    </div>
    <table style="margin-left: 110px; width: 88%">
        <colgroup>
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>报修单状态</th>
            <th>更改时间</th>
            <th>描述</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list.lists}" var="model">
            <tr>
                <td>${model.liststateStr}</td>
                <td>${model.liststatetime}</td>
                <td>${model.listDescription}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Unnamed (组合) -->
    <div id="u500" class="ax_default">

        <!-- Unnamed (矩形) -->
        <div id="u501" class="ax_default box_1">
            <div id="u501_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u502" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u503" class="ax_default label">
            <div id="u503_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u504" class="text" style="visibility: visible;">
                <p><span>校区</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u505" class="ax_default label">
            <div id="u505_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u506" class="text" style="visibility: visible;">
                <p><span>地点</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u507" class="ax_default label">
            <div id="u507_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u508" class="text" style="visibility: visible;">
                <p><span>位置</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u509" class="ax_default label">
            <div id="u509_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u510" class="text" style="visibility: visible;">
                <p><span>设备</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u513" class="ax_default label">
            <div id="u513_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u514" class="text" style="visibility: visible;">
                <p><span>${list.distinctName}</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u515" class="ax_default label">
            <div id="u515_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u516" class="text" style="visibility: visible;">
                <p><span>${list.buildingName}</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u517" class="ax_default label">
            <div id="u517_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u518" class="text" style="visibility: visible;">
                <p><span>${list.roomName}</span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u519" class="ax_default label">
            <div id="u519_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u520" class="text" style="visibility: visible;">
                <p><span>${list.equipmentName}</span></p>
            </div>
        </div>

        <!-- Unnamed (垂直线) -->
        <div id="u523" class="ax_default line">
            <img id="u523_img" class="img " src="${contextPath}/static/worker/images/operate/u271.png"/>
            <!-- Unnamed () -->
            <div id="u524" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u525" class="ax_default box_1">
        <div id="u525_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u526" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (下拉列表框) -->
    <div id="u527" class="ax_default droplist">
        <select id="u527_input" name="listState">
            <option value="0">请选择操作</option>
            <option value="2">已接单</option>
            <option value="3">已处理</option>
            <option value="4">已评价</option>
        </select>
    </div>

    <div id="u528" class="ax_default primary_button">
        <div id="u528_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u529" class="text" style="visibility: visible;">
            <p><span>提交</span></p>
        </div>
    </div>

    <!-- Unnamed (文本框) -->
    <div id="u530" class="ax_default text_field">
        <input id="u530_input" type="text" name="listDescription"/>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u531" class="ax_default box_1">
        <div id="u531_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u532" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u533" class="ax_default _三级标题">
        <div id="u533_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u534" class="text" style="visibility: visible;">
            <p><span>报修单操作（${list.listNumber}）</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u535" class="ax_default box_1">
        <div id="u535_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u536" class="text" style="visibility: visible;">
            <c:if test="${list.listState == 1}">
                <p><span>已报修</span></p>
            </c:if>
            <c:if test="${list.listState == 2}">
                <p><span>已接单</span></p>
            </c:if>
            <c:if test="${list.listState == 3}">
                <p><span>已处理</span></p>
            </c:if>
            <c:if test="${list.listState == 4}">
                <p><span>已评价</span></p>
            </c:if>
        </div>
    </div>

    <!-- Unnamed (组合) -->
    <div id="u537" class="ax_default">

        <!-- Unnamed (矩形) -->
        <div id="u538" class="ax_default label">
            <div id="u538_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u539" class="text" style="visibility: visible;">
                <p><span>返回</span></p>
            </div>
        </div>

        <!-- Unnamed (形状) -->
        <div id="u540" class="ax_default icon">
            <img id="u540_img" class="img " src="${contextPath}/static/worker/images/operate/u288.png"/>
            <!-- Unnamed () -->
            <div id="u541" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u542" class="ax_default button">
        <div id="u542_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u543" class="text" style="visibility: visible;">
            <p><span>放弃</span></p>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    $(function () {
        var listState;
        var listDescription;

        $('#u529').on('click', function () {
            var postData = {
                listState : $('#u527_input').val(),
                listDescription : $('#u530_input').val(),
                listNumber : ${list.listNumber}
            };

            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                dataType: 'json',
                url: '${contextPath}/app/worker/edit.action',
                data: postData,
                success: function (result) {}
            });
        })
    });
</script>

</body>
</html>
