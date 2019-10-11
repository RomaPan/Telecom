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
	
	
	<fmt:message bundle="${loc }" key="local.plans.copyright" var="copyright" />
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
		<p>
			<a href="#userManagement">${user_admin_button }</a>
		</p>
		<p>
			<a href="#accountManagement">${account_admin_button }</a>
		</p>
		<p><a href="#orders-section">${user_orders_button }</a></p>
		<p>${website_administration_button }</p>
		<p>${website_statistics_button }</p>
	</div>
	<section id="admin-section">
		<div class="div-box" id="userManagement">
			<p>${enter_user_email}</p>
			<p>
				<span class="red"> 
					<c:out value="${FindUserByEmailMessage}" />
					<c:out value="${ChangeUserRoleMessage }" />
					<c:out value="${BlockUserMessage }" />
				</span>
			</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="find-user-by-email" /> 
				<input type="text" name="email" value="" required />
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
			<br/>
			<form>
				<input type="hidden">
				<button>${delete_user_button }</button>
			</form>

		</div>
	</section>
	
	
	
	
	
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
					<td><c:out value="${sessionScope.userAdm.userID}" /></td>
					<td><c:out value="${sessionScope.userAdm.name}" /></td>
					<td><c:out value="${sessionScope.userAdm.surname}" /></td>
					<td><c:out value="${sessionScope.userAdm.addressL1}" /></td>
					<td><c:out value="${sessionScope.userAdm.addressL2}" /></td>
					<td><c:out value="${sessionScope.userAdm.addressL3}" /></td>
					<td><c:out value="${sessionScope.userAdm.email}" /></td>
					<td>
						<c:if test="${not empty sessionScope.userAdm.admin}">
						<c:choose>
   				 			<c:when test="${sessionScope.userAdm.admin == true}">
       	 					<c:out value="${table_status_yes }" />
    					</c:when>    
    						<c:otherwise>
       							<c:out value="${table_status_no }" /> 
        					</c:otherwise>
						</c:choose>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty sessionScope.userAdm.blocked}">
						<c:choose>
   				 			<c:when test="${sessionScope.userAdm.blocked == true}">
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
					type="hidden" name="userID" value="${sessionScope.userAdm.userID}" />
				<button>${change_role_button }</button>
			</form>
			

		</div>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<div class="div-box">
			<p>${view_all_users }</p>
			<p>
				<span class="red"> <c:out value="${UsersListMessage}" /> <c:out
						value="${UsersLisCleartMessage}" />
				</span>
			</p>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="get-list-of-all-users" />
				<button>${get_all_users_button}</button>
			</form>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="clear-list-of-all-users" />
				<button>${clear_results_button}</button>
			</form>
			<form action="controller" method="get">
				<input type="hidden" name="command" value="go-to-pagination" />
				<button>Brows on a separate page</button>
			</form>
			<br>
			<div>
					
				<c:set var="users" scope="session" value="${usersList}"/>
				<c:set var="totalCount" scope="session" value="${ usersList.size()}"/>
				<c:set var="perPage" scope="session"  value="${10}"/>
				<c:set var="pageStart" value="${param.start}"/>
				<c:if test="${empty pageStart or pageStart < 0}">
      			 	<c:set var="pageStart" value="0"/>
				</c:if>
				<c:if test="${totalCount < pageStart}">
      				 <c:set var="pageStart" value="${pageStart - perPage}"/>
				</c:if>
   				 <a href="?command=go-to-admin-auth-page&start=${pageStart - perPage}">Left</a>${pageStart + 1} - ${pageStart + perPage} 
   				 <a href="?command=go-to-admin-auth-page&start=${pageStart + perPage}">Right</a>                                            
			</div>	
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
   <c:forEach var="u" items="${usersList}" begin="${pageStart}" end="${pageStart + perPage - 1}">
              <tr>
	              		<td>${u.userID}</td>
							<td>${u.name}</td>
							<td>${u.surname}</td>
							<td>${u.addressL1}</td>
							<td>${u.addressL2}</td>
							<td>${u.addressL3}</td>
							<td>${u.email}</td>
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
   </c:forEach>
   </table>
		<div class="div-box"></div>
	</section>

	<div class="admin-spacer"></div>

	<section id="admin-section">
		<div class="div-box" id="accountManagement">
			<p>${enter_account_id}</p>
			<p>
				<span class="red"> <c:out value="${FindAccountByIDMessage}" />
					<c:out value="${AccountBlockMessage }" />
				</span>
			</p>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="find-account-by-id" /> 
				<input type="number" name="accountID" value="" required />
				<button>${search_button }</button>
			</form>

		</div>

		<table id="admin-table-account">
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
					<th>${table_call_rate }</th>
					<th>${table_minutes_left }</th>
					<th>${table_account_balance }</th>
					<th>${table_user_isblocked }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><c:out value="${sessionScope.account.accountID}" /></td>
					<td><c:out value="${sessionScope.account.accountCreatedAt}" /></td>
					<td><c:out value="${sessionScope.account.phoneNumberID}" /></td>
					<td><c:out
							value="${sessionScope.account.phoneNumberConnectedAt}" /></td>
					<td><c:out value="${sessionScope.account.accountPhoneNumber}" /></td>
					<td><c:out value="${sessionScope.account.callPlanID}" /></td>
					<td><c:out value="${sessionScope.account.callPlanCreatedAt}" /></td>
					<td><c:out value="${sessionScope.account.callPlanName}" /></td>
					<td><c:out value="${sessionScope.account.callPlanRate}" /></td>
					<td><c:out value="${sessionScope.account.callPlanMinutesLeft}" /></td>
					<td><c:out value="${sessionScope.account.accountBalance}" /></td>
					<td>
						<c:if test="${not empty sessionScope.account.blocked}">
								<c:choose>
   				 					<c:when test="${sessionScope.account.blocked == true}">
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


			<c:choose>
				<c:when test="${sessionScope.account.blocked == true}">
       	 			<form action="controller" method="post">
						<input type="hidden" name="command" value="unblock-account" />
						<input type="hidden" name="accountID" value="${sessionScope.account.accountID}" />
						<button>${table_unblock_account_button }</button>
					</form>
    			</c:when>    
    				<c:otherwise>
       					<form action="controller" method="post">
							<input type="hidden" name="command" value="block-account" />
							<input type="hidden" name="accountID" value="${sessionScope.account.accountID}" />
							<button>${table_block_account_button }</button>
						</form>
        			</c:otherwise>
			</c:choose>
			<form>
				<input type="hidden">
				<button>${table_delete_account_button }</button>
			</form>

		</div>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<p>Show all Accounts</p>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<p>Show all pending orders</p>
	</section>
	<div class="admin-spacer"></div>
	<section id="orders-section">
		<p>Show all orders</p>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<p>Activate accounts</p>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<p>Deactivate accounts</p>
	</section>
	<div class="admin-spacer"></div>
	<section id="admin-section">
		<p>Deactivate accounts</p>
	</section>

</body>
</html>