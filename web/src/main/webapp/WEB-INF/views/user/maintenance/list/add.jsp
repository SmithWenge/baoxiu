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

        // 地点查询位置
        $("#building").change(function() {
            var buildingCondition = {
                "buildingId": $("#building").val()
            }

            var room = document.getElementById("room");

            $.ajax({
                type: 'get',
                contentType: 'application/json',
                dataType: 'json',
                data: buildingCondition,
                url: 'https://nanqu.56team.com/web/api/v1/room/get/building.action',
                success: function (result) {
                    room.options.length = 0;
                    $.each(result.rooms, function (i, item) {
                        room.options.add(new Option(item.roomName, item.roomId));
                    });

                    $("#room").material_select();
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
</body>
</html>