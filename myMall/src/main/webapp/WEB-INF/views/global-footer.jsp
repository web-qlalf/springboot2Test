<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- </div> -->

<!--Footer Section-->
<section>
	<footer class="bg-dark-purple text-white">
		<div class="footer-main">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 col-md-12">my-mall</div>

				</div>
			</div>
		</div>
		<div class="bg-dark-purple text-white p-0">
			<div class="container">
				<div class="row d-flex">
					<div class="col-lg-12 col-sm-12 mt-3 mb-3 text-center ">
						Copyright © 2019
						<a href="#" class="fs-14 text-primary">Claylist</a>
						. Designed by
						<a href="#" class="fs-14 text-primary">Spruko</a>
						All rights reserved.
					</div>
				</div>
			</div>
		</div>
	</footer>
</section>
<!--/Footer Section-->

<!-- Back to top -->
<a href="#top" id="back-to-top">
	<i class="fa fa-rocket"></i>
</a>


<!-- Bootstrap js -->
<script type="application/js" src="../assets/plugins/bootstrap-4.3.1-dist/js/popper.min.js"></script>
<script type="application/js" src="../assets/plugins/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

<!--JQuery Sparkline Js-->
<script type="application/js" src="../assets/js/vendors/jquery.sparkline.min.js"></script>

<!-- Circle Progress Js-->
<script type="application/js" src="../assets/js/vendors/circle-progress.min.js"></script>

<!-- Star Rating Js-->
<script type="application/js" src="../assets/plugins/rating/jquery.rating-stars.js"></script>

<!--Owl Carousel js -->
<script type="application/js" src="../assets/plugins/owl-carousel/owl.carousel.js"></script>

<!--Horizontal Menu-->
<script type="application/js" src="../assets/plugins/Horizontal2/Horizontal-menu/horizontal.js"></script>

<!--JQuery TouchSwipe js-->
<script type="application/js" src="../assets/js/jquery.touchSwipe.min.js"></script>

<!--Select2 js -->
<script type="application/js" src="../assets/plugins/select2/select2.full.min.js"></script>
<script type="application/js" src="../assets/js/select2.js"></script>

<!-- sticky Js-->
<script type="application/js" src="../assets//js/sticky.js"></script>

<!-- Cookie js -->
<!-- <script type="application/js" src="../assets/plugins/cookie/jquery.ihavecookies.js"></script> -->
<!-- <script type="application/js" src="../assets/plugins/cookie/cookie.js"></script> -->

<!-- Custom scroll bar Js-->
<script type="application/js" src="../assets/plugins/scroll-bar/jquery.mCustomScrollbar.concat.min.js"></script>

<!-- Swipe Js-->
<script type="application/js" src="../assets/js/swipe.js"></script>

<!-- Scripts Js-->
<script type="application/js" src="../assets/js/scripts2.js"></script>

<!-- Custom Js-->
<script type="application/js" src="../assets/js/custom.js"></script>

<script>
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });
</script>
</body>
</html>


