package org.abubaker.archexample.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Step 01 - Define Table for Database using Room
 * At compile time, @Entity will create all the necessary code to create an SQLite Table for this object
 */
@Entity(tableName = "note_table")
public class Note {

    /**
     * Step 02 - Auto Generated Unique Key
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * Step 03 - Define Columns for Table
     */
    // TIP:
    // Turing following VARIABLES to PUBLIC will not require GETTERS

    @ColumnInfo(name = "col_title")
    private String title;

    @ColumnInfo(name = "col_desc")
    private String description;

    @ColumnInfo(name = "col_priority")
    private int priority;

    /**
     * Step 05 - Constructor for title, description and priority
     * To later create these Node Objects
     * Room also needs them to re-create from database
     */
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Step 06 - Setter for Primary-Key
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Step 04 - Generate Getters so DATA can be STORED in SQLite
     */
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
