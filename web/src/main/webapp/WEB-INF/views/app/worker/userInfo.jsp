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
                                    <div class="item-after">维修员工一</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">所属维修组</div>
                                    <div class="item-after">维修一组</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">工种</div>
                                    <div class="item-after">水电工</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">联系电话</div>
                                    <div class="item-after">13012345678</div>
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
            </div>
            <div class="card-content">
                <div class="list-block">
                    <ul>
                        <!-- Text inputs -->
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-password"></i></div>
                                <div class="item-inner">
                                    <div class="item-title label">原密码</div>
                                    <div class="item-input">
                                        <input type="password" placeholder="请输入原密码" class="">
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
                                        <input type="password" placeholder="请输入新密码" class="">
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
                                        <input type="password" placeholder="请再次输入新密码" class="">
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
                        <a href="done.html" class="button button-fill button-success">提交</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="content-block" style="margin-top: 0;">
            <div class="row">
                <div class="col-80" style="margin-left: 13%;"><a href="#" class="button button-big button-fill button-danger">退出登录</a></div>
            </div>
        </div>
        <nav class="bar bar-tab" style="background: #0E4d94;">
            <a class="tab-item external active" href="#" style="color: #fff;">
					    <span class="icon">
					    	<i class="fa fa-home fa-lg" aria-hidden="true"></i>
					    </span>
            </a>
            <a class="tab-item external" href="#" style="color: #fff;">
					    <span class="icon">
					    	<i class="fa fa-bell-o" aria-hidden="true"></i>
					    </span>
                <span class="badge">2</span>
            </a>
            <a class="tab-item external" href="#" style="color: #fff;">
					    <span class="icon">
					    	<i class="fa fa-briefcase" aria-hidden="true"></i>
					    </span>
            </a>
        </nav>
    </div>
</div>

<%@ include file="/WEB-INF/include/app/appJavascript.jsp"%>



<%@ include file="/WEB-INF/include/app/appFooter.jsp"%>