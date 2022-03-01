package repository;

import model.Auto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoRepository extends Repository<Auto>{

    private static AutoRepository instance;

    public static synchronized AutoRepository getInstance() {
        if (instance == null){
            instance = new AutoRepository();
        }
        return instance;
    }

    @Override
    public void insert(Auto auto) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        //String sql = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connector.prepareStatement("INSERT INTO auto " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?)");//sql);
        preparedStatement.setString(1, auto.getName());
        preparedStatement.setString(2, auto.getClazz());
        preparedStatement.setString(3, auto.getMark());
        preparedStatement.setFloat(4, auto.getPrice());
        preparedStatement.setString(5, auto.getRegistration());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement = connector.prepareStatement("SELECT id FROM auto WHERE registration = ?");
        preparedStatement.setString(1, auto.getRegistration());
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()){
            throw new SQLException("Insert synchronization failed");
        }
        auto.setId(result.getInt("id"));
        result.close();
        preparedStatement.close();
    }

    @Override
    public Auto findById(Integer id) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (id == null){
            throw new NullPointerException("Null search");
        }
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM auto WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()){
            //throw new SQLException("Insert synchronization failed");
            return null;
        }
        //User.UserBuilder builder = User.builder();
        //builder.id(id).name(result.getString(2)).surname(result.getString(3))
        //        .patron(result.getString(4)).ipn(result.getString(5))
        //        .phone(result.getString(6));
        preparedStatement.close();
        result.close();
        //return builder.build();
        return Auto.builder().id(id).name(result.getString(2)).clazz(result.getString(3))
                .mark(result.getString(4)).price(result.getFloat(5))
                .registration(result.getString(6)).build();
    }

    public Auto findByRegistration(String registration) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (registration == null){
            throw new NullPointerException("Null search");
        }
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM auto WHERE registration = ?");
        preparedStatement.setString(1, registration);
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()){
            //throw new SQLException("Insert synchronization failed");
            return null;
        }
        //User.UserBuilder builder = User.builder();
        //builder.id(id).name(result.getString(2)).surname(result.getString(3))
        //        .patron(result.getString(4)).ipn(result.getString(5))
        //        .phone(result.getString(6));
        preparedStatement.close();
        result.close();
        //return builder.build();
        return Auto.builder().id(result.getInt(1)).name(result.getString(2))
                .clazz(result.getString(3)).mark(result.getString(4))
                .price(result.getFloat(5)).registration(registration).build();
    }

    @Override
    public List<Auto> findAll() throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        List<Auto> list = null;
        Statement statement = connector.createStatement();
        statement.execute("SELECT * FROM auto");
        ResultSet result = statement.getResultSet();
        if (result.next()){
            list = new ArrayList<>();
            do {
                list.add(Auto.builder().id(result.getInt(1)).name(result.getString(2))
                        .clazz(result.getString(3)).mark(result.getString(4))
                        .price(result.getFloat(5)).registration(result.getString(6)).build());
            } while (result.next());
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (id == null){
            throw new NullPointerException("Null deletion");
        }
        //String sql = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connector.prepareStatement("DELETE FROM auto WHERE id = ?");//sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
