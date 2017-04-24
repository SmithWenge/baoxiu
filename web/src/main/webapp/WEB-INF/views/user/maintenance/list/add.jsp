<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field">
        <legend>
            <span class="layui-breadcrumb">
              <a><cite>报修单添加</cite></a>
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
        </div>
    </fieldset>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>

<script>
    $(function () {
        var form = layui.form();

        // 加载地点数据
        function loadBuildingData(result) {
            var $ = layui.jquery;
            var $form = $('#placeRoomForm');
            var form = layui.form();
            var optionsValue = '';

            var buildings = result.buildings;

            if(buildings.length > 0) {
                for (var i = 0; i < buildings.length; i++) {
                    optionsValue += '<option value="' + buildings[i].buildingId + '">' + buildings[i].buildingName + '</option>';
                }
            } else {
                optionsValue = '<option value="0">该下拉菜单为空</option>';
            }

            $form.find('select[id=buildingId]').empty();
            $form.find('select[id=buildingId]').append(optionsValue);
            form.render();
        }

        form.on('select(distinctId)', function(data) {
            var postData = {
                "distinctId": data.value
            };

            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/user/maintenance/list/buildings.action',
                data: postData,
                success: function (result) {
                    // 配置校区查询
                    loadBuildingData(result);
                }
            });
        });

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
            }

            $form.find('select[id=roomId]').empty();
            $form.find('select[id=roomId]').append(optionsValue);
            form.render();
        }

        form.on('select(buildingId)', function(data) {
            var postData = {
                "buildingId": data.value
            };

            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/user/maintenance/list/placeRoom.action',
                data: postData,
                success: function (result) {
                    // 配置校区查询
                    loadRoomData(result);
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

    });
</script>
<script>
    layui.use('upload', function(){
        layui.upload({
            url: '${contextPath}/static/maintencePictures/' //上传接口
            ,success: function(res){ //上传成功后的回调
                console.log(res)
            }
        });

        layui.upload({
            url: '/test/upload.json'
            ,elem: '#test' //指定原始元素，默认直接查找class="layui-upload-file"
            ,method: 'post' //上传接口的http类型
            ,success: function(res){
                LAY_demo_upload.src = res.url;
            }
        });
    });
</script>


<%@ include file="/WEB-INF/include/footer.jsp"%>

