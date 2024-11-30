package isetb.tp6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountryAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private CountryDatabase db;
    private CountryAdapter adapter;
    private ArrayList<Country> listCountry;
    private Button addCountryButton;
    private TextView noDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addCountryButton = findViewById(R.id.addCountryButton);
        noDataTextView = findViewById(R.id.noDataTextView);
        db = new CountryDatabase(this);

        addCountryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCountryActivity.class);
                startActivity(intent);
            }
        });

        listCountry = new ArrayList<>();
        adapter = new CountryAdapter(listCountry, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        listCountry.clear();
        listCountry.addAll(db.getAllCountry());
        if (listCountry.isEmpty()) {
            noDataTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noDataTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpdateClick(Country country) {
        Intent intent = new Intent(MainActivity.this, UpdateCountryActivity.class);
        intent.putExtra("countryId", country.getId());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Country country) {
        db.deleteCountry(country.getId());
        loadData();
    }
}