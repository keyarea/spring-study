package net.qipo.service;

import net.qipo.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class BookService {

    @Autowired
    BookDao bookDao;


    /**
     * 结账：传入那个用户买了哪本书
     * @param username
     * @param isbn
     */
    /**
     * 事务细节:
     * isolation-Isolation: 事务的隔离级别
     * propagation-Propagation: 事务的传播行为
     *
     * noRollbackFor-Class[]: 哪些异常事务可以不回滚
     * noRollbackForClassName-String[]: String全类名
     * rollbackFor-Class[]: 哪些异常事务需要回滚
     * rollbackForClassName-String[]: String全类名
     *
     * readOnly-boolean: 设置事务为只读事务
     *          可以进行事务优化;
     *          readonly=true加快查询速度;
     *          不用管事务那一些操作了.
     * timeout-int:(秒为单位) 超时,事务超出指定执行时长后自动终止并回滚
     */
    //@Transactional(noRollbackFor = {ArithmeticException.class, NullPointerException.class})
    //@Transactional(noRollbackForClassName = {"java.lang.ArithmeticException"})
//    @Transactional(rollbackFor = {FileNotFoundException.class})
    @Transactional(propagation = Propagation.REQUIRED)
    public void checkout(String username, String isbn) throws FileNotFoundException {
        // 1. 减库存
        bookDao.updateStock(isbn);

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int price = bookDao.getPrice(isbn);
        // 2. 减去余额
        bookDao.updateBalance(username, price);

        // 运行时异常
//        int i = 10 / 0;

        // 编译时异常
        //new FileInputStream("/root/hell.txt");

    }

    @Transactional(readOnly = true,isolation = Isolation.DEFAULT)
    public int getPrice(String isbn) {
        return bookDao.getPrice(isbn);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePrice(String isbn, int price) {
        bookDao.updatePrice(isbn, price);
        int i = 10 / 0;
    }
}