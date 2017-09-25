package com.starwarsgo.data.source;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.starwarsgo.data.source.database.DataBaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jsantini on 25/09/17.
 */

public class GenericRepository<E> extends DataBaseHelper<E> {

    protected Dao<E, Integer> dao;
    private Class<E> type;

    //Construtor
    public GenericRepository(Context context, Class<E> type) {
        super(context);
        this.type = type;
        setDao();
    }

    protected void setDao(){
        try {
            dao = DaoManager.createDao(getConnectionSource(), type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<E> getAll(){
        try{
            List<E> result = dao.queryForAll();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public E getById(int id){
        try {
            E obj = dao.queryForId(id);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void insert(E object) throws Exception {
        dao.create(object);
    }

    public void update(E object) throws SQLException {
        dao.update(object);
    }

    public void delete(E object) throws SQLException {
        dao.delete(object);
    }

    public Object getLast(E object) {
        QueryBuilder<E, Integer> queryBuilder = dao.queryBuilder();
        queryBuilder.orderBy("id", false);
        E response = null;
        try {
            response = dao.queryForFirst(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }
}
