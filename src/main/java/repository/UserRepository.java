package repository;

//import lombok.NoArgsConstructor;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
public class UserRepository extends Repository<User>{

    public UserRepository(Connection connection){
        super(connection);
    }

    @Override
    public void insert(User user) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        //String sql = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connector.prepareStatement("INSERT INTO user " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?)");//sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getPatron());
        preparedStatement.setString(4, user.getIpn());
        preparedStatement.setString(5, user.getPhone());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement = connector.prepareStatement("SELECT id FROM user WHERE ipn = ?");
        preparedStatement.setString(1, user.getIpn());
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()){
            throw new SQLException("Insert synchronization failed");
        }
        user.setId(result.getInt("id"));
        result.close();
        preparedStatement.close();
    }

    @Override
    public User findById(Integer id) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (id == null){
            throw new NullPointerException("Null search");
        }
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE id = ?");
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
        return User.builder().id(id).name(result.getString(2)).surname(result.getString(3))
                .patron(result.getString(4)).ipn(result.getString(5))
                .phone(result.getString(6)).build();
    }

    public User findByIpn(String ipn) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (ipn == null){
            throw new NullPointerException("Null search");
        }
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE ipn = ?");
        preparedStatement.setString(1, ipn);
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
        return User.builder().id(result.getInt(1)).name(result.getString(2))
                .surname(result.getString(3)).patron(result.getString(4)).ipn(ipn)
                .phone(result.getString(6)).build();
    }

    @Override
    public List<User> findAll() throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        List<User> list = null;
        Statement statement = connector.createStatement();
        statement.execute("SELECT * FROM user");
        ResultSet result = statement.getResultSet();
        if (result.next()){
            list = new ArrayList<>();
            do {
                list.add(User.builder().id(result.getInt(1)).name(result.getString(2))
                        .surname(result.getString(3)).patron(result.getString(4))
                        .ipn(result.getString(5)).phone(result.getString(6)).build());
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
        PreparedStatement preparedStatement = connector.prepareStatement("DELETE FROM user WHERE id = ?");//sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
