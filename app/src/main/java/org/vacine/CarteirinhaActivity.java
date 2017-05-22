package org.vacine;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.vacine.Util.Vacinas;
import org.vacine.adapter.VacinaAdapter;
import org.vacine.model.Vacina;

import java.util.List;

public class CarteirinhaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton facAddSong;
    private RecyclerView recyclerViewVacinas;

    private List<Vacina> vacinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteirinha);

        //String name = getIntent().getExtras().getString("name");
        vacinas = Vacinas.getVacinas();
        findViews();
//        setToolbar("Mauricio");
        setRecyclerView();
    }

    private void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewVacinas = (RecyclerView) findViewById(R.id.recycler_view_vacinas);
        facAddSong = (FloatingActionButton) findViewById(R.id.fac_add_song);
    }

    private void setRecyclerView(){
        recyclerViewVacinas.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewVacinas.setLayoutManager(mLayoutManager);

        VacinaAdapter songsAdapter = new VacinaAdapter(vacinas, CarteirinhaActivity.this);
        songsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerViewVacinas.getChildLayoutPosition(view);
                //ImageView img = (ImageView) view.findViewById(R.id.ivi_cover);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vacina", vacinas.get(position));
                //goToPlaySong(img, bundle);
            }
        });

        recyclerViewVacinas.setAdapter(songsAdapter);
        recyclerViewVacinas.setItemAnimator(new DefaultItemAnimator());
    }

    private void setToolbar(String name){
        toolbar.setTitle("Hi" + name);
        setSupportActionBar(toolbar);
    }
}
