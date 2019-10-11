<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 

<!DOCTYPE html>
<html lang="en">
	<title>Plans &amp; Pricing</title>
	<meta charset="utf-8">
	<head>
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
		
		<fmt:message bundle="${loc }" key="local.plans.main.p" var="plan_main_p" />
		<fmt:message bundle="${loc }" key="local.plans.main.p2" var="plan_main_p2" />
		<fmt:message bundle="${loc }" key="local.plans.comfort" var="comfort" />
		<fmt:message bundle="${loc }" key="local.plans.traffic" var="traffic" />
		<fmt:message bundle="${loc }" key="local.plans.unlimcalls" var="unlimcalls" />
		<fmt:message bundle="${loc }" key="local.plans.mintoland" var="mintoland" />
		<fmt:message bundle="${loc }" key="local.plans.unlimtoland" var="unlimtoland" />
		<fmt:message bundle="${loc }" key="local.plans.monthly" var="monthly" />
		
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
 				<input type="hidden" name="sitelocation" value="plans"/>
 				<input type="hidden" name="language" value="en_GB"/>
 				<input class="styled-button-lang" type="submit" value="${en_button }"/>
  			</form>
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="plans"/>
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
	
	
	
		<div class="registration-step-3-main-div">
			<div class="registration-step-3-div-child-left-text">
				<p>${plan_main_p}</p>
			</div>
		</div>
		
		<div class="registration-user-prompt">
			<p>${plan_main_p2}</p>
		</div>
		<hr color="teal" style="width: 100%;">
		
		<div class="spacer">
		</div>
		
		<div class="call-plan">
			<table>
    <thead >
        <tr>
            <th>${comfort} S</th>
            <th>${comfort} M</th>
            <th>${comfort} L</th>
            <th>${comfort} XL</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1GB ${traffic}</td>
            <td>7GB ${traffic}</td>
            <td>15GB ${traffic}</td>
            <td>35GB ${traffic}</td>
            
        </tr>
        <tr>
            <td>${unlimcalls}</td>
            <td>${unlimcalls}</td>
            <td>${unlimcalls}</td>
            <td>${unlimcalls}</td>
        </tr>
         <tr>
            <td>60 ${mintoland}</td>
            <td>120 ${mintoland}</td>
            <td>350 ${mintoland}</td>
            <td>${unlimtoland}</td>
        </tr>
        <tr>
            <td><h2><b class="red">18,35$ ${monthly}</b></h2></td>
            <td><h2><b class="red">30,70$ ${monthly}</b></h2></td>
            <td><h2><b class="red">39,95$ ${monthly}</b></h2></td>
            <td><h2><b class="red">58,45$ ${monthly}</b></h2></td>
        </tr>
        
    	</tbody>
    </table>
		</div>
		
		
			<div class="spacer"></div>
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
				${copyright } |<br> Roma Telecom Ltd<br><br><m:today/>
			</p>
		</div>
	</footer>
</body>
</html>