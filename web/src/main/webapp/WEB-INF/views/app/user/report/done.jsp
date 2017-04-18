<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>

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
                                    <div class="item-title">单号</div>
                                    <div class="item-after">00000001111112222222333333</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-inner">
                                    <div class="item-title">报修时间</div>
                                    <div class="item-after">2017/02/12 11:30</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-inner">
                                    <div class="item-title">报修人手机</div>
                                    <div class="item-after">15812135555</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-inner">
                                    <div class="item-title">报修队列数</div>
                                    <div class="item-after">4条</div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <a href="../track/track.html" class="button button-fill button-success">我的报修</a>
                <a href="../index.html" class="button button-fill button-primary">返回首页</a>
            </div>
        </div>

        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h1>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>