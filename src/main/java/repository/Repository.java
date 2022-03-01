package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class Repository<T> {
    protected Connection connector;

    abstract void insert(T t) throws SQLException;
    abstract T findById(Integer id) throws SQLException;
    abstract List<T> findAll() throws SQLException;
    abstract void deleteById(Integer id) throws SQLException;

    public boolean connect(String URL) {
        if (URL == null){
            connector = null;
            return false;
        }
        try {
            connector = DriverManager.getConnection(URL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
