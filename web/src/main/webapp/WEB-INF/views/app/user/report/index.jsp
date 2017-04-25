<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>


<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>
        <div class="content">
            <div class="card">
                <div class="card-header">
                    <header class="bar bar-nav" style="background-color: #FFF;">
                        <h1 class="title"><b>故障报修</b></h1>
                    </header>
                </div>
            </div>

            <div class="card">
                <form action="${contextPath}/app/user/add/tel/router.action" method="post" onsubmit="return checkSubmit();">
                    <div class="card-header" style="font-size: .7rem;">请填选下列内容,如列表中没有,请选择"其它"</div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="list-block">
                                <ul>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                            <div class="item-inner">
                                                <div class="item-title label">校区</div>
                                                <div class="item-input">
                                                    <select id="distinctId" name="distinctId">
                                                        <c:forEach items="${placeDistincts}" var="placeDistincts">
                                                            <option value="${placeDistincts.distinctId}">${placeDistincts.distinctName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                            <div class="item-inner">
                                                <div class="item-title label">地点</div>
                                                <div class="item-input">
                                                    <select id="buildingId" name="buildingId">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                            <div class="item-inner">
                                                <div class="item-title label">位置</div>
                                                <div class="item-input">
                                                    <select id="roomId" name="roomId">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                            <div class="item-inner">
                                                <div class="item-title label">设备</div>
                                                <div class="item-input">
                                                    <select id="equipmentId" name="equipmentId">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="align-top">
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-comment"></i></div>
                                            <div class="item-inner">
                                                <div class="item-title label">描述</div>
                                                <div class="item-input">
                                                    <textarea placeholder="请详细描述楼号和房间号码(室外请说明附近的参照物)并描述故障情况." style="font-size: .7rem;" id="listDescription" name="listDescription"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="content-block" style="margin-top: 2rem; margin-bottom: 0.5rem;">
                                <div class="row">
                                    <div class="col-50">
                                        <a href="${contextPath}/app/user/redirect/index.action" class="button button-big button-fill button-danger" style="background-color: #fdb970;" external>放弃</a>
                                    </div>
                                    <div class="col-50">
                                        <button class="button button-big button-fill button-success" type="submit" style="background-color: #02cbe9;">下一步</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="card-header">
            </div>
        </div>

        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h1>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>

<script type="text/javascript">
    $(function(){
        $('#distinctId').on('change',function(){
            var postData = {
                "distinctId": $("#distinctId").val()
            };
            $.ajax({
                url:"${contextPath}/app/user/buildings.action",
                data: postData,
                dataType:"json",
                type:"post",
                contentType:"application/x-www-form-urlencoded; charset=utf-8",
                success: function(result) {
                    $("#buildingId").empty();

                    $.each(result.buildings,function(item,value) {
                        $("#buildingId").append(new Option(value.buildingName,value.buildingId));
                    });
                    $("#buildingId").append(new Option("其他", 0));
                    $("#buildingId").trigger("change");
                },
                error: function (e) {
                    console.log("error information");
                }
            });
        });

        $('#buildingId').on('change',function(){
            var postData = {
                "buildingId": $("#buildingId").val()
            };

            if(buildingId == 0) {
                $("#roomId").append(new Option("其他", 0));
            } else {
                $.ajax({
                    url:"${contextPath}/app/user/placeRoom.action",
                    data: postData,
                    dataType:"json",
                    type:"post",
                    contentType:"application/x-www-form-urlencoded; charset=utf-8",
                    success: function(result) {
                        $("#roomId").empty();

                        $.each(result.rooms,function(item,value) {
                            $("#roomId").append(new Option(value.roomName,value.roomId));
                        });
                        $("#roomId").append(new Option("其他", 0));
                        $('#roomId').trigger("change");

                    },
                    error: function (e) {
                        console.log("error information");
                    }
                })
            }
        });

        $('#roomId').on('change',function(){
            var postData = {
                "roomId": $("#roomId").val()
            };
            if(roomId  == 0) {
                $("#equipmentId").append(new Option("其他", 0));
            }else{
                $.ajax({
                    url:"${contextPath}/app/user/equipment.action",
                    data: postData,
                    dataType:"json",
                    type:"post",
                    contentType:"application/x-www-form-urlencoded; charset=utf-8",
                    success: function(result) {
                        $("#equipmentId").empty();

                        $.each(result.equipments,function(item,value) {
                            $("#equipmentId").append(new Option(value.equipmentName,value.equipmentId));
                        });
                        $("#equipmentId").append(new Option("其他", 0));
                        $('#equipmentId').trigger("change");

                    },
                    error: function (e) {
                        console.log("error information");
                    }
                })
            }
        });

        $('#distinctId').trigger("change");
    });
</script>
<script type="text/javascript">
    function checkSubmit(){
        var listdiscription = document.getElementById("listDescription").value;

        if(listdiscription.length == 0 || listdiscription.test(/^\s+$/g)){
            alert("请添加报修单详情!")
            return false;
        }else{
            return true;
        }
    }
</script>
<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>