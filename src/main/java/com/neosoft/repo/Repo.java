package com.neosoft.repo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repo<T> {
    int insert(T t);

    int update(T t);

    void delete(T t);

    List<T> selectAll();

    T selectById(int id);

    Connection createConnection() throws SQLException, ClassNotFoundException;


}
