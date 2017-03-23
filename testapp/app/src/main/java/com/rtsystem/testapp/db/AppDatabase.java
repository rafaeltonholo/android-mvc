package com.rtsystem.testapp.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by rafaeltonholo on 28/12/2016.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    static final String NAME = "ZupTestApp";
    static final int VERSION = 2;
}
