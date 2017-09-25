package com.starwarsgo.data.source.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.starwarsgo.data.source.domain.model.Person;
import com.starwarsgo.data.source.domain.model.PersonFilm;
import com.starwarsgo.util.Constants;

/**
 * Created by jsantini on 25/09/17.
 */

public class DataBaseHelper<E> extends OrmLiteSqliteOpenHelper {

    //Construtor
    public DataBaseHelper(Context context ) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Person.class);
            TableUtils.createTable(connectionSource, PersonFilm.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Person.class, true);
            TableUtils.dropTable(connectionSource, PersonFilm.class, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }
}
