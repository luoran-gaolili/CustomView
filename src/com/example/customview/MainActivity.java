package com.example.customview;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;




public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*ActionBar actionBar=getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		ComponentName componentName=new ComponentName(pkg, cls);
		new Intent().setComponent(componentName);
		
		TestFragmnet testFragmnet = new TestFragmnet();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.framelayout, testFragmnet).commit();*/
	}

}
