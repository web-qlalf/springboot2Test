<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <Link  type="text/css"  rel="stylesheet" href="@{/css/style.css}"></link> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="msapplication-TileColor" content="#0f75ff">
<meta name="theme-color" content="#2ddcd3">
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">

<!-- CSRF -->
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<Link rel="icon" href="favicon.ico" type="image/x-icon" />
<Link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<Link type="text/css" rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
<Link type="text/css" rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-alpine.css">
<Link type="text/css" href="../assets/css/my-custom.css" rel="stylesheet" />
<!-- Bootstrap Css -->
<Link type="text/css" href="../assets/plugins/bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet" />
<!-- Dashboard Css -->
<Link type="text/css" href="../assets/css/dashboard.css" rel="stylesheet" />
<!-- Font-awesome  Css -->
<Link type="text/css" href="../assets/css/icons.css" rel="stylesheet" />
<!--Horizontal Menu-->
<Link type="text/css" href="../assets/plugins/Horizontal2/Horizontal-menu/dropdown-effects/fade-down.css" rel="stylesheet" />
<Link type="text/css" href="../assets/plugins/Horizontal2/Horizontal-menu/horizontal.css" rel="stylesheet" />
<Link type="text/css" href="../assets/plugins/Horizontal2/Horizontal-menu/color-skins/color.css" rel="stylesheet" />
<!--Select2 Plugin -->
<Link type="text/css" href="../assets/plugins/select2/select2.min.css" rel="stylesheet" />
<!-- Cookie css -->
<Link type="text/css" href="../assets/plugins/cookie/cookie.css" rel="stylesheet">
<!-- Owl Theme css-->
<Link type="text/css" href="../assets/plugins/owl-carousel/owl.carousel.css" rel="stylesheet" />
<!-- Custom scroll bar css-->
<Link type="text/css" href="../assets/plugins/scroll-bar/jquery.mCustomScrollbar.css" rel="stylesheet" />
<!-- COLOR-SKINS -->
<Link type="text/css" id="theme" rel="stylesheet" media="all" href="../assets/webslidemenu/color-skins/color10.css" />
<!-- <script  type="application/js" src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script> -->

<!-- jquery -->

<title>My MAll</title>
</head>
<body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script type="application/js" src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- 	<div id="wrapper"> -->
	<!--Loader-->
	<div id="global-loader" class="d-none">
		<img src="../assets/images/products/products/loader.png" class="loader-img floating" alt="">
	</div>

	<!--Topbar-->
	<div class="header-main">
		<div class="top-bar">
			<div class="container">
				<div class="row">
					<div class="col-xl-8 col-lg-8 col-sm-4 col-7">
						<div class="top-bar-left d-flex">
							<div class="clearfix d-none">
								<ul class="socials">
									<li><a class="social-icon text-dark" href="#">
											<i class="fa fa-facebook"></i>
										</a></li>
									<li><a class="social-icon text-dark" href="#">
											<i class="fa fa-twitter"></i>
										</a></li>
									<li><a class="social-icon text-dark" href="#">
											<i class="fa fa-linkedin"></i>
										</a></li>
									<li><a class="social-icon text-dark" href="#">
											<i class="fa fa-google-plus"></i>
										</a></li>
								</ul>
							</div>
							<div class="clearfix">
								<ul class="contact border-left">
									<li class="mr-5 d-lg-none"><a href="#" class="callnumber text-dark">
											<span><i class="fa fa-phone mr-1"></i>: +425 345 8765</span>
										</a></li>
									<li class="select-country mr-5"><select class="form-control select2-flag-search" data-placeholder="Select Country">
											<option value="UM">United States of America</option>
											<option value="AF">Afghanistan</option>
											<option value="AL">Albania</option>
											<option value="AD">Andorra</option>
											<option value="AG">Antigua and Barbuda</option>
											<option value="AU">Australia</option>
											<option value="AM">Armenia</option>
											<option value="AO">Angola</option>
											<option value="AR">Argentina</option>
											<option value="AT">Austria</option>
											<option value="AZ">Azerbaijan</option>
											<option value="BA">Bosnia and Herzegovina</option>
											<option value="BB">Barbados</option>
											<option value="BD">Bangladesh</option>
											<option value="BE">Belgium</option>
											<option value="BF">Burkina Faso</option>
											<option value="BG">Bulgaria</option>
											<option value="BH">Bahrain</option>
											<option value="BJ">Benin</option>
											<option value="BN">Brunei</option>
											<option value="BO">Bolivia</option>
											<option value="BT">Bhutan</option>
											<option value="BY">Belarus</option>
											<option value="CD">Congo</option>
											<option value="CA">Canada</option>
											<option value="CF">Central African Republic</option>
											<option value="CI">Cote d'Ivoire</option>
											<option value="CL">Chile</option>
											<option value="CM">Cameroon</option>
											<option value="CN">China</option>
											<option value="CO">Colombia</option>
											<option value="CU">Cuba</option>
											<option value="CV">Cabo Verde</option>
											<option value="CY">Cyprus</option>
											<option value="DJ">Djibouti</option>
											<option value="DK">Denmark</option>
											<option value="DM">Dominica</option>
											<option value="DO">Dominican Republic</option>
											<option value="EC">Ecuador</option>
											<option value="EE">Estonia</option>
											<option value="ER">Eritrea</option>
											<option value="ET">Ethiopia</option>
											<option value="FI">Finland</option>
											<option value="FJ">Fiji</option>
											<option value="FR">France</option>
											<option value="GA">Gabon</option>
											<option value="GD">Grenada</option>
											<option value="GE">Georgia</option>
											<option value="GH">Ghana</option>
											<option value="GH">Ghana</option>
											<option value="HN">Honduras</option>
											<option value="HT">Haiti</option>
											<option value="HU">Hungary</option>
											<option value="ID">Indonesia</option>
											<option value="IE">Ireland</option>
											<option value="IL">Israel</option>
											<option value="IN">India</option>
											<option value="IQ">Iraq</option>
											<option value="IR">Iran</option>
											<option value="IS">Iceland</option>
											<option value="IT">Italy</option>
											<option value="JM">Jamaica</option>
											<option value="JO">Jordan</option>
											<option value="JP">Japan</option>
											<option value="KE">Kenya</option>
											<option value="KG">Kyrgyzstan</option>
											<option value="KI">Kiribati</option>
											<option value="KW">Kuwait</option>
											<option value="KZ">Kazakhstan</option>
											<option value="LA">Laos</option>
											<option value="LB">Lebanons</option>
											<option value="LI">Liechtenstein</option>
											<option value="LR">Liberia</option>
											<option value="LS">Lesotho</option>
											<option value="LT">Lithuania</option>
											<option value="LU">Luxembourg</option>
											<option value="LV">Latvia</option>
											<option value="LY">Libya</option>
											<option value="MA">Morocco</option>
											<option value="MC">Monaco</option>
											<option value="MD">Moldova</option>
											<option value="ME">Montenegro</option>
											<option value="MG">Madagascar</option>
											<option value="MH">Marshall Islands</option>
											<option value="MK">Macedonia (FYROM)</option>
											<option value="ML">Mali</option>
											<option value="MM">Myanmar (formerly Burma)</option>
											<option value="MN">Mongolia</option>
											<option value="MR">Mauritania</option>
											<option value="MT">Malta</option>
											<option value="MV">Maldives</option>
											<option value="MW">Malawi</option>
											<option value="MX">Mexico</option>
											<option value="MZ">Mozambique</option>
											<option value="NA">Namibia</option>
											<option value="NG">Nigeria</option>
											<option value="NO">Norway</option>
											<option value="NP">Nepal</option>
											<option value="NR">Nauru</option>
											<option value="NZ">New Zealand</option>
											<option value="OM">Oman</option>
											<option value="PA">Panama</option>
											<option value="PF">Paraguay</option>
											<option value="PG">Papua New Guinea</option>
											<option value="PH">Philippines</option>
											<option value="PK">Pakistan</option>
											<option value="PL">Poland</option>
											<option value="QA">Qatar</option>
											<option value="RO">Romania</option>
											<option value="RU">Russia</option>
											<option value="RW">Rwanda</option>
											<option value="SA">Saudi Arabia</option>
											<option value="SB">Solomon Islands</option>
											<option value="SC">Seychelles</option>
											<option value="SD">Sudan</option>
											<option value="SE">Sweden</option>
											<option value="SG">Singapore</option>
											<option value="TG">Togo</option>
											<option value="TH">Thailand</option>
											<option value="TJ">Tajikistan</option>
											<option value="TL">Timor-Leste</option>
											<option value="TM">Turkmenistan</option>
											<option value="TN">Tunisia</option>
											<option value="TO">Tonga</option>
											<option value="TR">Turkey</option>
											<option value="TT">Trinidad and Tobago</option>
											<option value="TW">Taiwan</option>
											<option value="UA">Ukraine</option>
											<option value="UG">Uganda</option>
											<option value="UY">Uruguay</option>
											<option value="UZ">Uzbekistan</option>
											<option value="VA">Vatican City (Holy See)</option>
											<option value="VE">Venezuela</option>
											<option value="VN">Vietnam</option>
											<option value="VU">Vanuatu</option>
											<option value="YE">Yemen</option>
											<option value="ZM">Zambia</option>
											<option value="ZW">Zimbabwe</option>
										</select></li>
									<li class="dropdown mr-5"><a href="#" class="text-dark" data-toggle="dropdown">
											<span> Language <i class="fa fa-caret-down text-muted"></i></span>
										</a>
										<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
											<a href="#" class="dropdown-item"> English </a>
											<a class="dropdown-item" href="#"> Arabic </a>
											<a class="dropdown-item" href="#"> German </a>
											<a href="#" class="dropdown-item"> Greek </a>
											<a href="#" class="dropdown-item"> Spanish </a>
										</div></li>
									<li class="dropdown"><a href="#" class="text-dark" data-toggle="dropdown">
											<span>Currency <i class="fa fa-caret-down text-muted"></i></span>
										</a>
										<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
											<a href="#" class="dropdown-item"> USD </a>
											<a class="dropdown-item" href="#"> EUR </a>
											<a class="dropdown-item" href="#"> INR </a>
											<a href="#" class="dropdown-item"> GBP </a>
										</div></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-sm-8 col-5">
						<div class="top-bar-right">
							<ul class="custom">
								<li><a href="register.html" class="text-dark">
										<i class="fa fa-user mr-1"></i> <span>Register</span>
									</a></li>
								<li><a href="login.html" class="text-dark">
										<i class="fa fa-sign-in mr-1"></i> <span>Login</span>
									</a></li>
								<li class="dropdown"><a href="#" class="text-dark" data-toggle="dropdown">
										<i class="fa fa-home mr-1"></i><span> My Dashboard</span>
									</a>
									<div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
										<a href="mydash.html" class="dropdown-item">
											<i class="dropdown-icon icon icon-user"></i> My Profile
										</a>
										<a class="dropdown-item" href="#">
											<i class="dropdown-icon icon icon-speech"></i> Inbox
										</a>
										<a class="dropdown-item" href="#">
											<i class="dropdown-icon icon icon-bell"></i> Notifications
										</a>
										<a href="mydash.html" class="dropdown-item">
											<i class="dropdown-icon  icon icon-settings"></i> Account Settings
										</a>
										<a class="dropdown-item" href="#">
											<i class="dropdown-icon icon icon-power"></i> Log out
										</a>
									</div></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Mobile Header -->
		<div class="horizontal-header clearfix ">
			<div class="container">
				<a id="horizontal-navtoggle" class="animated-arrow">
					<span></span>
				</a>
				<span class="smllogo"><img src="../assets/images/brand/logo.png" width="120" alt="" /></span>
				<a href="tel:245-6325-3256" class="callusbtn">
					<i class="fa fa-phone" aria-hidden="true"></i>
				</a>
			</div>
		</div>
		<!-- /Mobile Header -->

		<div class="horizontal-main  bg-dark-transparent clearfix">
			<div class="horizontal-mainwrapper container clearfix">
				<div class="desktoplogo">
					<a href="index.html">
						<img src="../assets//images/brand/logo1.png" alt="">
					</a>
				</div>
				<div class="desktoplogo-1">
					<a href="index.html">
						<img src="../assets//images/brand/logo.png" alt="">
					</a>
				</div>
				<!--Nav-->
				<nav class="horizontalMenu clearfix d-md-flex">
					<ul class="horizontalMenu-list">
						<li aria-haspopup="true"><a href="#">
								Home <span class="fa fa-caret-down m-0"></span>
							</a>
							<ul class="sub-menu">
								<li aria-haspopup="true"><a href="index.html">Home-default</a></li>
								<li aria-haspopup="true"><a href="classifieds-text.html">Home style-1</a></li>
								<li aria-haspopup="true"><a href="classifieds-slides.html">Home style-2</a></li>
								<li aria-haspopup="true"><a href="classifieds-video.html">Home style-3</a></li>
								<li aria-haspopup="true"><a href="classifieds-animation.html">Home style-4 </a></li>
								<li aria-haspopup="true"><a href="classifieds-map.html">Home style-5</a></li>
								<li aria-haspopup="true"><a href="intro-page.html">Home Intro Page</a></li>
								<li aria-haspopup="true"><a href="popup-login.html">Home Pop-up login</a></li>
								<li aria-haspopup="true"><a href="banner.html">Banners</a></li>
							</ul></li>
						<li aria-haspopup="true"><a href="about.html">About Us </a></li>
						<li aria-haspopup="true"><a href="widgets.html">Widgets</a></li>
						<li aria-haspopup="true"><a href="#" class="active">
								Pages <span class="fa fa-caret-down m-0"></span>
							</a>
							<div class="horizontal-megamenu clearfix">
								<div class="container">
									<div class="megamenu-content">
										<div class="row">
											<ul class="col link-list">
												<li class="title">Custom pages</li>
												<li><a href="classifieds-list.html">Classifieds List</a></li>
												<li><a href="classifieds-list-right.html">Classifieds List Right</a></li>
												<li><a href="classifieds-list-map.html">Classifieds Map list</a></li>
												<li><a href="classifieds-list-map2.html">Classifieds Map list 02</a></li>
												<li><a href="classifieds-list-map3.html">Classifieds Map style 03</a></li>
												<li><a href="categories.html">Categories</a></li>
												<li><a href="inovice.html">Invoice</a></li>
												<li><a href="usersall.html">User Lists</a></li>
											</ul>
											<ul class="col link-list">
												<li class="title">Custom pages</li>
												<li><a href="ad-list.html">Ad Listing</a></li>
												<li><a href="ad-list-right.html">Ad Listing Right</a></li>
												<li><a href="ad-details.html">Ad Details</a></li>
												<li><a href="ad-details-right.html">Ad Details Right</a></li>
												<li><a href="ad-posts.html">Ad Posts</a></li>
												<li><a href="ad-posts2.html">Ad Posts2</a></li>
												<li><a href="pricing.html">Pricing</a></li>
												<li><a href="typography.html">Typography</a></li>
											</ul>
											<ul class="col link-list">
												<li><a href="userprofile.html"> User Profile</a></li>
												<li><a href="mydash.html">My Dashboard</a></li>
												<li><a href="myads.html">Ads</a></li>
												<li><a href="myfavorite.html">Favorite Ads</a></li>
												<li><a href="manged.html">Manged Ads</a></li>
												<li><a href="payments.html">Payments</a></li>
												<li><a href="orders.html"> Orders</a></li>
												<li><a href="settings.html"> Settings</a></li>
												<li><a href="tips.html">Tips</a></li>
											</ul>
											<ul class="col link-list">
												<li class="title">User pages</li>
												<li><a href="underconstruction.html">Under Constructions</a></li>
												<li><a href="404.html">404</a></li>
												<li><a href="register.html">Register</a></li>
												<li><a href="login.html">Login</a></li>
												<li><a href="login-2.html">Login 02</a></li>
												<li><a href="forgot.html">Forgot Password</a></li>
												<li><a href="lockscreen.html">Lock Screen</a></li>
												<li><a href="faq.html">Faq</a></li>
											</ul>
											<ul class="col link-list">
												<li class="title">User pages</li>
												<li><a href="header-style1.html">Header Style 01</a></li>
												<li><a href="header-style2.html">Header Style 02</a></li>
												<li><a href="header-style3.html">Header Style 03</a></li>
												<li><a href="header-style4.html">Header Style 04</a></li>
												<li><a href="footer-style.html">Footer Style 01</a></li>
												<li><a href="footer-style2.html">Footer Style 02</a></li>
												<li><a href="footer-style3.html">Footer Style 03</a></li>
												<li><a href="footer-style4.html">Footer Style 04</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div></li>
						<li aria-haspopup="true"><a href="#">
								Blog <span class="fa fa-caret-down m-0"></span>
							</a>
							<ul class="sub-menu">
								<li aria-haspopup="true"><a href="#">
										Blog Grid <i class="fa fa-angle-right float-right mt-1 d-none d-lg-block"></i>
									</a>
									<ul class="sub-menu">
										<li aria-haspopup="true"><a href="blog-grid.html">Blog Grid Left</a></li>
										<li aria-haspopup="true"><a href="blog-grid-right.html">Blog Grid Right</a></li>
										<li aria-haspopup="true"><a href="blog-grid-center.html">Blog Grid Center</a></li>
									</ul></li>
								<li aria-haspopup="true"><a href="#">
										Blog List <i class="fa fa-angle-right float-right mt-1 d-none d-lg-block"></i>
									</a>
									<ul class="sub-menu">
										<li aria-haspopup="true"><a href="blog-list.html">Blog List Left</a></li>
										<li aria-haspopup="true"><a href="blog-list-right.html">Blog List Right</a></li>
										<li aria-haspopup="true"><a href="blog-list-center.html">Blog List Center</a></li>
									</ul></li>
								<li aria-haspopup="true"><a href="#">
										Blog Details <i class="fa fa-angle-right float-right mt-1 d-none d-lg-block"></i>
									</a>
									<ul class="sub-menu">
										<li aria-haspopup="true"><a href="blog-details.html">Blog Details Left</a></li>
										<li aria-haspopup="true"><a href="blog-details-right.html">Blog Details Right</a></li>
										<li aria-haspopup="true"><a href="blog-details-center.html">Blog Details Center</a></li>
									</ul></li>
							</ul></li>
						<li aria-haspopup="true"><a href="#">
								Categories <span class="fa fa-caret-down m-0"></span>
							</a>
							<ul class="sub-menu">
								<li aria-haspopup="true"><a href="classified.html">Classifieds Left</a></li>
								<li aria-haspopup="true"><a href="classified-right.html">Classifieds Rights </a></li>
							</ul></li>
						<li aria-haspopup="true"><a href="contact.html">
								Contact Us <span class="wsarrow"></span>
							</a></li>
						<li aria-haspopup="true" class="d-lg-none mt-5 pb-5 mt-lg-0"><span><a class="btn btn-orange" href="ad-posts.html">Post Free Ad</a></span></li>
					</ul>
					<ul class="mb-0">
						<li aria-haspopup="true" class="mt-5 d-none d-lg-block "><span><a class="btn btn-orange ad-post" href="ad-posts.html">Post Free Ad</a></span></li>
					</ul>
				</nav>
				<!--Nav-->
			</div>
		</div>
	</div>