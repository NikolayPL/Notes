package nickerman.com.notes.screens.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import io.reactivex.Observable;
import nickerman.com.notes.R;
import nickerman.com.notes.adapters.AdapterMainNotes;
import nickerman.com.notes.adapters.INoteClick;
import nickerman.com.notes.models.Note;
import nickerman.com.notes.util.ui.VerticalSpacingItemDecorator;

public class NotesView implements NotesContract.View {

    private View root;
    private RecyclerView recyclerView;
    private AdapterMainNotes adapter;
    private FloatingActionButton fab;

    public NotesView(View root) {
        this.root = root;
        initView();
    }

    private void initView() {
        this.recyclerView = root.findViewById(R.id.recycler_view);
        this.fab = root.findViewById(R.id.fab);
    }

    @Override
    public void setAdapter(INoteClick iNoteClick) {

        LinearLayoutManager llm = new LinearLayoutManager(root.getContext());
        adapter = new AdapterMainNotes(iNoteClick);
        VerticalSpacingItemDecorator vsd = new VerticalSpacingItemDecorator(10);
        recyclerView.addItemDecoration(vsd);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setNotes(List<Note> notes) {
        adapter.setItems(notes);
    }

    @Override
    public Observable<Object> fabAction() {
        return RxView.clicks(fab);
    }
}
