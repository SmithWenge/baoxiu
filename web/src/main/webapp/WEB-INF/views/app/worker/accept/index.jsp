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
                        <h3 class="title">
                            <%--<i class="fa fa-circle" aria-hidden="true" style="color: ${list.listStateFrontStyleColor};"></i>--%>
                            <b>报修单操作(${list.listNumber})</b>
                        </h3>
                    </header>
                </div>
            </div>
            <form action="${contextPath}/app/worker/edit.action" method="post">
                <div class="card">
                    <div class="card-header">
                        <div class="card-content">
                            <div class="card-content-inner">
                                <div class="list-block">
                                    <ul>
                                        <li><input type="hidden" name="listNumber" value="${list.listNumber}"></li>
                                        <li>
                                            <div class="item-content">
                                                <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                                <div class="item-inner">
                                                    <div class="item-input">
                                                        <select name="listState">
                                                            <option value="-1">请选择操作</option>
                                                            <option value="2">接单</option>
                                                            <option value="3">完成</option>
                                                            <option value="6">延期</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="card-content">
                                <div class="card-content-inner">
                                    <div class="list-block">
                                        <ul>
                                            <li>
                                                <div class="item-content">
                                                    <div class="item-media"><i class="icon icon-form-email"></i>
                                                    </div>
                                                    <div class="item-inner">
                                                        <div class="item-input">
                                                            <input type="text" name="listDescription" placeholder="请填写简要操作（可选）">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-footer">
                        <p class="link"></p>
                        <div class="row">
                            <div class="col-30" style="margin-right: .5rem;">
                                <button type="submit" class="button">提交</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="card">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block">
                            <ul>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">校区</div>
                                        <div class="item-after">${list.distinctName}</div>
                                    </div>`
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">地点</div>
                                        <div class="item-after">${list.buildingName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">位置</div>
                                        <div class="item-after">${list.roomName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">设备</div>
                                        <div class="item-after">${list.equipmentName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">故障描述</div>
                                        <div class="item-after">${list.listBigDescription}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">报修单状态</div>
                                        <c:if test="${list.listState == 1}">
                                            <div class="item-after">已提交</div>
                                        </c:if>
                                        <c:if test="${list.listState == 2}">
                                            <div class="item-after">已接单</div>
                                        </c:if>
                                        <c:if test="${list.listState == 3}">
                                            <div class="item-after">已处理</div>
                                        </c:if>
                                        <c:if test="${list.listState == 4}">
                                            <div class="item-after">已评价</div>
                                        </c:if>
                                        <c:if test="${list.listState == 5}">
                                            <div class="item-after">已延迟</div>
                                        </c:if>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block" style="font-size: 0.7rem;">
                            <ul>
                                <c:forEach items="${list.lists}" var="model">
                                    <li class="item-content">
                                        <div class="item-media"><i class="icon icon-f7"></i></div>
                                        <div class="item-inner">
                                            <div class="item-title">${model.liststatetime}</div>
                                            <div class="item-after">${model.listDescription}</div>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title"></div>
                                        <div class="item-after"></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-header">
            </div>
        </div>
        <nav class="bar bar-tab" style="background: #0E4d94;opacity: 1;">
            <a class="tab-item external active" href="${contextPath}/app/worker/routeDoingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-hourglass-o" aria-hidden="true"></i>
                </span>
                <c:if test="${maintenanceList.onDoMaintenanceSum != 0}">
                    <span class="badge">
                            ${maintenanceList.onDoMaintenanceSum}
                    </span>
                </c:if>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeWaitingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                </span>
                <c:if test="${maintenanceList.waitToDoMaintenanceSum !=0}">
                     <span class="badge">
                             ${maintenanceList.waitToDoMaintenanceSum}
                     </span>
                </c:if>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeLatestList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-file-text-o" aria-hidden="true"></i>
                </span>
                <c:if test="${maintenanceList.allDoMaintenanceSum != 0}">
                     <span class="badge">
                             ${maintenanceList.allDoMaintenanceSum}
                     </span>
                </c:if>
            </a>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>