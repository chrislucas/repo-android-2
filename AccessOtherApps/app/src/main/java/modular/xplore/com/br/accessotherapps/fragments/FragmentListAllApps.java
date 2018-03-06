package modular.xplore.com.br.accessotherapps.fragments;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import modular.xplore.com.br.accessotherapps.R;
import modular.xplore.com.br.accessotherapps.activities.MainActivity;
import modular.xplore.com.br.accessotherapps.utils.BuildUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListAllApps.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListAllApps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListAllApps extends Fragment implements View.OnClickListener {


    private OnFragmentInteractionListener mListener;

    public FragmentListAllApps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment FragmentListAllApps.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListAllApps newInstance() {
        FragmentListAllApps fragment = new FragmentListAllApps();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_all_apps, container, false);

        view.findViewById(R.id.icon_icomon_com_vs).setOnClickListener(this);
        view.findViewById(R.id.icon_app_materiais).setOnClickListener(this);
        view.findViewById(R.id.icon_app_abertura_horario).setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onClick(View v) {

        String category, action, packageClass, id;
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.icon_icomon_com_vs:
                category = "com.project.icomoncomvc.category.START_OTHER_APP";
                action = "com.project.icomoncomvc.action.START_OTHER_APP";
                packageClass = "com.project.icomoncomvc";
                id = "com.project.icomoncomvc";
                bundle.putInt("BUNDLE", 1);
                doSomething(bundle, action, category, packageClass, id);
                break;

            case R.id.icon_app_materiais:
                break;

            case R.id.icon_app_abertura_horario:
                category = "br.com.icomon.aberturadehorario.category.START_OTHER_APP";
                action = "br.com.icomon.aberturadehorario.action.START_OTHER_APP";
                packageClass = "br.com.icomon.aberturadehorario";
                id = "br.com.icomon.aberturadehorario";
                bundle.putInt("BUNDLE", 2);
                doSomething(bundle, action, category, packageClass, id);
                break;
        }
    }


    // funciona se sabemos qual activity tem um intent-filter com action main
    // mesmo se a category nao for LAUNCHER
    //Intent intent = new Intent(Intent.ACTION_MAIN);
    //intent.setClassName("com.example.yourapp", "com.example.yourapp.MainActivity");
    //startActivity(intent);

    private void doSomething(Bundle bundle, String action, String category, String packageClass, String id) {

        List<ResolveInfo> list =  getActivity().getPackageManager().queryIntentActivities(new Intent(action), PackageManager.MATCH_DEFAULT_ONLY);

        getActivity().getPackageManager().resolveActivity(new Intent(action), 0);

        if(list != null && list.size() > 0) {}

        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(packageClass);
        if (intent != null) {
            intent.setAction(action);
            intent.addCategory(category);
            intent.putExtras(bundle);
            boolean occurException = false;
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e("ACTIVITY_NOT_FOUND", e.getMessage());
                occurException = true;
            }
            finally {
                if(occurException) {
                    openGooglePlay(id);
                }
            }
        }
        else {
            openGooglePlay(id);
        }
    }


    private void openGooglePlay(String id) {
        if(!openGooglePlayWithBaseUrl(id)) {
            if(!openGooglePlayWithMarketBaseUrl(id)) {

            }
            else {

            }
        }
    }

    private boolean openGooglePlayWithMarketBaseUrl(String id) {
        String url = String.format("market://details?id=%s", id);
        return BuildUtils.openGooglePlayWithMarketBaseUrl(getActivity(), url);
    }


    private boolean openGooglePlayWithBaseUrl(String id) {
        String url = String.format("https://play.google.com/store/apps/details?id=%s&hl=pt", id);
        return BuildUtils.openGooglePlayWithBaseUrl(getActivity(), url);
    }



    private void test(String baseUrl) {
        /**
         * Implementacao de metodo que busca versao do APP na página da google play
         * */
        BuildUtils.requestVersionCodeGooglePlay(new BuildUtils.AsyncRequestGooglePlayStore.Callback() {
            @Override
            public void execute(String version, String response) {
                if(version != null  && !version.equals(response)) {
                    /**
                     * TODO informar ao usuario que a versao do app dele esta desatualizada
                     * Podemos tentar abrir o google play
                     * */
                }
                else {
                    // Nao foi possivel
                }
            }
        }, baseUrl);
    }
}
