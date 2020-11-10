package pl.coderslab.dao;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao  {
    private static final String createAdminQuery = "INSERT INTO admins(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String deleteAdminQuery = "DELETE FROM admins WHERE id = ?";
    private static final String findAllAdminsQuery = "SELECT * FROM admins0";
    private static final String updateAdminQuery = "UPDATE admins SET first_name=?, last_name=?, email=?, password = ? WHERE id=?";
    private static final String findAdminQuery = "SELECT * FROM admins WHERE id=?";

    public Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAdminQuery)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }
}
