package com.wipro.book.dao;

import java.sql.*;
import com.wipro.book.bean.*;
import com.wipro.book.util.DBUtil;

public class BookDAO {

    public int createBook(BookBean bookBean) {
        String query =
        "INSERT INTO BOOK_TBL (ISBN, BOOK_TITLE, BOOK_TYPE, AUTHOR_CODE, BOOK_COST) VALUES (?,?,?,?,?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookBean.getIsbn());
            ps.setString(2, bookBean.getBookName());
            ps.setString(3, String.valueOf(bookBean.getBookType()));
            ps.setInt(4, bookBean.getAuthor().getAuthorCode());
            ps.setFloat(5, bookBean.getCost());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public BookBean fetchBook(String isbn) {
        String query = "SELECT * FROM BOOK_TBL WHERE ISBN=?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BookBean b = new BookBean();
                b.setIsbn(rs.getString("ISBN"));
                b.setBookName(rs.getString("BOOK_TITLE"));
                b.setBookType(rs.getString("BOOK_TYPE").charAt(0));
                b.setCost(rs.getFloat("BOOK_COST"));

                AuthorBean author =
                    new AuthorDAO().getAuthor(rs.getInt("AUTHOR_CODE"));
                b.setAuthor(author);

                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
