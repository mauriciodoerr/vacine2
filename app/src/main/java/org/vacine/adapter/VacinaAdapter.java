package org.vacine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.vacine.R;
import org.vacine.model.Vacina;

import java.util.List;

/**
 * Created by alvardev on 18/05/17.
 * Adapter for RecyclerView
 */

public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Vacina> mData;
    private View.OnClickListener listener;
    private Context context;

    public VacinaAdapter(List<Vacina> myData, Context context) {
        this.mData = myData;
        this.context = context;
    }

    @Override
    public VacinaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacina_layout, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewPlace;

        ViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.text_view_vacina_name);
            textViewDate = (TextView) v.findViewById(R.id.text_view_vacina_date);
            textViewPlace = (TextView) v.findViewById(R.id.text_view_vacina_place);
        }
    }


}
