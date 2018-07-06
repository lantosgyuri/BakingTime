package com.example.lanto.bakingtime.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.lanto.bakingtime.data.Recipe;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "bakingTime";
    private static RecipeDatabase sInstance;

    public static RecipeDatabase getsInstance(Context context){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        RecipeDatabase.class,
                        RecipeDatabase.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();

            }
        }
        return sInstance;
    }

    public abstract RecipeDao recipeDao();
}
