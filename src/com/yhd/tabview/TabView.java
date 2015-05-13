package com.yhd.tabview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TabView extends LinearLayout{

	private Context context;
	private int tabCount;
	private RelativeLayout[] midViews;
	private ImageView[] childViews;
	private String[] picNormal;
	private String[] picSelected;
	private int itemWidth;
	
	public TabView(Context context) {
		this(context, null, 0);
	}

	public TabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.tab_view, defStyle, 0);
		int count = a.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.tab_view_tab_count:
				tabCount = a.getInteger(attr, 0);
				break;
			case R.styleable.tab_view_tab_pic_normal:
				picNormal = getResources().getStringArray(a.getResourceId(attr, 0));	
				break;
			case R.styleable.tab_view_tab_pic_selected:
				picSelected = getResources().getStringArray(a.getResourceId(attr, 0));
				break;
			}
		}
		setParentView();
		addMidView();
		addChildView();
		a.recycle();
	}
	
	private void setParentView() {
		this.setOrientation(HORIZONTAL);
		midViews = new RelativeLayout[tabCount];
		childViews = new ImageView[tabCount];
		itemWidth = ScreenTool.getScreenWidth(context)/tabCount;
	}
	
	private void addMidView() {
		for (int i = 0; i < tabCount; i++) {
			midViews[i] = new RelativeLayout(context);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(itemWidth, android.widget.RelativeLayout.LayoutParams.MATCH_PARENT);
			midViews[i].setLayoutParams(lp);
			addView(midViews[i]);
		}
	}
	
	@SuppressLint("NewApi")
	private void addChildView() {
		for (int i = 0; i < tabCount; i++) {
			childViews[i] = new ImageView(context);
			Log.e("123", ""+picNormal[i]);
			childViews[i].setBackgroundResource(getResId(picNormal[i]));
			midViews[i].addView(childViews[i]);
		}
	}
	
	private int getResId(String res){
		return 0x7f020003;
	}

}
