<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>
        <div class="card">
            <div class="card-content">
                <div class="card-header color-white no-border no-padding">
                    <div valign="bottom" class="card-header color-white no-border no-padding">
                        <img class='card-cover' src="${contextPath}/static/images/app/user_index_header.jpg">
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-content">
                <div class="card-content">
                    <div class="card-content-inner">
                        <div class="row">
                            <div class="col-50">
                                <div class="card" style="margin-left: 0px; margin-right: 0px;">
                                    <div class="card-content">
                                        <div class="list-block media-list">
                                            <ul>
                                                <li class="item-content">
                                                    <div class="item-media">
                                                        <a href="report/index.html">
                                                            <i class="fa fa-wrench" aria-hidden="true"></i>
                                                        </a>
                                                    </div>
                                                    <div class="item-inner">
                                                        <div class="item-title-row">
                                                            <a href="report/index.html">
                                                                <div class="item-title">我要报修</div>
                                                            </a>
                                                        </div>
                                                        <a href="report/index.html">
                                                            <div class="item-subtitle">Repair</div>
                                                        </a>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-50">
                                <div class="card" style="margin-left: 0px; margin-right: 0px;">
                                    <div class="card-content">
                                        <div class="list-block media-list">
                                            <ul>
                                                <li class="item-content">
                                                    <div class="item-media">
                                                        <a href="#">
                                                            <i class="fa fa-suitcase" aria-hidden="true"></i>
                                                        </a>
                                                    </div>
                                                    <div class="item-inner">
                                                        <div class="item-title-row">
                                                            <div class="item-title">
                                                                <a href="#">我的报修</a>
                                                            </div>
                                                        </div>
                                                        <div class="item-subtitle">
                                                            <a href="#">Tracking</a>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <b>紧急保修方式</b>
            </div>
            <div class="card-content">
                <div class="card-content-inner">自来水管线爆裂等需要紧急维修的情况,请直接拨打后勤处维修科24小时报修电话:<b style="color: green;">84108410</b>.</div>
            </div>
        </div>
        <div class="card">
            <div class="card-header">
                <b>后勤风采</b>
            </div>
            <div class="card-content">
                <div class="list-block media-list">
                    <ul>
                        <li class="item-content">
                            <div class="item-media">
                                <a href="#">
                                    <img src="${contextPath}/static/images/app/user_index_fengcai.png" width="50">
                                </a>
                            </div>
                            <div class="item-inner">
                                <a href="#">
                                    <p style="font-size: .7rem; color: #000000;">近日,后勤处精心购置了一批树苗,于3月25日在教学南区开展了植树活动.</p>
                                </a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h1>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>