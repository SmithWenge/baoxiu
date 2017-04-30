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
                        <h1 class="title"><b>您的报修历史(${userTel.userTel})</b></h1>
                    </header>
                </div>
            </div>
            <c:forEach items="${maintenanceLists}" var="maintenance">
                <div class="card">
                    <div class="card-header" style="font-size: .8rem;">
                        <i class="fa fa-circle" aria-hidden="true" style="color: ${maintenance.listStateFrontStyleColor};">已提交</i>
                       报修单号：${maintenance.listNumber}
                    </div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            ${maintenance.distinctName}_${maintenance.buildingName}_${maintenance.roomName}_${maintenance.equipmentName}
                        </div>
                    </div>
                    <div class="card-footer">
                        <span>${maintenance.liststatetime}</span>
                        <a href="${contextPath}/app/user/select/oneMaintenance/${maintenance.listNumber}.action">详情>></a>
                    </div>
                </div>
            </c:forEach>

            <div class="content-block">
                <a href="${contextPath}/app/user/redirect/index.action" class="button button-big" style="background-color: #fdb970; color: #000; border: 0px;" external>返回首页</a>
            </div>
            <div class="card-header">
            </div>
        </div>
        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h3 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h3>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>

<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>