package org.abubaker.archexample.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Define all methods (functions) for our Note TABLE
 * -
 * We will not define WHAT to do, we will only create a pattern and add @annotation,
 * then ROOM will do what it has to do
 */

@Dao
public interface NoteDao {

    // Insert
    @Insert
    void insert(Note note);

    // Update
    @Update
    void update(Note note);

    // Delete
    @Delete
    void delete(Note note);

    // Use Query() when no default operation is supported for our purpose
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //
    @Query("SELECT * FROM note_table ORDER BY col_priority DESC")
    LiveData<List<Note>> getAllNotes();

}
