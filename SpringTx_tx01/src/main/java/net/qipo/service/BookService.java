package net.qipo.service;

import net.qipo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;


    /**
     * 结账：传入那个用户买了哪本书
     * @param username
     * @param isbn
     */
    public void checkout(String username, String isbn) {
        // 1. 减库存
        bookDao.updateStock(isbn);

        int price = bookDao.getPrice(isbn);
        // 2. 减去余额
        bookDao.updateBalance(username, price);


    }
}
