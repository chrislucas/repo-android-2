package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.callbacks;

public interface CallbackPageChange {
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    public void onPageSelected(int position);
    public void onPageScrollStateChanged(int state);
}
