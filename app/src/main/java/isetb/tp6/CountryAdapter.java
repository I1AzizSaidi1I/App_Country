package isetb.tp6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<Country> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onUpdateClick(Country country);
        void onDeleteClick(Country country);
    }

    public CountryAdapter(ArrayList<Country> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Country country = list.get(position);
        holder.bind(country, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}