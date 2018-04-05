package br.com.xplorer.bindservicewithmessegerclass.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.xplorer.bindservicewithmessegerclass.R;
import br.com.xplorer.bindservicewithmessegerclass.models.DemonstrativeResult;
import br.com.xplorer.bindservicewithmessegerclass.viewholder.ViewHolderListDataSalaries;

/**
 * Created by r028367 on 04/04/2018.
 */

public class AdapterRecycleViewDataSalaries extends RecyclerView.Adapter<ViewHolderListDataSalaries> {

    private List<DemonstrativeResult> list;
    private Context context;

    public AdapterRecycleViewDataSalaries(@NonNull List<DemonstrativeResult> list) {
        this.list = list;
    }

    @Override
    public ViewHolderListDataSalaries onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View rootLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_data_demonstrative, parent, false);

        return new ViewHolderListDataSalaries(rootLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolderListDataSalaries holder, int position) {
        DemonstrativeResult demonstrativeResult = list.get(position);
        holder.getDescription().setText(demonstrativeResult.getDescription());
        holder.getReference().setText(String.format("R$ %s"
                , demonstrativeResult.getFormattedReference()));
        holder.getResult().setText(demonstrativeResult.getFormattedResult());
        holder.getResult()
                .setTextColor(ContextCompat.getColor(context
                        , demonstrativeResult.getResult() < 0
                                ? R.color.negative_bank_balance : R.color.positive_bank_balance));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
