/**
 * Created by shen on 2017/3/15.
 */

require.config({
    urlArgs: "v=201703220103",
    paths: {
        'jquery': ['//cdn.bootcss.com/jquery/2.2.4/jquery.min'],
        'css': ['/admin-template/static/js/css.min'],
        'layui': ['/admin-template/static/layui/layui'],
        'highcharts': ['/admin-template/static/js/highcharts'],
        'jsgrid': ['/admin-template/static/jsgrid/jsgrid.min']
    },
    shim: {
        'jsgrid': {
            deps: ['css!/admin-template/static/jsgrid/jsgrid.min.css', 'css!/admin-template/static/jsgrid/jsgrid-theme.min.css']
        }
    }
});

require(['jquery', 'jsgrid', 'layui', 'css', 'highcharts'], function ($, jsGrid) {
    layui.config({dir: '/admin-template/static/layui/'});
    layui.use(['layer', 'element', 'jquery', 'form', 'laydate', 'layedit', 'code'], function () {
        var from = layui.form(),
            code = layui.code(),
            layer = layui.layer;

        // 通用 ajax-post
        $(document).on('click', '.ajax-post', function () {
            var that = $(this);
            if (that.hasClass('layui-btn')) {
                that.addClass('layui-btn-disabled').prop('disabled', true);
            }
            var url = $('#' + that.attr('ajax-form')).attr('action');
            var query = $('#' + that.attr('ajax-form')).serialize();
            var title = that.attr('ajax-title') ? that.attr('ajax-title') : "操作";
            if (that.hasClass('confirm')) {
                layer.confirm('确定' + title + '吗?', {
                    icon: 3,
                    title: '温馨提示',
                    btn: ['确定'],
                    cancel: function () {
                        if (that.hasClass('layui-btn')) {
                            that.removeClass('layui-btn-disabled').prop('disabled', false);
                        }
                    }
                }, function () {
                    var load = layer.load(2);
                    $.post(url, query).success(function (res) {
                        if (res.status == 1) {
                            layer.msg(res.info, {icon: 6, time: 2000}, function () {
                                if (res.url) {
                                    location.href = res.url
                                } else {
                                    location.reload();
                                }
                            });
                        } else {
                            layer.msg(res.info, {icon: 2, time: 2000}, function () {
                                if (res.url) {
                                    location.href = res.url
                                } else {
                                    location.reload();
                                }
                            });
                        }
                    });
                })
            } else {
                var load = layer.load(2);
                $.post(url, query).success(function (res) {
                    if (res.status == 1) {
                        layer.msg(res.info, {icon: 6, time: 2000}, function () {
                            if (res.url) {
                                location.href = res.url
                            } else {
                                location.reload();
                            }
                        });
                    } else {
                        layer.msg(res.info, {icon: 2, time: 2000}, function () {
                            if (res.url) {
                                location.href = res.url
                            } else {
                                location.reload();
                            }
                        })
                    }
                });
            }
        });

        // 通用 ajax-get
        $(document).on('click', '.ajax-get', function () {
            var that = $(this);
            if (that.hasClass('layui-btn')) {
                that.addClass('layui-btn-disabled').prop('disabled', true);
            }
            var url = that.attr('ajax-url');
            var title = that.attr('ajax-title') ? that.attr('ajax-title') : "操作";
            if (that.hasClass('confirm')) {
                layer.confirm('确定' + title + '吗?', {
                    icon: 3,
                    title: '温馨提示',
                    btn: ['确定'],
                    cancel: function () {
                        if (that.hasClass('layui-btn')) {
                            that.removeClass('layui-btn-disabled').prop('disabled', false);
                        }
                    }
                }, function () {
                    var load = layer.load(2);
                    $.get(url, function (res) {
                        if (res.status == 1) {
                            layer.msg(res.info, {icon: 6, time: 2000}, function () {
                                if (res.url) {
                                    location.href = res.url
                                } else {
                                    location.reload();
                                }
                            });
                        } else {
                            layer.msg(res.info, {icon: 2, time: 2000}, function () {
                                if (res.url) {
                                    location.href = res.url
                                } else {
                                    location.reload();
                                }
                            });
                        }
                    });
                })
            } else {
                var load = layer.load(2);
                $.get(url, function (res) {
                    if (res.status == 1) {
                        layer.msg(res.info, {icon: 6, time: 2000}, function () {
                            if (res.url) {
                                location.href = res.url
                            } else {
                                location.reload();
                            }
                        });
                    } else {
                        layer.msg(res.info, {icon: 2, time: 2000}, function () {
                            if (res.url) {
                                location.href = res.url
                            } else {
                                location.reload();
                            }
                        });
                    }
                });
            }
        });

        // 通用模态框
        $(document).on('click', '.show-modal', function () {
            layer.open({
                type: 2,
                anim: 2,
                title: $(this).attr('modal-title') ? $(this).attr('modal-title') : '窗口',
                content: $(this).attr('modal-url'),
                area: [$(this).attr('modal-width') ? $(this).attr('modal-width') : '350px', $(this).attr('modal-height') ? $(this).attr('modal-height') : '280px']
            });
        });

        // 图表
        if ($('#container').length > 0) {
            var myChart = Highcharts.chart('container', {
                chart: {
                    type: 'bar'
                },
                title: {
                    text: 'Fruit Consumption'
                },
                xAxis: {
                    categories: ['Apples', 'Bananas', 'Oranges']
                },
                yAxis: {
                    title: {
                        text: 'Fruit eaten'
                    }
                },
                series: [{
                    name: 'Jane',
                    data: [1, 0, 4]
                }, {
                    name: 'John',
                    data: [5, 7, 3]
                }]
            });
        }


        // 数据网格
        var clients = [
            {'Id': 1, "Name": "Otto Clay", "Age": 25, "Country": 1, "Address": "Ap #897-1459 Quam Avenue", "Married": false},
            {'Id': 2, "Name": "Connor Johnston", "Age": 45, "Country": 2, "Address": "Ap #370-4647 Dis Av.", "Married": true},
            {'Id': 3, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 3, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 4, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 5, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 6, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 7, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 8, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 10, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 11, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 12, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 13, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 14, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 15, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 16, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 17, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 18, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 19, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 20, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 21, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 22, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 23, "Name": "Lacey Hess", "Age": 29, "Country": 3, "Address": "Ap #365-8835 Integer St.", "Married": false},
            {'Id': 24, "Name": "Timothy Henson", "Age": 56, "Country": 1, "Address": "911-5143 Luctus Ave", "Married": true},
            {
                'Id': 5,
                "Name": "Ramona Benton",
                "Age": 32,
                "Country": 3,
                "Address": "Ap #614-689 Vehicula Street",
                "Married": true
            }
        ];

        var countries = [
            {Name: "", Id: 0},
            {Name: "United States", Id: 1},
            {Name: "Canada", Id: 2},
            {Name: "United Kingdom", Id: 3}
        ];

        $("#data-grid").jsGrid({
            width: "100%",
            height: "500px",
            inserting: false,
            editing: false,
            sorting: false,
            paging: true,
            pagerFormat: "页码: {first} {prev} {pages} {next} {last}    {pageIndex} / {pageCount}",
            pagePrevText: "上一页",
            pageNextText: "下一页",
            pageFirstText: "首页",
            pageLastText: "尾页",
            data: clients,
            fields: [
                {title: 'ID', name: 'Id', width: '25'},
                {title: "姓名", name: "Name", type: "text", width: 150, validate: "required"},
                {name: "Age", type: "number", width: 50},
                {name: "Address", type: "text", width: 200},
                {name: "Country", type: "select", items: countries, valueField: "Id", textField: "Name"},
                {name: "Married", type: "checkbox", title: "Is Married", sorting: false},
                {title: '操作', type: "text", align: 'center', cellRenderer: function () {
                    return "<td><button class='layui-btn layui-btn-danger layui-btn-mini layui-btn-radius'>删除</button></td>"
                }}
            ]
        });
    })
});