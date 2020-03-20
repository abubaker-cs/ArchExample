package org.abubaker.archexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.abubaker.archexample.db.Note;

// Here we will assign our custom Holder: myNoteHolder to RecyclerView.Adapter<>
public class NoteAdapter extends ListAdapter<Note, NoteAdapter.myNoteHolder> {

    // For Comparision Logic
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        // areItemsTheSame
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        // areContentsTheSame = if nothing changed in the DATA (Looks)
        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == newItem.getPriority();
        }
    };
    // private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    // Using DiffUtil Library
    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

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
        Note currentNote = getItem(position);

        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvDescription.setText(currentNote.getDescription());
        holder.tvPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    // Method
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Interface for onClick Listener
     */
    public interface OnItemClickListener {
        void onItemClick(Note note);
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

            //
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

}
