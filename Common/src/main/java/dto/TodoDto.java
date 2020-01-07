package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private int id;
    private int userId;
    private String title;
    private String content;

}
