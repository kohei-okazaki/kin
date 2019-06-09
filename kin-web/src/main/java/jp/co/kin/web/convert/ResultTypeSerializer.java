package jp.co.kin.web.convert;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import jp.co.kin.web.type.ResultType;

/**
 * JSONのAPIの結果コードのシリアライズクラス
 */
public class ResultTypeSerializer extends JsonSerializer<ResultType> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void serialize(ResultType resultType, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(resultType.getValue());
	}
}