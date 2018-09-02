package task.sharedelement.winkl.winkltask.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import task.sharedelement.winkl.winkltask.MainActivity;
import task.sharedelement.winkl.winkltask.R;
import task.sharedelement.winkl.winkltask.adapter.ImagePagerAdapter;

import java.util.List;
import java.util.Map;

public class ImagePagerFragment extends Fragment {

  private ViewPager viewPager;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    viewPager = (ViewPager) inflater.inflate(R.layout.fragment_pager, container, false);
    viewPager.setAdapter(new ImagePagerAdapter(this));
    viewPager.setCurrentItem(MainActivity.currentPosition);
    viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageSelected(int position) {
        MainActivity.currentPosition = position;
      }
    });

    prepareSharedElementTransition();

    if (savedInstanceState == null) {
      postponeEnterTransition();
    }

    return viewPager;
  }

  private void prepareSharedElementTransition() {
    Transition transition =
        TransitionInflater.from(getContext())
            .inflateTransition(R.transition.image_shared_element_transition);
      Toast.makeText(getActivity().getApplicationContext(), "image_shared", Toast.LENGTH_SHORT).show();

      setSharedElementEnterTransition(transition);

    setEnterSharedElementCallback(
        new SharedElementCallback() {
          @Override
          public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {

            Fragment currentFragment = (Fragment) viewPager.getAdapter()
                .instantiateItem(viewPager, MainActivity.currentPosition);
            View view = currentFragment.getView();
            if (view == null) {
              return;
            }
            sharedElements.put(names.get(0), view.findViewById(R.id.image));
          }
        });
  }
}
