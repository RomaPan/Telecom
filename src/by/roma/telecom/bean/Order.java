package by.roma.telecom.bean;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String orderID;
	private String userID;
	private String type;
	private String placedAt;
	private String completedAt;

	public Order() {

	}

	public Order(String id, String orderID, String userID, String type, String placedAt) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.userID = userID;
		this.type = type;
		this.placedAt = placedAt;
	}

	public Order(String id, String orderID, String userID, String type, String placedAt, String completedAt) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.userID = userID;
		this.type = type;
		this.placedAt = placedAt;
		this.completedAt = completedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completedAt == null) ? 0 : completedAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((placedAt == null) ? 0 : placedAt.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		Order other = (Order) obj;
		if (completedAt == null) {
			if (other.completedAt != null)
				return false;
		} else if (!completedAt.equals(other.completedAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (placedAt == null) {
			if (other.placedAt != null)
				return false;
		} else if (!placedAt.equals(other.placedAt))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(String placedAt) {
		this.placedAt = placedAt;
	}

	public String getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}

	@Override
	public String toString() {
		return "Order [id=" + getId() + ", orderID=" + getOrderID() + ", userID=" + getUserID() + ", type=" + getType()
				+ ", placedAt=" + getPlacedAt() + ", completedAt=" + getCompletedAt() + "]";
	}

}
