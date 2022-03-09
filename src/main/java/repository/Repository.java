package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class Repository<T> {
    protected Connection connector;

    abstract void insert(T t) throws SQLException;
    abstract T findById(Integer id) throws SQLException;
    abstract List<T> findAll() throws SQLException;
    abstract void deleteById(Integer id) throws SQLException;

    public Repository(Connection connection) {
        connector = connection;
    }
}
