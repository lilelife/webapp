package exceptions;

import utils.ErrorCode;

/**
 * 自定义异常
 */
public class UncheckedException extends  RuntimeException {

    private ErrorCode errorCode;
    private String userDefinedMsg;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getUserDefinedMsg() {
        return userDefinedMsg;
    }

    @Override
    public String getMessage() {
        return this.userDefinedMsg;
    }

    public UncheckedException(){

    }

    public UncheckedException(Throwable cause) {
        super(cause);
        this.errorCode = ErrorCode.SERVER_ERROR;
        this.userDefinedMsg = ErrorCode.SERVER_ERROR.getMsg();
    }

    public UncheckedException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.userDefinedMsg = errorCode.getMsg();
    }

    public UncheckedException(ErrorCode errCode, String msg) {
        this(errCode);
        if (msg != null) this.userDefinedMsg = msg;
    }

    public UncheckedException(Throwable cause, String msg) {
        this(cause);
        if (msg != null) this.userDefinedMsg = msg;
    }
}
