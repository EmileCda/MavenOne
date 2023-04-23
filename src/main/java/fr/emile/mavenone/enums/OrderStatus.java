package fr.emile.mavenone.enums;

public enum OrderStatus {
	VALIDATED("Valide"), IN_PROGRESS("en cours"), CANCELLED("annulée"), NULL("");

	private String orderStatus;

	private OrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getValue() {
		return this.orderStatus;
	}

	public static OrderStatus fromString(String text) {
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (orderStatus.orderStatus.equalsIgnoreCase(text)) {
				return orderStatus;
			}
		}
		return null;
	}

}
