<%@ page import="vo.MemberVO"%>
<%@ page contentType="text/html;charset=euc-kr" %>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>������ ã�Ƽ�! | ���ִ� ��</title>
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
							<li><a href="index.jsp">���ִ� ��</a></li>
							<li><a href="map.jsp">������ �˻�</a></li>
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
</script>
								
							</header>
                
<!-- START of main content -->
	<TABLE align="center" BORDER=0 CELLSPACING=0 CELLPADDING=0 WIDTH=800>
		<%
			MemberVO member = (MemberVO) request.getAttribute("member");
		%>

		<P>
			<FONT color=#0000ff size=3><CENTER>
					<STRONG>[ȸ������ ���(����)��� ]</STRONG>
					<CENTER></FONT>
		</P>
		<P>
			<FONT size=3><CENTER>
					<STRONG><a href="index.jsp">���ִ� ��� �̵�</a></STRONG>
					<CENTER></FONT>
		</P>
		<table align="center""> 
			<tr>
				<td>���̵� :</td>
				<td><B><%=member.getMemID()%><br></B></td>
			</tr>
			<tr>
				<td>��&emsp;�� :</td>
				<td><B><%=member.getName()%> ���� ���(����)�Ǿ����ϴ�.</B></td>
			</tr>
		</table>
	</TABLE>
<!-- END of main content -->
</div>
</div>
            
				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<section>
								<h2>���ִ� ��</h2>
								<p>���� ������ �����ּ���!</p>
								<form method="post" action="#">
									<div class="fields">
										<div class="field half">
											<input type="text" name="name" id="name" placeholder="Name" />
										</div>
										<div class="field half">
											<input type="email" name="email" id="email" placeholder="Email" />
										</div>
										<div class="field">
											<textarea name="message" id="message" placeholder="Message"></textarea>
										</div>
									</div>
									<ul class="actions">
										<li><input type="submit" value="Send" class="primary" /></li>
									</ul>
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
            