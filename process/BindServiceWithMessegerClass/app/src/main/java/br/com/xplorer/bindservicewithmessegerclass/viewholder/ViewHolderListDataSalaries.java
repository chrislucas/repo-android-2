package br.com.xplorer.bindservicewithmessegerclass.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.xplorer.bindservicewithmessegerclass.R;

/**
 * Created by r028367 on 04/04/2018.
 */

public class ViewHolderListDataSalaries extends RecyclerView.ViewHolder {

    private View layout;
    private TextView result         // esse textview indica um vencimento ou um desconto. Se for um valor positivo e um vencimento do contrário desconto
            ,description    // descricao do vencimento | desconto
            ,reference      // valor de referencia para o calculo do vencimento | desconto
            ;

    public ViewHolderListDataSalaries(View layout) {
        super(layout);
        this.layout = layout;
        result = this.layout.findViewById(R.id.salaries_info_or_discount_info);
        description = this.layout.findViewById(R.id.description_info);
        reference = this.layout.findViewById(R.id.reference_info);
    }

    public View getLayout() {
        return layout;
    }

    public TextView getResult() {
        return result;
    }

    public TextView getDescription() {
        return description;
    }

    public TextView getReference() {
        return reference;
    }
}
