package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminDao {
    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name=?, last_name=?, email=?, password = ? WHERE id=?";
    private static final String CHANGE_SUPER_ADMIN_QUERY = "UPDATE admins SET superadmin=? WHERE id=?";
    private static final String FIND_ADMIN_QUERY = "SELECT * FROM admins WHERE id=?";
    private static final String VERIFY_EMAIL_QUERY = "SELECT * FROM admins WHERE email=?";
    private static final String VERIFY_PASSWORD_QUERY = "SELECT * FROM admins WHERE password=?";
    private static final String VERIFY_SUPERADMIN_QUERY = "SELECT * FROM admins WHERE password=? AND superadmin=1";


    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_QUERY)
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

    public  static List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                adminList.add(admin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;

    }

    public static  Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, hashPassword(admin.getPassword()));
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  static void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(5, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static  void changeIfSuperadmin(Integer adminId, Integer ifSuperadmin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_SUPER_ADMIN_QUERY)) {
            statement.setInt(2, adminId);
            statement.setInt(1, ifSuperadmin);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  static boolean verifyLogin(String email, String password) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(VERIFY_EMAIL_QUERY)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("login");
                    return false;
                } else {
                    String hashed = resultSet.getString("password");
                    System.out.println(hashed);
                    if(!BCrypt.checkpw(password, hashed)){
                        System.out.println("hasło");
                        return false;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public  static boolean verifySuperAdmin(int id) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(VERIFY_SUPERADMIN_QUERY)
        ) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
