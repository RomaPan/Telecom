package by.roma.telecom.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ReadPropertyFile {

	private static final Logger LOGGER = Logger.getLogger(ReadPropertyFile.class);

	private static final ReadPropertyFile instance = new ReadPropertyFile();

	private String driverDB;
	private String loginDB;
	private String url;
	private String pass;
	private int poolSizeDB;

	private String insertUser;
	private String insertAccount;
	private String insertOrder;
	private String insertPhoneNumber;
	private String insertUserAccount;
	private String insertCallPlan;

	private String userSearchByID;
	private String userSearchByEmailAndPassword;
	private String userSearchByEmail;
	private String userUpdateProfile;
	private String userChangePass;
	private String changeUserRoleToAdmin;
	private String changeUserBlockStatus;
	private String getListOfAllUsers;

	private String accountAddCallPlan;
	private String accountCallPlanInfo;
	private String accountUpdateCallPlan;
	private String accountFullInfo;
	private String selectAccountAndCallPlan;
	private String selectAccountAndPhoneNumber;
	private String selectPhoneNumberAndAccountPhoneNumber;
	private String selectAccountByID;
	private String chargeToAccount;
	private String changeAccountBlockStatus;

	private String selectCallPlanRate;
	private String selectPhoneNumber;
	private String connectPhoneNumberToAccount;
	private String reservePhoneNumber;
	private String viewAvailablePhoneNumbers;
	private String disconnectPhoneNumberFromAccount;
	private String getPhoneNumberIDByAccountID;
	private String getAccountBalance;
	private String updateAccountBalance;

	private String findAccountByPhoneNumber;
	private String getListOfAllAccounts;
	private String insertUserWithoutAutoIncrement;
	private String deleteUserJunitTest;
	
	private String deleteAccount;
	private String deleteUser;
	
	private ReadPropertyFile() {

		Properties prop = null;
		InputStream is = null;

		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/config.properties");
			prop = new Properties();
			prop.load(is);
			this.driverDB = prop.getProperty("driverDB");
			this.url = prop.getProperty("url");
			this.loginDB = prop.getProperty("login");
			this.pass = prop.getProperty("pass");
			try {
				this.poolSizeDB = Integer.parseInt(prop.getProperty("poolSize"));
			} catch (NumberFormatException e) {
				LOGGER.error("Read Property File : Number Format Exception : Failed to obtain pool size value.", e);
				poolSizeDB = 5;
			}
			this.insertUser = prop.getProperty("insertUser");
			this.insertAccount = prop.getProperty("insertAccount");
			this.insertUserAccount = prop.getProperty("insertUserAccount");
			this.userSearchByID = prop.getProperty("userSearchByID");
			this.userSearchByEmailAndPassword = prop.getProperty("userSearchByEmailAndPassword");
			this.userUpdateProfile = prop.getProperty("userUpdateProfile");
			this.userChangePass = prop.getProperty("userChangePass");
			this.accountAddCallPlan = prop.getProperty("accountAddCallPlan");
			this.accountCallPlanInfo = prop.getProperty("accountCallPlanInfo");
			this.accountUpdateCallPlan = prop.getProperty("accountUpdateCallPlan");
			this.viewAvailablePhoneNumbers = prop.getProperty("viewAvailablePhoneNumbers");
			this.accountFullInfo = prop.getProperty("accountFullInfo");
			this.selectAccountAndCallPlan = prop.getProperty("selectAccountAndCallPlan");
			this.selectCallPlanRate = prop.getProperty("selectCallPlanRate");
			this.selectPhoneNumber = prop.getProperty("selectPhoneNumber");
			this.connectPhoneNumberToAccount = prop.getProperty("connectPhoneNumberToAccount");
			this.reservePhoneNumber = prop.getProperty("reservePhoneNumber");
			this.selectAccountAndPhoneNumber = prop.getProperty("selectAccountAndPhoneNumber");
			this.selectPhoneNumberAndAccountPhoneNumber = prop.getProperty("selectPhoneNumberAndAccountPhoneNumber");
			this.selectAccountByID = prop.getProperty("selectAccountByID");
			this.chargeToAccount = prop.getProperty("chargeToAccount");
			this.changeUserRoleToAdmin = prop.getProperty("changeUserRoleToAdmin");
			this.changeUserBlockStatus = prop.getProperty("changeUserBlockStatus");
			this.getListOfAllUsers = prop.getProperty("getListOfAllUsers");
			this.changeAccountBlockStatus = prop.getProperty("changeAccountBlockStatus");
			this.disconnectPhoneNumberFromAccount = prop.getProperty("disconnectPhoneNumberFromAccount");
			this.getPhoneNumberIDByAccountID = prop.getProperty("getPhoneNumberIDByAccountID");
			this.getAccountBalance = prop.getProperty("getAccountBalance");
			this.updateAccountBalance = prop.getProperty("updateAccountBalance");
			this.userSearchByEmail = prop.getProperty("userSearchByEmail");
			this.findAccountByPhoneNumber = prop.getProperty("findAccountByPhoneNumber");
			this.getListOfAllAccounts = prop.getProperty("getListOfAllAccounts");
			this.insertUserWithoutAutoIncrement = prop.getProperty("insertUserWithoutAutoIncrement");
			this.deleteUserJunitTest = prop.getProperty("deleteUserJunitTest");
			this.deleteAccount = prop.getProperty("deleteAccount");
			this.deleteUser = prop.getProperty("deleteUser");
		} catch (FileNotFoundException e) {
			LOGGER.error("Read Property File : No property file found!", e);
		} catch (IOException e) {
			LOGGER.error("Read Property File : IOException ", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				LOGGER.error("Read Property File : IOException ", e);
			}
		}
	}

	public String getDriverDB() {
		return driverDB;
	}

	public String getUrl() {
		return url;
	}

	public String getLoginDB() {
		return loginDB;
	}

	public String getPass() {
		return pass;
	}

	public int getPoolSizeDB() {
		return poolSizeDB;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public String getInsertAccount() {
		return insertAccount;
	}

	public String getInsertOrder() {
		return insertOrder;
	}

	public String getInsertPhoneNumber() {
		return insertPhoneNumber;
	}

	public String getInsertUserAccount() {
		return insertUserAccount;
	}

	public String getInsertCallPlan() {
		return insertCallPlan;
	}

	public String getUserSearchByID() {
		return userSearchByID;
	}

	public String getUserSearchByEmailAndPassword() {
		return userSearchByEmailAndPassword;
	}

	public String getUserUpdateProfile() {
		return userUpdateProfile;
	}

	public String getUserChangePass() {
		return userChangePass;
	}

	public String getAccountAddCallPlan() {
		return accountAddCallPlan;
	}

	public String getAccountCallPlanInfo() {
		return accountCallPlanInfo;
	}

	public String getAccountUpdateCallPlan() {
		return accountUpdateCallPlan;
	}

	public String getViewAvailablePhoneNumbers() {
		return viewAvailablePhoneNumbers;
	}

	public String getAccountFullInfo() {
		return accountFullInfo;
	}

	public static ReadPropertyFile getInstance() {
		return instance;
	}

	public String getSelectAccountAndCallPlan() {
		return selectAccountAndCallPlan;
	}

	public String getSelectCallPlanRate() {
		return selectCallPlanRate;
	}

	public String getSelectPhoneNumber() {
		return selectPhoneNumber;
	}

	public String getConnectPhoneNumberToAccount() {
		return connectPhoneNumberToAccount;
	}

	public String getReservePhoneNumber() {
		return reservePhoneNumber;
	}

	public String getSelectAccountAndPhoneNumber() {
		return selectAccountAndPhoneNumber;
	}

	public String getSelectPhoneNumberAndAccountPhoneNumber() {
		return selectPhoneNumberAndAccountPhoneNumber;
	}

	public String getSelectAccountByID() {
		return selectAccountByID;
	}

	public String getChargeToAccount() {
		return chargeToAccount;
	}

	public String getChangeUserRoleToAdmin() {
		return changeUserRoleToAdmin;
	}

	public String getChangeUserBlockStatus() {
		return changeUserBlockStatus;
	}

	public String getListOfAllUsers() {
		return getListOfAllUsers;
	}

	public String getChangeAccountBlockStatus() {
		return changeAccountBlockStatus;
	}

	public String getDisconnectPhoneNumberFromAccount() {
		return disconnectPhoneNumberFromAccount;
	}

	public String getPhoneNumberIDByAccountID() {
		return getPhoneNumberIDByAccountID;
	}

	public String getAccountBalance() {
		return getAccountBalance;
	}

	public String getUpdateAccountBalance() {
		return updateAccountBalance;
	}

	public String getUserSearchByEmail() {
		return userSearchByEmail;
	}

	public String getAccountByPhoneNumber() {
		return findAccountByPhoneNumber;
	}

	public String getListOfAllAccounts() {
		return getListOfAllAccounts;
	}
	
	public String getInsertUserWithoutAutoIncrement() {
		return insertUserWithoutAutoIncrement;
	}
	
	public String getDeleteUserJunitTest() {
		return deleteUserJunitTest;
	}
	
	public String getDeleteAccount() {
		return deleteAccount;
	}
	
	public String getDeleteUser() {
		return deleteUser;
	}
}
