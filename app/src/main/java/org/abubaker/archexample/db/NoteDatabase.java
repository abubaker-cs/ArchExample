package org.abubaker.archexample.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
        entities = {Note.class},
        version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    // Fetch Objects from NoteDatabase.class
    private static NoteDatabase instance;

    // Fetch Methods from NoteDao.class
    public abstract NoteDao noteDao();

    // Now Create Database
    // synchronized = only 1 thread at a time can access it
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    // Demo fake DATA
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        // Only when DB is Created
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        // Every time whenever DB is Opened
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
