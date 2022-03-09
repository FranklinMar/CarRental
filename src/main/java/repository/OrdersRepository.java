package repository;


import model.Auto;
import model.Orders;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepository extends Repository<Orders>{

    public OrdersRepository(Connection connection){
        super(connection);
    }

    @Override
    public void insert(Orders orders) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        //String sql = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connector.prepareStatement("INSERT INTO orders " +
                "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)");//sql);
        preparedStatement.setBoolean(1, orders.getDriver());
        preparedStatement.setFloat(2, orders.getBill());
        preparedStatement.setFloat(3, orders.getAdd_bill());
        preparedStatement.setInt(4, orders.getId_auto().getId());
        preparedStatement.setInt(5, orders.getId_user().getId());
        preparedStatement.setTimestamp(6, orders.getOrder_date());
        preparedStatement.setTimestamp(7, orders.getReturn_date());
        preparedStatement.setString(8, orders.getDenial());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        preparedStatement = connector.prepareStatement("SELECT id FROM orders WHERE driver = ? AND bill = ? AND " +
                "add_bill = ? AND id_auto = ? AND id_user = ? AND order_date = ? AND return_date = ? AND denial = ?");
        preparedStatement.setBoolean(1, orders.getDriver());
        preparedStatement.setFloat(2, orders.getBill());
        preparedStatement.setFloat(3, orders.getAdd_bill());
        preparedStatement.setInt(4, orders.getId_auto().getId());
        preparedStatement.setInt(5, orders.getId_user().getId());
        preparedStatement.setTimestamp(6, orders.getOrder_date());
        preparedStatement.setTimestamp(7, orders.getReturn_date());
        preparedStatement.setString(8, orders.getDenial());
        //preparedStatement.executeUpdate();
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()){
            throw new SQLException("Insert synchronization failed");
        }
        orders.setId(result.getInt("id"));
        result.close();
        preparedStatement.close();
    }

    @Override
    public Orders findById(Integer id) throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        if (id == null){
            throw new NullPointerException("Null search");
        }
        PreparedStatement preparedStatement = connector.prepareStatement("SELECT * FROM orders WHERE id = ?");
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
        Orders.OrdersBuilder builder = Orders.builder().id(id).driver(result.getBoolean(2))
                .bill(result.getFloat(3)).add_bill(result.getFloat(4))
                .order_date(result.getTimestamp(7)).return_date(result.getTimestamp(8))
                .denial(result.getString(9));
        String idTemp = result.getString(5);
        //Integer idTemp = result.getInt(5);
        if (!idTemp.equalsIgnoreCase("null")) {
            preparedStatement = connector.prepareStatement("SELECT * FROM auto WHERE id = ?");
            preparedStatement.setInt(1, Integer.parseInt(idTemp));
            ResultSet temp = preparedStatement.executeQuery();
            if (!temp.next()){
                builder.id_auto(null);
            } else {
                builder.id_auto(Auto.builder().id(id).name(result.getString(2)).clazz(result.getString(3))
                        .mark(result.getString(4)).price(result.getFloat(5))
                        .registration(result.getString(6)).build());
            }
            preparedStatement.close();
            temp.close();
        }

        idTemp = result.getString(6);
        //Integer idTemp = result.getInt(5);
        if (!idTemp.equalsIgnoreCase("null")) {
            preparedStatement = connector.prepareStatement("SELECT * FROM user WHERE id = ?");
            preparedStatement.setInt(1, Integer.parseInt(idTemp));
            ResultSet temp = preparedStatement.executeQuery();
            if (!temp.next()){
                builder.id_user(null);
            } else {
                builder.id_user(User.builder().id(id).name(result.getString(2)).surname(result.getString(3))
                        .patron(result.getString(4)).ipn(result.getString(5))
                        .phone(result.getString(6)).build());
            }
            preparedStatement.close();
            temp.close();
        }
        return builder.build();
    }

    @Override
    public List<Orders> findAll() throws SQLException{
        if (connector == null){
            throw new SQLException("No connector found");
        }
        List<Orders> list = null;
        Statement statement = connector.createStatement();
        statement.execute("SELECT id FROM orders");
        ResultSet result = statement.getResultSet();
        if (result.next()){
            list = new ArrayList<>();
            do {
                list.add(findById(result.getInt(1)));
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
        PreparedStatement preparedStatement = connector.prepareStatement("DELETE FROM orders WHERE id = ?");//sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
