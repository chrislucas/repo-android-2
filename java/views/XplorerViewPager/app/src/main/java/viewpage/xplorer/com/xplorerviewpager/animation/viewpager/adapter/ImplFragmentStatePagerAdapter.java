package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import viewpage.xplorer.com.xplorerviewpager.fragments.FragmentSample;

public class ImplFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    private List<Bundle> elements;

    public ImplFragmentStatePagerAdapter(FragmentManager fm, List<Bundle> elements) {
        super(fm);
        this.elements = elements;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = elements.get(position);
        FragmentSample fs = FragmentSample.newInstance();
        fs.setArguments(bundle);
        return fs;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Bundle bundle = elements.get(position);
        return super.getPageTitle(position);
    }
}
