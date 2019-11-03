package by.roma.telecom.session.message.cleaner;

import javax.servlet.http.HttpSession;

public class SessionMessageCleaner {

	private SessionMessageCleaner() {
	}

	public static void cleanMessageAttributes(HttpSession session) {

		if (session.getAttribute("UnblockAccountMessage") != null) {
			session.removeAttribute("UnblockAccountMessage");
		} else if (session.getAttribute("BlockAccountMessage") != null) {
			session.removeAttribute("BlockAccountMessage");
		} else if (session.getAttribute("FindUserByIDMessage") != null) {
			session.removeAttribute("FindUserByIDMessage");
		} else if (session.getAttribute("ChangeUserRoleMessage") != null) {
			session.removeAttribute("ChangeUserRoleMessage");
		} else if (session.getAttribute("BlockUserMessage") != null) {
			session.removeAttribute("BlockUserMessage");
		} else if (session.getAttribute("PasswordUpdate") != null) {
			session.removeAttribute("PasswordUpdate");
		} else if (session.getAttribute("UsersListMessage") != null) {
			session.removeAttribute("UsersListMessage");
		} else if (session.getAttribute("FindAccountByIDMessage") != null) {
			session.removeAttribute("FindAccountByIDMessage");
		} else if (session.getAttribute("UsersLisCleartMessage") != null) {
			session.removeAttribute("UsersLisCleartMessage");
		} else if (session.getAttribute("CallPlanMessage") != null) {
			session.removeAttribute("CallPlanMessage");
		} else if (session.getAttribute("FindUserByEmailMessage") != null) {
			session.removeAttribute("FindUserByEmailMessage");
		} else if (session.getAttribute("NumbersMessage") != null) {
			session.removeAttribute("NumbersMessage");
		} else if (session.getAttribute("ChangePhoneNumberSuccess") != null) {
			session.removeAttribute("ChangePhoneNumberSuccess");
		} else if (session.getAttribute("FindAccountByPhoneNumberMessage") != null) {
			session.removeAttribute("FindAccountByPhoneNumberMessage");
		} else if (session.getAttribute("account") != null) {
			session.removeAttribute("account");
		} else if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
		} else if (session.getAttribute("AccountsListClearMessage") != null) {
			session.removeAttribute("AccountsListClearMessage");
		} else if (session.getAttribute("AccountsListMessage") != null) {
			session.removeAttribute("AccountsListMessage");
		}else if (session.getAttribute("ChangePhoneNumberFailed") != null) {
			session.removeAttribute("ChangePhoneNumberFailed");
		}else if (session.getAttribute("ConnPhoneNumberMessage") != null) {
			session.removeAttribute("ConnPhoneNumberMessage");
		}
	}
}
