<%@ page language="java" contentType="text/html; charset=euc-kr"
    pageEncoding="euc-kr"%>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>회원가입</title>
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
							
<!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">          	          
            <div class="checkout__form">
                <h2>회원정보수정</h2>
                <form action="mainController?choice=update-member" method="post">
                <jsp:useBean id="member" class="vo.MemberVO" scope="request"  />
                	<div class="col-lg-4 col-md-6">
                       <div class="checkout__order">
                        <div class="col-lg-8 col-md-6">
                            <div class="checkout__input">
                                <p>이름<span>*</span></p>
                                <input type="text" name="name" value="<jsp:getProperty name="member" property="name"/>">
								<br>                            
                            </div>
                            <div class="checkout__input">
                                <p>아이디<span>*</span></p>
                                <input type="text" name="memID" value="<jsp:getProperty name="member" property="memID"/>">
                                <INPUT TYPE='hidden' NAME='memID'  value='<jsp:getProperty name="member" property="memID"/>'>
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>비밀번호<span>*</span></p>
                                <input type="password" name="password" value="<jsp:getProperty name="member" property="password"/>">
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>주민번호<span>*</span></p>
                                <input type="text" name="ssn" value="<jsp:getProperty name="member" property="ssn"/>">
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>이메일<span>*</span></p>
                                <input type="email" name="email" value="<jsp:getProperty name="member" property="email"/>">
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>전화번호<span>*</span></p>
                                <input type="text" name="tel" value="<jsp:getProperty name="member" property="tel"/>">
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>우편번호<span>*</span></p>
                                <input type="text" name="zipcode" value="<jsp:getProperty name="member" property="zipcode"/>">
                            	<br>
                            </div>
                            <div class="checkout__input">
                                <p>주소<span>*</span></p>
                                <input type="text" name="address" value="<jsp:getProperty name="member" property="address"/>">
                            	<br>
                            </div>								                                                                                                                                                                 
                                <input type="submit" class="site-btn" value="정보수정"/>
                            </div>
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
								<h2>맛있는 길</h2>
								<p>맛집 정보를 보내주세요!</p>
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