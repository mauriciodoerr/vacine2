package org.vacine.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.vacine.R;
import org.vacine.model.Vacina;

import java.util.List;

/**
 * Created by alvardev on 18/05/17.
 * Adapter for RecyclerView
 */

public class VacinaAdapter extends RecyclerView.Adapter<VacinaViewHolder>
        implements View.OnClickListener {

    private List<Vacina> mData;
    private View.OnClickListener listener;
    private Context mContext;

    public VacinaAdapter(List<Vacina> myData, Context context) {
        this.mData = myData;
        this.mContext = context;
    }

    @Override
    public VacinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacina_layout, parent, false);
        view.setOnClickListener(this);
        return new VacinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VacinaViewHolder holder, int position) {
        holder.textViewName.setText(mData.get(position).getName());
        holder.textViewDate.setText(mData.get(position).getDate());
        holder.textViewPlace.setText(mData.get(position).getPlace());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

}
