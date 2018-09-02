package task.sharedelement.winkl.winkltask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import task.sharedelement.winkl.winkltask.fragment.ImageFragment;

import static task.sharedelement.winkl.winkltask.adapter.ImageData.IMAGE_DRAWABLES;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public ImagePagerAdapter(Fragment fragment) {
        super(fragment.getChildFragmentManager());
    }

    @Override
    public int getCount() {
        return IMAGE_DRAWABLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(IMAGE_DRAWABLES[position]);
    }
}