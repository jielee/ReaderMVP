package zan.jie.lee.commons.db;

import java.util.List;

/**
 * Created by jie.lee on 10/3/15.
 */
public interface IDB <T> {

    void create(T entity);

    List<T> getAll();

    void clear();




}
