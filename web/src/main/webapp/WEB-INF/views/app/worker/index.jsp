<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/app/appHeader.jsp"%>

<div class="page-group">
    <div class="page page-current" style="background-color: #164A81;">
        <div class="content-padded" style="color: #fff;">
            <h1 style="text-align: center;">大连交通大学</h1>
            <h1 style="text-align: center;">后勤维修处理系统</h1>
            <h1 style="text-align: center;">&nbsp;</h1>
        </div>

        <form action="${contextPath}/app/worker/login.action" method="post">
            <div class="card">
                <div class="card-header">
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="list-block">
                                <ul>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="fa fa-user-circle" aria-hidden="true"></i>
                                            </div>
                                            <div class="item-inner">
                                                <div class="item-input">
                                                    <input type="text" id="workerTel" name="workerTel" placeholder="请输入用户名">
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-header">
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="list-block">
                                <ul>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="fa fa-key" aria-hidden="true"></i>
                                            </div>
                                            <div class="item-inner">
                                                <div class="item-input">
                                                    <input type="text" id="workerPass" name="workerPass" placeholder="请输入账号密码">
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
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="card-content">
                                <div class="card-content-inner">
                                    <header class="bar bar-nav" style="background-color: #FFF;">
                                        <button type="submit" class="button">登录</button>
                                    </header>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="content-padded" style="color: #fff;">
            <h1 style="text-align: center;">&nbsp;</h1>
            <h4 style="text-align: center;">大连交通大学56工作室设计制作</h4>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>


<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>