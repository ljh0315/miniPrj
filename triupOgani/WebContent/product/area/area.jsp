<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="sl063.domain.*"%>
<%!static int basketSize;%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>

<!-- Google Font -->

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
						<a href="index.jsp" id="logo">Triup</a>
					</div>
				</div>
				<div class="col-lg-6">
					<nav class="header__menu">
						<ul>
							<li class="active"><a href="index.jsp">Home</a></li>
							<li><a href="productController?choice=seoul">지역별</a></li>
							<li><a href="#">테마별</a>
								<ul class="header__menu__dropdown">
									<li><a href="../thema/family.jsp">가족여행</a></li>
									<li><a href="../thema/pets.jsp">반려동물 동반여행</a></li>
									<li><a href="../thema/couple.jsp">연인</a></li>
									<li><a href="../thema/alone.jsp">나홀로</a></li>
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
						<a href="../../member/login.jsp" id="user"><i
							class="fa fa-user"></i>Login</a>
						<%
							} else {
						%>
						<i class="fa fa-user"></i> <B><%=(String) session.getAttribute("Member.id")%>님
							환영합니다.</B> <br> <a
							href="../../mainController?choice=select-member">정보수정</a> <a
							href="../../mainController?choice=logout">로그아웃</a>
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

	<!-- Hero Section Begin -->
	<section class="hero hero-normal">
		<div class="container">
			<div class="row">
				<div class="hero__search">
					<div class="hero__search__form">
						<form action="#">
							<div class="hero__search__categories">
								All Categories <span class="arrow_carrot-down"></span>
							</div>
							<input type="text" placeholder="What do yo u need?">
							<button type="submit" class="site-btn">SEARCH</button>
						</form>
					</div>
					<div class="hero__search__phone">
						<div class="hero__search__phone__icon">
							<i class="fa fa-phone"></i>
						</div>
						<div class="hero__search__phone__text">
							<h5>+65 11.188.888</h5>
							<span>support 24/7 time</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->

	<!-- Breadcrumb Section Begin -->
	<section class="breadcrumb-section set-bg"
		data-setbg="../../img/area_banner.png">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="breadcrumb__text">
						<h2>지역별 여행코스</h2>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Breadcrumb Section End -->

	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-5">
					<div class="sidebar">
						<div class="sidebar__item">
							<h4>지역</h4>
							<ul>
								<li><a href="productController?choice=seoul">서울</a></li>
								<li><a href="gyeonggi.jsp">경기</a></li>
								<li><a href="productController?choice=kangwon">강원</a></li>
								<li><a href="gyeongbuk.jsp">경북</a></li>
								<li><a href="gyeongnam.jsp">경남</a></li>
								<li><a href="chungbuk.jsp">충북</a></li>
								<li><a href="chungnam.jsp">충남</a></li>
								<li><a href="junbuk.jsp">전북</a></li>
								<li><a href="junnam.jsp">전남</a></li>
								<li><a href="jeju.jsp">제주</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-lg-9 col-md-7">
					<table>
						<tr>
							<%
								ArrayList pCollection = (ArrayList) request.getAttribute("SearchListProduct.collection");
								Iterator iter2 = pCollection.iterator();
								sl063.domain.Product product = null;
								for (int i = 0; iter2.hasNext(); i++) {
									product = (sl063.domain.Product) iter2.next();
									if ((i % 3) != 0) {
							%>
							<td>
								<div class="col-lg-4 col-md-6 col-sm-6">
									<div class="product__item">
										<div class="product__item__pic set-bg">
											<a
												href='mainController?choice=select-product&productId=<%=product.getProductId()%>'>
												<img width="270" height="270"
												src="area-img/<%=product.getPhotoDir()%>">
											</a>
										</div>
										<div class="product__item__text">
											<h6>
												<a
													href='mainController?choice=select-product&productId=<%=product.getProductId()%>'>
													<%=product.getProductName()%></a>
											</h6>
											<h5><%=product.getPrice2()%></h5>
										</div>
									</div>
								</div>
							</td>
							<%
								} else {
							%>
						</tr>
						<tr>
							<td>
								<div class="col-lg-4 col-md-6 col-sm-6">
									<div class="product__item">
										<div class="product__item__pic set-bg">
											<a
												href='mainController?choice=select-product&productId=<%=product.getProductId()%>'>
												<img width="270" height="270"
												src="area-img/<%=product.getPhotoDir()%>">
											</a>
										</div>
										<div class="product__item__text">
											<h6>
												<a
													href='mainController?choice=select-product&productId=<%=product.getProductId()%>'>
													<%=product.getProductName()%></a>
											</h6>
											<h5><%=product.getPrice2()%></h5>
										</div>
									</div>
								</div>
							</td>
							<%
								}
							%>
							<%
								}
							%>
						
					</table>
					<div class="product__pagination">
						<a href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#"><i
							class="fa fa-long-arrow-right"></i></a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="../../index.jsp"><img src="../../img/logo.png"
								alt=""></a>
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
							<img src="../../img/payment-item.png" alt="">
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