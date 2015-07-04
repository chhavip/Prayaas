package helper;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.chhavi.prayaas.AllEventsFragment;
import com.example.chhavi.prayaas.MyEventsFragment;

/**
 * Created by shikharkhetan on 6/19/15.
 */
public class PrayaasFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public PrayaasFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position)   {
            case 0:
                fragment = new AllEventsFragment();
                break;
            case 1:
                fragment = new MyEventsFragment();
                break;

        }
        return fragment;

    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "All Events";
               // break;
            case 1:
                return "My Events";
            //     break;
        }

        return "Events";


    }
}
