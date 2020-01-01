package com.lile.dao.daoImpl;

import com.lile.common.mybits.model.Book;
import com.lile.common.mybits.model.BookExample;
import com.lile.common.mybits.persistence.BookMapper;
import com.lile.dao.BookDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public class BookDaoImpl implements BookDao {
    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Book> findBookByUser(int userId, int status) {
        BookExample bookExample = new BookExample();
        bookExample.or().andUserIdEqualTo(userId).andStatusEqualTo(status);
        return bookMapper.selectByExample(bookExample);
    }

    @Override
    public boolean insertBook(Book book) {
        return bookMapper.insertSelective(book)!=0;
    }
}
