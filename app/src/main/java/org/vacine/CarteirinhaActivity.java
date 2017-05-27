package org.vacine;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.*;

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

//        String name = getIntent().getExtras().getString("name");
        vacinas = Vacinas.getVacinas();
        Vacinas.setVacinasFirebase();
        findViews();
//        setToolbar("Mauricio");
        setRecyclerView();
        setActions();
    }

    private void findViews(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewVacinas = (RecyclerView) findViewById(R.id.recycler_view_vacinas);
        facAddSong = (FloatingActionButton) findViewById(R.id.fac_add_vacina);
    }

    private void setActions() {
        facAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createVacina();
            }
        });
    }

    private void setRecyclerView(){
        recyclerViewVacinas.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewVacinas.setLayoutManager(mLayoutManager);

        VacinaAdapter vacinaAdapter = new VacinaAdapter(vacinas, CarteirinhaActivity.this);
        vacinaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerViewVacinas.getChildLayoutPosition(view);
                //ImageView img = (ImageView) view.findViewById(R.id.ivi_cover);
                TextView viewTransition = (TextView) view.findViewById(R.id.text_view_vacina_name);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vacina", vacinas.get(position));
                goToVacina(viewTransition, bundle);
            }
        });

        recyclerViewVacinas.setAdapter(vacinaAdapter);
        recyclerViewVacinas.setItemAnimator(new DefaultItemAnimator());
    }

    private void goToVacina(View view, Bundle bundle){
        Intent intent = new Intent(CarteirinhaActivity.this, VacinaActivity.class);
        if (bundle != null) {
            intent.putExtra("extra", bundle);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
            ActivityOptions opt = view != null ?
                    ActivityOptions.makeSceneTransitionAnimation(this, view, "view") :
                    ActivityOptions.makeSceneTransitionAnimation(this);
            startActivity(intent, opt.toBundle());
        } else {
            startActivity(intent);
        }
    }

    private void createVacina(){
        Intent intent = new Intent(CarteirinhaActivity.this, VacinaActivity.class);
        startActivity(intent);
    }

    private void setToolbar(String name){
        toolbar.setTitle("Hi" + name);
        setSupportActionBar(toolbar);
    }

}
