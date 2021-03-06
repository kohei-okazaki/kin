package jp.co.kin.tool.source;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

import jp.co.kin.common.util.StringUtil;
import jp.co.kin.tool.type.AccessType;

/**
 * Setter
 *
 * @since 1.0.0
 */
public class Setter extends Method {

    /** 接頭語 */
    private static final String PREFIX = "set";

    /**
     * コンストラクタ
     *
     * @param field
     *     Field
     */
    public Setter(Field field) {
        this(field, AccessType.PUBLIC);
    }

    /**
     * コンストラクタ
     *
     * @param field
     *     Field
     * @param accessType
     *     アクセスタイプ
     */
    public Setter(Field field, AccessType accessType) {
        super(field, accessType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        final String TAB = "	";

        StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
        body.add(TAB + accessType.getValue() + " void " + getMethodName() + "("
                + field.getClassType().getSimpleName() + " " + field.getName() + ") {");
        body.add(TAB + TAB + "this." + field.getName() + " = " + field.getName() + ";");
        body.add(TAB + "}");

        return body.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMethodName() {
        return PREFIX + StringUtils.capitalize(field.getName());
    }
}
