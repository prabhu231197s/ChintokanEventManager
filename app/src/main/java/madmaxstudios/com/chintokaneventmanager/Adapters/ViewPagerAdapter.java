package madmaxstudios.com.chintokaneventmanager.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import madmaxstudios.com.chintokaneventmanager.R;

/**
 * Created by Prabhu Sivanandam on 04-May-17.
 */

public class ViewPagerAdapter extends PagerAdapter {

    Context ctx;
    LayoutInflater inflater;
    int Resource[]={R.layout.photo1, R.layout.photo2,R.layout.photo3,R.layout.photo4};

    public ViewPagerAdapter(Context ctx) {
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return Resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(Resource[position],container,false);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
