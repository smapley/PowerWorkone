package com.okay.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.okay.R;
import com.okay.adapter.RefreshAdapter;
import com.yalantis.taurus.PullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";
    private final String TAG = "ContentFragment";

    private View containerView;
    protected int res;
    private Bitmap bitmap;
    public static final int REFRESH_DELAY = 4000;

    private PullToRefreshView mPullToRefreshView;
    private static Context mcontext;

    public static ContentFragment newInstance(int resId, Context context) {
        mcontext = context;
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }


    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    private void initView(View courentView) {

        HashMap<String, Integer> map;
        ArrayList<HashMap<String, Integer>> sampleList = new ArrayList<>();


        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        int[] tripNames = {
                R.string.trip_to_india,
                R.string.trip_to_italy,
                R.string.trip_to_indonesia};

        for (int i = 0; i < tripNames.length; i++) {
            map = new HashMap<>();
            map.put(RefreshAdapter.KEY_NAME, tripNames[i]);
            map.put(RefreshAdapter.KEY_COLOR, colors[i]);
            sampleList.add(map);
        }

        ListView listView = (ListView) courentView.findViewById(R.id.list_view);
        listView.setAdapter(new RefreshAdapter(mcontext, sampleList));


        mPullToRefreshView = (PullToRefreshView) courentView.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
            }
        });

    }

   
}

