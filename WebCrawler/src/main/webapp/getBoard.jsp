<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	ArrayList<InfoVO> infoList = (ArrayList) request.getAttribute("infoList");
	HashMap<String,List<DataVO>> dataMap = (HashMap) request.getAttribute("dataMap");    
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>Board</title>
</head>
<body>
<!-- Navbar : Login, 알람 정보 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
   <a class="navbar-brand" href="#">MODOO</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Welcome! ${user.name }<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="logout.do">logout</a>
      </li>
    </ul>
    <div id="roomOpen"></div>
  </div>
</nav>

<!-- getBoardDesign -->
<form method="post" name="form">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <tbody>
	    <tr>
	      <th scope="row">제목</th>
	      <td>${board.title }</td>
	    </tr>
	    <tr>
	      <th scope="row">작성자</th>
	      <td>${board.id }</td>
	    </tr>
	    <tr>
	      <th scope="row">내용</th>
	      <td>${board.content }</td>
	    </tr>
	    <tr>
	    <td colspan="2">
	    <div class="input-group mb-3">
	    	<input type="hidden" name="bseq" value="${board.bseq }"/>
	    	<input type="text" class="form-control" name="ctitle" placeholder="CSV Title" aria-label="Recipient's username" aria-describedby="basic-addon2">
			<div class="input-group-append">
	    		<button class="btn btn-outline-secondary" type="button" onclick="javascript: form.action='convertCSV.do'; form.submit()">Convert CSV</button>
	    	</div>
	    </div>
	    <td>
	    </tr>
	    <c:if test="${board.id eq user.id }">
		<tr>
			<td colspan="2"> 
				<div class="input-group mb-3">
				  <input type="hidden" name="id" value="${board.id }"/>
				  <input type="hidden" name="bnum" value="${board.bseq }"/>
				  <input type="text" class="form-control" name="rtitle" placeholder="Chat Room Title" aria-label="Recipient's username" aria-describedby="basic-addon2">
				  <div class="input-group-append">
				    <button class="btn btn-outline-secondary" type="button" onclick="javascript: send()">New Chat</button>
				  </div>
				  	
				</div>
			</td>
		</tr>
		</c:if>
	    <tr>
	    	<td colspan="2">
	    	<nav>
	    	<!-- Info정보 -->
			<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
			<c:forEach items="${infoList }" var="info">
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-${info.seq }" role="tab" aria-controls="nav-test" aria-selected="false">${info.field }</a>
			</c:forEach>
			<a class="nav-item nav-link" id="nav-home-tab" data-toggle="tab" href="#nav-table" role="tab" aria-controls="nav-test" aria-selected="false">All</a>
			</div>
			</nav>
			</td>
		</tr>
		<tr>
			<!-- Data정보 -->
			<td colspan="2">
			<div class="tab-content" id="nav-tabContent">
			<%
				for(InfoVO info : infoList){
					List<DataVO> dataList = dataMap.get(info.getSeq()+"");
			%>
			<div class="tab-pane fade" id="nav-<%=info.getSeq() %>" role="tabpanel" aria-labelledby="nav-home-tab">
			     <table class="table">
				  <tbody>
				  			<%for(DataVO data : dataList){ %>
						   <tr>
							   	<td>
							   		<%=data.getData() %>
							   	</td>
						   </tr>
						    <%} %>
			  	 </tbody>
				</table>
			</div>
		    <% } %>
		    <div class="tab-pane fade" id="nav-table" role="tabpanel" aria-labelledby="nav-home-tab">
		    	<table class="table">
		    	<thead>
		    	<tr>
		    	<%
		    	for(InfoVO info : infoList){
		    	%>
		    				<th scope="col"><%=info.getField() %></th>
		    	<% } %>
		    	</tr>
		    	</thead>
		    	<tbody>
		    	<tr>
		    	<% for(InfoVO info : infoList){ 
		    		List<DataVO> dataList = dataMap.get(info.getSeq()+"");
		    	%>
		    		<td>
		    		<table class="table">
		    		<tbody>
				  			<%for(DataVO data : dataList){ %>
						   <tr>
							   	<td>
							   		<%=data.getData() %>
							   	</td>
						   </tr>
						    <%} %>
			  		 </tbody>
		    		</table>	
		    		</td>
		    	<%} %>
		    	</tr>
		    	</tbody>
		    	</table>
		    </div>
		    </div>
			</td>
	    </tr>
	  </tbody>
	</table>
</div>
</div>

<div class="container" style="margin-top: 15px;">
<div class="row">
	<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
	  	<div class="btn-group mr-2" role="group" aria-label="First group">
	  	<c:if test="${board.id eq user.id }">
	  	<button type="button" class="btn btn-secondary" onclick="javascript: form.action='deleteBoard.do'; form.submit()">DeleteBoard</button>
	    </c:if>
	    <button type="button" class="btn btn-secondary" onclick="javascript: form.action='getBoardList.do'; form.submit()">BoardList</button>
	  	</div>
  	</div>
</div>
</div>
<input type="hidden" value='${user.id }' id='alarm_id' />
</form>
<c:if test="${board.id eq user.id }">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
    var webSocket = new WebSocket('ws://192.168.24.7:8080/WCrawl/alarm.do');
    webSocket.onerror = function(event) {
        onError(event)
    };
    webSocket.onopen = function(event) {
        onOpen(event)
    };
    webSocket.onmessage = function(event) {
        onMessage(event)
    };
    function onOpen(event){
    	$("#roomOpen").html("<p style='color:white; '><strong>채팅방 생성 알람 작동중</strong></p>");
    };
    function send() {
    	var form = document.form;
    	form.action="roomAdd_proc.do";
        webSocket.send($("#alarm_id").val() + " 님이 새로운 채팅방을 오픈했습니다.");
        form.submit();
    }
    function onError(event) {
        alert(event.data);
    }
</script>
</c:if>
</body>
</html>