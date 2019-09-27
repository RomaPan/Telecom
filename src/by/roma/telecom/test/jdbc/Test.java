package by.roma.telecom.test.jdbc;

//import by.roma.telecom.bean.User;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.impl.SQLUserDao;
//import by.roma.telecom.dao.impl.UserDAOimpl;

public class Test {

	public static void main(String[] args) throws DaoException {
		
		SQLUserDao sql = new SQLUserDao();
		
		System.out.println(sql.authorization("sveta@mail.ru", "sveta"));
		
		
		

	}

}
