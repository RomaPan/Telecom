<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Registration</title>
	<meta charset="utf-8">
		<link rel="stylesheet" href="css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap" rel="stylesheet">
		
		<fmt:setLocale value="${sessionScope.local }" />
		<fmt:setBundle basename="localization.local" var="loc" />
		<fmt:message bundle="${loc }" key="local.userGreeting" var="greeting" />
		<fmt:message bundle="${loc }" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc }" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc }" key="local.signout.button" var="signout_button" />
		<fmt:message bundle="${loc }" key="local.home.button" var="home_button" />
		<fmt:message bundle="${loc }" key="local.plan.button" var="plan_button" />
		<fmt:message bundle="${loc }" key="local.contact.button" var="contact_button" />
		<fmt:message bundle="${loc }" key="local.register.button" var="register_button" />
		<fmt:message bundle="${loc }" key="local.signin.button" var="signin_button" />
		<fmt:message bundle="${loc }" key="local.repeat.password" var="repeat_password" />
		
		<fmt:message bundle="${loc }" key="local.registration.p" var="registration_p" />
		
		<fmt:message bundle="${loc }" key="local.table.user.name" var="table_user_name" />
		<fmt:message bundle="${loc }" key="local.table.user.surname" var="table_user_surname" />
		<fmt:message bundle="${loc }" key="local.table.user.address" var="table_user_address" />
		<fmt:message bundle="${loc }" key="local.table.user.city" var="table_user_city" />
		<fmt:message bundle="${loc }" key="local.table.user.zip" var="table_user_zip" />
		<fmt:message bundle="${loc }" key="local.table.user.email" var="table_user_email" />
		
		<fmt:message bundle="${loc }" key="local.plans.comfort" var="comfort" />
		<fmt:message bundle="${loc }" key="local.plans.traffic" var="traffic" />
		<fmt:message bundle="${loc }" key="local.plans.unlimcalls" var="unlimcalls" />
		<fmt:message bundle="${loc }" key="local.plans.mintoland" var="mintoland" />
		<fmt:message bundle="${loc }" key="local.plans.unlimtoland" var="unlimtoland" />
		<fmt:message bundle="${loc }" key="local.plans.monthly" var="monthly" />
		
		<fmt:message bundle="${loc }" key="local.edit.profile.button" var="edit_profile_button" />
		<fmt:message bundle="${loc }" key="local.change.password.button" var="change_password_button" />
		<fmt:message bundle="${loc }" key="local.change.call.plan.button" var="change_call_plan_button" />
		<fmt:message bundle="${loc }" key="local.change.telephone.number.button" var="change_telephone_number_button" />
		<fmt:message bundle="${loc }" key="local.your.fibre.button" var="your_fibre_button" />
		<fmt:message bundle="${loc }" key="local.payment.options.button" var="payment_options_button" />
		<fmt:message bundle="${loc }" key="local.request.paper.bill.button" var="request_paper_bill_button" />
		<fmt:message bundle="${loc }" key="local.order.history.button" var="order_history_button" />
		
		<fmt:message bundle="${loc }" key="local.account.summary" var="account_summary" />
		<fmt:message bundle="${loc }" key="local.back.to.menu.button" var="back_to_menu_button" />
		
		<fmt:message bundle="${loc }" key="local.table.account.id" var="table_account_id" />
		<fmt:message bundle="${loc }" key="local.table.created.at" var="table_created_at" />
		<fmt:message bundle="${loc }" key="local.table.number.id" var="table_number_id" />
		<fmt:message bundle="${loc }" key="local.table.connected" var="table_connected" />
		<fmt:message bundle="${loc }" key="local.table.phone.number" var="table_phone_number" />
		<fmt:message bundle="${loc }" key="local.table.call.plan.id" var="table_call_plan_id" />
		<fmt:message bundle="${loc }" key="local.table.call.plan.created" var="table_call_plan_created" />
		<fmt:message bundle="${loc }" key="local.table.call.plan.name" var="table_call_plan_name" />
		<fmt:message bundle="${loc }" key="local.table.call.plan.rate" var="table_call_plan_rate" />
		<fmt:message bundle="${loc }" key="local.table.minutes.left" var="table_minutes_left" />
		<fmt:message bundle="${loc }" key="local.table.account.balance" var="table_account_balance" />
		<fmt:message bundle="${loc }" key="local.table.account.status" var="table_account_status" />
		
		<fmt:message bundle="${loc }" key="local.submit.button" var="submit_button" />
		<fmt:message bundle="${loc }" key="local.select.your.phone.number" var="select_your_phone_number" />
		<fmt:message bundle="${loc }" key="local.placeholder.password" var="placeholder_password" />
		
		<fmt:message bundle="${loc }" key="local.old.password" var="old_password" />
		<fmt:message bundle="${loc }" key="local.new.password" var="new_password" />
		<fmt:message bundle="${loc }" key="local.repeat.password" var="repeat_password" />
		
		<fmt:message bundle="${loc }" key="local.select.this.number.button" var="select_this_number_button" />
		
		<fmt:message bundle="${loc }" key="local.view.available.phone.numbers" var="view_available_phone_numbers" />
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
 				<input type="hidden" name="sitelocation" value="registration"/>
 				<input type="hidden" name="language" value="en_GB"/>
 				<input class="styled-button-lang" type="submit" value="${en_button }"/>
  			</form>
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="registration"/>
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


	<div class="registration-main-div">
		<div class="reg-image">
		<div class="registration-child-left-text">
			<p>STEP ONE</p>
		</div>
		<div class="registration-child-div">
			<form class="registration-form" action="controller" method="post">
			<input type="hidden" name="command" value="registration"/>
					
			<p class="registration-grand-child-div">${registration_p }</p>
						
			<div class="registration-grand-child-div">
				<label for="first-name">${table_user_name }<span class="red">*</span></label><br>
				<input id="first-name" pattern="[a-z A-Z]+" oninvalid="setCustomValidity('Please enter only letters. ')" type="text" name="name" placeholder="${table_user_name }">
			</div>
				<br/>
			<div class="registration-grand-child-div">
				<label for="last-name">${table_user_surname }<span class="red">*</span></label><br>
				<input id="last-name" pattern="^[a-z A-Z]+" oninvalid="setCustomValidity('Please enter only letters. ')" type="text" name="surname" placeholder="${table_user_surname }">
			</div>
				<br>
			<div class="registration-grand-child-div">
				<label for="email">${table_user_email }<span class="red">*</span></label><br>
				<input id="email" type="email" name="email" placeholder="${table_user_email }">
			</div>
				<br>
			<div class="registration-grand-child-div">
				<label for="address">${table_user_address }<span class="red">*</span></label><br>
				<input id="address" type="text" name="addressL1" placeholder="${table_user_address }">
			</div >
				<br>
			<div class="registration-grand-child-div">
				<label for="city">${table_user_city }<span class="red">*</span></label><br>
				<input id="city" type="text" name="addressL2" placeholder="${table_user_city }">
			</div>
				<br>
			<div class="registration-grand-child-div">
				<label for="zip">${table_user_zip }<span class="red">*</span></label><br>
				<input id="zip" type="number" name="addressL3" placeholder="${table_user_zip }">
			</div>
				<br>
			<div class="registration-grand-child-div">
				<label for="pass">${placeholder_password }<span class="red">*</span></label><br>
				<input id="pass" id="pass" name="pass" type="password" pattern="^\S{4,16}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 4 characters' : ''); if(this.checkValidity()) form.pass2.pattern = this.value;" placeholder="${placeholder_password }" required>
			</div>
				<br>
			<div class="registration-grand-child-div">
				<label for="pass2">${repeat_password }<span class="red">*</span></label><br>
				<input id="pass2" name="pass2" type="password" pattern="^\S{4,16}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Passwords do not match. Please enter the same Password as above' : '');" placeholder="${repeat_password }" required>
			</div>
				<br>
				<br>
			<div class="registration-grand-child-button">
				<button class="reg-unstyled-button">${submit_button }</button>
			</div>
			</form>
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
				${copyright } |<br> Roma Telecom Ltd<br><br><m:today/>
			</p>
		</div>
	</footer>
</body>
</html>