package jp.co.kin.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jp.co.kin.common.exception.BaseException;
import jp.co.kin.common.log.Logger;
import jp.co.kin.common.log.LoggerFactory;
import jp.co.kin.common.util.ThrowableBiConsumer;
import jp.co.kin.web.form.BaseApiRequest;
import jp.co.kin.web.form.BaseApiResponse;
import jp.co.kin.web.form.ErrorResponse;

/**
 * Rest形式の基底コントローラ<br>
 * すべてのRestコントローラはこのクラスを継承すること
 *
 * @param <Rq>
 *     リクエスト
 * @param <Rs>
 *     レスポンス
 * @since 1.0.0
 */
@RestController
public abstract class BaseRestController<Rq extends BaseApiRequest, Rs extends BaseApiResponse>
        implements ThrowableBiConsumer<Rq, Rs>, BaseController {

    /** LOG */
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * POST通信処理行う
     *
     * @param request
     *     APIリクエスト
     * @return APIレスポンス
     * @throws BaseException
     *     API関連の例外
     */
    public Rs doPost(@RequestBody Rq request) throws BaseException {

        Rs response = getApiResponse();

        this.accept(request, response);

        return response;
    }

    /**
     * APIレスポンスを返す
     *
     * @return APIレスポンス
     */
    protected abstract Rs getApiResponse();

    /**
     * アプリケーション例外のエラーハンドリング
     *
     * @param e
     *     アプリエラー
     * @return エラーレスポンス
     */
    @SuppressWarnings("unchecked")
    @ExceptionHandler(BaseException.class)
    public Rs appExceptionHandle(BaseException e) {
        Rs response = (Rs) new ErrorResponse(e);
        switch (e.getErrorCode().getLogLevel()) {
        case WARN:
            LOG.warnRes(response, e);
            break;
        case ERROR:
            LOG.errorRes(response, e);
            break;
        default:
            break;
        }
        return response;
    }
}
