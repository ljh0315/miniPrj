<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "vo.QnAVO" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%
   int num = Integer.parseInt(request.getParameter("num"));
   String pageNum = (String)request.getAttribute("pageNum");
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

   try{      
	   QnAVO board =  (QnAVO)request.getAttribute("board");
  
	  int ref=board.getRef();
	  int re_step=board.getRe_step();
	  int re_level=board.getRe_level();
%>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>���� ������ �����ϼ���!</title>
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
									<span class="symbol"><img src="images/logo.png" alt="" /></span><span class="title">&nbsp;���ִ� ��</span>
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
							<li><a href="./index.jsp">���ִ� ��</a></li>
							<li><a href="./map.jsp">������ �˻�</a></li>
						<%
							String memID = (String) session.getAttribute("Member.id");
							if (memID == null || memID.equals("")) {
						%>
							<li><a href="member/login.jsp" id="user">ȸ������ / �α���</a></li>
							<%
							} else {
						%>
								<B><%=(String) session.getAttribute("Member.id")%>��</B>
								<br>
								<a href="mainController?choice=select-member">��������</a>
								<a href="mainController?choice=logout">�α׾ƿ�</a>
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
								<h1>���ִ� ��</h1>
								<h2>���ִ� ��� �Բ� ������ ã�ƺ����� !</h2>
								<p>�����Ӱ� ���� ������ �����ϰ� �� �ֺ� ���� ���� ������ ã��</p>
								</center>
								<br>
								<br>
</script>
								
							</header>

<body>  
<center><b>�۳��� ����</b>
<br>
<br>
<form>
<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">  
  <tr height="30">
    <td align="center" width="125">�۹�ȣ</td>
    <td align="center" width="125" align="center">
	     <%=board.getNum()%></td>
    <td align="center" width="125">��ȸ��</td>
    <td align="center" width="125" align="center">
	     <%=board.getReadcount()%></td>
  </tr>
  <tr height="30">
    <td align="center" width="125">�ۼ���</td>
    <td align="center" width="125" align="center">
	     <%=board.getWriter()%></td>
    <td align="center" width="125">�ۼ���</td>
    <td align="center" width="125" align="center">
	     <%= sdf.format(board.getReg_date())%></td>
  </tr>
  <tr height="30">
    <td align="center" width="125">������</td>
    <td align="center" width="375" align="center" colspan="3">
	     <%=board.getSubject()%></td>
  </tr>
  <tr>
    <td align="center" width="125">�۳���</td>
    <td align="left" width="375" colspan="3"><pre><%=board.getContent()%></pre></td>
  </tr>
  <tr height="30">      
    <td colspan="4" align="right" > 
	  <input type="button" value="�ۼ���" 
       onclick="document.location.href='qnaController?choice=updateGet-board&num=<%=board.getNum()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
	  <input type="button" value="�ۻ���" 
       onclick="document.location.href='qnaBoard/deleteForm.jsp?num=<%=board.getNum()%>&pageNum=<%=pageNum%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="��۾���" 
       onclick="document.location.href='qnaBoard/writeForm.jsp?num=<%=num%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'">
	   &nbsp;&nbsp;&nbsp;&nbsp;
       <input type="button" value="�۸��" 
       onclick="document.location.href='qnaController?choice=getAll-board&pageNum=<%=pageNum%>'">
    </td>
  </tr>
</table>    
<%
 }catch(Exception e){} 
 %>
</div>
</div>

				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<section>
								<h2>���ִ� ��</h2>
								<br>
								<p>���� ������ �����ϼ���!</p>
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

	</body>
</html>