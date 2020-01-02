package br.com.zup.onboarding.android.view.register;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

import br.com.zup.onboarding.android.R;
import br.com.zup.onboarding.android.Utils;

public class SpinnerAdapter extends ArrayAdapter {
    private final Context context;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setTypeface(Utils.getFont(context));

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        parent.setBackgroundColor(ContextCompat.getColor(context, R.color.white));

        ((TextView) view).setTextColor(ContextCompat.getColor(context, android.R.color.black));
        ((TextView) view).setTypeface(Utils.getFont(context));

        return view;
    }
}
