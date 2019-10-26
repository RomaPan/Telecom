package by.roma.telecom.service.impl;

import java.util.List;
import by.roma.telecom.dao.DaoException;
import by.roma.telecom.dao.DaoProvider;
import by.roma.telecom.dao.UtilDao;
import by.roma.telecom.service.ServiceException;
import by.roma.telecom.service.UtilService;

public class UtilServiceImpl implements UtilService {

	@Override
	public List<String> getAvailablePhoneNumbers() throws ServiceException {
		UtilDao utilDao = DaoProvider.getInstance().getUtilDao();
		List<String> numbers;
		try {
			numbers = utilDao.getAvailablePhoneNumbers();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return numbers;
	}

}
