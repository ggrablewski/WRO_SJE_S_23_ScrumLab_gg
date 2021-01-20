package pl.coderslab.dao;

import pl.coderslab.model.AboutContact;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AboutContactDao {

        private static final String ABOUT_QUERY = "SELECT * FROM about;";
        private static final String CONTACT_QUERY = "SELECT * FROM contact;";

        public static List<AboutContact> readAbout() {
            List<AboutContact> itemList = new ArrayList<>();
            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(ABOUT_QUERY);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    AboutContact item = new AboutContact();
                    item.setTitle(resultSet.getString("title"));
                    item.setContents(resultSet.getString("contents"));
                    itemList.add(item);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return itemList;
        }

    public static List<AboutContact> readContact() {
        List<AboutContact> itemList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CONTACT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                AboutContact item = new AboutContact();
                item.setTitle(resultSet.getString("title"));
                item.setHref(resultSet.getString("href"));
                item.setContents(resultSet.getString("contents"));
                itemList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }

}
