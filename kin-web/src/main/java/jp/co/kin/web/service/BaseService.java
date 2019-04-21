package jp.co.kin.web.service;

import jp.co.kin.common.exception.BaseException;
import jp.co.kin.web.request.BaseRequest;
import jp.co.kin.web.response.BaseResponse;

/**
 * API基底サービス<br>
 * 各機能のサービスの親インターフェース
 *
 * @param <Rq>
 *     リクエスト
 * @param <Rs>
 *     レスポンス
 */
public interface BaseService<Rq extends BaseRequest, Rs extends BaseResponse> {

	/**
	 * 継承先でそれぞれチェックを実装
	 *
	 * @param request
	 *     Request実装クラス
	 * @throws BaseException
	 *     基底例外
	 */
	void checkRequest(Rq request) throws BaseException;

	/**
	 * メイン処理<br>
	 * リクエスト型を処理し、レスポンス型で返す
	 *
	 * @param request
	 *     Request実装クラス
	 * @return Rs Response実装クラス
	 * @throws BaseException
	 *     基底例外
	 */
	Rs execute(Rq request) throws BaseException;

}