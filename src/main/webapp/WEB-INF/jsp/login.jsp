<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Sign in</title>
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
		<fmt:message bundle="${loc }" key="local.submit.button" var="submit_button" />
		<fmt:message bundle="${loc }" key="local.placeholder.email" var="placeholder_email" />
		<fmt:message bundle="${loc }" key="local.placeholder.password" var="placeholder_password" />
		<fmt:message bundle="${loc }" key="local.login.register.message" var="login_register_message" />
		<fmt:message bundle="${loc }" key="local.login.register.button" var="login_register_button" />
		<fmt:message bundle="${loc }" key="local.plans.copyright" var="copyright" />
		<fmt:message bundle="${loc }" key="local.login.failed.message" var="login_failed_message" />
		<fmt:message bundle="${loc }" key="local.session.timeout.message" var="session_timeout_message" />
		<fmt:message bundle="${loc }" key="local.login.failed.user.blocked" var="login_failed_user_blocked" />
		<fmt:message bundle="${loc }" key="local.signout.message" var="signout_message" />
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
 				<input type="hidden" name="sitelocation" value="signin"/>
 				<input type="hidden" name="language" value="en_GB"/>
 				<input class="styled-button-lang" type="submit" value="${en_button }"/>
  			</form>
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="signin"/>
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
	<div class="spacer">
	</div>
	<div class="main-2">
		<div class="login-image">
			<div class="login-block">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="authorization" />
					<div class="login-form">
						
						<p class="red">
							<c:if test="${sessionScope.LogInFailed != null}">
							<c:out value="${login_failed_message}"/></c:if>
							
							<c:if test="${requestScope.Message != null}">
							<c:out value="${session_timeout_message}"/></c:if>
							
							<c:if test="${sessionScope.LogInFailedUserBlocked != null}">
							<c:out value="${login_failed_user_blocked}"/></c:if>
							
							<c:if test="${requestScope.SignOut != null}">
							<c:out value="${signout_message}"/></c:if>
						</p>
						<br>
						<input type="hidden" name="command" value="authorization" /> 
						<input type="email" name="email" placeholder="${placeholder_email}">
						<br>
						<input type="password" name="pass" placeholder="${placeholder_password}">
						<br>
						<button class="styled-button" type="submit" class="login-form">${submit_button}</button>
					</div>
				</form>
				<div class="login-register-button">
				<form action="controller" method="get">
 				<input type="hidden" name="command" value="go-to-registration-page"/>
  				<button class="unstyled-register-button">${login_register_message} <b>${login_register_button}</b></button></form>
  				</div>
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
				${copyright} |<br> Roma Telecom Ltd<br><br><m:today/>
			</p>
		</div>
	</footer>
</body>
</html>