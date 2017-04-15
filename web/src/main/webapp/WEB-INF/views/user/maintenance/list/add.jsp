<<<<<<< HEAD


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a><cite>保修单添加</cite></a>
            </span>
        </legend>
        <div style="width: 30%; margin-top: 15px; ">
            <form action="${contextPath}/user/maintenance/list/add/do.action" method="post" class="layui-form" id="placeRoomForm">

                <div class="layui-form-item">
                    <label class="layui-form-label">选择校区</label>
                    <div class="layui-input-block">
                        <select name="distinctId" id="distinctId" lay-filter="distinctId">

                            <c:forEach items="${placeDistincts}" var="distinct">
                                <option value="${distinct.distinctId}">${distinct.distinctName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择地点</label>
                    <div class="layui-input-block">
                        <select name="buildingId" id="buildingId" lay-filter="buildingId">
                            <option value="0">请选择地点</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择位置</label>
                    <div class="layui-input-block">
                        <select name="roomId" id="roomId" lay-filter="roomId" >
                            <option value="0">请选择位置</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">选择设备</label>
                    <div class="layui-input-block">
                        <select name="equipmentId" id="equipmentId" lay-filter="equipmentId">
                            <option value="0">请选择设备</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">添加图片</label>
                    <div class="layui-input-block">
                        <input name="listPicture" id="listPicture" class="layui-upload-file" type="file">
                    </div>
                </div>

                <div class="layui-form-item ">
                    <label class="layui-form-label">详细信息</label>
                    <div class="layui-input-block">
                        <textarea name="listDescription" id="listDescription" placeholder="请输入内容" class="layui-textarea"></textarea>
                    </div>
                </div>


                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="addPlaceDistinct">保存</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8" />
    <title>交大报修系统</title>
    <!--Import Google Icon Font-->
    <!--<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">-->
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${contextPath}/static/css/materialize.min.css" media="screen,projection" />

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<body>
<div class="row">
    <form class="col s12">
        <div class="input-field col s12">
            <select id="distinct">
                <option value="" disabled selected>请选择校区</option>
            </select>
        </div>
        <div class="input-field col s12">
            <select id="building">
                <option value="" disabled selected>请选择地点</option>
            </select>
        </div>
        <div class="input-field col s12">
            <select id="room">
                <option value="" disabled selected>请选择位置</option>
            </select>
        </div>
        <div class="input-field col s12">
            <select id="equipment">
                <option value="" disabled selected>请选择设备</option>
            </select>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <textarea id="textarea1" class="materialize-textarea"></textarea>
                <label for="textarea1">请填写详细描述</label>
            </div>
>>>>>>> 20f506c83ee04d3d1bb0dbb24899fe1da86ee3f8
        </div>

        <a class="waves-effect waves-light btn" href="list.html">报修</a>
    </form>
</div>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="${contextPath}/static/plugin/jquery/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${contextPath}/static/plugin/jquery/materialize.min.js"></script>

<script>
    $(document).ready(function() {
        $('select').material_select();

        // 加载校区信息
        var distinct = document.getElementById("distinct");
        $.ajax({
            type: 'get',
            contentType: 'application/json',
            dataType: 'json',
            url: 'https://nanqu.56team.com/web/api/v1/distinct/get/all.action',
            success: function (result) {
                distinct.options.length = 0;
                $.each(result.distincts, function (i, item) {
                    distinct.options.add(new Option(item.distinctName, item.distinctId));
                });

                $('#distinct').material_select();
            }
        });

        // 校区查询地点
        $("#distinct").change(function() {
            var distinctCondtion = {
                "distinctId": $("#distinct").val()
            }

            var building = document.getElementById("building");
            $.ajax({
                type: 'get',
                contentType: 'application/json',
                dataType: 'json',
                data: distinctCondtion,
                url: 'https://nanqu.56team.com/web/api/v1/building/get/distinct.action',
                success: function (result) {
                    building.options.length = 0;
                    $.each(result.buildings, function (i, item) {
                        building.options.add(new Option(item.buildingName, item.buildingId));
                    });

                    $("#building").material_select();
                }
            });
        });

<<<<<<< HEAD
        // 加载位置数据
        function loadRoomData(result) {
            var $ = layui.jquery;
            var $form = $('#placeRoomForm');
            var form = layui.form();
            var optionsValue = '';

            var rooms = result.rooms;

            if(rooms.length > 0) {
                for (var i = 0; i < rooms.length; i++) {
                    optionsValue += '<option value="' + rooms[i].roomId + '">' + rooms[i].roomName + '</option>';
                }
=======
        // 地点查询位置
        $("#building").change(function() {
            var buildingCondition = {
                "buildingId": $("#building").val()
>>>>>>> 20f506c83ee04d3d1bb0dbb24899fe1da86ee3f8
            }

            var room = document.getElementById("room");

            $.ajax({
                type: 'get',
                contentType: 'application/json',
                dataType: 'json',
<<<<<<< HEAD
                url: '${contextPath}/user/maintenance/list/placeRoom.action',
                data: postData,
=======
                data: buildingCondition,
                url: 'https://nanqu.56team.com/web/api/v1/room/get/building.action',
>>>>>>> 20f506c83ee04d3d1bb0dbb24899fe1da86ee3f8
                success: function (result) {
                    room.options.length = 0;
                    $.each(result.rooms, function (i, item) {
                        room.options.add(new Option(item.roomName, item.roomId));
                    });

                    $("#room").material_select();
                }
            });
        });
        // 加载设备数据
        function loadEquipmentData(result) {
            var $ = layui.jquery;
            var $form = $('#placeRoomForm');
            var form = layui.form();
            var optionsValue = '';

            var equipments = result.equipments;

            if(equipments.length > 0) {
                for (var i = 0; i < equipments.length; i++) {
                    optionsValue += '<option value="' + equipments[i].equipmentId + '">' + equipments[i].equipmentName + '</option>';
                }
            }

            $form.find('select[id=equipmentId]').empty();
            $form.find('select[id=equipmentId]').append(optionsValue);
            form.render();
        }

        form.on('select(roomId)', function(data) {
            var postData = {
                "roomId": data.value
            };

            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/user/maintenance/list/equipment.action',
                data: postData,
                success: function (result) {
                    // 配置设施查询
                    loadEquipmentData(result);
                }
            });
        });

        // 位置查询设备
        $("#room").change(function() {
            var roomCondition = {
                "roomId": $("#room").val()
            }

            var equipment = document.getElementById("equipment");

            $.ajax({
                type: 'get',
                contentType: 'application/json',
                dataType: 'json',
                data: roomCondition,
                url: 'https://nanqu.56team.com/web/api/v1/equipment/get/room.action',
                success: function (result) {
                    console.log(result);
                    equipment.options.length = 0;
                    $.each(result.equipments, function (i, item) {

                        equipment.options.add(new Option(item.equipmentName, item.equipmentId));
                    });

                    $("#equipment").material_select();
                }
            });
        });
    });
</script>
<<<<<<< HEAD


<%@ include file="/WEB-INF/include/footer.jsp"%>



=======
</body>
</html>
>>>>>>> 20f506c83ee04d3d1bb0dbb24899fe1da86ee3f8
