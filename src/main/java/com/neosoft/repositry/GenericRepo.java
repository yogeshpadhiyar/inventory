package com.neosoft.repositry;

import java.util.List;

public interface GenericRepo< T , C > {
    int insert(T t);

    void update(T t);

    void delete(T t);

    List<T> selectAll();

    T selectById(int id);

    C databaseConnector();
}
