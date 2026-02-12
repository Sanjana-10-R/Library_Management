package com.wipro.book.service;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean) {
        if (bookBean == null ||
            bookBean.getIsbn() == null || bookBean.getIsbn().trim().isEmpty() ||
            bookBean.getBookName() == null || bookBean.getBookName().trim().isEmpty() ||
            (bookBean.getBookType() != 'G' && bookBean.getBookType() != 'T') ||
            bookBean.getCost() <= 0 ||
            bookBean.getAuthor() == null) {

            return "INVALID";
        }

        int result = new BookDAO().createBook(bookBean);
        return (result == 1) ? "SUCCESS" : "FAILURE";
    }

    public BookBean viewBook(String isbn) {
        return new BookDAO().fetchBook(isbn);
    }
}
