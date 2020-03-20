package org.abubaker.archexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.abubaker.archexample.db.Note;

import java.util.ArrayList;
import java.util.List;

// Here we will assign our custom Holder: myNoteHolder to RecyclerView.Adapter<>
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.myNoteHolder> {

    private List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public myNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new myNoteHolder(itemView);
    }

    // Here we take care of taking the data of single note object
    // into the Views of our myNoteHolder
    @Override
    public void onBindViewHolder(@NonNull myNoteHolder holder, int position) {
        Note currentNote = notes.get(position);

        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    /**
     * @return total size of rows
     * @getItemCount How many items do we want to display?
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }

    /**
     * @param notes
     * @setNotes inform RecyclerView for the change
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;

        // Better to avoid it, as it is not the best practice
        notifyDataSetChanged();
    }

    /**
     * @myNoteHolder custom ViewHolder that defines and assigns variables to TextViews
     */
    class myNoteHolder extends RecyclerView.ViewHolder {

        // Variables for our 3 different views
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvPriority;

        // Constructor matching supper
        public myNoteHolder(@NonNull View itemView) {
            super(itemView);

            // Assign our three text views
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPriority = itemView.findViewById(R.id.tv_priority);
        }
    }
}
