package org.vacine.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.vacine.R;

/**
 * Created by mauricio on 27/05/17.
 */

public class VacinaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDate;
        TextView textViewPlace;

    VacinaViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.text_view_vacina_name);
            textViewDate = (TextView) v.findViewById(R.id.text_view_vacina_date);
            textViewPlace = (TextView) v.findViewById(R.id.text_view_vacina_place);
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public void setTextViewName(TextView textViewName) {
        this.textViewName = textViewName;
    }

    public TextView getTextViewDate() {
        return textViewDate;
    }

    public void setTextViewDate(TextView textViewDate) {
        this.textViewDate = textViewDate;
    }

    public TextView getTextViewPlace() {
        return textViewPlace;
    }

    public void setTextViewPlace(TextView textViewPlace) {
        this.textViewPlace = textViewPlace;
    }
}
