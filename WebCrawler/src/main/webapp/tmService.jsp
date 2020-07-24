<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="styles/css/tmService.css?33"></link>
<script type="text/javascript" src="styles/js/tmService.js"></script>
<title>MODOO</title>
</head>
<body>
<jsp:include page="components/header.html"/>
<section> 
	<div class="sideContent">
		<div class="sideItem" onClick="location.href='infoService.do'">呪増</div>
		<div class="sideItem active" onClick="location.href='tmService.jsp'">努什闘原戚閑 掻!</div>
		<div class="sideItem">獣唖鉢</div>
	</div>
	<div class="contents active" id="tmList">
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					戚暗 掩惟 照潤辞 蟹廃砺 益君澗闇亜? 格巷馬陥 ばばばばば;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					戚暗 掩惟 照潤辞 蟹廃砺 益君澗闇亜? 格巷馬陥 ばばばばば;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					戚暗 掩惟 照潤辞 蟹廃砺 益君澗闇亜? 格巷馬陥 ばばばばば;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					戚暗 掩惟 照潤辞 蟹廃砺 益君澗闇亜? 格巷馬陥 ばばばばば;
					</p>
			</div>
			<div class="contentCard">
			<h1>TM Title</h1>
			<hr/>
					<p>
					戚暗 掩惟 照潤辞 蟹廃砺 益君澗闇亜? 格巷馬陥 ばばばばば;
					</p>
			</div>
	</div>
	<div class="contents" id="tmMakeList">
			<div class="contentCard" onClick="location.href='infoServiceByTm.do'">
			<h1>WordCloud</h1>
			<hr/>
					<p>
					舘嬢税 朔亀呪研 域至馬食 WordCloud稽 妊薄 背 掃艦陥.
					</p>
			</div>
			<div class="contentCard">
			<h1>SocialNetworkAnalysis</h1>
			<hr/>
					<p>
					唖 舘嬢級 娃税 尻淫失聖 尻衣失聖 搭背 妊薄 背 掃艦陥.
					</p>
			</div>
			<div class="contentCard">
			<h1>SentimentAnalysis</h1>
			<hr/>
					<p>
					舘嬢 紺 園舛, 掻験, 採舛, 3亜走税 姶失聖 歳汐馬食 妊薄 背 掃艦陥.
					</p>
			</div>
	</div>
	<div class="sideContent">
		<div class="sideItem active" onclick="onChange(this,'list')" id="firstActive">My List</div>
		<div class="sideItem" onclick="onChange(this,'makeList')">Make TM</div>
	</div>
</section>
</body>
</html>