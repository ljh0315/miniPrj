<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<%@ page import="java.util.*" %>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>������ ã�Ƽ�! | �α���</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../assets/css/main.css" />
		<noscript><link rel="stylesheet" href="../assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">
		<!-- Wrapper -->
			<div id="wrapper">
			
				<!-- Header -->
					<header id="header">
						<div class="inner">

							<!-- Logo-->
								<a href="../index.jsp" class="logo">
									<span class="symbol"><img src="../images/logo.png" alt="" /></span><span class="title">&nbsp;���ִ� ��</span>
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
							<li><a href="../index.jsp">���ִ� ��</a></li>
							<li><a href="../map.jsp">������ �˻�</a></li>
							<li><a href="login.jsp" id="user">ȸ������ / �α���</a></li>
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
							
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">               
        <div class="row">
                <div class="col-lg-12">
                    <h2><span class="icon_tag_alt"></span> ���� ȸ���� �ƴ϶��? <a href="join.jsp">ȸ�������� �ϼ���!</a></h2>
                    <br>
                </div>
            </div>            
            <div class="checkout__form">
                <h2>�α���</h2>
                <form action="../mainController?choice=login" method="post">
                <%
			    // Retrieve the errorMsgs from the request-scope
			    List errorMsgs = (List) request.getAttribute("loginErrorMsgs");
			    if ( (errorMsgs != null) && !errorMsgs.isEmpty() ) {
			    %>
			    <p>
			        <font color='red'>
			            <ul>
			                <%
			                Iterator items = errorMsgs.iterator();
			                while ( items.hasNext() ) {
			                    String message = (String) items.next();
			                %>
			                <li><%= message %></li>
			                <%
			                } // END of while loop over errorMsgs
			                %>
			            </ul>
			        </font>
			    </p>
			    <%
			    } // END of if errorMsgs is not empty
			    %>                       
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">       
                                    <div class="checkout__input">
                                        <p>���̵�<span>*</span></p>
                                        <input type="text" name="memID">
                                        <br>
                                	</div>
                                    <div class="checkout__input">
                                        <p>��й�ȣ<span>*</span></p>
                                        <input type="password" name="password">
                                        <br>
                                    </div>                           
                                <input type="submit" class="site-btn" name="login" value="�α���"/>
                            </div>
                        </div>
                </form>
            </div>
        </div>
    </section>
<!-- Checkout Section End -->
</div>
</div>

				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<section>
								<h2>���ִ� ��</h2>
								<br>
								<p>���� ������ �����ϼ���!!</p>
								<form method="post" action="#">
									<a href="../qnaController?choice=getAll-board">CLICK!</a>
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
			<script src="../assets/js/jquery.min.js"></script>
			<script src="../assets/js/browser.min.js"></script>
			<script src="../assets/js/breakpoints.min.js"></script>
			<script src="../assets/js/util.js"></script>
			<script src="../assets/js/main.js"></script>

	</body>
</html>