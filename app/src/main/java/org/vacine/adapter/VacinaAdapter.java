package org.vacine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.vacine.R;
import org.vacine.model.Vacina;

import java.util.List;

/**
 * Adapter to receive information from Carteirinha and fill up each ViewHolder with vacina information
 *
 * @author Mauricio
 * @since 18/05/2017
 * @version 1.0
 */

public class VacinaAdapter extends RecyclerView.Adapter<VacinaViewHolder>
        implements View.OnClickListener {

    private List<Vacina> mData;
    private View.OnClickListener listener;

    /**
     * Receives a list of Vacinas
     * @param myData list of Vacinas
     */
    public VacinaAdapter(List<Vacina> myData) {
        this.mData = myData;
    }

    @Override
    public VacinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacina_layout, parent, false);
        view.setOnClickListener(this);
        return new VacinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VacinaViewHolder holder, int position) {
        // Populate layout views with Vacina information
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
