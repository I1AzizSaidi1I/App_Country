package isetb.tp6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddCountryActivity extends AppCompatActivity {
    private EditText nameEditText, populationEditText;
    private Button addButton;
    private CountryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_country);

        nameEditText = findViewById(R.id.nameEditText);
        populationEditText = findViewById(R.id.populationEditText);
        addButton = findViewById(R.id.addButton);
        db = new CountryDatabase(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                double population = Double.parseDouble(populationEditText.getText().toString());
                Country country = new Country(name, population);
                db.addCountry(country);
                finish();
            }
        });
    }
}