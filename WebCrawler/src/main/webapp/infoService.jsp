<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/infoService.css?e"></link>
<script type="text/javascript" src="styles/js/infoService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active" onClick="location.href='infoService.do'">수집 중!</div>
		<div class="sideItem" onClick="location.href='tmService.jsp'">텍스트마이닝</div>
		<div class="sideItem">시각화</div>
	</div>
	<div class="contents">
		<c:forEach items="${infoList }" var="info">
					<div class="contentCard" onclick="location.href='dataService.do?iseq=${info.iseq}'">
					<h1>${info.title }</h1>
					<hr/>
					<c:forEach items="${info.dataList }" var="data">
						<p>
							${data }
						</p>
					</c:forEach>
				</div>
		</c:forEach>
	</div>
	<div class="sideContent">
		<div class="sideItem" onclick="onInsertPage('crawling')">CRAWLING</div>
		<div class="sideItem" onclick="onInsertPage('csv')">CSV</div>
	</div>
</section>
</body>
</html>