package br.com.xplorer.bindservicewithmessegerclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.xplorer.bindservicewithmessegerclass.adapter.AdapterRecycleViewDataSalaries;
import br.com.xplorer.bindservicewithmessegerclass.models.Demonstrative;
import br.com.xplorer.bindservicewithmessegerclass.models.DemonstrativeResult;
import br.com.xplorer.bindservicewithmessegerclass.models.User;

public class ActivityDemonstrative extends AppCompatActivity {

    private Demonstrative demonstrative;
    private User user = new User("Christoffer", "r028367");
    private RecyclerView recyclerView;


    private static final String BUNDLE_DEMONSTRATIVE = "BUNDLE_DEMONSTRATIVE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_demonstrative);

        if(savedInstanceState == null) {
            fake();
        }
        else {
            demonstrative = savedInstanceState.getParcelable(BUNDLE_DEMONSTRATIVE);
        }
        fillLayout();
        recyclerView = findViewById(R.id.list_data_demonstrative);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterRecycleViewDataSalaries adapter = new AdapterRecycleViewDataSalaries(demonstrative.getResults());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putParcelable(BUNDLE_DEMONSTRATIVE, demonstrative);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            demonstrative = savedInstanceState.getParcelable(BUNDLE_DEMONSTRATIVE);
        }
    }

    private void fake() {
        demonstrative = new Demonstrative();
        List<DemonstrativeResult> list = new ArrayList<>();
        DemonstrativeResult demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("101");
        demonstrativeResult.setDescription("SALARIOS");
        demonstrativeResult.setReference(30.00);
        demonstrativeResult.setResult(2500.00);
        list.add(demonstrativeResult);

        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("973");
        demonstrativeResult.setDescription("INSS");
        demonstrativeResult.setReference(11);
        demonstrativeResult.setResult(-275.00);
        list.add(demonstrativeResult);

        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("987");
        demonstrativeResult.setDescription("IRFF S.SALARIO");
        demonstrativeResult.setReference(7.5);
        demonstrativeResult.setResult(-187.50);
        list.add(demonstrativeResult);


        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("987");
        demonstrativeResult.setDescription("IRFF2 S.SALARIO");
        demonstrativeResult.setReference(3.35);
        demonstrativeResult.setResult(-83.75);
        list.add(demonstrativeResult);

        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("987");
        demonstrativeResult.setDescription("IRFF3 S.SALARIO");
        demonstrativeResult.setReference(13.35);
        demonstrativeResult.setResult(-333.75);
        list.add(demonstrativeResult);


        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("987");
        demonstrativeResult.setDescription("IRFF4 S.SALARIO");
        demonstrativeResult.setReference(13.33);
        demonstrativeResult.setResult(-333.25);
        list.add(demonstrativeResult);


        demonstrativeResult = new DemonstrativeResult();
        demonstrativeResult.setCode("987");
        demonstrativeResult.setDescription("IRFF5 S.SALARIO");
        demonstrativeResult.setReference(78.23);
        demonstrativeResult.setResult(-1955.75);
        list.add(demonstrativeResult);


        demonstrative.setDiscountedSalary(2130.78);
        demonstrative.setDataDemonstrative(System.currentTimeMillis());
        demonstrative.setTotalSalary(2130.78);
        demonstrative.setTotalDiscounts(361.22);
        demonstrative.setBaseSalary(2500.0);
        demonstrative.setContributionSalaryINSS(2500.0);
        demonstrative.setFgtsBaseCalc(2500.0);
        demonstrative.setFgtsTaxMouth(200.0);
        demonstrative.setIrffBaseCalc(2225.0);
        demonstrative.setIrff("02");

        demonstrative.setResults(list);
    }


    private void fillLayout() {
        ((TextView)findViewById(R.id.text_view_user_name))
                .setText( user.getName());
        ((TextView)findViewById(R.id.text_view_user_register))
                .setText( user.getRegister());


        SimpleDateFormat sdf = new SimpleDateFormat("MMM/YYYY", Locale.getDefault());
        ((TextView)findViewById(R.id.text_view_demonstrative_date))
                .setText(sdf.format(new Date(demonstrative.getDataDemonstrative())));


        ((TextView)findViewById(R.id.text_view_discounted_value))
                .setText(String.format("R$ %s", demonstrative.getFormattedDiscountedSalary()));

        ((TextView)findViewById(R.id.text_view_total_salaries))
                .setText(String.format("R$ %s", demonstrative.getFormattedTotalSalary()));
        ((TextView)findViewById(R.id.text_view_total_discounts))
                .setText(String.format("R$ %s", demonstrative.getFormattedTotalDiscounts()));


        ((TextView)findViewById(R.id.salary_base))
                .setText(demonstrative.getFormattedBaseSalary());
        ((TextView)findViewById(R.id.salary_contrib_inss))
                .setText(demonstrative.getFormattedContributionSalaryINSS());
        ((TextView)findViewById(R.id.base_calc_fgts))
                .setText(demonstrative.getFormattedFgtsBaseCalc());


        ((TextView)findViewById(R.id.fgts_tax_mouth))
                .setText(demonstrative.getFormattedFgtsTaxMouth());
        ((TextView)findViewById(R.id.calc_irff))
                .setText(demonstrative.getFormattedIrffBaseCalc());
        ((TextView)findViewById(R.id.value_irff))
                .setText(demonstrative.getIrff());

    }


}
