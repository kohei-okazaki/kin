package jp.co.kin.business.db.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.kin.business.db.search.LoginUserDataSearchService;
import jp.co.kin.business.login.dto.LoginUserDataDto;
import jp.co.kin.common.bean.DtoFactory;
import jp.co.kin.db.dao.LoginUserDataDao;
import jp.co.kin.db.entity.LoginUserData;

@Service
public class LoginUserDataSearchServiceImpl implements LoginUserDataSearchService {

	@Autowired
	private LoginUserDataDao dao;

	@Override
	public LoginUserDataDto search(String loginId) {
		LoginUserData entity = dao.selectById(loginId);
		return DtoFactory.getDto(LoginUserDataDto.class, entity);
	}

}
