package dto.request;

import com.lile.enumns.BookStauts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateReq {
    private int id;
    private int percent ;
    private String bookCover; //书面
    private BookStauts status;// 书本状态
}
