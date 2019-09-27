package by.roma.telecom.bean;

import java.io.Serializable;

public class RegistrationInfo implements Serializable {

	private static final long serialVersionUID = 11L;
	private int userID;
	private String name;
	private String surname;
	private String addressL1;
	private String addressL2;
	private String addressL3;
	private String email;
	private String password;
	private boolean admin;
	private boolean blocked;

	public RegistrationInfo() {

	}

	public RegistrationInfo(String name, String surname, String addressL1, String addressL2, String addressL3,
			String email, String password) {
		this.name = name;
		this.surname = surname;
		this.addressL1 = addressL1;
		this.addressL2 = addressL2;
		this.addressL3 = addressL3;
		this.email = email;
		this.password = password;
		this.admin = false;
		this.blocked = false;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int customerID) {
		this.userID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddressL1() {
		return addressL1;
	}

	public void setAddressL1(String addressL1) {
		this.addressL1 = addressL1;
	}

	public String getAddressL2() {
		return addressL2;
	}

	public void setAddressL2(String addressL2) {
		this.addressL2 = addressL2;
	}

	public String getAddressL3() {
		return addressL3;
	}

	public void setAddressL3(String addressL3) {
		this.addressL3 = addressL3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressL1 == null) ? 0 : addressL1.hashCode());
		result = prime * result + ((addressL2 == null) ? 0 : addressL2.hashCode());
		result = prime * result + ((addressL3 == null) ? 0 : addressL3.hashCode());
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		RegistrationInfo other = (RegistrationInfo) obj;
		if (addressL1 == null) {
			if (other.addressL1 != null)
				return false;
		} else if (!addressL1.equals(other.addressL1))
			return false;
		if (addressL2 == null) {
			if (other.addressL2 != null)
				return false;
		} else if (!addressL2.equals(other.addressL2))
			return false;
		if (addressL3 == null) {
			if (other.addressL3 != null)
				return false;
		} else if (!addressL3.equals(other.addressL3))
			return false;
		if (admin != other.admin)
			return false;
		if (blocked != other.blocked)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationInfo [customerID=" + getUserID() + ", name=" + getName() + ", surname=" + getSurname()
				+ ", addressL1=" + getAddressL1() + ", addressL2=" + getAddressL2() + ", addressL3=" + getAddressL3()
				+ ", email=" + getEmail() + ", password=" + getPassword() + ", isAdmin=" + isAdmin() + ", isBlocked=" + isBlocked() + "]";
	}

}
