package jp.co.kin.common.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import jp.co.kin.common.util.BeanUtil;
import jp.co.kin.common.util.StringUtil;
import jp.co.kin.common.validate.annotation.Min;

public class MinValidator implements ConstraintValidator<Min, Object> {

	private int size;

	private boolean isEqual;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(Min annotation) {
		this.size = annotation.size();
		this.isEqual = annotation.isEqual();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (BeanUtil.isNull(value) || StringUtil.isEmpty(value.toString())) {
			return true;
		}
		if (isEqual) {
			return this.size <= value.toString().length();
		} else {
			return this.size < value.toString().length();
		}
	}
}
