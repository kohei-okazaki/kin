package jp.co.kin.web.type;

import jp.co.kin.common.type.BaseEnum;

public enum ResultType implements BaseEnum {

	/** SUCCESS */
	SUCCESS("0", "成功"),
	/** FAILURE */
	FAILURE("1", "失敗");

	/** コード値 */
	private String value;
	/** メッセージ */
	private String message;

	/**
	 * コンストラクタ
	 *
	 * @param value
	 *            値
	 * @param message
	 *            メッセージ
	 */
	private ResultType(String value, String message) {
		this.value = value;
		this.message = message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * messageを返す
	 *
	 * @return message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @see jp.co.kin.common.type.BaseEnum#of(Class, String)
	 * @param value
	 *            値
	 * @return ResultType
	 */
	public static ResultType of(String value) {
		return BaseEnum.of(ResultType.class, value);
	}

}