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
import android.transition.Explode;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.vacine.Util.PopulateCarteirinhaUtil;
import org.vacine.adapter.VacinaAdapter;
import org.vacine.model.Carteirinha;
import org.vacine.model.Vacina;

public class CarteirinhaActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef = database.getReference("carteirinha");;

    private FloatingActionButton facAddVacina;
    private RecyclerView recyclerViewVacinas;

    private Carteirinha carteirinha = new Carteirinha();

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteirinha);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = getIntent().getExtras().getString("name");
        findViews();
        loadVacinasFromFirebase();
        setActions();
    }

    private void createCarteirinha() {
        PopulateCarteirinhaUtil.setVacinasFirebase(name);
    }

    private void findViews(){
        recyclerViewVacinas = (RecyclerView) findViewById(R.id.recycler_view_vacinas);
        facAddVacina = (FloatingActionButton) findViewById(R.id.fac_add_vacina);
    }

    private void setActions() {
        facAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createVacina();
            }
        });
    }

    private void setRecyclerView(Carteirinha carteirinhaTemp){
        recyclerViewVacinas.setHasFixedSize(true);
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewVacinas.setLayoutManager(mLayoutManager);

        if (carteirinha.getVacinas().isEmpty()) {
            createCarteirinha();
        }

        VacinaAdapter vacinaAdapter = new VacinaAdapter(carteirinhaTemp.getVacinas(), CarteirinhaActivity.this);
        vacinaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = recyclerViewVacinas.getChildLayoutPosition(view);
                TextView viewTransition = (TextView) view.findViewById(R.id.text_view_vacina_name);
                Bundle bundle = new Bundle();
                bundle.putSerializable("vacina", carteirinha.getVacinas().get(position));
                bundle.putSerializable("carteirinha", carteirinha);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("vacina", new Vacina());
        bundle.putSerializable("carteirinha", carteirinha);
        intent.putExtra("extra", bundle);
        startActivity(intent);
    }

    private void loadVacinasFromFirebase(){

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot obj : children) {
                    if (name.equals(obj.getValue(Carteirinha.class).getName())){
                        carteirinha = obj.getValue(Carteirinha.class);
                    }
                }

                setRecyclerView(carteirinha);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
