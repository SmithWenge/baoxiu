<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav" style="background-color: #0E4d94; position: static;">
            <h1 class="title" style=" color: #FFF; font-weight: bold;">大连交通大学后勤维修处理系统</h1>
            <a class="icon pull-right" style="color: #fff;">
                <i class="fa fa-user-circle" aria-hidden="true"></i>
            </a>
        </header>
        <div class="content">
            <div class="card">
                <div class="card-header" style="font-weight: bold;">
                    基本信息
                </div>
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block">
                            <ul>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">姓名</div>
                                        <div class="item-after">${info.workerName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">工种</div>
                                        <div class="item-after">${info.typeName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">所属维修组</div>
                                        <div class="item-after">${info.groupName}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">工作单位</div>
                                        <div class="item-after">${info.workerUnite}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">职位</div>
                                        <div class="item-after">${info.workerJob}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">维修人员状态</div>
                                        <div class="item-after">${info.workerState}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">工作部门</div>
                                        <div class="item-after">${info.workerDepartment}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-medi   a"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">&nbsp;&nbsp;&nbsp;联系电话</div>
                                        <div class="item-after">${info.workerTel}</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card">
                <div class="card-header" style="font-weight: bold;">
                    修改密码
                    <a style="color: red">${message}</a>
                </div>
                <form action="${contextPath}/app/worker/changePass.action" method="post" id="workerPasswordForm">
                <div class="card-content">
                    <div class="list-block">
                        <ul>
                            <!-- Text inputs -->
                            <input type="hidden" name="workerTel" value="${info.workerTel}">
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-password"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">原密码</div>
                                        <div class="item-input">
                                            <input type="password" name="workerPass" placeholder="请输入原密码" class="">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-password"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">新密码</div>
                                        <div class="item-input">
                                            <input type="password" name="newWorkerPass" id="newWorkerPass" placeholder="请输入新密码" class="">
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-password"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">确认密码</div>
                                        <div class="item-input">
                                            <input type="password" name="newWorkerPassRe" id="newWorkerPassRe" placeholder="请再次输入新密码" class="">
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
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
                </form>
            </div>
            <div class="content-block" style="margin-top: 0;">
                <div class="row">
                    <div class="col-80" style="margin-left: 13%;"><a href="${contextPath}/app/worker/logout.action" class="button button-big button-fill button-danger">退出登录</a></div>
                </div>
            </div>
        </div>
        <nav class="bar bar-tab" style="background: #0E4d94;opacity: 1;">
            <a class="tab-item external active" href="${contextPath}/app/worker/routeDoingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                </span>
                <span class="badge">${maintenanceList.onDoMaintenanceSum}</span>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeWaitingList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-bell-o" aria-hidden="true"></i>
                </span>
                <span class="badge">${maintenanceList.waitToDoMaintenanceSum}</span>
            </a>
            <a class="tab-item external" href="${contextPath}/app/worker/routeLatestList.action" style="color: #fff;">
                <span class="icon">
                    <i class="fa fa-briefcase" aria-hidden="true"></i>
                </span>
                <span class="badge">${maintenanceList.allDoMaintenanceSum}</span>
            </a>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>
<script type="text/javascript" src="${contextPath}/static/plugin/jquery-validate/jquery.validate.js" ></script>

<%--<script type="text/javascript">--%>
    <%--$(function() {--%>
        <%--$("#workerPasswordForm").validate({--%>
            <%--rules: {--%>
                <%--newWorkerPass: {--%>
                    <%--required: true,--%>
                    <%--minlength: 5,--%>
                    <%--maxlength: 20--%>
                <%--},--%>
                <%--newWorkerPassRe: {--%>
                    <%--required: true,--%>
                    <%--minlength: 5,--%>
                    <%--maxlength: 20,--%>
                    <%--equalTo: '#newWorkerPass'--%>
                <%--}--%>
            <%--},--%>
            <%--messages: {--%>
                <%--adminLoginPassNew: {--%>
                    <%--required: "请输入新密码.",--%>
                    <%--minlength: "请确定新密码的长度为5到20之间.",--%>
                    <%--maxlength: "请确定新密码的长度为5到20之间."--%>
                <%--},--%>
                <%--adminLoginPassNewRe: {--%>
                    <%--required: "请输入新密码.",--%>
                    <%--minlength: "请确定新密码的长度为5到20之间.",--%>
                    <%--maxlength: "请确定新密码的长度为5到20之间.",--%>
                    <%--equalTo: "请保证两次输入的新密码一样."--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>


<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>