package isetb.tp6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateCountryActivity extends AppCompatActivity {
    private EditText nameEditText, populationEditText;
    private Button updateButton;
    private CountryDatabase db;
    private int countryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_country);

        nameEditText = findViewById(R.id.nameEditText);
        populationEditText = findViewById(R.id.populationEditText);
        updateButton = findViewById(R.id.updateButton);
        db = new CountryDatabase(this);

        countryId = getIntent().getIntExtra("countryId", -1);
        Country country = db.getCountryById(countryId);
        if (country != null) {
            nameEditText.setText(country.getName());
            populationEditText.setText(String.valueOf(country.getPopulation()));
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                double population = Double.parseDouble(populationEditText.getText().toString());
                Country updatedCountry = new Country(countryId, name, population);
                db.updateCountry(updatedCountry);
                Toast.makeText(UpdateCountryActivity.this, "Country updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}