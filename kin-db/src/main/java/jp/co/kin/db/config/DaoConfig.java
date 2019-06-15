package jp.co.kin.db.config;

import java.util.function.Supplier;

import javax.sql.DataSource;

import org.seasar.doma.jdbc.AbstractJdbcLogger;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.JdbcLogger;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jp.co.kin.common.log.Logger;
import jp.co.kin.common.log.LoggerFactory;

/**
 * Dao設定情報クラス
 */
@Configuration
public class DaoConfig implements Config {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private Dialect dialect;
	@Autowired
	private SqlFileRepository sqlFileRepository;

	public DaoConfig() {
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	@Override
	public SqlFileRepository getSqlFileRepository() {
		return sqlFileRepository;
	}

	public void setSqlFileRepository(SqlFileRepository sqlFileRepository) {
		this.sqlFileRepository = sqlFileRepository;
	}

	@Override
	public JdbcLogger getJdbcLogger() {
		return new DaoLogger(Level.INFO);
	}

	/**
	 * Daoのロガークラス
	 *
	 */
	public static class DaoLogger extends AbstractJdbcLogger<Level> {

		private static final Logger LOG = LoggerFactory.getLogger(DaoLogger.class);

		public DaoLogger() {
			this(Level.DEBUG);
		}

		public DaoLogger(Level level) {
			super(level);
		}

		@Override
		public void log(Level level, String callerClassName, String callerMethodName, Throwable throwable,
				Supplier<String> messageSupplier) {

			switch (level) {
			case ERROR:
				LOG.error(buildLogMessage(callerClassName, callerMethodName, messageSupplier), throwable);
				break;
			case WARN:
				LOG.warn(buildLogMessage(callerClassName, callerMethodName, messageSupplier), throwable);
				break;
			case INFO:
				LOG.info(buildLogMessage(callerClassName, callerMethodName, messageSupplier));
				break;
			case DEBUG:
				LOG.debug(buildLogMessage(callerClassName, callerMethodName, messageSupplier));
				break;
			default:
				LOG.trace(buildLogMessage(callerClassName, callerMethodName, messageSupplier));
				break;
			}

		}

		private String buildLogMessage(String callerClassName, String callerMethodName,
				Supplier<String> messageSupplier) {
			return "| - " + callerClassName + ", " + messageSupplier.get();
		}

	}

}
