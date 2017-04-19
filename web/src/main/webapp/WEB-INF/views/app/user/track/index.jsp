<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current">
        <header class="bar bar-nav bar-nav-2" style="background-color: #0E4d94; position: static;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">大连交通大学后勤报修系统</h1>
        </header>
        <div class="card">
            <div class="card-header">
                <header class="bar bar-nav" style="background-color: #FFF;">
                    <h1 class="title"><b>查看报修和维修进度查询</b></h1>
                </header>
            </div>
        </div>
        <div class="card">
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
                                            <input type="text" placeholder="请输入您报修时填写的电话号码">
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="content-block">
            <div class="row">
                <div class="col-50"><a href="#" class="button button-big button-fill button-success">查询</a></div>
                <div class="col-50"><a href="#" class="button button-big button-fill button-warning">返回</a></div>
            </div>
        </div>
        <div class="list-block">
            <ul>
                <li class="item-content">
                    <div class="item-media"><i class="icon icon-f7"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title">近一月全校报修数量：
                        </div>
                    </div>
                </li>
                <li class="item-content">
                    <div class="item-media"><i class="icon icon-f7"></i></div>
                    <div class="item-inner">
                        <div class="item-title">一期</div>
                        <div class="item-after">1条</div>
                    </div>
                </li>
                <li class="item-content">
                    <div class="item-media"><i class="icon icon-f7"></i>
                    </div>
                    <div class="item-inner">
                        <div class="item-title">二期</div>
                        <div class="item-after">3条</div>
                    </div>
                </li>
                <li class="item-content">
                    <div class="item-media"><i class="icon icon-f7"></i></div>
                    <div class="item-inner">
                        <div class="item-title">南区</div>
                        <div class="item-after">5条</div>
                    </div>
                </li>
            </ul>
        </div>
        <nav class="bar bar-tab" style="background-color: #0E4d94;">
            <h1 class="title title-2" style=" color: #FFF; font-weight: bold;">程序设计：大连交通大学56工作室</h1>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>