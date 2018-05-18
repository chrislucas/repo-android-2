package br.com.xplorer.bindservicewithmessegerclass.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.xplorer.bindservicewithmessegerclass.R;
import br.com.xplorer.bindservicewithmessegerclass.viewholder.ViewHolderListLatestDemonstrative;

/**
 * Created by r028367 on 09/04/2018.
 */

public class AdapterRecycleViewLatestDemonstratives extends RecyclerView.Adapter<ViewHolderListLatestDemonstrative>  {

    private String [] lastMonths;

    public AdapterRecycleViewLatestDemonstratives(String [] lastMonths) {
        this.lastMonths = lastMonths;
    }

    @Override
    public ViewHolderListLatestDemonstrative onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_latest_demonstrative, parent, false);
        return new ViewHolderListLatestDemonstrative(rootLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolderListLatestDemonstrative holder, int position) {
        TextView textView = holder.getLastMonth();
        textView.setText(lastMonths[position]);
    }

    @Override
    public int getItemCount() {
        return this.lastMonths.length;
    }
}
