package br.com.xplorer.bindservicewithmessegerclass.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.xplorer.bindservicewithmessegerclass.R;

/**
 * Created by r028367 on 09/04/2018.
 */

public class ViewHolderListLatestDemonstrative extends RecyclerView.ViewHolder {

    private TextView lastMonth;

    public ViewHolderListLatestDemonstrative(View itemView) {
        super(itemView);
        lastMonth = itemView.findViewById(R.id.text_view_last_month);
    }

    public TextView getLastMonth() {
        return lastMonth;
    }
}
