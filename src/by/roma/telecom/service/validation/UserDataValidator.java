package by.roma.telecom.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.roma.telecom.property.ReadPropertyFile;

public class UserDataValidator {

	private static final UserDataValidator instance = new UserDataValidator();
	ReadPropertyFile propertyProvider = ReadPropertyFile.getInstance();

	private UserDataValidator() {
	}

	public boolean check(String login, String password) {

		String regexEmail;
		String regexPass;
		Pattern patternEmail;
		Pattern patternPass;
		Matcher matcherEmail;
		Matcher matcherPass;

		regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//		regexPass = "^[a-zA-Z0-9]{8,16}+$";
		regexPass = "^[a-zA-Z0-9]+$";

		patternEmail = Pattern.compile(regexEmail);
		matcherEmail = patternEmail.matcher(login);

		patternPass = Pattern.compile(regexPass);
		matcherPass = patternPass.matcher(password);

		if (matcherEmail.matches() && matcherPass.matches()) {
			return true;
		} else {
			System.out.println("Incorrect email or password format");
			return false;
		}
	}

	public boolean checkProfileData(String name, String surname, String email, String addressL1, String addressL2,
			String addressL3) {
		String regexNameSurname;
		String regexEmail;
		String regexAddress;

		Pattern patternNameSurname;
		Pattern patternEmail;
		Pattern patternAddress;

		Matcher matcherName;
		Matcher matcherSurname;
		Matcher matcherEmail;
		Matcher matcherAddressL1;
		Matcher matcherAddressL2;
		Matcher matcherAddressL3;

		regexNameSurname = "^[a-zA-Z ]{2,}+$";
		regexEmail = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		regexAddress = "^[a-zA-Z0-9 ]+$";

		patternNameSurname = Pattern.compile(regexNameSurname);
		patternEmail = Pattern.compile(regexEmail);
		patternAddress = Pattern.compile(regexAddress);

		matcherName = patternNameSurname.matcher(name);
		matcherSurname = patternNameSurname.matcher(surname);
		matcherEmail = patternEmail.matcher(email);
		matcherAddressL1 = patternAddress.matcher(addressL1);
		matcherAddressL2 = patternAddress.matcher(addressL2);
		matcherAddressL3 = patternAddress.matcher(addressL3);

		if (matcherName.matches() && matcherSurname.matches() && matcherEmail.matches() && matcherAddressL1.matches()
				&& matcherAddressL2.matches() && matcherAddressL3.matches()) {
			return true;
		} else {
			System.out.println("Incorrect name, surname, email or address provided");
			return false;
		}
	}

	public boolean checkPassUpdate(String passOld, String passNew) {

		String regexPass;
		Pattern patternPass;
		Matcher matcherPassOld;
		Matcher matcherPassNew;

//		regexPass = "^[a-zA-Z0-9]{8,16}+$";
		regexPass = "^[a-zA-Z0-9]+$";

		patternPass = Pattern.compile(regexPass);
		matcherPassOld = patternPass.matcher(passOld);
		matcherPassNew = patternPass.matcher(passNew);

		if (matcherPassOld.matches() && matcherPassNew.matches()) {
			return true;
		} else {
			System.out.println("Validator: New or old password do not match pass req-s");
			return false;
		}

	}
	
	
	public boolean checkPhoneNumber (String phoneNumber) {
		String regexPhoneNumber;
		Pattern patternPhoneNumber;
		Matcher matcherPhoneNumber;
		
		regexPhoneNumber = "^\\d{9,9}$";
		
		patternPhoneNumber = Pattern.compile(regexPhoneNumber);
		matcherPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
		
		if (matcherPhoneNumber.matches()) {
			return true;
		} else {
			System.out.println("Phone number string does not match the specification for provision.");
			return false;
		}
	}

	public static UserDataValidator getInstane() {
		return instance;
	}
}
