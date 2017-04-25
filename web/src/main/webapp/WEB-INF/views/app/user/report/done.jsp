<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>
        <div class="content">
            <div class="card">
                <div class="card-content">
                    <div class="list-block media-list">
                        <ul>
                            <li class="item-content">
                                <div class="item-media">
                                    <img src="${contextPath}/static/images/app/user_done.png" width="44">
                                </div>
                                <div class="item-inner">
                                    <p><b>我们已经受理您的报修</b></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="list-block">
                            <ul>
                                <li class="item-content">
                                    <div class="item-inner">
                                        <div class="item-title">报修单号</div>
                                        <div class="item-after">${maintenance.listNumber}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-inner">
                                        <div class="item-title">报修时间</div>
                                        <div class="item-after">${maintenance.liststatetime}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-inner">
                                        <div class="item-title">报修人手机</div>
                                        <div class="item-after">${maintenance.userTel}</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-inner">
                                        <div class="item-title">报修队列数</div>
                                        <div class="item-after">${maintenance.sum}条</div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <a href="${contextPath}/app/user/select/all/maintenance.action" class="button button-fill button-success" style="background-color: #02cbe9;" external>我的报修</a>
                    <a href="${contextPath}/app/user/redirect/index.action" class="button button-fill button-primary" style="background-color: #fdb970;" external>返回首页</a>
                </div>
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



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>