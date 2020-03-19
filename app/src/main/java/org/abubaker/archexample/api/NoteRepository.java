package org.abubaker.archexample.api;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.abubaker.archexample.db.Note;
import org.abubaker.archexample.db.NoteDao;
import org.abubaker.archexample.db.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    // Application is SubClass of Context
    public NoteRepository(Application application) {

        // Select DB
        NoteDatabase database = NoteDatabase.getInstance(application);

        /**
         * NoteDao defines all methods (functions) for our Note TABLE
         */
        // Abstract method which we created in NoteDatabase.java class
        // Normally we cannot directly call them as they do not have body
        noteDao = database.noteDao();

        // Fetch all Notes
        allNotes = noteDao.getAllNotes();
    }

    /**
     * Methods for all Public DB Operations
     * These 5 Methods are the API, which Repository exposes to the outside
     */

    // 01 Insert
    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    // 02 Update
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    // 03 Delete
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    // 04 Delete all Notes - Do not pass anything
    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    // 05 Method which turns LiveData
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /**
     * Constructor for: Insert
     */
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    /**
     * Constructor for: Update
     */
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    /**
     * Constructor for: Delete
     */
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    /**
     * Constructor for: Delete All
     */
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }


}
