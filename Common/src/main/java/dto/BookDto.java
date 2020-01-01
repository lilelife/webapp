package dto;

import lombok.Data;

@Data
public class BookDto {

    private int userId;
    private String name;
    private String author;
    private String bookCover;
    private String introduction;
}
