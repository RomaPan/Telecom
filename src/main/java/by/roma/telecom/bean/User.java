package by.roma.telecom.bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 22L;
	private int userID;
	private String name;
	private String surname;
	private String addressL1;
	private String addressL2;
	private String addressL3;
	private String email;
	private boolean admin;
	private boolean blocked;

	public User() {

	}

	public User(int id, String name, String surname, String addressl1, String addressl2, String addressl3, String email,
			boolean isAdmin, boolean isBlocked) {
		this.userID = id;
		this.name = name;
		this.surname = surname;
		this.addressL1 = addressl1;
		this.addressL2 = addressl2;
		this.addressL3 = addressl3;
		this.email = email;
		this.admin = isAdmin;
		this.blocked = isBlocked;

	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.blocked = isBlocked;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		User other = (User) obj;
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
		return "User [userID=" + getUserID() + ", name=" + getName() + ", surname=" + getSurname() + ", addressL1="
				+ getAddressL1() + ", addressL2=" + getAddressL2() + ", addressl3=" + getAddressL3() + ", email="
				+ getEmail() + ", admin=" + isAdmin() + ", blocked=" + isBlocked() + "]";
	}

}
