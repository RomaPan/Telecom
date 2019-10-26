<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Error 404</title>
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
	</head>
<body>
	<div class="header">
		<form class="logo" action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-index-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Roma Telecom</div></button>
  		</form>
		
		<div class="header-right">
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-index-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Home</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-plans-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Plans &amp; Pricing</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-contacts-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Contact us</div></button>
  			</form>
  			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-registration-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Get Started</div></button>
  			</form>
			
			<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-login-page"/>
  				<button class="unstyled-button" class="border"><div class="active">Sign in</div></button>
  			</form>
		</div>
	</div>
	<div class="spacer"></div>
	
	<div class="registration-step-3-main-div">
			<div class="registration-step-3-div-child-left-text">
				<p>Error 404:</p>
			</div>
		</div>
		<br>
		<br>
		<br>
		<div class="registration-user-prompt">
			<p>Ooops, something went wrong... </p><br>
		</div>
		<br>
		<br>
		<br>
	
	
	
	
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
				Copyright ©2019 All rights reserved |<br> Roma Telecom Ltd<br><br><m:today/>
			</p>
		</div>
	</footer>
</body>
</html>