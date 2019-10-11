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
				<p>STEP THREE:</p>
			</div>
		</div>
		
		<div class="registration-user-prompt">
			<p>We are almost done... Please choose your call plan</p>
		</div>
		<hr color="teal" style="width: 100%;">
		
		<div class="spacer">
		</div>
		
		<div class="call-plan">
			<table>
    <thead >
        <tr>
            <th>Comfort S</th>
            <th>Comfort M</th>
            <th>Comfort L</th>
            <th>Comfort XL</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1GB free traffic</td>
            <td>7GB free traffic</td>
            <td>15GB free traffic</td>
            <td>35GB free traffic</td>
            
        </tr>
        <tr>
            <td>Unlimited calls inside network</td>
            <td>Unlimited calls inside network</td>
            <td>Unlimited calls inside network</td>
            <td>Unlimited calls inside network</td>
        </tr>
         <tr>
            <td>60 min to landlines.</td>
            <td>120 min to landlines</td>
            <td>350 min to landlines</td>
            <td>Unlimited calls to landlines</td>
        </tr>
        <tr>
            <td>18,35$ monthly fee</td>
            <td>30,70$ monthly fee</td>
            <td>39,95$ monthly fee</td>
            <td>58,45$ monthly fee</td>
        </tr>
        <tr>
            <td>
            	<form action="controller" method="post" >
            		<input type="hidden" name="command" value="set-call-plan" />
            		<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
            		<input type="hidden" name="callPlan" value="1">
            		<button class="cp-unstyled-button">Select</button>
            	</form>
            </td>
            <td>
				<form action="controller" method="post" >
            		<input type="hidden" name="command" value="set-call-plan" />
            		<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
            		<input type="hidden" name="callPlan" value="2">
            		<button class="cp-unstyled-button">Select</button>
            	</form>
			</td>
            <td>
				<form action="controller" method="post" >
            		<input type="hidden" name="command" value="set-call-plan" />
            		<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
            		<input type="hidden" name="callPlan" value="3">
            		<button class="cp-unstyled-button">Select</button>
            	</form>
			</td>
            <td>
				<form action="controller" method="post" >
            		<input type="hidden" name="command" value="set-call-plan" />
            		<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
            		<input type="hidden" name="callPlan" value="4">
            		<button class="cp-unstyled-button">Select</button>
            	</form>
			</td>
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
				Copyright ©2019 All rights reserved |<br> Roma Telecom Ltd<br><br><m:today/>
			</p>
		</div>
	</footer>
</body>
</html>