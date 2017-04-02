<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>大连交通大学后勤保修系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${contextPath}/static/images/djtu.ico" />
    <link rel="stylesheet" href="${contextPath}/static/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="${contextPath}/static/plugin/template/css/main.css">

    <style type="text/css">
        label.error {
            color: red;
        }
    </style>
</head>
<body>