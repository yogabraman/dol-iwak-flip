package creative.station.dol_iwak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import app.num.numandroidpagecurleffect.PageCurlView;
import creative.station.dol_iwak.flip.CurlPage;
import creative.station.dol_iwak.flip.CurlView;

public class MainActivity extends AppCompatActivity {
    PageCurlView pageCurlView;
    List<Integer> images;
    private CurlView mCurlView;
    int index_page = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int index = 0;
        if (getLastNonConfigurationInstance() != null) {
            index = (Integer) getLastNonConfigurationInstance();
        }
        mCurlView = (CurlView) findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setCurrentIndex(index);
//        mCurlView.setBackgroundColor(0xFF202830);
        mCurlView.setBackgroundColor(Color.LTGRAY);

//        pageCurlView = findViewById(R.id.flipBook);
//        images = new ArrayList<>();
//
//        images.add(R.drawable.a);
//        images.add(R.drawable.b);
//        images.add(R.drawable.c);
//        images.add(R.drawable.d);
//        images.add(R.drawable.e);
//        images.add(R.drawable.f);
//        images.add(R.drawable.g);
//
//        pageCurlView.setCurlView(images);
//        pageCurlView.setCurlSpeed(10000);

//        Log.i("CurrentIndex", "page : "+mCurlView.getCurrentIndex());

        FloatingActionButton add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, mCurlView.getCurrentIndex()+"\n"+index_page, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurlView.onResume();
    }

//    @Override
//    public final Object onRetainNonConfigurationInstance() {
//        return mCurlView.getCurrentIndex();
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mCurlView.getCurrentIndex();
    }

    /**
     * Bitmap provider.
     */
    private class PageProvider implements CurlView.PageProvider {

        // Bitmap resources.
        private int[] mBitmapIds = {
                R.layout.activity_first,
                R.layout.activity_second,
                R.layout.activity_third,
                R.layout.activity_fourth,
                R.layout.activity_fifth,
                R.layout.activity_sixth};

        @Override
        public int getPageCount() {
            return mBitmapIds.length;
        }

        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {
//            if (index == mBitmapIds.length-1){
//                Bitmap front = loadBitmap(width, height, 0);
//                page.setTexture(front, CurlPage.SIDE_BACK);
//            }

            index_page = index;
            Bitmap front = loadBitmap(width, height, index);
            page.setTexture(front, CurlPage.SIDE_BOTH);

//            switch (index) {
//                // First case is image on front side, solid colored back.
//                case 0: {
//                    Bitmap front = loadBitmap(width, height, 0);
//                    page.setTexture(front, CurlPage.SIDE_BOTH);
////                    page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
//                    break;
//                }
//                // Second case is image on back side, solid colored front.
//                case 1: {
//                    Bitmap back = loadBitmap(width, height, 1);
//                    page.setTexture(back, CurlPage.SIDE_BOTH);
////                    page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
//                    break;
//                }
//                // Third case is images on both sides.
//                case 2: {
//                    Bitmap front = loadBitmap(width, height, 2);
//                    Bitmap back = loadBitmap(width, height, 3);
//                    page.setTexture(front, CurlPage.SIDE_BOTH);
////                    page.setTexture(back, CurlPage.SIDE_BACK);
//                    break;
//                }
//                // Fourth case is images on both sides - plus they are blend against
//                // separate colors.
//                case 3: {
//                    Bitmap front = loadBitmap(width, height, 3);
//                    Bitmap back = loadBitmap(width, height, 1);
//                    page.setTexture(front, CurlPage.SIDE_BOTH);
////                    page.setTexture(back, CurlPage.SIDE_BACK);
////                    page.setColor(Color.argb(127, 170, 130, 255),
////                            CurlPage.SIDE_FRONT);
////                    page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
//                    break;
//                }
//                // Fifth case is same image is assigned to front and back. In this
//                // scenario only one texture is used and shared for both sides.
//                case 4:
//                    Bitmap front = loadBitmap(width, height, 5);
//                    page.setTexture(front, CurlPage.SIDE_BOTH);
////                    page.setColor(Color.argb(127, 255, 255, 255),
////                            CurlPage.SIDE_BACK);
//                    break;
//            }

        }

        private Bitmap loadBitmap(int width, int height, int index) {
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(mBitmapIds[index],null);

            Log.i("CurrentLayout", getResources().getResourceEntryName(mBitmapIds[index]));

//            View v = inflater.inflate(R.layout.activity_first,null);
//            TextView jumboPrice = v.findViewById(R.id.jumboPrice);
//            jumboPrice.setText("990000");

            if(getResources().getResourceEntryName(mBitmapIds[index]).contains("first")){
                TextView jumboPrice = v.findViewById(R.id.jumboPrice);
                jumboPrice.setText("990000");
            }

            v.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(),Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.draw(c);
            return b;
        }

    }

    /**
     * CurlView size changed observer.
     */
    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
                mCurlView.setMargins(.0f, .0f, .0f, .0f);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
                mCurlView.setMargins(.0f, .0f, .0f, .0f);
            }
        }
    }
}