package com.example.customview;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class CustomView extends View {

	private String mCustomTitle;
	private String mCustonSize;
	private int mCustomColor;
	private String customText;
	private int customColor;
	private int custonSize;
	private Paint paint;
	private Rect mBound;

	public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTitleView, defStyleAttr, 0);
		int count = a.getIndexCount();
		Log.d("LUORAN", "****" + count);
		for (int i = 0; i < count; i++) {
			int index = a.getIndex(i);
			Log.d("LUORAN", "****" + index);
			switch (index) {
			case R.styleable.CustomTitleView_titleText:
				customText = a.getString(index);
				break;
			case R.styleable.CustomTitleView_titleColor:
				customColor = a.getColor(index, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleSize:
				custonSize = a.getDimensionPixelSize(index, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
								getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}

		}
		a.recycle();
		paint = new Paint();
		paint.setTextSize(custonSize);
		mBound = new Rect();
		paint.getTextBounds(customText, 0, customText.length(), mBound);
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				customText=getRandomText();
				postInvalidate();
				
			}
		});
	}
    //点击获取随机数
	protected String getRandomText() {
		// TODO Auto-generated method stub
		
		Set<Integer> randomSet=new HashSet<Integer>();
		
		Random random=new Random();
		while(randomSet.size()<4){
			randomSet.add(random.nextInt(10));
			
		}
		StringBuffer stringBuffer=new StringBuffer();
		for(Integer i:randomSet){
			stringBuffer.append(i);
			
		}
		return stringBuffer.toString();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			//paint.measureText(customText)相对于mBound.getWidth()获取文本的宽度更加精确
			float textWidth = paint.measureText(customText);
			int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
			width = desired;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			//获取绘制文本的高度
			FontMetrics fontMetrics=paint.getFontMetrics();
			float quaHeight=Math.abs((fontMetrics.bottom-fontMetrics.top));
			
			float textHeight = quaHeight;
			int desired = (int) (getPaddingTop() + textHeight + getPaddingBottom());
			height = desired;
		}

		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		
		
		FontMetricsInt fontMetricsInt=paint.getFontMetricsInt();
		int startY=getHeight()/2-fontMetricsInt.descent+(fontMetricsInt.bottom-fontMetricsInt.top)/2;

		paint.setColor(Color.YELLOW);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
        //这个地方有一个坑，虽然我们设置了wrap_content,,但是如果我们不设置padding值的话，会发现文本根本就没有绘制的很完整。
		paint.setColor(customColor);
		
		canvas.drawText(customText, getWidth() / 2 - paint.measureText(customText) / 2,
				startY, paint);
		/*paint.setColor(Color.BLACK);
		canvas.drawPoint(getWidth()/3, getHeight()/3, paint);*/
	}

}
