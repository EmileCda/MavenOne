package fr.emile.mavenone.enums;

public enum DeliveryMethod {
	CHRONOPOST("Chronopost"), POSTE("La Poste"), PARCEL_RELAY("Relais colis"), UPS("UPS"),NULL("") ;

	private String deliveryMethod;

	private DeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getValue() {
		return this.deliveryMethod;
	}

	public static DeliveryMethod fromString(String text) {
		for (DeliveryMethod deliveryMethod : DeliveryMethod.values()) {
			if (deliveryMethod.deliveryMethod.equalsIgnoreCase(text)) {
				return deliveryMethod;
			}
		}
		return null;
	}
}
