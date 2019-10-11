<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Raleway&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap"
	rel="stylesheet">

<fmt:setLocale value="${sessionScope.local }" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc }" key="local.userGreeting" var="greeting" />
<fmt:message bundle="${loc }" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc }" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc }" key="local.signout.button"
	var="signout_button" />

<fmt:message bundle="${loc }" key="local.table.user.name"
	var="table_user_name" />
<fmt:message bundle="${loc }" key="local.table.user.surname"
	var="table_user_surname" />
<fmt:message bundle="${loc }" key="local.table.user.address"
	var="table_user_address" />
<fmt:message bundle="${loc }" key="local.table.user.city"
	var="table_user_city" />
<fmt:message bundle="${loc }" key="local.table.user.zip"
	var="table_user_zip" />
<fmt:message bundle="${loc }" key="local.table.user.email"
	var="table_user_email" />

<fmt:message bundle="${loc }" key="local.plans.comfort" var="comfort" />
<fmt:message bundle="${loc }" key="local.plans.traffic" var="traffic" />
<fmt:message bundle="${loc }" key="local.plans.unlimcalls"
	var="unlimcalls" />
<fmt:message bundle="${loc }" key="local.plans.mintoland"
	var="mintoland" />
<fmt:message bundle="${loc }" key="local.plans.unlimtoland"
	var="unlimtoland" />
<fmt:message bundle="${loc }" key="local.plans.monthly" var="monthly" />

<fmt:message bundle="${loc }" key="local.edit.profile.button"
	var="edit_profile_button" />
<fmt:message bundle="${loc }" key="local.change.password.button"
	var="change_password_button" />
<fmt:message bundle="${loc }" key="local.change.call.plan.button"
	var="change_call_plan_button" />
<fmt:message bundle="${loc }" key="local.change.telephone.number.button"
	var="change_telephone_number_button" />
<fmt:message bundle="${loc }" key="local.your.fibre.button"
	var="your_fibre_button" />
<fmt:message bundle="${loc }" key="local.payment.options.button"
	var="payment_options_button" />
<fmt:message bundle="${loc }" key="local.request.paper.bill.button"
	var="request_paper_bill_button" />
<fmt:message bundle="${loc }" key="local.order.history.button"
	var="order_history_button" />

<fmt:message bundle="${loc }" key="local.account.summary"
	var="account_summary" />
<fmt:message bundle="${loc }" key="local.back.to.menu.button"
	var="back_to_menu_button" />

<fmt:message bundle="${loc }" key="local.table.account.id"
	var="table_account_id" />
<fmt:message bundle="${loc }" key="local.table.created.at"
	var="table_created_at" />
<fmt:message bundle="${loc }" key="local.table.number.id"
	var="table_number_id" />
<fmt:message bundle="${loc }" key="local.table.connected"
	var="table_connected" />
<fmt:message bundle="${loc }" key="local.table.phone.number"
	var="table_phone_number" />
<fmt:message bundle="${loc }" key="local.table.call.plan.id"
	var="table_call_plan_id" />
<fmt:message bundle="${loc }" key="local.table.call.plan.created"
	var="table_call_plan_created" />
<fmt:message bundle="${loc }" key="local.table.call.plan.name"
	var="table_call_plan_name" />
<fmt:message bundle="${loc }" key="local.table.call.plan.rate"
	var="table_call_plan_rate" />
<fmt:message bundle="${loc }" key="local.table.minutes.left"
	var="table_minutes_left" />
<fmt:message bundle="${loc }" key="local.table.account.balance"
	var="table_account_balance" />
<fmt:message bundle="${loc }" key="local.table.account.status"
	var="table_account_status" />

<fmt:message bundle="${loc }" key="local.submit.button"
	var="submit_button" />
<fmt:message bundle="${loc }" key="local.select.your.phone.number"
	var="select_your_phone_number" />

<fmt:message bundle="${loc }" key="local.old.password"
	var="old_password" />
<fmt:message bundle="${loc }" key="local.new.password"
	var="new_password" />
<fmt:message bundle="${loc }" key="local.repeat.password"
	var="repeat_password" />

<fmt:message bundle="${loc }" key="local.select.this.number.button"
	var="select_this_number_button" />

<fmt:message bundle="${loc }" key="local.view.available.phone.numbers"
	var="view_available_phone_numbers" />
<fmt:message bundle="${loc }" key="local.charge.account.prompt"
	var="charge_account_prompt" />
	
<fmt:message bundle="${loc }" key="local.account.status.active" var="account_status_active" />
<fmt:message bundle="${loc }" key="local.account.status.blocked" var="account_status_blocked" />

<fmt:message bundle="${loc }" key="local.table.unlimited" var="table_unlimited" />
<fmt:message bundle="${loc }" key="local.your.account" var="your_account" />
<title>${your_account }</title>

</head>
<body>
	<div class="header" id="header">
		<form class="logo" action="controller" method="get">
			<input type="hidden" name="command" value="go-to-index-page" />
			<button class="unstyled-button" class="border">
				<div class="active">Roma Telecom</div>
			</button>
		</form>

		<div id="localization-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="localize" /> <input
					type="hidden" name="sitelocation" value="userpage" /> <input
					type="hidden" name="language" value="en_GB" /> <input
					class="styled-button-lang" type="submit" value="${en_button }" />
			</form>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="localize" /> <input
					type="hidden" name="sitelocation" value="userpage" /> <input
					type="hidden" name="language" value="ru_RU" /> <input
					class="styled-button-lang" type="submit" value="${ru_button }" />
			</form>
		</div>


		<div class="header-right">
			<div class="header-nav-buttons">
				<form action="controller" method="get">
					<input type="hidden" name="command" value="sign-out" />
					<button class="unstyled-button" class="border">
						<div class="active-sign-out">${signout_button }</div>
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="spacer"></div>
	<div class="account-main-div">
		<div class="admin-greeting">
			<p>
				${greeting }
				<c:out value="${sessionScope.user.name }" />
			</p>

		</div>
		<div class="user-menu">
			<div class="user-menu-nav-bar">
				<p>
					<a href="#editProfile" style="text-decoration: none;">${edit_profile_button }</a>
				</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>
					<a href="#change-password" style="text-decoration: none;">${change_password_button }</a>
				</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>
					<a href="#change-call-plan" style="text-decoration: none;">${change_call_plan_button }</a>
				</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>
					<a href="#change-phone-number" style="text-decoration: none;">${change_telephone_number_button }</a>
				</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>${your_fibre_button }</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>${payment_options_button }</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>${request_paper_bill_button }</p>
			</div>
			<div class="user-menu-nav-bar">
				<p>${order_history_button }</p>
			</div>
		</div>
		<div id="your-account">
			<br>
			<h3>${account_summary }</h3>
			<h3>
				<span class="red"> 
				<c:set var="messageProfile" value="${requestScope.ProfileUpdate}" scope="request" /> 
				<c:out value="${messageProfile}" /> 
				<c:set var="messagePass" value="${requestScope.PasswordUpdate}" scope="request" /> 
				<c:out value="${messagePass}" /> 
				<c:set var="messageCallPlan" value="${sessionScope.CallPlanMessage}" scope="session" /> 
				<c:out value="${messageCallPlan}" /> 
				<c:set var="NumbersMessage" value="${sessionScope.NumbersMessage}" scope="session" /> 
				<c:out value="${NumbersMessage}" />
				
				<c:set var="numberChanged" value="${sessionScope.ChangePhoneNumber}" scope="request" /> 
				<c:out value="${numberChanged}" />
				
				</span>
			</h3>
			<hr color="teal" style="width: 100%;">
			<div id="account">

				<table>
					<thead>
						<tr>
							<th><p>${table_account_id}</p></th>
							<th><p>${table_phone_number}</p></th>
							<th><p>${table_call_plan_name}</p></th>
							<th><p>${table_call_plan_created}</p></th>
							<th><p>${table_call_plan_rate}</p></th>
							<th><p>${table_minutes_left}</p></th>
							<th><p>${table_account_status}</p></th>
							<th><p>${table_account_balance}</p></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<p><c:out value="${sessionScope.account.accountID }" /></p>
							</td>
							<td>
								<p>+375 <c:out value="${sessionScope.account.accountPhoneNumber }" /></p>
							</td>
							<td><p>
									<c:set var = "string1" value = "${sessionScope.account.callPlanName }"/>
      								<c:set var = "string2" value = "${fn:substring(string1, 5, 15)}" />
									<c:out value="${sessionScope.account.callPlanName }" />
								</p></td>
							<td><p>
									<c:out value="${sessionScope.account.callPlanCreatedAt }" />
								</p></td>
							<td><p>
									$
									<c:out value="${sessionScope.account.callPlanRate }" />
								</p></td>
							<td><p>
									<c:choose>
  										<c:when test="${sessionScope.account.callPlanMinutesLeft > 350}">
    											<c:out value="${table_unlimited}" />
												  </c:when>
												  
												  <c:otherwise>
												    <c:out value="${sessionScope.account.callPlanMinutesLeft}" />
												  </c:otherwise>
												</c:choose>
								
										
								</p></td>
							<td><p>
									<c:if test="${sessionScope.account.blocked eq false}">
										<c:out value="${account_status_active}" />
									</c:if>
									<c:if test="${sessionScope.account.blocked eq true}">
										<c:out value="${account_status_blocked}" />
									</c:if>
								</p></td>
							<td><p>
									$
									<c:out value="${sessionScope.account.accountBalance }" />
								</p></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div id="editProfile">
			<div>
				<div class="edit-and-back">
					<br>
					<h3>${edit_profile_button }</h3>
					<h3 class="back-to-menu">
						<a href="#header" style="text-decoration: none;">${back_to_menu_button }</a>
					</h3>
				</div>
			</div>
			<hr color="teal" style="width: 100%;">
			<br> <br>
			<form class="editProfileForm" action="controller" method="post">
				<input type="hidden" name="command" value="update-profile" /> <input
					type="hidden" name="userID" value="${sessionScope.user.userID}" />
				<label for="FirstName">${table_user_name }<span class="red">*</span></label>
				<br> <input id="FirstName" type="text" name="name"
					placeholder="${sessionScope.user.name}"> <br> <label
					for="LastName">${table_user_surname }<span class="red">*</span></label><br>
				<input id="LastName" type="text" name="surname"
					placeholder="${sessionScope.user.surname}"> <br> <label
					for="email">${table_user_email }<span class="red">*</span></label><br>
				<input id="email" type="text" name="email"
					placeholder="${sessionScope.user.email}"> <br> <label
					for="address">${table_user_address }<span class="red">*</span></label><br>
				<input id="address" type="text" name="addressL1"
					placeholder="${sessionScope.user.addressL1}"> <br> <label
					for="city">${table_user_city }<span class="red">*</span></label><br>
				<input id="city" type="text" name="addressL2"
					placeholder="${sessionScope.user.addressL2}"> <br> <label
					for="zip">${table_user_zip }<span class="red">*</span></label><br>
				<input id="zip" type="text" name="addressL3"
					placeholder="${sessionScope.user.addressL3}"><br>
				<div>
					<br>
					<button class="styled-button">${submit_button }</button>
				</div>
			</form>
		</div>
		<br> <br>
		<div class="spacer"></div>
		<div class="account-main-div">
			<div class="change-pass">
				<div id="change-password">
					<div>
						<div class="change-password-and-back">
							<br>
							<h3>${change_password_button }</h3>
							<h3 class="back-to-menu">
								<a href="#header" style="text-decoration: none;">${back_to_menu_button }</a>
							</h3>
						</div>
					</div>
				</div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<hr color="teal" style="width: 100%;">
				<div class="spacer"></div>
				<div class="spacer"></div>
				<div class="spacer"></div>
				<form class="editProfileForm" action="controller" method="post">
					<input type="hidden" name="command" value="change-password" /> <input
						type="hidden" name="userID" value="${sessionScope.user.userID}" />
					<label for="pass">${old_password }<span class="red">*</span></label>
					<input id="pass" type="password" name="pass"
						placeholder="${old_password }"> <br> <br> <label
						for="password">${new_password }<span class="red">*</span></label>
					<input id="password" name="passNew" type="password"
						pattern="^\S{4,16}$"
						onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Password must have at least 4 characters ' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;"
						placeholder="${new_password }" required><br> <br>
					<label for="password_two">${repeat_password }<span
						class="red">*</span></label> <input id="password_two" name="passNew2"
						type="password" pattern="^\S{4,16}$"
						onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Passwords do not match, please try again' : '');"
						placeholder="${repeat_password }" required>
					<div class="spacer"></div>
					<div class="spacer"></div>
					<div>
						<button class="styled-button">${submit_button }</button>
					</div>
				</form>
			</div>
		</div>
		<div class="spacer"></div>
		<div class="change-phone">
			<div id="change-phone-number">
				<div>
					<div class="change-telephone-number-and-back">
						<br>
						<h3>${change_telephone_number_button }</h3>
						<h3 class="back-to-menu">
							<a href="#header" style="text-decoration: none;">${back_to_menu_button }</a>
						</h3>
					</div>
				</div>
			</div>
			<div class="spacer"></div>
			<div class="spacer"></div>

			<h3>${select_your_phone_number }</h3>

			<form action="controller" method="get">
				<input type="hidden" name="command"
					value="get-list-of-available-numbers" />
				<p>${charge_account_prompt }</p>
				<br>
				<button class="styled-button">${view_available_phone_numbers }</button>
			</form>

			<form action="controller" method="post">

				<input type="hidden" name="command" value="change-phone-number" />
				<input type="hidden" name="userID"
					value="${sessionScope.user.userID}" />
				<table>
					<c:choose>
						<c:when test="${sessionScope.numbers != null}">
							<c:forEach var="i" begin="0" end="2">
								<li><input style="" type="radio" name="phoneNumber"
									value="${sessionScope.numbers[i]}">+375 <c:out
										value="${sessionScope.numbers[i]}" /></li>
							</c:forEach>
							<br />
						</c:when>
						<c:otherwise>
							<br />
						</c:otherwise>
					</c:choose>

				</table>
				<br />
				<button class="styled-button">${select_this_number_button }</button>

			</form>
		</div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<div class="spacer"></div>
		<hr>
		<div class="change-password-and-back">
			<br>
			<h3 id="change-call-plan">${change_call_plan_button }</h3>
			<h3 class="back-to-menu">
				<a href="#header" style="text-decoration: none;">${back_to_menu_button }</a>
			</h3>
		</div>

		<div id="call-plans">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="set-call-plan" /> <input
					type="hidden" name="userID" value="${sessionScope.user.userID}" />
				<div style="display: table;">
					<table>
						<tr>
							<td><input type="radio" name="callPlan" value="1"><b>${comfort}
									S - </b>1GB ${traffic}. ${unlimcalls}. 60 ${mintoland}. 18,35$
								${monthly}</td>
						</tr>
						<tr>
							<td><input type="radio" name="callPlan" value="2"><b>${comfort}
									M - </b>7GB ${traffic}. ${unlimcalls}. 120 ${mintoland}. 30,70$
								${monthly}</td>
						</tr>
						<tr>
							<td><input type="radio" name="callPlan" value="3"><b>${comfort}
									L - </b>15GB ${traffic}. ${unlimcalls}. 350 ${mintoland}. 39,95$
								${monthly}</td>
						</tr>
						<tr>
							<td><input type="radio" name="callPlan" value="4"><b>${comfort}
									XL - </b>35GB ${traffic}. ${unlimcalls}. ${unlimtoland}. 58,45$
								${monthly}</td>
						</tr>
					</table>
					<br> <br>
				</div>
				<button class="styled-button">${submit_button }</button>
				<br> <br>
			</form>
			<br> <br>
		</div>


	</div>
</body>
</html>