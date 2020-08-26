<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/boardService.css?d"></link>
<script type="text/javascript" src="styles/js/boardService.js?"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="contents">
		<ul class="contentNav">
			<li class="navItem active">
				�Խ��� ���� ��!
			</li>
			<li class="navItem" onclick="location.href='roomService.do'">
				ä�ù�
			</li>
		</ul>
		<div class="searchBar">
					<div>
							<input id="keyword" type="text" placeholder="����� ���ϴ� �˻���� ���󰡰ھ�." />
							<span class="textBottomEffect"></span>
					</div>
						<button type="button" onclick="onKeyword('board')">�˻�</button>
		</div>
		<table>
			<thead>
				<tr>
					<td class="title">
						����
					</td>
					<td class="subtd">
						�ۼ���
					</td>
					<td class="subtd">
						�ۼ���
					</td>
				</tr>
			</thead>
			<tbody>
					<c:forEach items="${boardList }" var="board">
					<tr>
						<td class="title" onclick="location.href='boardDetailService.do?bseq=${board.bseq}'">
							${board.title }
						</td>
						<td class="subtd">
							${board.id }
						</td>
						<td class="subtd">
							${board.regdate }
						</td>
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
</section>
</body>
</html>