package org.vacine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.vacine.R;

/**
 * Linked to vacina layout in order to show each vacina into RecyclerView
 *
 * @author Mauricio
 * @since 27/05/2017
 * @version 1.0
 */

class VacinaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewPlace;


    /**
     * Constructor setting up layout views
     */
    VacinaViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.text_view_vacina_name);
            textViewDate = (TextView) v.findViewById(R.id.text_view_vacina_date);
            textViewPlace = (TextView) v.findViewById(R.id.text_view_vacina_place);
    }

}
