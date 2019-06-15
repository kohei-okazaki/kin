package jp.co.kin.db.config;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@AnnotateWith(annotations = {
		// 生成されたDAO実装クラスに@Repository
		@Annotation(target = AnnotationTarget.CLASS, type = Repository.class),
		// 生成されたDAO実装クラスのコンストラクタに@Autowired
		@Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class)
})
public @interface DaoRepository {

}
