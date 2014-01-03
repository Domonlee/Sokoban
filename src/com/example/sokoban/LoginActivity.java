package com.example.sokoban;

import com.example.sokoban.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class LoginActivity extends Activity {

	private final int LOGIN_TIME = 1500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = LoginActivity.this.getWindow();
		// 取消标题
		requestWindowFeature(window.FEATURE_NO_TITLE);
		// 设置全屏
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_login);

		// 延迟3s打开主界面
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, LOGIN_TIME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
