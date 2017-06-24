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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.vacine.Util.PopulateCarteirinhaUtil;
import org.vacine.adapter.VacinaAdapter;
import org.vacine.model.Carteirinha;
import org.vacine.model.Vacina;

/**
 * Carteirinha
 *
 * @author Mauricio
 * @since 18/05/2017
 * @version 1.0
 */
public class CarteirinhaActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dataRef = database.getReference("carteirinha");;

    private FloatingActionButton facAddVacina;
    private RecyclerView recyclerViewVacinas;

    private Carteirinha carteirinha = new Carteirinha();

    private String name;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteirinha);

        // Get the id and name which has been received via Bundle from PerfilActivity
        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        findViews();
        loadVacinasFromFirebase();
        setActions();
    }

    /**
     * When the user is accessing the system for the first time, this method will load the default vacinas into Firebase
     */
    private void createCarteirinha() {
        PopulateCarteirinhaUtil.setVacinasFirebase(id, name);
    }

    /**
     * Map the layout into variables
     */
    private void findViews(){
        recyclerViewVacinas = (RecyclerView) findViewById(R.id.recycler_view_vacinas);
        facAddVacina = (FloatingActionButton) findViewById(R.id.fac_add_vacina);
    }

    /**
     * Setup listeners for buttons in layout
     * When clicking the "+" button on bottom right corner, it will open up details screen to save a new Vacina
     */
    private void setActions() {
        facAddVacina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createVacina();
            }
        });
    }

    /**
     * Setup RecyclerView with Carteirinha information
     * @param carteirinhaTemp populated object received to load information on screen
     */
    private void setRecyclerView(Carteirinha carteirinhaTemp){
        recyclerViewVacinas.setHasFixedSize(true);

        // Create layout with vertical orientation and maximum of three items per row
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        // Setup layout into RecyclerView
        recyclerViewVacinas.setLayoutManager(mLayoutManager);

        // Validate if carteirinha loaded from Firebase has no vacinas, if yes, it will create and load the default vacinas list
        if (carteirinha.getVacinas().isEmpty()) {
            createCarteirinha();
        }

        // Setup adapter passing the list of vacinas
        VacinaAdapter vacinaAdapter = new VacinaAdapter(carteirinhaTemp.getVacinas());

        // Every list item will be clickable and load up a details screen
        vacinaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the position of the item into the RecyclerView list
                int position = recyclerViewVacinas.getChildLayoutPosition(view);

                // Get the vacina name to animate when loading details
                TextView viewTransition = (TextView) view.findViewById(R.id.text_view_vacina_name);

                // Setup Bundle with vacina and the whole user Carteirinha
                Bundle bundle = new Bundle();
                bundle.putSerializable("vacina", carteirinha.getVacinas().get(position));
                bundle.putSerializable("carteirinha", carteirinha);

                // Load details screen with animation and passing the vacina and carteirinha details
                goToVacina(viewTransition, bundle);
            }
        });

        recyclerViewVacinas.setAdapter(vacinaAdapter);
        recyclerViewVacinas.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * Go to Vacina details
     * @param view from which view it will animate when loading details screen
     * @param bundle information from vacina and carteirinha
     */
    private void goToVacina(View view, Bundle bundle){
        Intent intent = new Intent(CarteirinhaActivity.this, VacinaActivity.class);
            intent.putExtra("extra", bundle);

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

    /**
     * Open up vacina details with Bundle setup with carteirinha and a new Vacina with no parameters filled up
     */
    private void createVacina(){
        Intent intent = new Intent(CarteirinhaActivity.this, VacinaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("vacina", new Vacina());
        bundle.putSerializable("carteirinha", carteirinha);
        intent.putExtra("extra", bundle);
        startActivity(intent);
    }

    /**
     * Load Carteirinha of the user from Firebase
     */
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

    /**
     * Create a menu on top right with logout option
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Go back to previous screen when clicking into back button on top left corner
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.fb_logout) {
            LoginManager.getInstance().logOut();
            startActivity(new Intent(CarteirinhaActivity.this, PerfilActivity.class));
            finish();
            return true;
        }

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
