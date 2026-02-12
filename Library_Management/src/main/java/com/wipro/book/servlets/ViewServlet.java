package com.wipro.book.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.wipro.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        BookBean bookBean = (BookBean) session.getAttribute("book");

        out.println("<html><body>");
        out.println("<h3>Book Details</h3>");

        if (bookBean == null) {
            out.println("No book details found");
        } else {
            out.println("Book Title: " + bookBean.getBookName() + "<br>");
            out.println("Author Name: " + bookBean.getAuthor().getAuthorName() + "<br>");
            out.println("Author Contact: " + bookBean.getAuthor().getContactNo() + "<br>");
            out.println("Book Price: " + bookBean.getCost() + "<br>");
            out.println("Book ISBN: " + bookBean.getIsbn());
        }

        out.println("</body></html>");
    }
}
