<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "entity.QnAEntity" %>
<%@ page import = "vo.QnAVO" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<%!    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>

<%
    int pageNum = request.getAttribute("pageNum") != null ? Integer.parseInt(request.getAttribute("pageNum").toString()) : 1;
    int count = 0;
    int number=0;

    List boardList = null;
    if(request.getAttribute("count") != null) {
    	count = Integer.parseInt((request.getAttribute("count")).toString());
    } 
    
    if (count > 0) {
    	boardList = (List)request.getAttribute("list");
    }

	number=count-(pageNum-1)*10;
%>
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
							<li><a href="index.jsp">맛있는 길</a></li>
							<li><a href="map.jsp">지도로 검색</a></li>
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
    
    <!-- Contact Form Begin -->
    <div class="contact-form spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="contact__form__title">
                    </div>
                </div>
            </div>

<div align="center"><b>맛집 정보 공유 게시판 글목록(<%=count%>)</b>
<table width="1000">
<p>
<tr>
    <td align="right">
    <a href="qnaBoard/writeForm.jsp">글쓰기</a>
    </td>
</table>

<%
    if (count == 0) {
%>
<table width="1000" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>

<%  } else {    %>
<table border="1" width="1000" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30" bgcolor="#ACFFEF"> 
      <td align="center"  width="100">번 호</td> 
      <td align="center"  width="250">제   목</td> 
      <td align="center"  width="100">작성자</td>
      <td align="center"  width="150">작성일</td> 
      <td align="center"  width="100">조 회</td> 
      <td align="center"  width="100">IP</td>    
    </tr>
<%  
        for (int i = 0 ; i < boardList.size() ; i++) {
        	QnAVO board = (QnAVO)boardList.get(i);
%>
   <tr height="30">
    <td align="center"  width="50" > <%=number--%></td>
    <td  width="250" >
	<%
	      int wid=0; 
	      if(board.getRe_level()>0){
	        wid=5*(board.getRe_level());
	%>
	  <img src="qnaBoard/images/level.gif" width="<%=wid%>" height="16">
	  <img src="qnaBoard/images/re.gif">
	<%}else{%>
	  <img src="qnaBoard/images/level.gif" width="<%=wid%>" height="16">
	<%}%>
           
      <a href="qnaController?choice=get-board&num=<%=board.getNum()%>&pageNum=<%=pageNum%>">
           <%=board.getSubject()%></a> 
          <% if(board.getReadcount()>=20){%>
         <img src="qnaBoard/images/hot.gif" border="0"  height="16"><%}%> </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=board.getEmail()%>"><%=board.getWriter()%></a></td>
    <td align="center"  width="150"><%= sdf.format(board.getReg_date())%></td>
    <td align="center"  width="50"><%=board.getReadcount()%></td>
    <td align="center" width="100" ><%=board.getIp()%></td>
  </tr>
     <%}%>
</table>
<%}%>

<%
    if (count > 0) {
        int pageCount = count / 10 + ( count % 10 == 0 ? 0 : 1);
		 
        int startPage = (int)(pageNum/10)*10+1;
		int pageBlock=10;
        int endPage = startPage + pageBlock-1;
        if (endPage > pageCount) endPage = pageCount;
        
        if (startPage > 10) {    %>
        <a href="QnAProcessing?choice=getAll-board&pageNum=<%= startPage - 10 %>&startRow=<%=(startPage - 1) * 10 + 1%>&endRow=<%=startPage * 10%>">[����]</a>
<%      }
        for (int i = startPage ; i <= endPage ; i++) {  %>
        <a href="QnAProcessing?choice=getAll-board&pageNum=<%= i %>&startRow=<%=(i - 1) * 10 + 1%>&endRow=<%=i * 10%>">[<%= i %>]</a>
<%                                      
        }
        if (endPage < pageCount) {  %>
        <a href="QnAProcessing?choice=getAll-board&pageNum=<%= startPage + 10 %>&startRow=<%=(startPage - 1) * 10 + 1%>&endRow=<%=startPage * 10%>">[����]</a>
<%
        }
    }
%>
</div>      
</div>
</div>
</div>
</div>
    <!-- Contact Form End -->

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
									<li><a href="#" class="icon brands style2 fa-twitter"><span class="label">Twitter</span></a></li>
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

	</body>
</html>