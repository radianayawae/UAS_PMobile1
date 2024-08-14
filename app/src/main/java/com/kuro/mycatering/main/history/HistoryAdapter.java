package com.kuro.mycatering.main.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kuro.mycatering.R;
import com.kuro.mycatering.main.database.DatabaseModel;
import com.kuro.mycatering.main.utils.FunctionHelper;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<DatabaseModel> modelDatabase;
    Context mContext;

    public HistoryAdapter(Context context, List<DatabaseModel> modelInputList) {
        this.mContext = context;
        this.modelDatabase = modelInputList;
    }

    //untuk set data ke menu view
    public void setDataAdapter(List<DatabaseModel> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    //untuk inisialisasi layout
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_riwayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DatabaseModel data = modelDatabase.get(position);

        holder.tvNama.setText(data.getNama_menu());
        holder.tvDate.setText(FunctionHelper.getToday());
        holder.tvJml.setText(data.getItems() + " item");
        holder.tvPrice.setText(FunctionHelper.rupiahFormat(data.getHarga()));
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    //untuk inisialisasi id di Adapter
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNama, tvDate, tvJml, tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvJml = itemView.findViewById(R.id.tvJml);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }

    //untuk function swipe hapus data
    public void setSwipeRemove(int position) {
        modelDatabase.remove(position);
        notifyItemRemoved(position);
    }

    //untuk restore data jika cancel delete
    public void restoreItem(DatabaseModel databaseModel, int position) {
        modelDatabase.add(position, databaseModel);
        notifyItemInserted(position);
    }

    public List<DatabaseModel> getData() {
        return modelDatabase;
    }

}
