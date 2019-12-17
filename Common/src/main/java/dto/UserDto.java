package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user传输 对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String phone;
    private String pwd;
    private String name;
}
