package by.roma.telecom.service.match;

public class Match {

	public static boolean compareElements(String stringA, String stringB) {
		if (stringA != null && stringB != null) {
			if (stringA.equals(stringB)) {
				return true;
			}
		}
		return false;
	}

}
