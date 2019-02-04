package viewpage.xplorer.com.xplorerviewpager.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import viewpage.xplorer.com.xplorerviewpager.R;
import viewpage.xplorer.com.xplorerviewpager.animation.viewpager.adapter.ImplFragmentStatePagerAdapter;
import viewpage.xplorer.com.xplorerviewpager.animation.viewpager.callbacks.impl.ImplCallbackPageChange;
import viewpage.xplorer.com.xplorerviewpager.animation.viewpager.listener.ImplOnPageListener;
import viewpage.xplorer.com.xplorerviewpager.animation.viewpager.transformer.ScaleViewPageTransformer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentWithViewPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentWithViewPage extends Fragment {


    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private PagerTitleStrip mPagerTitleStrip;
    private ViewPager.PageTransformer mPageTransformer;
    private ViewPager.OnPageChangeListener mPageChangeListener;


    private int pages;

    public FragmentWithViewPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment FragmentWithViewPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentWithViewPage newInstance(int pages) {
        FragmentWithViewPage fragment = new FragmentWithViewPage();
        fragment.pages = pages;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_with_view_page, container, false);

        mViewPager = view.findViewById(R.id.pager);
        mPagerTitleStrip = view.findViewById(R.id.title);
        mPageTransformer = new ScaleViewPageTransformer();

        List<Bundle> list = new ArrayList<>();

        Bundle bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 1);
        list.add(bundle);

        bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 2);
        list.add(bundle);

        bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 3);
        list.add(bundle);

        bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 4);
        list.add(bundle);

        bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 5);
        list.add(bundle);

        bundle = new Bundle();
        bundle.putInt(FragmentSample.DATA_ARG, 6);
        list.add(bundle);

        mPagerAdapter = new ImplFragmentStatePagerAdapter(getFragmentManager(), list);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setPageTransformer(true, mPageTransformer);
        mPageChangeListener = new ImplOnPageListener(new ImplCallbackPageChange());
        mViewPager.addOnPageChangeListener(mPageChangeListener);

        return view;
    }

}
