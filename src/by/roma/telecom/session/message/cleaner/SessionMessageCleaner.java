package by.roma.telecom.session.message.cleaner;

import javax.servlet.http.HttpSession;

public class SessionMessageCleaner {

	private SessionMessageCleaner() {
	}

	public static void cleanMessageAttributes(HttpSession session) {

		if (session.getAttribute("AccountBlockMessage") != null) {
			session.removeAttribute("AccountBlockMessage");
		}

		if (session.getAttribute("FindUserByIDMessage") != null) {
			session.removeAttribute("FindUserByIDMessage");
		}

		if (session.getAttribute("ChangeUserRoleMessage") != null) {
			session.removeAttribute("ChangeUserRoleMessage");
		}

		if (session.getAttribute("BlockUserMessage") != null) {
			session.removeAttribute("BlockUserMessage");
		}

		if (session.getAttribute("PasswordUpdate") != null) {
			session.removeAttribute("PasswordUpdate");
		}

		if (session.getAttribute("UsersListMessage") != null) {
			session.removeAttribute("UsersListMessage");
		}
		
		if (session.getAttribute("FindAccountByIDMessage") != null ) {
			session.removeAttribute("FindAccountByIDMessage");
		}
		
		if (session.getAttribute("UsersLisCleartMessage") != null) {
			session.removeAttribute("UsersLisCleartMessage");
		}

		if (session.getAttribute("CallPlanMessage") != null) {
			session.removeAttribute("CallPlanMessage");
		}
		
		if (session.getAttribute("FindUserByEmailMessage") != null) {
			session.removeAttribute("FindUserByEmailMessage");
		}
		
		if (session.getAttribute("NumbersMessage") != null) {
			session.removeAttribute("NumbersMessage");
		}
		
		if (session.getAttribute("ChangePhoneNumber") != null) {
			session.removeAttribute("ChangePhoneNumber");
		}
	}
}
