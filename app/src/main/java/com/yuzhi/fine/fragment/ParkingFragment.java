package com.yuzhi.fine.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuzhi.fine.A_star.AStar2;
import com.yuzhi.fine.R;
import com.yuzhi.fine.ui.pulltorefresh.PullToRefreshListFragment;
import com.yuzhi.fine.ui.tabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Sinyu on 2017/10/25.
 */

public class ParkingFragment extends Fragment {
    private EditText edit_query;
    private Button bt_query,bt_clear;
    ImageView img_map,ImageView02;
    FrameLayout frameLayout;
    private int start_x, start_y,end_x,end_y;
    private AbsoluteLayout absolute;
    Bitmap mBitmap;
    int[][] arrs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_parking, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        getPixColor();
    }
    private void initView(View view){
        edit_query= (EditText) view.findViewById(R.id.edit_query);
        bt_query= (Button) view.findViewById(R.id.bt_query);
        bt_clear= (Button) view.findViewById(R.id.bt_clear);
        img_map= (ImageView) view.findViewById(R.id.img_map);
        ImageView02= (ImageView) view.findViewById(R.id.ImageView02);
        absolute= (AbsoluteLayout) view.findViewById(R.id.absolute);
        frameLayout= (FrameLayout) view.findViewById(R.id.frameLayout);
        bt_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endPoint=edit_query.getText().toString().trim();
                if (endPoint.isEmpty())
                    return;
                //getPixColor();
                startA_star_2();
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absolute.removeAllViews();
                absolute.addView(frameLayout);
//                frameLayout.addView(img_map);
//                frameLayout.addView(ImageView02);
                getPixColor();
                isStart=true;
            }
        });
        ImageView02.setOnTouchListener(imgSourceOnTouchListener);
    }
    public void getPixColor(){
        Bitmap src =  BitmapFactory.decodeResource(getResources(),R.drawable.testmap1);
        Bitmap image =  BitmapFactory.decodeResource(getResources(),R.drawable.testmap);

        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);//创建资源文件图片大小的图片，返回mutable

        arrs=new int[height][width];//h行 w列

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixelColor = src.getPixel(x, y);
                mBitmap.setPixel(x, y, 00000000);  //给mutable的Bitmap设置像素点，使其显示成资源文件里面的图片
                A = Color.alpha(pixelColor);
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);
                if (R==255&&G==255&&B==255)
                {
                    //Log.e("ARGB:", A+"-"+R+"-"+G+"-"+B+"");
                    arrs[y][x]=0;
                }else {
                    arrs[y][x]=1;
                }
            }
        }
        img_map.setImageBitmap(image);
        ImageView02.setImageBitmap(mBitmap);

    }
    private void startA_star_2(){
        AStar2 aStar2=new AStar2();

//        int[][] arrsnew=new int[arrs.length/3][arrs[0].length/3];//h行 w列
//        int i =0,j=0;
//        for (int y = 0; y < arrs.length&&i<arrs.length/3; y+=3) {
//            for (int x = 0; x < arrs[0].length&&j<arrs[0].length/3; x+=3) {
//                boolean isOne=false;
//                for(int y1=y;y1<3;y1++)
//                    for(int x1=x;x1<3;x1++){
//                        try {
//                            if (arrs[y1][x1]==1){
//                                arrsnew[i][j]=1;
//                                isOne=true;
//                                break;
//                            }
//                        }catch (Exception e){
//                            //超越边界
//                        }
//
//                    }
//                if (!isOne)
//                    arrsnew[i][j]=0;
//                j++;
//            }
//            i++;
//        }
        //ArrayList<AStar2.Node>  arrayList=aStar2.start(arrsnew, start_x/3, start_y/3, end_x/3, end_y/3);

        ArrayList<AStar2.Node> arrayList=aStar2.start(arrs, start_x, start_y, end_x, end_y);

        for (AStar2.Node n : arrayList){
            int x=n.x;
            int y=n.y;
            mBitmap.setPixel(y, x, Color.RED);
//            mBitmap.setPixel(y, x+1, Color.RED);
//            mBitmap.setPixel(y, x-1, Color.RED);
//            mBitmap.setPixel(y+1, x, Color.RED);
//            mBitmap.setPixel(y-1, x, Color.RED);

//            mBitmap.setPixel(y, x+2, Color.RED);
//            mBitmap.setPixel(y, x-2, Color.RED);
//            mBitmap.setPixel(y+2, x, Color.RED);
//            mBitmap.setPixel(y-2, x, Color.RED);
//            double x=n.y/biliX;
//            double y=n.x/biliY;
//            ImageView iv=new ImageView(ParkingFragment.this.getActivity());
//            iv.setLayoutParams(new LinearLayout.LayoutParams(5,5));
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            //iv.setScaleType(ImageView.ScaleType.CENTER);//居中显示
//            iv.setImageResource(R.drawable.point);
//            iv.setX((int)x);
//            iv.setY((int)y);
//            absolute.addView(iv);//添加iv
        }
        ImageView02.setImageBitmap(mBitmap);
    }
    boolean isStart=true;
    double biliX,biliY;
    View.OnTouchListener imgSourceOnTouchListener = new View.OnTouchListener()
    {

        @Override
        public boolean onTouch(View view, MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();
            float[] eventXY = new float[] {eventX, eventY};

            Matrix invertMatrix = new Matrix();
            ((ImageView)view).getImageMatrix().invert(invertMatrix);

            invertMatrix.mapPoints(eventXY);
            int x = Integer.valueOf((int)eventXY[0]);
            int y = Integer.valueOf((int)eventXY[1]);
            biliX=y/eventX;
            biliY=x/eventY;

//            touchedXY.setText(
//                    "touched position: "
//                            + String.valueOf(eventX) + " / "
//                            + String.valueOf(eventY));

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if (isStart){
                        start_x=y;
                        start_y =x;
                        isStart=false;
                        ImageView iv=new ImageView(ParkingFragment.this.getActivity());
                        iv.setLayoutParams(new LinearLayout.LayoutParams(50,50));
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        //iv.setScaleType(ImageView.ScaleType.CENTER);//居中显示
                        iv.setImageResource(R.drawable.startpoint);
                        iv.setX(eventX-20);
                        iv.setY(eventY-20);
                        absolute.addView(iv);//添加iv
                        //setContentView(absolute);//显示manLayout

                    }else {
                        end_x=y;
                        end_y =x;
                        //isStart=true;
                        ImageView iv=new ImageView(ParkingFragment.this.getActivity());
                        iv.setLayoutParams(new LinearLayout.LayoutParams(50,50));
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        //iv.setScaleType(ImageView.ScaleType.CENTER);//居中显示
                        iv.setImageResource(R.drawable.endpoint);
                        iv.setX(eventX-20);
                        iv.setY(eventY-20);
                        absolute.addView(iv);//添加iv
                    }

                    break;
            }


            edit_query.setText(
                    "touched 像素: "
                            + String.valueOf(x) + " / "
                            + String.valueOf(y));


            Drawable imgDrawable = ((ImageView)view).getDrawable();
            Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();

//            imgSize.setText(
//                    "drawable size: "
//                            + String.valueOf(bitmap.getWidth()) + " / "
//                            + String.valueOf(bitmap.getHeight()));

            //Limit x, y range within bitmap
            if(x < 0)
            {
                x = 0;
            }
            else if(x > bitmap.getWidth()-1)
            {
                x = bitmap.getWidth()-1;
            }

            if(y < 0)
            {
                y = 0;
            }
            else if(y > bitmap.getHeight()-1)
            {
                y = bitmap.getHeight()-1;
            }

            int touchedRGB = bitmap.getPixel(x, y);

            //edit_query.setText("touched color: " + "#" + Integer.toHexString(touchedRGB));
//            colorRGB.setTextColor(touchedRGB);

            return true;
        }
    };
}
