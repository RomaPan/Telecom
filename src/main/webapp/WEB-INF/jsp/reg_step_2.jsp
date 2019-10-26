<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 

<!DOCTYPE html>
<html lang="en">
	<title>Choose your mobile number</title>
	<meta charset="utf-8">
	<head>
		<link rel="stylesheet" href="css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap" rel="stylesheet">
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
				<p>STEP TWO:</p>
			</div>
		</div>
		<div class="registration-user-prompt">
			<p>A few more steps... Please select your new phone number</p>
		</div>
		<hr color="teal" style="width: 100%;">
		
		<div class="spacer">
		</div>
	
	
	<div class="registration-step-2-main-div">

		
		<div class="registration-step-2-child-div">
			
		
			<form class="new-telephone-number-form" action="controller" method="post">
				<input type="hidden" name="command" value="connect-phone-number" />
				<input type="hidden" name="userID" value="${sessionScope.user.userID}"/>
			<table>
	
		<c:choose>
    		<c:when test="${sessionScope.numbers != null}">
        	<c:forEach var = "i" begin = "0" end = "2">
			<li style="list-style:none;">
				<input type="radio" name="phoneNumber" value="${sessionScope.numbers[i]}">+375 
				<c:out value = "${sessionScope.numbers[i]}"/>
			</li><br>
      		</c:forEach> 
       	 		
    		</c:when>
    		 
    		<c:otherwise>
        		<c:out value="Something went wrong, unable to retrieve new phone number selection."/>
    		</c:otherwise>
	</c:choose>
	
  </table>
	
	<button class="cp-unstyled-button">Select this number</button>
	
	<c:set var="connPhoneNumberMessage" value="${requestScope.connPhoneNumberMessage}" scope="request"/>

		<h2><c:out value="${connPhoneNumberMessage}"/></h2>
	
	
	</form>
			
			
			
			
	</div>
	
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
				<br>Copyright ©2019 All rights reserved | Roma Telecom Ltd
			</p>
		</div>
	</footer>
</body>
</html>