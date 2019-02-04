package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 *
 * {@link  android.support.v4.app.FragmentPagerAdapter}
 * */

public class ImplPagerAdapter <T> extends PagerAdapter {


    private List<T> elements;


    public ImplPagerAdapter(List<T> elements) {
        this.elements = elements;
    }
    

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
