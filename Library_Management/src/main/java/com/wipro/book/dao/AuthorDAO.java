package com.wipro.book.dao;

import java.sql.*;
import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {
        String query = "SELECT * FROM AUTHOR_TBL WHERE AUTHOR_CODE=?";
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, authorCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AuthorBean a = new AuthorBean();
                a.setAuthorCode(rs.getInt("AUTHOR_CODE"));
                a.setAuthorName(rs.getString("AUTHOR_NAME"));
                a.setContactNo(rs.getLong("CONTACT_NO"));
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
