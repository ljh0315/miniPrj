<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="entity.BoardEntity"%>
<%@ page import="vo.BoardVO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="sl063.domain.*"%>
<%@ page import="java.util.*"%>
<%@ include file="color.jspf"%>
<%!static int basketSize;%>

<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");%>

<%
	int pageNum = request.getAttribute("pageNum") != null
			? Integer.parseInt(request.getAttribute("pageNum").toString())
			: 1;
	int count = 0;
	int number = 0;

	List boardList = null;
	if (request.getAttribute("count") != null) {
		count = Integer.parseInt((request.getAttribute("count")).toString());
	}

	if (count > 0) {
		boardList = (List) request.getAttribute("list");
	}

	number = count - (pageNum - 1) * 10;
%>
<html>
<head>
<title>�ı� �Խ���</title>
<meta charset=UTF-8>
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
<link href="style.css" rel="stylesheet" type="text/css">
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
							<li><a href="../product/area/seoul.jsp">������</a></li>
							<li><a href="#">�׸���</a>
								<ul class="header__menu__dropdown">
									<li><a href="../product/thema/family.jsp">��������</a></li>
									<li><a href="../product/thema/pets.jsp">�ݷ����� ���ݿ���</a></li>
									<li><a href="../product/thema/couple.jsp">����</a></li>
									<li><a href="../product/thema/alone.jsp">��Ȧ��</a></li>
								</ul></li>
							<li><a href="boardController?choice=getAll-board">�ı� �Խ���</a></li>
							<li><a href="qnaController?choice=getAll-board">������</a></li>
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
						<i class="fa fa-user"></i> <B><%=(String) session.getAttribute("Member.id")%>��
							ȯ���մϴ�.</B> <br> <a href="mainController?choice=select-member">��������</a>
						<a href="mainController?choice=logout">�α׾ƿ�</a>
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
	<section class="hero">
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

	<!-- Contact Form Begin -->
	<div class="contact-form spad">
		<div class="container">

			<div align="center">
				<table width="700" bgcolor="<%=value_c%>" border="1">
					<tr>
						<td style="border-right: hidden;"><b>�ı� �Խ���(��ü ��:<%=count%>)
						</b></td>
						<td align="right"><a href="index.jsp">Ȩ���� �̵�</a> <a
							href="reviewBoard/writeForm.jsp">�۾���</a></td>
				</table>

				<%
					if (count == 0) {
				%>
				<table width="700" border="1" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">�Խ��ǿ� ����� ���� �����ϴ�.</td>
				</table>

				<%
					} else {
				%>
				<table border="1" width="700" cellpadding="0" cellspacing="0"
					align="center" bgcolor="<%=bodyback_c%>">
					<tr height="30" bgcolor="<%=value_c%>">
						<td align="center" width="50">�� ȣ</td>
						<td align="center" width="250">�� ��</td>
						<td align="center" width="100">�ۼ���</td>
						<td align="center" width="150">�ۼ���</td>
						<td align="center" width="50">�� ȸ</td>
						<td align="center" width="100">IP</td>
					</tr>
					<%
						for (int i = 0; i < boardList.size(); i++) {
								BoardVO board = (BoardVO) boardList.get(i);
					%>
					<tr height="30">
						<td align="center" width="50"><%=number--%></td>
						<td width="250">
							<%
								int wid = 0;
										if (board.getRe_level() > 0) {
											wid = 5 * (board.getRe_level());
							%> <img src="img/board-img/level.gif" width="<%=wid%>" height="16">
							<img src="img/board-img/re.gif"> <%
 	} else {
 %> <img
							src="img/board-img/level.gif" width="<%=wid%>" height="16">
							<%
								}
							%> <a
							href="boardController?choice=get-board&num=<%=board.getNum()%>&pageNum=<%=pageNum%>">
								<%=board.getSubject()%></a> <%
 	if (board.getReadcount() >= 20) {
 %> <img
							src="img/board-img/hot.gif" border="0" height="16">
							<%
								}
							%>
						</td>
						<td align="center" width="100"><a
							href="mailto:<%=board.getEmail()%>"><%=board.getWriter()%></a></td>
						<td align="center" width="150"><%=sdf.format(board.getReg_date())%></td>
						<td align="center" width="50"><%=board.getReadcount()%></td>
						<td align="center" width="100"><%=board.getIp()%></td>
					</tr>
					<%
						}
					%>
				</table>
				<%
					}
				%>

				<%
					if (count > 0) {
						int pageCount = count / 10 + (count % 10 == 0 ? 0 : 1);

						int startPage = (int) (pageNum / 10) * 10 + 1;
						int pageBlock = 10;
						int endPage = startPage + pageBlock - 1;
						if (endPage > pageCount)
							endPage = pageCount;

						if (startPage > 10) {
				%>
				<a
					href="boardController?choice=getAll-board&pageNum=<%=startPage - 10%>&startRow=<%=(startPage - 1) * 10 + 1%>&endRow=<%=startPage * 10%>">[����]</a>
				<%
					}
						for (int i = startPage; i <= endPage; i++) {
				%>
				<a
					href="boardController?choice=getAll-board&pageNum=<%=i%>&startRow=<%=(i - 1) * 10 + 1%>&endRow=<%=i * 10%>">[<%=i%>]
				</a>
				<%
					}
						if (endPage < pageCount) {
				%>
				<a
					href="boardController?choice=getAll-board&pageNum=<%=startPage + 10%>&startRow=<%=(startPage - 1) * 10 + 1%>&endRow=<%=startPage * 10%>">[����]</a>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
	<footer class="footer spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__about__logo">
							<a href="./index.jsp"><img src="../img/logo.png" alt=""></a>
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
							<img src="../img/payment-item.png" alt="">
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