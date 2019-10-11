package by.roma.telecom.bean;

import java.io.Serializable;

public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userID;
	private int accountID;
	private int phoneNumberID;
	private int callPlanID;

	private float accountBalance;
	private String accountCreatedAt;
	private String accountCeasedAt;
	private boolean blocked;

	private int accountPhoneNumber;
	private String phoneNumberConnectedAt;
	private String phoneNumberCeasedAt;

	private String callPlanName;
	private float callPlanRate;
	private int callPlanMinutesLeft;
	private String callPlanCreatedAt;
	private String callPlanCeasedAt;

	public Account() {
	}

	public Account(int accountID, float accountBalance, boolean blocked, int callPlanID, String callPlanCreatedAt,
			String callPlanCeasedAt) {
		this.accountID = accountID;
		this.accountBalance = accountBalance;
		this.blocked = blocked;
		this.callPlanID = callPlanID;
		this.callPlanCreatedAt = callPlanCreatedAt;
		this.callPlanCeasedAt = callPlanCeasedAt;
	}

	public Account(int userID, int accountID, int phoneNumberID, int callPlanID, float accountBalance,
			String accountCreatedAt, String accountCeasedAt, boolean blocked, String phoneNumberConnectedAt,
			String phoneNumberCeasedAt, String callPlanCreatedAt, String callPlanCeasedAt) {
		super();
		this.userID = userID;
		this.accountID = accountID;
		this.phoneNumberID = phoneNumberID;
		this.callPlanID = callPlanID;
		this.accountBalance = accountBalance;
		this.accountCreatedAt = accountCreatedAt;
		this.accountCeasedAt = accountCeasedAt;
		this.blocked = blocked;
		this.phoneNumberConnectedAt = phoneNumberConnectedAt;
		this.phoneNumberCeasedAt = phoneNumberCeasedAt;
		this.callPlanCreatedAt = callPlanCreatedAt;
		this.callPlanCeasedAt = callPlanCeasedAt;
	}

	public Account(int accountID, float accountBalance, boolean blocked, int callPlanID, String callPlanCreatedAt,
			String callPlanCeasedAt, int phoneNumberID, String phoneNumberConnectedAt, String phoneNumberCeasedAt,
			String accountCreatedAt, String accountCeasedAt) {

		this.accountID = accountID;
		this.accountBalance = accountBalance;
		this.blocked = blocked;
		this.callPlanID = callPlanID;
		this.accountCreatedAt = accountCreatedAt;
		this.accountCeasedAt = accountCeasedAt;
		this.phoneNumberID = phoneNumberID;
		this.phoneNumberConnectedAt = phoneNumberConnectedAt;
		this.phoneNumberCeasedAt = phoneNumberCeasedAt;
		this.accountCreatedAt = accountCreatedAt;
		this.accountCeasedAt = accountCeasedAt;
	}
	
	public Account (int userID, int accountID, float accountBalance, boolean blocked, int callPlanID, String callPlanCreatedAt,
			String callPlanCeasedAt, int phoneNumberID, String phoneNumberConnectedAt, String phoneNumberCeasedAt, String accountCreatedAt,
			String accountCeasedAt, int accountPhoneNumber, String callPlanName, float callPlanRate , int callPlanMinutesLeft) {
		
		this.userID = userID;
		this.accountID = accountID;
		this.accountBalance = accountBalance;
		this.blocked = blocked;
		this.callPlanID = callPlanID;
		this.callPlanCreatedAt = callPlanCreatedAt;
		this.callPlanCeasedAt = callPlanCeasedAt;
		this.phoneNumberID = phoneNumberID;
		this.phoneNumberConnectedAt = phoneNumberConnectedAt;
		this.phoneNumberCeasedAt = phoneNumberCeasedAt;
		this.accountCreatedAt = accountCreatedAt;
		this.accountCeasedAt = accountCeasedAt;
		this.accountPhoneNumber = accountPhoneNumber;
		this.callPlanName = callPlanName;
		this.callPlanRate = callPlanRate;
		this.callPlanMinutesLeft = callPlanMinutesLeft;
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getPhoneNumberID() {
		return phoneNumberID;
	}

	public void setPhoneNumberID(int phoneNumberID) {
		this.phoneNumberID = phoneNumberID;
	}

	public int getCallPlanID() {
		return callPlanID;
	}

	public void setCallPlanID(int callPlanID) {
		this.callPlanID = callPlanID;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountCreatedAt() {
		return accountCreatedAt;
	}

	public void setAccountCreatedAt(String accountCreatedAt) {
		this.accountCreatedAt = accountCreatedAt;
	}

	public String getAccountCeasedAt() {
		return accountCeasedAt;
	}

	public void setAccountCeasedAt(String accountCeasedAt) {
		this.accountCeasedAt = accountCeasedAt;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.blocked = isBlocked;
	}

	public String getPhoneNumberConnectedAt() {
		return phoneNumberConnectedAt;
	}

	public void setPhoneNumberConnectedAt(String phoneNumberConnectedAt) {
		this.phoneNumberConnectedAt = phoneNumberConnectedAt;
	}

	public String getPhoneNumberCeasedAt() {
		return phoneNumberCeasedAt;
	}

	public void setPhoneNumberCeasedAt(String phoneNumberCeasedAt) {
		this.phoneNumberCeasedAt = phoneNumberCeasedAt;
	}

	public String getCallPlanCreatedAt() {
		return callPlanCreatedAt;
	}

	public void setCallPlanCreatedAt(String callPlanCreatedAt) {
		this.callPlanCreatedAt = callPlanCreatedAt;
	}

	public String getCallPlanCeasedAt() {
		return callPlanCeasedAt;
	}

	public void setCallPlanCeasedAt(String callPlanCeasedAt) {
		this.callPlanCeasedAt = callPlanCeasedAt;
	}

	public int getAccountPhoneNumber() {
		return accountPhoneNumber;
	}

	public void setAccountPhoneNumber(int accountPhoneNumber) {
		this.accountPhoneNumber = accountPhoneNumber;
	}

	public String getCallPlanName() {
		return callPlanName;
	}

	public void setCallPlanName(String callPlanName) {
		this.callPlanName = callPlanName;
	}

	public float getCallPlanRate() {
		return callPlanRate;
	}

	public void setCallPlanRate(float callPlanRate) {
		this.callPlanRate = callPlanRate;
	}

	public int getCallPlanMinutesLeft() {
		return callPlanMinutesLeft;
	}

	public void setCallPlanMinutesLeft(int callPlanMinutesLeft) {
		this.callPlanMinutesLeft = callPlanMinutesLeft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + ((accountCeasedAt == null) ? 0 : accountCeasedAt.hashCode());
		result = prime * result + ((accountCreatedAt == null) ? 0 : accountCreatedAt.hashCode());
		result = prime * result + accountID;
		result = prime * result + accountPhoneNumber;
		result = prime * result + ((callPlanCeasedAt == null) ? 0 : callPlanCeasedAt.hashCode());
		result = prime * result + ((callPlanCreatedAt == null) ? 0 : callPlanCreatedAt.hashCode());
		result = prime * result + callPlanID;
		result = prime * result + callPlanMinutesLeft;
		result = prime * result + ((callPlanName == null) ? 0 : callPlanName.hashCode());
		result = prime * result + Float.floatToIntBits(callPlanRate);
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((phoneNumberCeasedAt == null) ? 0 : phoneNumberCeasedAt.hashCode());
		result = prime * result + ((phoneNumberConnectedAt == null) ? 0 : phoneNumberConnectedAt.hashCode());
		result = prime * result + phoneNumberID;
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(accountBalance) != Float.floatToIntBits(other.accountBalance))
			return false;
		if (accountCeasedAt == null) {
			if (other.accountCeasedAt != null)
				return false;
		} else if (!accountCeasedAt.equals(other.accountCeasedAt))
			return false;
		if (accountCreatedAt == null) {
			if (other.accountCreatedAt != null)
				return false;
		} else if (!accountCreatedAt.equals(other.accountCreatedAt))
			return false;
		if (accountID != other.accountID)
			return false;
		if (accountPhoneNumber != other.accountPhoneNumber)
			return false;
		if (callPlanCeasedAt == null) {
			if (other.callPlanCeasedAt != null)
				return false;
		} else if (!callPlanCeasedAt.equals(other.callPlanCeasedAt))
			return false;
		if (callPlanCreatedAt == null) {
			if (other.callPlanCreatedAt != null)
				return false;
		} else if (!callPlanCreatedAt.equals(other.callPlanCreatedAt))
			return false;
		if (callPlanID != other.callPlanID)
			return false;
		if (callPlanMinutesLeft != other.callPlanMinutesLeft)
			return false;
		if (callPlanName == null) {
			if (other.callPlanName != null)
				return false;
		} else if (!callPlanName.equals(other.callPlanName))
			return false;
		if (Float.floatToIntBits(callPlanRate) != Float.floatToIntBits(other.callPlanRate))
			return false;
		if (blocked != other.blocked)
			return false;
		if (phoneNumberCeasedAt == null) {
			if (other.phoneNumberCeasedAt != null)
				return false;
		} else if (!phoneNumberCeasedAt.equals(other.phoneNumberCeasedAt))
			return false;
		if (phoneNumberConnectedAt == null) {
			if (other.phoneNumberConnectedAt != null)
				return false;
		} else if (!phoneNumberConnectedAt.equals(other.phoneNumberConnectedAt))
			return false;
		if (phoneNumberID != other.phoneNumberID)
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [userID=" + getUserID() + ", accountID=" + getAccountID() + ", phoneNumberID=" + getPhoneNumberID()
				+ ", callPlanID=" + getCallPlanID() + ", accountBalance=" + getAccountBalance() + ", accountCreatedAt="
				+ getAccountCreatedAt() + ", accountCeasedAt=" + getAccountCeasedAt() + ", isBlocked=" + isBlocked()
				+ ", accountPhoneNumber=" + getAccountPhoneNumber() + ", phoneNumberConnectedAt=" + getPhoneNumberConnectedAt()
				+ ", phoneNumberCeasedAt=" + getPhoneNumberCeasedAt() + ", callPlanName=" + getCallPlanName() + ", callPlanRate="
				+ callPlanRate + ", callPlanMinutesLeft=" + callPlanMinutesLeft + ", callPlanCreatedAt="
				+ getCallPlanCreatedAt() + ", callPlanCeasedAt=" + getCallPlanCeasedAt() + "]";
	}

	

}
