<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>맛집을 찾아서! | 맛있는 길</title>
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
								<h2>위치</h2>
                        <select onchange="categoryChange(this)">
                              <option>지역을 선택하세요</option>
                               <option value="a">강남구</option>
                               <option value="b">강동구</option>
                               <option value="c">강북구</option>
                              <option value="d">강서구</option>
                               <option value="e">관악구</option>
                               <option value="f">광진구</option>
                               <option value="g">구로구</option>
                               <option value="h">금천구</option>
                               <option value="i">노원구</option>
                               <option value="j">도봉구</option>
                               <option value="k">동대문구</option>
                               <option value="l">동작구</option>
                               <option value="m">마포구</option>
                               <option value="n">서대문구</option>
                               <option value="o">서초구</option>
                               <option value="p">성동구</option>
                               <option value="q">성북구</option>
                               <option value="r">송파구</option>
                               <option value="s">양천구</option>
                               <option value="t">영등포구</option>
                               <option value="u">용산구</option>
                               <option value="v">은평구</option>
                               <option value="w">종로구</option>
                               <option value="x">중구</option>
                               <option value="y">중랑구</option>
                           </select>
                           <br>
                           <select id="local">
                              <option> </option>
                           </select>
                           <script>
                           function categoryChange(e) {
                           var local_a = ["개포동", "논현동", "대치동", "도곡동", "삼성동", "세곡동", "수서동", "신사동", "압구정동", "역삼동", "율현동", "일원동", "일원본동", "자곡동", "청담동"];
                           var loacl_b = ["강일동", "고덕동", "길동", "둔촌동", "명일동", "상일동", "성내동", "암사동", "천호동"];
                           var local_c = ["미아동", "번동", "삼각산동", "삼양동", "송중동", "송천동", "수유동", "우이동", "인수동"];
                           var local_d = ["가양동", "개화동", "공항동", "과해동", "내발산동", "등촌동", "마곡동", "발산동", "방화동", "염창동", "오곡동", "오쇠동", "외발산동", "우장산동", "화곡동", "화곡본동"];
                           var local_e = ["낙성대동", "난곡동", "난향동", "남현동", "대학동", "미성동", "보라매동", "봉천동", "삼성동", "서림동", "서원동", "성현동", "신림동", "신사동", "신원동", "은천동", "인헌동", "조원동", "중앙동", "청룡동", "청림동", "행운동"];
                           var local_f = ["광장동", "구의동", "군자동", "능동", "자양동", "중곡동", "화양동"];
                           var local_g = ["가리봉동", "개봉동", "고척동", "구로동", "궁동", "수궁동", "신도림동", "오류동", "온수동", "천왕동", "향동"];
                           var local_h = ["가산동", "독산동", "시흥동"];
                           var local_i = ["공릉동", "상계동", "월계동", "중계동", "하계동"];
                           var local_j = ["도봉동", "방학동", "쌍문동", "창동"];
                           var local_k = ["답십리동", "신설동", "용두동", "용신동", "이문동", "장안동", "전농동", "제기동", "청량리동", "회기동", "휘경동"];
                           var local_l = ["노량진동", "대방동", "동작동", "본동", "사당동", "상도동", "신대방동", "흑석동"];
                           var local_m = ["공덕동", "구수동", "노고산동", "당인동", "대흥동", "도화동", "동교동", "마포동", "망원동", "상수동", "상암동", "서강동", "서교동", "성산동", "신공덕동", "신수동", "신정동", "아현동", "연남동", "염리동", "용강동", "중동", "창전동", "토정동", "하중동", "합정동", "현석동"];
                           var local_n = ["남가좌동", "냉천동", "대신동", "대현동", "미근동", "봉원동", "북가좌동", "북아현동", "신촌동", "연희동", "영천동", "옥천동", "창전동", "천연동", "충정로", "충현동", "합동", "현저동", "홍은동", "홍제동"];
                           var local_o = ["내곡동", "반포동", "반포동", "방배동", "서초동", "신원동", "양재동", "염곡동", "우면동", "원지동", "잠원동"];
                           var local_p = ["금호동", "도선동", "마장동", "사근동", "상왕십리동", "성수동", "송정동", "옥수동", "왕십리동", "왕십리도선동", "용답동", "응봉동", "하왕십리동", "행당동", "홍익동"];
                           var local_q = ["길음동", "돈암동", "동선동", "동소문동", "보문동", "삼선동", "상월곡동", "석관동", "성북동", "안암동", "월곡동", "장위동", "정릉동", "종암동", "하월곡동"];
                           var local_r = ["가락동", "거여동", "마천동", "문정동", "방이동", "삼전동", "석촌동", "송파동", "신천동", "오금동", "오륜동", "위례동", "잠실동", "장지동", "풍납동"];
                           var local_s = ["목동", "신월동", "신정동"];
                           var local_t = ["당산동", "대림동", "도림동", "문래동", "신길동", "양평동", "양화동", "여의도동", "여의동", "영등포동"];
                           var local_u = ["길월동", "남영동", "도원동", "도빙고동", "동자동", "문배동", "보광동", "산천동", "서계동", "서빙고동", "신계동", "신창동", "용문동", "용산동", "원효로", "이촌동", "이태원동", "주석동", "청암동", "청파동", "한강로", "한남동", "효창동", "후암동"];
                           var local_v = ["갈현동", "구산동", "녹번동", "대조동", "불광동", "수색동", "신사동", "역촌동", "응암동", "증산동", "진관동"];
                           var local_w = ["가회동", "견지동", "경운동", "계동", "공평동", "관수동", "관철동", "관훈동", "교남동", "교북동", "구기동", "궁정동", "권농동", "낙원동", "내수동", "내자동", "누상동","누하동", ",당주동", "도렴동", "돈의동", "동숭동", "명륜동", "묘동", "무악동", "봉익동", "부암동", "사간동", "사직동", "삼청동", "서린동", "세종로", "소격동", "송월동", "송현동", "수송동", "숭인동", "신교동", "신문로", "신영동", "안국동", "연건동", "연지동", "예지동", "옥인동", "와룡동", "운니동", "원남동", "원서동", "이화동", "익선동", "인사동", "인의동", "장사동", "재동", "적선동", "종로동", "중학동", "창성동", "창신동", "청운동", "청운효자동", "청진동", "체부동", "충신동", "통의동", "통인동", "팔판동", "평동", "평창동", "필운동", "행촌동", "혜화동", "홍지동", "홍파동", "화동", "효자동", "효제동", "훈정동"];
                           var local_x = ["광희동", "남대문로", "남산동", "남창동", "남학동", "다동", "다산동", "동화동", "만리동", "명동", "무교동", "무학동", "묵정동", "방산동", "봉래동", "북창동", "산림동", "삼각동", "서소문동", "소공동", "수표동", "수하동", "순화동", "신당동", "쌍림동", "약수동", "예관동", "예장동", "오장동", "을지로동", "의주로", "인현동", "입정동", "장교동", "장충동", "저동", "정동", "주교동", "주자동", "중림동", "청구동", "초동", "충무로", "태평로", "필동", "확학동", "회현동", "흥인동"];
                           var local_y = ["망우동", "망우본동", "면복동", "면목본동", "묵동", "상봉동", "신내동", "중화동"];
                           
                           var target = document.getElementById("local");

                           if(e.value == "a") var d = local_a;
                           else if(e.value == "b") var d = local_b;
                           else if(e.value == "c") var d = local_c;
                           else if(e.value == "d") var d = local_d;
                           else if(e.value == "e") var d = local_e;
                           else if(e.value == "f") var d = local_f;
                           else if(e.value == "g") var d = local_g;
                           else if(e.value == "h") var d = local_h;
                           else if(e.value == "i") var d = local_i;
                           else if(e.value == "j") var d = local_j;
                           else if(e.value == "k") var d = local_k;
                           else if(e.value == "l") var d = local_l;
                           else if(e.value == "m") var d = local_m;
                           else if(e.value == "n") var d = local_n;
                           else if(e.value == "o") var d = local_o;
                           else if(e.value == "p") var d = local_p;
                           else if(e.value == "q") var d = local_q;
                           else if(e.value == "r") var d = local_r;
                           else if(e.value == "s") var d = local_s;
                           else if(e.value == "i") var d = local_t;
                           else if(e.value == "u") var d = local_u;
                           else if(e.value == "v") var d = local_v;
                           else if(e.value == "w") var d = local_w;
                           else if(e.value == "x") var d = local_x;
                           else if(e.value == "y") var d = local_y;

                           target.options.length = 0;

                           for (x in d) {
                              var opt = document.createElement("option");
                              opt.value = d[x];
                              opt.innerHTML = d[x];
                              target.appendChild(opt);
   }   
}
</script>
								
							</header>
							<br>
							<section class="tiles">
								<article class="style1">
									<span class="image">
										<img src="images/bibimbap.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>한식</h2>
									</a>
								</article>
								<article class="style2">
									<span class="image">
										<img src="images/steak.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>양식</h2>
									</a>
								</article>
								<article class="style3">
									<span class="image">
										<img src="images/sushi.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>일식</h2>
									</a>
								</article>
								<article class="style4">
									<span class="image">
										<img src="images/jjamppong.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>중식</h2>
									</a>
								</article>
								<article class="style5">
									<span class="image">
										<img src="images/toppokki.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>분식</h2>
									</a>
								</article>
								<article class="style6">
									<span class="image">
										<img src="images/burger.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>패스트푸드</h2>
									</a>
								</article>
								<article class="style2">
									<span class="image">
										<img src="images/chicken.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>치킨</h2>
									</a>
								</article>
								<article class="style3">
									<span class="image">
										<img src="images/pizza.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>피자</h2>
									</a>
								</article>
								<article class="style1">
									<span class="image">
										<img src="images/cake.jpg" alt="" />
									</span>
									<a href="generic.html">
										<h2>카페 · 디저트</h2>
									</a>
								</article>
							</section>
						</div>
					</div>

				<!-- Footer -->
					<footer id="footer">
						<div class="inner">
							<section>
								<h2>맛있는 길</h2>
								<br>
								<p>맛집 정보를 공유하세요!!</p>
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