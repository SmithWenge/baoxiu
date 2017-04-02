<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/include/nav.jsp"%>


    <!-- 内容主体 -->
    <div class="layui-body body">
        <fieldset class="layui-elem-field">
            <legend>更新日志</legend>
            <div class="layui-field-box">
                <h1>功能开发进度</h1>
                <pre>
                    使用了requirejs，第一次用，所以有不合适的地方多多包涵，欢迎大家下载改版
                    项目源码在Coding.net上面
                    地址 https://git.coding.net/shenfakuan/admin-template.git
                    演示地址 http://shenfakuan.coding.me/admin-template/
                    大家可以在 https://coding.net/u/shenfakuan/p/admin-template/topic 讨论
                </pre>
                <table class="layui-table">
                    <thead>
                    <tr>
                        <td>功能</td>
                        <td>状态</td>
                        <td>完成时间</td>
                        <td>备注</td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href="./show-modal.html">通用modal弹框</a></td>
                        <td>√</td>
                        <td>2017-03-21</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><a href="./ajax-get.html">通用ajax-get提交</a></td>
                        <td>√</td>
                        <td>2017-03-21</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><a href="./ajax-get.html">通用ajax-post提交</a></td>
                        <td>√</td>
                        <td>2017-03-21</td>
                        <td>-</td>
                    </tr>
                    <tr>
                        <td><a href="./data-grid.html">数据表格</a></td>
                        <td>√</td>
                        <td>2017-03-22</td>
                        <td><a href="http://js-grid.com/" target="_blank">点击查看js-grid文档</a></td>
                    </tr>
                    <tr>
                        <td><a href="./highcharts.html">图表功能</a></td>
                        <td>√</td>
                        <td>2017-03-22</td>
                        <td><a href="http://www.highcharts.com/" target="_blank">点击查看highcharts文档</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>

<%@ include file="/WEB-INF/include/javascript.jsp"%>
<%@ include file="/WEB-INF/include/footer.jsp"%>