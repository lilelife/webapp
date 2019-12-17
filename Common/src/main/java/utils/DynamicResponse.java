package utils;


import com.alibaba.fastjson.JSONObject;
import exceptions.UncheckedException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.function.Supplier;

/**
 * response包装
 * @param <T>
 */
@ApiModel
@Data
public class DynamicResponse<T> extends  Response<T>{

    @ApiModelProperty(value = "返回码",required = true)
    private String  code;
    @ApiModelProperty(value = "异常信息")
    private String msg;
    @ApiModelProperty(value = "返回值")
    private T result;
    private static Log logger =  LogFactory.getLog("DynamicResponse");
    public DynamicResponse() {

    }


    public static  <T> DynamicResponse<T> of(Supplier<T> supplier){
        DynamicResponse<T> dynamicResponse = new DynamicResponse<>();
        try {
            T result = supplier.get();
            dynamicResponse.setResult(result);
            dynamicResponse.setCode(ErrorCode.SUCCESS.getCode());
            dynamicResponse.setMsg(ErrorCode.SUCCESS.getMsg());

        } catch (UncheckedException e) {

            dynamicResponse.setCode(e.getErrorCode().getCode());
            dynamicResponse.setMsg(e.getMessage());
        }catch (Throwable throwable){
            logger.info(throwable.getMessage());
            dynamicResponse.setCode(ErrorCode.SERVER_ERROR.getCode());
            dynamicResponse.setMsg(ErrorCode.SERVER_ERROR.getMsg());
        }

//        logger.info(JSONObject.toJSONString(dynamicResponse));

        return dynamicResponse;
    }


    public static <T> DynamicResponse<T> ofErrorCode(ErrorCode errorCode){
        DynamicResponse<T> dynamicResponse = new DynamicResponse<>();
        dynamicResponse.setMsg(errorCode.getMsg());
        dynamicResponse.setCode(errorCode.getCode());
        return dynamicResponse;
    }


    public DynamicResponse(String code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }


    @Override
    public T getResult() {
        return result;
    }

    @Override
    public String getMsg() {
        return msg;
    }

//    public String getCode() {
//        return code;
//    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
