<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!static int basketSize;%>
<%@ page import="sl063.domain.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<!-- Header Section Begin -->
	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="./index.jsp" id="logo">Triup</a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="./index.jsp">Home</a></li>
							<li><a href="productController?choice=seoul">지역별</a></li>
							<li><a href="#">테마별</a>
								<ul class="header__menu__dropdown">
									<li><a href="product/thema/family.jsp">가족여행</a></li>
									<li><a href="product/thema/pets.jsp">반려동물 동반여행</a></li>
									<li><a href="product/thema/couple.jsp">연인</a></li>
									<li><a href="product/thema/alone.jsp">나홀로</a></li>
								</ul></li>
							<li><a href="boardController?choice=getAll-board">후기 게시판</a></li>
							<li><a href="qnaController?choice=getAll-board">고객문의</a></li>
						</ul>
					</nav>
				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<%
								BasketCart basketCart = (BasketCart) session.getAttribute("BasketCart");
								if (basketCart != null) {
									Collection collection = basketCart.getItems();
									//out.println("basketcart size()" + collection.size());
									basketSize = collection.size();
							%>
							<li><a href="cart.jsp"><i class="fa fa-shopping-bag"></i>
									<span><%=basketSize%></span></a></li>					
						<%
							} else {
						%>
							<li><a href="cart.jsp"><i class="fa fa-shopping-bag"></i></a></li>						
						<%
							}
						%>
						</ul>
						<%
							String memID = (String) session.getAttribute("Member.id");
							if (memID == null || memID.equals("")) {
						%>
						<a href="member/login.jsp" id="user"><i class="fa fa-user"></i>Login</a>
						<%
							} else {
						%>
						<i class="fa fa-user"></i> <B><%=(String) session.getAttribute("Member.id")%>님
							환영합니다.</B> <br> <a href="mainController?choice=select-member">정보수정</a>
						<a href="mainController?choice=logout">로그아웃</a>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->
	<hr>
	<br>
	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="hero__item set-bg" data-setbg="img/hero/banner.jpg">
					<div class="hero__text">
						<!-- 
                            <span>FRUIT FRESH</span>
                            <h2>Vegetable <br />100% Organic</h2>
                            <p>Free Pickup and Delivery Available</p>
                            <a href="#" class="primary-btn">SHOP NOW</a>
                        -->
					</div>
				</div>

			</div>
		</div>
	</section>

	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="hero__search">
					<div class="hero__search__form">
						<form action="mainController?choice=search-keyword" method="post">
							<div class="hero__search__categories">
								All Categories <span class="arrow_carrot-down"></span>
							</div>
							<input type="text" placeholder="What do you need?" name="keyword">
							<button type="submit" class="site-btn">SEARCH</button>
						</form>
					</div>

				</div>

			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Categories Section Begin -->


	<!-- Featured Section Begin -->
	<section class="featured spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<h2>지역별 상품</h2>
					</div>
					<section class="categories">
						<div class="container">
							<div class="row">
								<div class="categories__slider owl-carousel">
									<div class="col-lg-3">
										<div class="categories__item set-bg"
											data-setbg="img/categories/cat-1.jpg">
											<h5>
												<a href="#">경 주</a>
											</h5>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="categories__item set-bg"
											data-setbg="img/categories/cat-2.jpg">
											<h5>
												<a href="#">전 주</a>
											</h5>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="categories__item set-bg"
											data-setbg="img/categories/cat-3.jpg">
											<h5>
												<a href="#">강 릉</a>
											</h5>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="categories__item set-bg"
											data-setbg="img/categories/cat-4.jpg">
											<h5>
												<a href="#">부 산</a>
											</h5>
										</div>
									</div>
									<div class="col-lg-3">
										<div class="categories__item set-bg"
											data-setbg="img/categories/cat-5.jpg">
											<h5>
												<a href="#">제 주 도</a>
											</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
					</section>
					<!-- Categories Section End -->
				</div>
			</div>
		</div>
	</section>
	<!-- Featured Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.jsp"><img src="img/logo.png" alt=""></a>
						</div>
						<ul>
							<li>Address: 60-49 Road 11378 New York</li>
							<li>Phone: +65 11.188.888</li>
							<li>Email: hello@colorlib.com</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
					<div class="footer__widget">
						<h6>Useful Links</h6>
						<ul>
							<li><a href="#">About Us</a></li>
							<li><a href="#">About Our Shop</a></li>
							<li><a href="#">Secure Shopping</a></li>
							<li><a href="#">Delivery infomation</a></li>
							<li><a href="#">Privacy Policy</a></li>
							<li><a href="#">Our Sitemap</a></li>
						</ul>
						<ul>
							<li><a href="#">Who We Are</a></li>
							<li><a href="#">Our Services</a></li>
							<li><a href="#">Projects</a></li>
							<li><a href="#">Contact</a></li>
							<li><a href="#">Innovation</a></li>
							<li><a href="#">Testimonials</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-12">
					<div class="footer__widget">
						<h6>Join Our Newsletter Now</h6>
						<p>Get E-mail updates about our latest shop and special
							offers.</p>
						<form action="#">
							<input type="text" placeholder="Enter your mail">
							<button type="submit" class="site-btn">Subscribe</button>
						</form>
						<div class="footer__widget__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-pinterest"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="footer__copyright">
						<div class="footer__copyright__text">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="fa fa-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
						<div class="footer__copyright__payment">
							<img src="img/payment-item.png" alt="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>