package pl.coderslab.dao;


import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DayNameDao {
    private static final String FIND_ALL_DAY_NAME = "SELECT id,name,display_order FROM day_name;";
    private static final String READ_DAY_BY_NAME_QUERY = "SELECT * from day_name where name = ?;";


    public  static List<DayName> findAll() {
        List<DayName> dayNameList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_DAY_NAME);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DayName dayNameToAdd = new DayName();
                dayNameToAdd.setId(resultSet.getInt("id"));
                dayNameToAdd.setName(resultSet.getString("name"));
                dayNameToAdd.setDisplayOrder(resultSet.getInt("display_order"));
                dayNameList.add(dayNameToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dayNameList;

    }

    public static DayName readByName(String day) {
        DayName dayName = new DayName();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_DAY_BY_NAME_QUERY)
        ) {
            statement.setString(1, day);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dayName.setId(resultSet.getInt("id"));
                    dayName.setName(resultSet.getString("name"));
                    dayName.setDisplayOrder(resultSet.getInt("display_order"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayName;
    }
}
