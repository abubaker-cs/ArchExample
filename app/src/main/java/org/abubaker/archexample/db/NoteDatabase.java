package org.abubaker.archexample.db;

import android.content.Context;
import android.os.AsyncTask;

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
    /**
     * RoomDatabase.Callbac = Fake initial DATA
     */
    // Demo fake DATA
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        // Only when DB is Created
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // Now Create Database
    // synchronized = only 1 thread at a time can access it
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback) // Attaching Dummy Data from RoomDatabase.Callback + PopulateDbAsyncTask
                    .build();
        }
        return instance;
    }

    // Fetch Methods from NoteDao.class
    public abstract NoteDao noteDao();

    /**
     * PopulateDbAsyncTask
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        // Constructor
        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        ;

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            noteDao.insert(new Note("Title 4", "Description 4", 4));
            noteDao.insert(new Note("Title 5", "Description 5", 5));
            return null;
        }
    }
}
