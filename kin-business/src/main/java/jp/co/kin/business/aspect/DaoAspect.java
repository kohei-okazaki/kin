package jp.co.kin.business.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import jp.co.kin.common.db.Crypter;
import jp.co.kin.common.log.Logger;
import jp.co.kin.common.log.LoggerFactory;

/**
 * DaoAspect
 *
 */
@Aspect
@Component
public class DaoAspect {

	/** LOG */
	private static final Logger LOG = LoggerFactory.getLogger(DaoAspect.class);

	@Autowired
	@Qualifier("aesCrypter")
	private Crypter crypter;

	@Around("execution(* jp.co.kin.db.dao.*Dao.select*(..))")
	public Object select(ProceedingJoinPoint pjp) throws Throwable {

		LOG.info(this.getClass() + " select called...");
		Object result = pjp.proceed();
		LOG.info(this.getClass() + " select success");

		return result;
	}
}