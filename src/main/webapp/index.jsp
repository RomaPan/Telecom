<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Roma Telecom Ltd</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local }"/>
		<fmt:setBundle basename="localization.local" var="loc"/>
		<fmt:message bundle="${loc }" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc }" key="local.locbutton.name.en" var="en_button"/>
		<fmt:message bundle="${loc }" key="local.home.button" var="home_button" />
		<fmt:message bundle="${loc }" key="local.plan.button" var="plan_button" />
		<fmt:message bundle="${loc }" key="local.contact.button" var="contact_button" />
		<fmt:message bundle="${loc }" key="local.register.button" var="register_button" />
		<fmt:message bundle="${loc }" key="local.signin.button" var="signin_button" />
		<fmt:message bundle="${loc }" key="local.see.deals.button" var="seedeals_button" />
		<fmt:message bundle="${loc }" key="local.promo.h1" var="promo_h1" />
		<fmt:message bundle="${loc }" key="local.promo.p1" var="promo_p1" />
		<fmt:message bundle="${loc }" key="local.promo.p2" var="promo_p2" />
		<fmt:message bundle="${loc }" key="local.promo.p3" var="promo_p3" />
		<fmt:message bundle="${loc }" key="local.promo.p4" var="promo_p4" />
		<fmt:message bundle="${loc }" key="local.promo.p5" var="promo_p5" />
		
		<fmt:message bundle="${loc }" key="local.plans.copyright" var="copyright" />
	</head>
	<body>
		
		<div class="header">
			<form class="logo" action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-index-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Roma Telecom</div></button>
  			</form>
  			<div id="localization-button">
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="index"/>
 				<input type="hidden" name="language" value="en_GB"/>
 				<input class="styled-button-lang" type="submit" value="${en_button }"/>
  			</form>
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="index"/>
 				<input type="hidden" name="language" value="ru_RU"/>
 				<input class="styled-button-lang" type="submit" value="${ru_button }"/>
  			</form>
  			</div>
			<div class="header-right">
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-index-page"/>
  				<button class="unstyled-button" class="border"><div class="active">${home_button}</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-plans-page"/>
  				<button class="unstyled-button" class="border"><div class="active">${plan_button}</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-contacts-page"/>
  				<button class="unstyled-button" class="border"><div class="active">${contact_button}</div></button>
  			</form>
  			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-registration-page"/>
  				<button class="unstyled-button" class="border"><div class="active">${register_button}</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-login-page"/>
  				<button class="unstyled-button" class="border"><div class="active">${signin_button}</div></button>
  			</form>
				
			</div>
		</div>
		<div class="spacer"></div>
		
		<div class="main">
			<div class="promo">
				<div class="grey-box">
					<h1>${promo_h1 }</h1>
					<p>${promo_p1 }</p>
					<p>${promo_p2 }</p>
					<form action="controller" method="get">
 						<input type="hidden" name="command" value="go-to-plans-page"/>
  						<button class="button">${seedeals_button}</button>
  					</form>
					
					<p>${promo_p3 }</p>
				</div>
			</div>
			<div class="bottom">
				<div class="columns">
					Galaxy S10e. <br>${promo_p4 }
				</div>
					<form action="controller" method="get">
 						<input type="hidden" name="command" value="go-to-plans-page"/>
  						<button class="button">${seedeals_button}</button>
  					</form>
				<div class="columns">
					<p>${promo_p5 }</p>
				</div>
				
			</div>
		</div>
		<div class="spacer"></div>
		<footer>
		<div>
			<ul class="left"  style="list-style:none;">
			<li>Apps &amp; Services</li>
			<li>Device trade-in</li>
			<li>Premium Visual Voicemail</li>
			<li>See More Apps</li>
			</ul>
			<p class="centered">
				Brands<br>Apple<br>ASUS<br>Motorola
			</p>

			<p class="right">
				${copyright } |<br> Roma Telecom Ltd<br><br><m:today/><br>
				
			</p>
		</div>
	</footer>
	</body>
</html>