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
                    <h1 class="title"><b>故障报修</b></h1>
                </header>
            </div>
        </div>

        <div class="card">
            <div class="card-header" style="font-size: .7rem;">请填选下列内容,如列表中没有,请选择"其它"</div>
            <div class="card-content">
                <div class="card-content-inner">
                    <div class="list-block">
                        <ul>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">校区</div>
                                        <div class="item-input">
                                            <select>
                                                <option>其他</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">地点</div>
                                        <div class="item-input">
                                            <select>
                                                <option>其他</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">位置</div>
                                        <div class="item-input">
                                            <select>
                                                <option>其他</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">设备</div>
                                        <div class="item-input">
                                            <select>
                                                <option>其他</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="align-top">
                                <div class="item-content">
                                    <div class="item-media"><i class="icon icon-form-comment"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title label">描述</div>
                                        <div class="item-input">
                                            <textarea placeholder="请详细描述楼号和房间号码(室外请说明附近的参照物)并描述故障情况." style="font-size: .7rem;"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="content-block" style="margin-top: 2rem; margin-bottom: 0.5rem;">
                        <div class="row">
                            <div class="col-50">
                                <a href="../index.html" class="button button-big button-fill button-danger">放弃</a>
                            </div>
                            <div class="col-50">
                                <a href="confirm.html" class="button button-big button-fill button-success">下一步</a>
                            </div>
                        </div>
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