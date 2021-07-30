<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "vo.QnAVO" %>
<%@ page import = "entity.QnAEntity" %>

<%  
  int num = Integer.parseInt(request.getParameter("num"));
  int pageNum = Integer.parseInt(request.getParameter("pageNum"));
  
  
  try{
      
	  QnAVO board =  (QnAVO)request.getAttribute("board");

%>

<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>맛집 정보를 공유하세요!</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">
		<!-- Wrapper -->
			<div id="wrapper">
			
				<!-- Header -->
					<header id="header">
						<div class="inner">

							<!-- Logo-->
								<a href="index.jsp" class="logo">
									<span class="symbol"><img src="images/logo.png" alt="" /></span><span class="title">&nbsp;맛있는 길</span>
								</a>

							<!-- Nav -->
								<nav>
									<ul>
										<li><a href="#menu">Menu</a></li>
									</ul>
								</nav>

						</div>
					</header>

				<!-- Menu -->
					<nav id="menu">
						<h2>Menu</h2>
						<ul>
							<li><a href="./index.jsp">맛있는 길</a></li>
							<li><a href="./map.jsp">지도로 검색</a></li>
						<%
							String memID = (String) session.getAttribute("Member.id");
							if (memID == null || memID.equals("")) {
						%>
							<li><a href="member/login.jsp" id="user">회원가입 / 로그인</a></li>
							<%
							} else {
						%>
								<B><%=(String) session.getAttribute("Member.id")%>님</B>
								<br>
								<a href="mainController?choice=select-member">정보수정</a>
								<a href="mainController?choice=logout">로그아웃</a>
						<%
							}
						%>
						</ul>
					</nav>

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<header>
								<center>
								<br>
								<h1>맛있는 길</h1>
								<h2>맛있는 길과 함께 맛집을 찾아보세요 !</h2>
								<p>자유롭게 맛집 정보를 공유하고 내 주변 맛집 쉽고 빠르게 찾기</p>
								</center>
								<br>
								<br>
</script>
								
							</header>

<body>  
<center><b>글수정</b>
<br>
<br>
<form method="post" name="writeform" action="qnaController?choice=update-board&pageNum=<%=pageNum%>" onsubmit="return writeSave()">
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td  width="70" align="center">이 름</td>
    <td align="left" width="330">
       <input type="text" size="10" maxlength="10" name="writer" value="<%=board.getWriter()%>">
	   <input type="hidden" name="num" value="<%=board.getNum()%>"></td>
  </tr>
  <tr>
    <td  width="70" align="center" >제 목</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="50" name="subject" value="<%=board.getSubject()%>"></td>
  </tr>
  <tr>
    <td  width="70" align="center">Email</td>
    <td align="left" width="330">
       <input type="text" size="40" maxlength="30" name="email" value="<%=board.getEmail()%>"></td>
  </tr>
  <tr>
    <td  width="70" align="center" >내 용</td>
    <td align="left" width="330">
     <textarea name="content" rows="13" cols="40"><%=board.getContent()%></textarea></td>
  </tr>
  <tr>
    <td  width="70" align="center" >비밀번호</td>
    <td align="left" width="330" >
     <input type="password" size="8" maxlength="12" name="passwd">
     
	 </td>
  </tr>
  <tr>      
   <td colspan=2 align="center"> 
     <input type="submit" value="글수정" >  
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기" 
       onclick="document.location.href='boardController?choice=getAll-board&pageNum=<%=pageNum%>'">
   </td>
 </tr>
 </table>
</form> 
</div>
</div>
 
				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<section>
								<h2>맛있는 길</h2>
								<br>
								<p>맛집 정보를 공유하세요!</p>
								<form method="post" action="#">
									<a href="qnaController?choice=getAll-board">CLICK!</a>
								</form>
							</section>
							<section>
								<h2>Follow</h2>
								<ul class="icons">
									<li><a href="#" class="icon brands stsyle2 fa-twitter"><span class="label">Twitter</span></a></li>
									<li><a href="#" class="icon brands style2 fa-facebook-f"><span class="label">Facebook</span></a></li>
									<li><a href="#" class="icon brands style2 fa-instagram"><span class="label">Instagram</span></a></li>
									<li><a href="#" class="icon brands style2 fa-dribbble"><span class="label">Dribbble</span></a></li>
									<li><a href="#" class="icon brands style2 fa-github"><span class="label">GitHub</span></a></li>
									<li><a href="#" class="icon brands style2 fa-500px"><span class="label">500px</span></a></li>
									<li><a href="#" class="icon solid style2 fa-phone"><span class="label">Phone</span></a></li>
									<li><a href="#" class="icon solid style2 fa-envelope"><span class="label">Email</span></a></li>
								</ul>
							</section>
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
							</ul>
						</div>
					</footer>

			</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>  
<%
}catch(Exception e){}%>    
</body>
</html> 