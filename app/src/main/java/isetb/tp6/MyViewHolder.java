package isetb.tp6;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView countryNameTextView, countryPopulationTextView;
    Button updateButton, deleteButton;

    public MyViewHolder(View itemView) {
        super(itemView);
        countryNameTextView = itemView.findViewById(R.id.countryNameTextView);
        countryPopulationTextView = itemView.findViewById(R.id.countryPopulationTextView);
        updateButton = itemView.findViewById(R.id.updateButton);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }

    public void bind(Country country, CountryAdapter.OnItemClickListener listener) {
        countryNameTextView.setText("Country: " + country.getName());
        countryPopulationTextView.setText("Population: " + country.getPopulation());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpdateClick(country);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(country);
            }
        });
    }
}