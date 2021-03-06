package jp.co.kin.common.bean;

import java.util.Optional;

import jp.co.kin.common.log.Logger;
import jp.co.kin.common.log.LoggerFactory;
import jp.co.kin.common.util.BeanUtil;

/**
 * DtoのFactory<br>
 * beanのフィールドと対応したclassを指定してDtoを取得する
 *
 * @since 1.0.0
 *
 */
public class DtoFactory {

    private static final Logger LOG = LoggerFactory.getLogger(DtoFactory.class);

    private DtoFactory() {
    }

    /**
     * DTOを返す
     *
     * @param <T>
     *     DTO具象クラス
     * @param dtoClass
     *     DTOクラス
     * @param bean
     *     コピー元データ
     * @return DTO
     */
    public static <T extends BaseDto> T getDto(Class<T> dtoClass, Object bean) {

        if (BeanUtil.isNull(bean)) {
            return null;
        }

        try {
            T t = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtil.copy(bean, t);
            return t;
        } catch (Exception e) {
            LOG.error("DTOの生成に失敗しました", e);
            return null;
        }
    }

    /**
     * DTOを返す
     *
     * @param <T>
     *     DTO具象クラス
     * @param dtoClass
     *     DTOクラス
     * @param bean
     *     コピー元データ
     * @return DTO
     */
    public static <T extends BaseDto> Optional<T> getNullableDto(Class<T> dtoClass,
            Object bean) {

        if (BeanUtil.isNull(bean)) {
            return Optional.ofNullable(null);
        }

        try {
            T t = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtil.copy(bean, t);
            return Optional.ofNullable(t);
        } catch (Exception e) {
            LOG.error("DTOの生成に失敗しました", e);
            return Optional.ofNullable(null);
        }
    }

}
