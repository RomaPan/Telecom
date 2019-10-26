<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="mytags"%>
<!DOCTYPE html>
<html>
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

<fmt:message bundle="${loc }" key="local.user.administration.button"
	var="user_admin_button" />
<fmt:message bundle="${loc }" key="local.account.administration.button"
	var="account_admin_button" />
<fmt:message bundle="${loc }" key="local.user.orders.button"
	var="user_orders_button" />
<fmt:message bundle="${loc }" key="local.website.administration.button"
	var="website_administration_button" />
<fmt:message bundle="${loc }" key="local.website.statistics.button"
	var="website_statistics_button" />
<fmt:message bundle="${loc }" key="local.search.button"
	var="search_button" />

<fmt:message bundle="${loc }" key="local.enter.user.id"
	var="enter_user_id" />

<fmt:message bundle="${loc }" key="local.table.user.id"
	var="table_user_id" />
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
<fmt:message bundle="${loc }" key="local.table.user.isadmin"
	var="table_user_isadmin" />
<fmt:message bundle="${loc }" key="local.table.user.isblocked"
	var="table_user_isblocked" />
<fmt:message bundle="${loc }" key="local.change.role.button"
	var="change_role_button" />
<fmt:message bundle="${loc }" key="local.block.user.button"
	var="block_user_button" />
<fmt:message bundle="${loc }" key="local.unblock.user.button"
	var="unblock_user_button" />
<fmt:message bundle="${loc }" key="local.delete.user.button"
	var="delete_user_button" />
<fmt:message bundle="${loc }" key="local.enter.account.id"
	var="enter_account_id" />
<fmt:message bundle="${loc }" key="local.view.all.users"
	var="view_all_users" />
<fmt:message bundle="${loc }" key="local.get.all.users.button"
	var="get_all_users_button" />
<fmt:message bundle="${loc }" key="local.clear.results.button"
	var="clear_results_button" />

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
<fmt:message bundle="${loc }" key="local.table.block.account.button"
	var="table_block_account_button" />
<fmt:message bundle="${loc }" key="local.table.unblock.account.button"
	var="table_unblock_account_button" />
<fmt:message bundle="${loc }" key="local.table.delete.account.button"
	var="table_delete_account_button" />

<fmt:message bundle="${loc }" key="local.plans.copyright"
	var="copyright" />
<fmt:message bundle="${loc }" key="local.back.button" var="back_button" />
<fmt:message bundle="${loc }" key="local.left.button" var="left_button" />
<fmt:message bundle="${loc }" key="local.right.button"
	var="right_button" />

<fmt:message bundle="${loc }" key="local.pagination.prompt"
	var="pagination_prompt" />
	
	<fmt:message bundle="${loc }" key="local.table.status.yes" var="table_status_yes" />
	<fmt:message bundle="${loc }" key="local.table.status.no" var="table_status_no" />
	
	<fmt:message bundle="${loc }" key="local.get.all.accounts.button" var="get_all_accounts_button" />
	<title>Pagination Accounts</title>
</head>
<body>

	<c:set var="status_true" scope="session" value="Yes" />
	<c:set var="status_false" scope="session" value="No" />



	<c:set var="accounts" scope="session" value="${accountsList}" />
	<c:set var="totalCount" scope="session" value="${ accountsList.size()}" />
	<c:set var="perPage" scope="session" value="${10}" />
	<c:set var="pageStart" value="${param.start}" />
	<c:if test="${empty pageStart or pageStart < 0}">
		<c:set var="pageStart" value="0" />
	</c:if>
	<c:if test="${totalCount < pageStart}">
		<c:set var="pageStart" value="${pageStart - perPage}" />
	</c:if>

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
					type="hidden" name="sitelocation" value="account-pagination" /> <input
					type="hidden" name="language" value="en_GB" /> <input
					class="styled-button-lang" type="submit" value="${en_button }" />
			</form>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="localize" /> <input
					type="hidden" name="sitelocation" value="account-pagination" /> <input
					type="hidden" name="language" value="ru_RU" /> <input
					class="styled-button-lang" type="submit" value="${ru_button }" />
			</form>
		</div>

		<div class="header-right">
			<div class="header-nav-buttons">
				<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-admin-auth-page" />
					<button class="unstyled-button" class="border">
						<div class="active-sign-out">${back_button}</div>
					</button>
				</form>


				<form action="controller" method="get">
					<input type="hidden" name="command" value="sign-out" />
					<button class="unstyled-button" class="border">
						<div class="active-sign-out">${signout_button}</div>
					</button>
				</form>
			</div>
		</div>
	</div>

	<div class="spacer"></div>
	<div class="system-message">
			<p>
				<span class="red"> 
					<c:out value="${AccountsListMessage}" />
					<c:out value="${AccountsListClearMessage}" />	   
				</span>
			</p>
		</div>

	<div id="pagin-nav">
		<p>${pagination_prompt}</p>
		<br>
		<p>
			<a href="?command=go-to-account-pagination&start=${pageStart - perPage}">${left_button}</a>
		</p>
		<p>${pageStart + 1}</p>
		<p>-</p>
		<p>${pageStart + perPage}</p>
		<p>
			<a href="?command=go-to-account-pagination&start=${pageStart + perPage}">${right_button}</a>
		</p>
		
		<form action="controller" method="get">
			<input type="hidden" name="command" value="get-list-of-all-accounts" />
			<button>${get_all_accounts_button}</button>
		</form>
		
		<form action="controller" method="get">
			<input type="hidden" name="command" value="clear-list-of-all-accounts" />
			<button>${clear_results_button}</button>
		</form>
	</div>
	<div class="spacer"></div>

	<table id="admin-table">
		<thead>
			<tr>
					<th>${table_account_id }</th>
					<th>${table_created_at }</th>
					<th>${table_number_id }</th>
					<th>${table_connected }</th>
					<th>${table_phone_number }</th>
					<th>${table_call_plan_id }</th>
					<th>${table_call_plan_created}</th>
					<th>${table_call_plan_name}</th>
					<th>${table_call_plan_rate }</th>
					<th>${table_minutes_left }</th>
					<th>${table_account_balance }</th>
					<th>${table_user_isblocked }</th>
			</tr>
		</thead>
		<c:forEach var="a" items="${accountsList}" begin="${pageStart}"
			end="${pageStart + perPage - 1}">
			<tr>
				<td><c:out value="${a.accountID}" /></td>
					<td><c:out value="${a.accountCreatedAt}" /></td>
					<td><c:out value="${a.phoneNumberID}" /></td>
					<td><c:out value="${a.phoneNumberConnectedAt}" /></td>
					<td><c:out value="${a.accountPhoneNumber}" /></td>
					<td><c:out value="${a.callPlanID}" /></td>
					<td><c:out value="${a.callPlanCreatedAt}" /></td>
					<td><c:out value="${a.callPlanName}" /></td>
					<td><c:out value="${a.callPlanRate}" /></td>
					<td><c:out value="${a.callPlanMinutesLeft}" /></td>
					<td><c:out value="${a.accountBalance}" /></td>
					<td>
						<c:if test="${not empty a.blocked}">
								<c:choose>
   				 					<c:when test="${a.blocked == true}">
       	 							<c:out value="${table_status_yes }" />
    							</c:when>    
    								<c:otherwise>
       										<c:out value="${table_status_no }" /> 
        								</c:otherwise>
									</c:choose>
								</c:if>
					</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>