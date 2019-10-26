<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="m" uri="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<!DOCTYPE html>
	<html lang="en">
<head>
	<title>Admin Page</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
	<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow&display=swap" rel="stylesheet"> 
	<fmt:setLocale value="${sessionScope.local }" />
	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc }" key="local.userGreeting" var="greeting" />
	<fmt:message bundle="${loc }" key="local.locbutton.name.ru" var="ru_button" />
	<fmt:message bundle="${loc }" key="local.locbutton.name.en" var="en_button" />
	<fmt:message bundle="${loc }" key="local.signout.button" var="signout_button" />
	<fmt:message bundle="${loc }" key="local.user.administration.button" var="user_admin_button" />
	<fmt:message bundle="${loc }" key="local.account.administration.button" var="account_admin_button" />
	<fmt:message bundle="${loc }" key="local.user.orders.button" var="user_orders_button" />
	<fmt:message bundle="${loc }" key="local.website.administration.button" var="website_administration_button" />
	<fmt:message bundle="${loc }" key="local.website.statistics.button" var="website_statistics_button" />
	<fmt:message bundle="${loc }" key="local.search.button" var="search_button" />
	<fmt:message bundle="${loc }" key="local.enter.user.id" var="enter_user_id" />
	<fmt:message bundle="${loc }" key="local.table.user.id" var="table_user_id" />
	<fmt:message bundle="${loc }" key="local.table.user.name" var="table_user_name" />
	<fmt:message bundle="${loc }" key="local.table.user.surname" var="table_user_surname" />
	<fmt:message bundle="${loc }" key="local.table.user.address" var="table_user_address" />
	<fmt:message bundle="${loc }" key="local.table.user.city" var="table_user_city" />
	<fmt:message bundle="${loc }" key="local.table.user.zip" var="table_user_zip" />
	<fmt:message bundle="${loc }" key="local.table.user.email" var="table_user_email" />
	<fmt:message bundle="${loc }" key="local.table.user.isadmin" var="table_user_isadmin" />
	<fmt:message bundle="${loc }" key="local.table.user.isblocked" var="table_user_isblocked" />
	<fmt:message bundle="${loc }" key="local.change.role.button" var="change_role_button" />
	<fmt:message bundle="${loc }" key="local.block.user.button" var="block_user_button" />
	<fmt:message bundle="${loc }" key="local.unblock.user.button" var="unblock_user_button" />
	<fmt:message bundle="${loc }" key="local.delete.user.button" var="delete_user_button" />
	<fmt:message bundle="${loc }" key="local.enter.account.id" var="enter_account_id" />
	<fmt:message bundle="${loc }" key="local.view.all.users" var="view_all_users" />
	<fmt:message bundle="${loc }" key="local.get.all.users.button" var="get_all_users_button" />
	<fmt:message bundle="${loc }" key="local.clear.results.button" var="clear_results_button" />
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
	<fmt:message bundle="${loc }" key="local.table.block.account.button" var="table_block_account_button" />
	<fmt:message bundle="${loc }" key="local.table.unblock.account.button" var="table_unblock_account_button" />
	<fmt:message bundle="${loc }" key="local.table.delete.account.button" var="table_delete_account_button" />
	<fmt:message bundle="${loc }" key="local.table.status.yes" var="table_status_yes" />
	<fmt:message bundle="${loc }" key="local.table.status.no" var="table_status_no" />
	<fmt:message bundle="${loc }" key="local.enter.user.email" var="enter_user_email" />
	<fmt:message bundle="${loc }" key="local.enter.phone.number" var="enter_phone_number" />
	<fmt:message bundle="${loc }" key="local.plans.copyright" var="copyright" />
	<fmt:message bundle="${loc }" key="local.view.all.users" var="view_all_users" />
	<fmt:message bundle="${loc }" key="local.view.all.accounts" var="view_all_accounts" />
	<fmt:message bundle="${loc }" key="local.view.all.orders" var="view_all_orders" />
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
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="adminpage"/>
 				<input type="hidden" name="language" value="en_GB"/>
 				<input class="styled-button-lang" type="submit" value="${en_button }"/>
  			</form>
  			<form action="controller" method="post">
 				<input type="hidden" name="command" value="localize"/>
 				<input type="hidden" name="sitelocation" value="adminpage"/>
 				<input type="hidden" name="language" value="ru_RU"/>
 				<input class="styled-button-lang" type="submit" value="${ru_button }"/>
  			</form>
  		</div>
		<div class="header-right">
			<div class="header-nav-buttons">
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
	<div class="admin-greeting">
		<p>
			<c:out value="${greeting }"/> 
			<c:out value="${sessionScope.admin.name }" />
		</p>
	</div>
	<c:set var="status_true" scope="session"  value="Yes"/>
	<c:set var="status_false" scope="session"  value="No"/>
	<div id="admin-menu">
		
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-user-management-page" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${user_admin_button }</div>
					</button>
			</form>
			
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-user-pagination" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${view_all_users }</div>
					</button>
			</form>
					
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-account-management-page" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${account_admin_button }</div>
					</button>
			</form>
			
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-account-pagination" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${view_all_accounts }</div>
					</button>
			</form>
			
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-order-management-page" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${user_orders_button }</div>
					</button>
			</form>
			
			<form action="controller" method="get">
					<input type="hidden" name="command" value="go-to-account-pagination" />
					<button class="unstyled-button-menu" class="border">
						<div class="active-sign-out">${view_all_orders }</div>
					</button>
			</form>
			
	</div>
	
	<div class="spacer"></div>
	
	<p>Website administration</p>
	<p>Website statistics</p>
	

	<div class="admin-spacer"></div>

	

</body>
</html>