<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="m" uri="mytags"%>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta charset="utf-8">
<link rel="stylesheet" href="css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Raleway&display=swap"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Dosis&display=swap"
	rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=PT+Sans+Narrow&display=swap" rel="stylesheet"> 

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
	<fmt:message bundle="${loc }" key="local.table.minutes.left" var="table_minutes_left" />
	<fmt:message bundle="${loc }" key="local.table.account.balance" var="table_account_balance" />
	<fmt:message bundle="${loc }" key="local.table.block.account.button" var="table_block_account_button" />
	<fmt:message bundle="${loc }" key="local.table.unblock.account.button" var="table_unblock_account_button" />
	<fmt:message bundle="${loc }" key="local.table.delete.account.button" var="table_delete_account_button" />

	<fmt:message bundle="${loc }" key="local.plans.copyright" var="copyright" />
	<fmt:message bundle="${loc }" key="local.back.button" var="back_button" />
	<fmt:message bundle="${loc }" key="local.left.button" var="left_button" />
	<fmt:message bundle="${loc }" key="local.right.button" var="right_button" />


	
	<fmt:message bundle="${loc }" key="local.table.status.yes" var="table_status_yes" />
	<fmt:message bundle="${loc }" key="local.table.status.no" var="table_status_no" />
	
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
	
	<title>${user_admin_button }</title>
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
					type="hidden" name="sitelocation" value="user-management" /> <input
					type="hidden" name="language" value="en_GB" /> <input
					class="styled-button-lang" type="submit" value="${en_button }" />
			</form>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="localize" /> <input
					type="hidden" name="sitelocation" value="user-management" /> <input
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

	<div class="spacer"> </div>
		<div class="system-message">
			<p>
				<span class="red"> 
					<c:out value="${FindUserByEmailMessage}" />
					<c:out value="${ChangeUserRoleMessage }" />
					<c:out value="${BlockUserMessage }" />	
					<c:out value="${FindUserByIDMessage }"/>		   
				</span>
			</p>
		</div>
	<div class="spacer"> </div>
	
	<div class="account-section">
	<section>
		<div class="div-account-search" id="accountManagement">
			<p>${enter_user_email}</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="find-user-by-email" /> 
				<input type="email" name="email" value="" required />
				<button>${search_button }</button>
			</form>
			<p>${enter_user_id }</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="find-user-by-id" /> 
				<input type="number" name="userID" value="" required />
				<button>${search_button }</button>
			</form>	
		<div class="div-box">
			
			<c:choose>
				<c:when test="${sessionScope.user.blocked == true}">
       	 			<form action="controller" method="post">
						<input type="hidden" name="command" value="unblock-user" />
						<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
						<button>${unblock_user_button }</button>
					</form>
    			</c:when>    
    				<c:otherwise>
       					<form action="controller" method="post">
							<input type="hidden" name="command" value="block-user" />
							<input type="hidden" name="userID" value="${sessionScope.user.userID}" />
							<button>${block_user_button }</button>
						</form>
        			</c:otherwise>
			</c:choose>
			<br/>
			
			<form>
				<input type="hidden">
				<button>${delete_user_button }</button>
			</form>

		</div>	
		</div>
		
		
	
	
		<table id="admin-table-account">
			<thead>
				<tr>
					<th>${table_user_id }</th>
					<th>${table_user_name }</th>
					<th>${table_user_surname }</th>
					<th>${table_user_address }</th>
					<th>${table_user_city }</th>
					<th>${table_user_zip }</th>
					<th>${table_user_email }</th>
					<th>${table_user_isadmin }</th>
					<th>${table_user_isblocked }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${sessionScope.user.userID}" /></td>
					<td><c:out value="${sessionScope.user.name}" /></td>
					<td><c:out value="${sessionScope.user.surname}" /></td>
					<td><c:out value="${sessionScope.user.addressL1}" /></td>
					<td><c:out value="${sessionScope.user.addressL2}" /></td>
					<td><c:out value="${sessionScope.user.addressL3}" /></td>
					<td><c:out value="${sessionScope.user.email}" /></td>
					<td> 
						<c:if test="${not empty sessionScope.user.admin}">
						<c:choose>
   				 			<c:when test="${sessionScope.user.admin == true}">
       	 					<c:out value="${table_status_yes }" />
    					</c:when>    
    						<c:otherwise>
       							<c:out value="${table_status_no }" /> 
        					</c:otherwise>
						</c:choose>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty sessionScope.user.blocked}">
						<c:choose>
   				 			<c:when test="${sessionScope.user.blocked == true}">
       	 					<c:out value="${table_status_yes }" />
    					</c:when>    
    						<c:otherwise>
       							<c:out value="${table_status_no }" /> 
        					</c:otherwise>
						</c:choose>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		
	</section>
	</div>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<div class="div-box" id="userManagement">
			<p>${enter_user_id }</p>
			<p>
				<span class="red"> 
					<c:out value="${FindUserByIDMessage}"/>
					<c:out value="${ChangeUserRoleMessage }"/>
				</span>
			</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="find-user-by-id" /> 
				<input type="number" name="userID" value="" required />
				<button>${search_button }</button>
			</form>

		</div>

		<table id="admin-table">
			<thead>
				<tr>
					<th>${table_user_id }</th>
					<th>${table_user_name }</th>
					<th>${table_user_surname }</th>
					<th>${table_user_address }</th>
					<th>${table_user_city }</th>
					<th>${table_user_zip }</th>
					<th>${table_user_email }</th>
					<th>${table_user_isadmin }</th>
					<th>${table_user_isblocked }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${sessionScope.user.userID}" /></td>
					<td><c:out value="${sessionScope.user.name}" /></td>
					<td><c:out value="${sessionScope.user.surname}" /></td>
					<td><c:out value="${sessionScope.user.addressL1}" /></td>
					<td><c:out value="${sessionScope.user.addressL2}" /></td>
					<td><c:out value="${sessionScope.user.addressL3}" /></td>
					<td><c:out value="${sessionScope.user.email}" /></td>
					<td>
						<c:if test="${not empty sessionScope.user.admin}">
						<c:choose>
   				 			<c:when test="${sessionScope.user.admin == true}">
       	 					<c:out value="${table_status_yes }" />
    					</c:when>    
    						<c:otherwise>
       							<c:out value="${table_status_no }" /> 
        					</c:otherwise>
						</c:choose>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty sessionScope.user.blocked}">
						<c:choose>
   				 			<c:when test="${sessionScope.user.blocked == true}">
       	 					<c:out value="${table_status_yes }" />
    					</c:when>    
    						<c:otherwise>
       							<c:out value="${table_status_no }" /> 
        					</c:otherwise>
						</c:choose>
						</c:if>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="div-box">

			<form action="controller" method="post">
				<input type="hidden" name="command" value="change-user-role" /> <input
					type="hidden" name="userID" value="${sessionScope.user.userID}" />
				<button>${change_role_button }</button>
			</form>
			

		</div>
	</section>
	

</body>
</html>