package viewpage.xplorer.com.xplorerviewpager.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import viewpage.xplorer.com.xplorerviewpager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSample extends Fragment {


    public static final String BUNDLE_ARGS = "BUNDLE_ARGS";
    public static final String DATA_ARG = "DATA_ARG";

    public FragmentSample() {
        // Required empty public constructor
    }


    public static FragmentSample newInstance() {
        return new FragmentSample();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args != null) {
                int data = args.getInt(BUNDLE_ARGS);
                ( (TextView) view.findViewById(R.id.text)).setText(String.format(Locale.getDefault(), "Pagina %d", data));
            }
        }
        return view;
    }

}
