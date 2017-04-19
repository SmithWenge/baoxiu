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
                            <i class="fa fa-circle" aria-hidden="true" style="color:#CC00FF;"></i>
                            <b>报修单操作(00000000)</b>
                        </h3>
                    </header>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <div class="card-content">
                        <div class="card-content-inner">
                            <div class="list-block">
                                <ul>
                                    <li>
                                        <div class="item-content">
                                            <div class="item-media"><i class="icon icon-form-gender"></i></div>
                                            <div class="item-inner">
                                                <div class="item-input">
                                                    <select>
                                                        <option>请选择操作</option>
                                                        <option>已修复</option>
                                                        <option>经勘查，无故障</option>
                                                        <option>非本部门维修范围</option>
                                                        <option>等待缺少的配件</option>
                                                        <option>其他情况</option>
                                                        <select>
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
                                                        <input type="text" placeholder="请填写紧要操作（可选）">
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
                            <a href="done.html" class="button button-fill button-success">提交</a>
                        </div>
                        <div class="col-30" style="margin-right: .5rem;">
                            <a href="../index.html" class="button button-fill button-danger">放弃</a>
                        </div>
                    </div>
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
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">故障描述</div>
                                        <div class="item-after">305教室的灯不亮</div>
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
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">2017/4/13&nbsp;14:00</div>
                                        <div class="item-after">系统派单（电工一组）</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">2017/4/13&nbsp;14:00</div>
                                        <div class="item-after">管理员定义流程</div>
                                    </div>
                                </li>
                                <li class="item-content">
                                    <div class="item-media"><i class="icon icon-f7"></i></div>
                                    <div class="item-inner">
                                        <div class="item-title">2017/4/13&nbsp;14:00</div>
                                        <div class="item-after">用户报修</div>
                                    </div>
                                </li>
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
        </div>
        <div class="card-footer">
        </div>
        <nav class="bar bar-tab" style="background: #0E4d94;opacity: 1;">
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