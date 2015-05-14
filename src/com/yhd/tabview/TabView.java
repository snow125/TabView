package com.yhd.tabview;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TabView extends LinearLayout{

	private Context context;
	private int tabCount = 1;
	private RelativeLayout[] midViews;
	private ImageView[] childViews;
	private String[] picSelector;
	private int midWidth;
	private OnClickListener[] onClicks;
	private int viewHeight;
	private int childWidth, childHeight;
	private String mainProjectPackageName;
	
	public TabView(Context context) {
		this(context, null, 0);
	}

	public TabView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TabView, defStyle, 0);
		int count = a.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = a.getIndex(i);
			if(attr == R.styleable.TabView_tab_count){
				tabCount = a.getInteger(attr, 1);
			}else if(attr == R.styleable.TabView_tab_pic_selector){
				picSelector = getResources().getStringArray(a.getResourceId(attr, 0));
			}else if(attr == R.styleable.TabView_tab_height){
				viewHeight = (int) a.getDimension(attr, 0);
			}else if(attr == R.styleable.TabView_item_width){
				childWidth = (int) a.getDimension(attr, 0);
			}else if(attr == R.styleable.TabView_item_height){
				childHeight = (int) a.getDimension(attr, 0);
			}
		}
		setParentView();
		addMidView();
		addChildView();
		a.recycle();
	}
	
	private void setParentView() {
		this.setOrientation(HORIZONTAL);
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		midViews = new RelativeLayout[tabCount];
		childViews = new ImageView[tabCount];
		onClicks = new OnClickListener[tabCount];
		midWidth = ScreenTool.getScreenWidth(context)/tabCount;
	}
	
	private void addMidView() {
		for (int i = 0; i < tabCount; i++) {
			midViews[i] = new RelativeLayout(context);
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(midWidth, viewHeight);
			midViews[i].setLayoutParams(lp);
			midViews[i].setGravity(Gravity.CENTER);
			addView(midViews[i]);
		}
	}
	
	@SuppressLint("NewApi")
	private void addChildView() {
		if(mainProjectPackageName != null){
			for (int i = 0; i < tabCount; i++) {
				childViews[i] = new ImageView(context);
				childViews[i].setBackgroundResource(getResId(picSelector[i]));
				childViews[i].setEnabled(true);
				childViews[i].setLayoutParams(new LayoutParams(childWidth, childHeight));
				midViews[i].addView(childViews[i]);
			}
		}
	}
	
	private int getResId(String res){
		Class<?> cls = null;
		Field f = null;
		Object obj = null;
		try {
			cls = Class.forName(mainProjectPackageName+"R$drawable");
			f = cls.getField(res);
			obj = f.get(cls);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return (Integer) obj;
	}

	public OnClickListener[] getOnClicks() {
		return onClicks;
	}

	public void setOnClicks(OnClickListener[] onClicks) {
		if(this.onClicks == onClicks){
			return;
		}
		this.onClicks = onClicks;
		for (int i = 0; i < tabCount; i++) {
			//设置监听函数 否则无点击效果
			childViews[i].setOnClickListener(onClicks[i]);
		}
	}

	public String getMainProjectPackageName() {
		return mainProjectPackageName;
	}

	public void setMainProjectPackageName(String mainProjectPackageName) {
		this.mainProjectPackageName = mainProjectPackageName;
		addChildView();
	}

}
