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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_demonstrative);

        fake();
        fillLayout();

        recyclerView = findViewById(R.id.list_data_demonstrative);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterRecycleViewDataSalaries adapter = new AdapterRecycleViewDataSalaries(demonstrative.getResults());
        recyclerView.setAdapter(adapter);
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
        demonstrativeResult.setDescription("IRFF3 S.SALARIO");
        demonstrativeResult.setReference(7.5);
        demonstrativeResult.setResult(-21.83);
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
        ((TextView)findViewById(R.id.text_view_user_data))
                .setText(String.format(getString(R.string.formatted_user_info), user.getName(), user.getRegister()));

        SimpleDateFormat sdf = new SimpleDateFormat("MMM/YYYY", Locale.getDefault());
        ((TextView)findViewById(R.id.text_view_demonstrative_date))
                .setText(sdf.format(new Date(demonstrative.getDataDemonstrative())));


        ((TextView)findViewById(R.id.text_view_discounted_value))
                .setText(demonstrative.getFormattedDiscountedSalary());

        ((TextView)findViewById(R.id.text_view_total_salaries))
                .setText(demonstrative.getFormattedTotalSalary());
        ((TextView)findViewById(R.id.text_view_total_discounts))
                .setText(demonstrative.getFormattedTotalDiscounts());


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
