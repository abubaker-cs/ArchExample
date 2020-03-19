package org.abubaker.archexample.vModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.abubaker.archexample.api.NoteRepository;
import org.abubaker.archexample.db.Note;

import java.util.List;

/**
 * viewModel: This will persist data during screen rotation
 * Our Activity will only have ACCESS to viewModel, not to the Repository
 */
public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    // Constructor matching supper class
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    // Insert
    public void insert(Note note) {
        repository.insert(note);
    }

    // Update
    public void update(Note note){
        repository.update(note);
    }

    // Delete
    public void delete(Note note){
        repository.delete(note);
    }

    // Delete All
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    // Return LiveData
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
