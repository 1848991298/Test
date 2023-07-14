package com.leows.testapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class VXActivity extends AppCompatActivity {
    PopupWindow mPopupWindow;
    int screenHeight;
    int screenWidth;
    int downX;
    int downY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vx);
        // 获取屏幕的高宽
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        ListView mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new CustomAdapter());
    }


    private void showPopupWindow(final View anchorView) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popup_content2_layout, null);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Click ", Toast.LENGTH_SHORT).show();
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu1_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu1_item2).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu1_item3).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu1_item4).setOnClickListener(menuItemOnClickListener);

//        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
//        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
//        contentView.findViewById(R.id.menu_item3).setOnClickListener(menuItemOnClickListener);
//        contentView.findViewById(R.id.menu_item4).setOnClickListener(menuItemOnClickListener);
//        contentView.findViewById(R.id.menu_item5).setOnClickListener(menuItemOnClickListener);
        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //计算View位置
        final int windowPos[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int popuHeight = contentView.getMeasuredHeight();
        final int popuWidth = contentView.getMeasuredWidth();
        // 判断Y坐标
        if (downY > screenHeight / 2) {
            //向上弹出
            windowPos[1] = downY - popuHeight;
        } else {
            //向下弹出
            windowPos[1] = downY;
        }
        // 判断X坐标
        if (downX > screenWidth / 2) {
            //向左弹出
            windowPos[0] = downX - popuWidth;
        } else {
            //向右弹出
            windowPos[0] = downX;
        }
        int xOff = -20; // 调整偏移
        windowPos[0] -= xOff;
        mPopupWindow.showAtLocation(anchorView, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 20;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.listview_item, null);
                viewHolder = new ViewHolder();
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.Root = convertView.findViewById(R.id.root);


            viewHolder.Root.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    downX = (int) motionEvent.getRawX();
                    downY = (int) motionEvent.getRawY();
                    return false;
                }
            });
            viewHolder.Root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showPopupWindow(viewHolder.Root);
                    return false;
                }
            });
            return convertView;
        }
    }


    class ViewHolder {
        View Root;
    }
}
