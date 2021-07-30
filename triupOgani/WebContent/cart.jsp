<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="java.util.*,java.text.NumberFormat,java.util.Locale"%>
<%@ page import="sl063.domain.*"%>
<%!static int basketSize;%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
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

<body>
<%
BasketCart basketCart = (BasketCart) session.getAttribute("BasketCart");
%>
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
						<li><a href="mainController?choice=selectAll-product">지역별</a></li>
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
						if (basketCart != null) {
						Collection collection = basketCart.getItems();
						//out.println("basketcart size()" + collection.size());
						basketSize = collection.size();
						%>
							<li><a href="cart.jsp"><i class="fa fa-shopping-bag"></i> <span><%=basketSize %></span></a></li>
						<%} else {%>
							<li><a href="cart.jsp"><i class="fa fa-shopping-bag"></i></a></li>
						<%} %>
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

			</div>
		</div>
	</div>
</section>
<!-- Hero Section End -->
<%
	String bodyback_c = "#F3F6FA";
	String value_c = "#ededed";
%>
<div align="center">

	<table border="1" width="700" cellpadding="0" cellspacing="0" align="center" bgcolor="<%=bodyback_c%>">
		<tr height="30" bgcolor="<%=value_c%>">
			<td align="center" width="50">상품명</td>
			<td align="center" width="250">수량</td>
			<td align="center" width="100">판매가</td>
			<td align="center" width="150">합계(단위:원)</td>
			<td align="center" width="100">선택삭제</td>
		</tr>
		<%
			int totalPrice = 0;
			if (basketCart != null) {
				Collection collection = basketCart.getItems();
				//out.println("basketcart size()" + collection.size());
				basketSize = collection.size();
				Iterator iterator = collection.iterator();
				while (iterator.hasNext()) {
					Basket basket = (Basket) iterator.next();
					totalPrice = totalPrice + basket.getQtyPrice();
		%>
		<tr height="30">
			<td align="center" width="250"><a
				href='mainController?choice=select-product&productId=<%=basket.getProductId()%>'><%=basket.getProductName()%></a></td>
			<td align="center" width="100"><%=basket.getQuantity()%></td>
			<td align="center" width="150"><%=basket.getPrice_S()%></td>
			<td align="right" width="50"><%=basket.getQtyPrice_S()%></td>
			<td align="center" width="100"><a
				href='mainController?choice=emptyOne-basket&productId=<%=basket.getProductId()%>'>삭제</a></td>
		</tr>
		<%
			}
		%>
		<table width="700" cellpadding="0" cellspacing="0">
			<tr>
			<td align="right">
			총 금액
			<font color="#0100FF"><%=NumberFormat.getInstance(Locale.KOREA).format(totalPrice)%></font>
			</td>
			</tr>
		</table>
		<%
			}
		%>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<br>
	<br>
	<Form>
		<input type=button value="쇼핑계속 "
			onclick="location='mainController?choice=selectAll-product'">&nbsp;
		<%
			//out.println("basketcart size()" + basketSize);
			if (basketSize >= 1) {
		%>
		<input type=button value="구   매  "
			onclick="location='mainController?choice=select-memberPurchaser'">&nbsp;
		<input type=button value="장바구니비우기 "
			onclick="location='mainController?choice=emptyAll-basket'">&nbsp;
		<input type="hidden" name="token"
			value="<%=request.getAttribute("token")%>">
		<%
			}
		%>
	</Form>
</div>



<!-- Js Plugins -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
</head>
</html>