<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>

<!-- 内容主体 -->
<div class="layui-body body">
    <fieldset class="layui-elem-field layui-field-title">
        <legend>报修单查看</legend>
    <div class="layui-field-box">
        <table class="layui-table">
            <thead>
            <tr>
                <td>序号</td>
                <td>报修单号</td>
                <td>报修单状态</td>
                <td>设备名</td>
                <td>维修小组</td>
                <td>报修时间</td>
            </tr>
            </thead>
            <tbody id="pageTableBody" >
            </tbody>
        </table>
    </div>
    <div class="layui-field-box">
        <div id="pageNav"></div>
    </div>
</div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>
<script>
    $(function () {

        var condition = {
            "roomId": '',
            "buildingId": '',
            "equipmentId":'',
            "listState": '',
            "repairGroupId": '',
            "stopListTime": '',
            "startListTime": ''
        };

        // 分页
        var laypage = layui.laypage;

        // 跳转不同分页数据渲染
        function jumpPage(curr) {
            if (curr <= 0) curr = 1;

            var pageData = {
                "page": curr - 1,
                "buildingId": condition.buildingId,
                "roomId": condition.roomId,
                "equipmentId": condition.equipmentId,
                "repairGroupId": condition.repairGroupId,
                "stopListTime": condition.stopListTime,
                "startListTime": condition.startListTime
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/home/route/page.action',
                data: pageData,
                success: function (result) {
                    $("#pageTableBody").empty();
                    $.each(result.page.content, function (i, item) {
                        var trData = "<tr><td>" + (i + 1) + "</td><td><a href=\"${contextPath}/admin/maintenance/list/manage/details/route/" + item.listNumber + ".action\">" + item.listNumber + "</a></td><td>" + item.liststateStr ;
                        trData += "<td>" + item.equipmentName + "</td><td>" + item.groupName + "</td><td>"  + item.listTime + "</td>"+ "</td>" ;
                        $("#pageTableBody").append(trData);
                    });
                }
            });
        }
        // 初始化页面加载数据
        function loadPageData() {
            var pageData = {
                "page": 0,
                "buildingId": condition.buildingId,
                "roomId": condition.roomId,
                "equipmentId": condition.equipmentId,
                "repairGroupId": condition.repairGroupId,
                "stopListTime": condition.stopListTime,
                "startListTime": condition.startListTime
            };
            $.ajax({
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                dataType: 'json',
                url: '${contextPath}/admin/home/route/page.action',
                data: pageData,
                success: function (result) {
                    laypage({
                        cont: 'pageNav',
                        pages: result.page.totalPages,
                        skin: '#1E9FFF',
                        prev: '<em><</em>',
                        next: '<em>></em>',
                        groups: 10,
                        first: 1,
                        last: result.page.totalPages,
                        jump: function (obj) {
                            jumpPage(obj.curr);
                        }
                    });
                }
            });
        }

        loadPageData();
    });

</script>

<%@ include file="/WEB-INF/include/footer.jsp"%>