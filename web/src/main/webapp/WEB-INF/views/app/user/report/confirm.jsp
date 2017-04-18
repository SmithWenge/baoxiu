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
                    <h1 class="title"><b>请确认您的报修信息</b></h1>
                </header>
            </div>
        </div>

        <div class="card">
            <div class="card-content">
                <div class="card-content-inner">
                    <div class="list-block">
                        <ul>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">校区</div>
                                    <div class="item-after">南区</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">地点</div>
                                    <div class="item-after">教学楼</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">位置</div>
                                    <div class="item-after">房间内</div>
                                </div>
                            </li>
                            <li class="item-content">
                                <div class="item-media"><i class="icon icon-f7"></i></div>
                                <div class="item-inner">
                                    <div class="item-title">设备</div>
                                    <div class="item-after">灯</div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-inner">
                                        <div class="content-padded" style="font-size: .7rem;">305教室中间的灯不亮.</div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-header" style="padding: 0rem;;">
                <div class="list-block">
                    <ul>
                        <li>
                            <div class="item-content">
                                <div class="item-media"><i class="icon icon-form-email"></i></div>
                                <div class="item-inner">
                                    <div class="item-input">
                                        <input type="text" placeholder="请输入您的手机号" autofocus="true">
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="card-content">
                <div class="card-content-inner">注:手机号用来识别您的报修,务必准确输入.今后您查询报修进度需要输入您的手机号,并在后勤部门无法确定故障问题时用来与您联系,仅后勤处维修管理员可以查看您的联系方式,其他人无法获取.</div>
            </div>
            <div class="card-footer">
                <a href="index.html" class="link">返回</a>
                <div class="row">
                    <div class="col-30" style="margin-right: .5rem;">
                        <a href="done.html" class="button button-fill button-success">报修</a>
                    </div>
                    <div class="col-30" style="margin-right: .5rem;">
                        <a href="../index.html" class="button button-fill button-danger">放弃</a>
                    </div>
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