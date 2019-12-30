package response;

import lombok.Data;

/**
 * user返回信息
 */
@Data
public class UserInfo {

    private int id;
    private String name;
    private String phone;
    private String photo;
    private String token;
}
