<%@ include file="topHead.jsp" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<title>Board Confirm Page</title>
</head>
<body>
<!-- Navbar : Login, �˶� ���� -->
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

<form method="post">
<div class="container" style="margin-top: 15px;">
<div class="row">
	<table class="table">
	  <tbody>
		<tr>
		<td colspan="2">
			<div style="margin:auto; text-align: center;">
				 <img src="download/test.png">
			</div>
		    <!-- Button -->
			<div class="container" style="margin-top: 15px;">
			<div class="row">
				<div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style="margin:auto;">
				  	<div class="btn-group mr-2" role="group" aria-label="First group">
				  	<button type="button" class="btn btn-secondary" onclick="location.href='getTmInfo.do?seq=${rtnseq }'">Cancel</button>
				  	<button type="button" id="crawling" class="btn btn-secondary" onclick="javascript: form.action='multiAdd_proc.do'; form.submit()">Result Add</button>
				  	</div>
			  	</div>
			</div>
			</div>
		</td>
		</tr>
	  </tbody>
	</table>
</div>
</div>
</form>
</body>
</html>