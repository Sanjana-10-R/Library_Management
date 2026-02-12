package com.wipro.book.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

     
        if ("AddBook".equals(operation)) {

            String isbn = request.getParameter("isbn");
            String bookName = request.getParameter("bookName");
            char bookType = request.getParameter("bookType").charAt(0);
            float cost = Float.parseFloat(request.getParameter("cost"));
            int authorCode = Integer.parseInt(request.getParameter("authorCode"));

            BookBean book = new BookBean();
            book.setIsbn(isbn);
            book.setBookName(bookName);
            book.setBookType(bookType);
            book.setCost(cost);
            book.setAuthor(new AuthorDAO().getAuthor(authorCode));

            String result = new Administrator().addBook(book);

            if ("SUCCESS".equals(result))
                response.sendRedirect("Menu.html");
            else if ("INVALID".equals(result))
                response.sendRedirect("Invalid.html");
            else
                response.sendRedirect("Failure.html");
        }

        
        else if ("Search".equals(operation)) {

            String isbn = request.getParameter("isbn");
            BookBean book = new Administrator().viewBook(isbn);

            if (book == null) {
                response.sendRedirect("Invalid.html");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("book", book);

                RequestDispatcher rd =
                        request.getRequestDispatcher("ViewServlet");
                rd.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
