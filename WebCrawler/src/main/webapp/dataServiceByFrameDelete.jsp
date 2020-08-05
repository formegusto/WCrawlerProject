<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/dataServiceByFrame.css?z"></link>
<script type="text/javascript" src="styles/js/dataServiceByFrame.js?x"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem active">가공 중!</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=read'">읽기</div>
		<div class="sideItem" onclick="location.href='dataServiceByFrame.do?fseq=${frame.fseq}&mode=update'">수정</div>
		<div class="sideItem active">삭제 중!</div>
	</div>
	<form name="saveFrame" method="post" action="frameMake.do">
		<input type="text" name="title" value="" id="title"/>
		<table>
			<tbody>
				<c:forEach items="${fhiList }" var="fhi">
					<tr>
						<td class="fieldHeader">
							${fhi.field }
						</td>
						<c:forEach items="${fhi.dataList }" var="dvo">
							<td>
								${dvo.data }
								<div class="dataTools" onclick="pushDelData(this,${dvo.dseq})">
									지나간 데이터는 돌아오지 않습니다.
								</div>
							</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<div class="sideContent">
		<div class="sideItem" onclick="onDelSubmit()">삭제</div>
		<hr/>
		<div class="sideItem">인풋</div>
		<div class="sideItem" onclick="location.href='frameService.do'">CANCLE</div>
	</div>
	<form name="delForm" method="post" action="deleteDataByFrame.do" style="display:none">
		<input type="hidden" name="fseq" value="${frame.fseq }" />
		<input id="delDseqInput" type="hidden" name="dseqList" value=""/>
	</form>
</section>
</body>
</html>