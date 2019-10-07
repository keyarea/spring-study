package net.qipo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
public class MulService {

    @Autowired
    BookService bookService;

    @Transactional
    public void mulTx() throws FileNotFoundException {
        // 都是可以设置的
        // 传播行为来设置这个事务方法是不是和之前的大事务共享一个事务(使用同一条链接);
        bookService.checkout("Tom", "ISBN-001");

        bookService.updatePrice("ISBN-002", 998);

//        int i = 10/0;
    }
}
