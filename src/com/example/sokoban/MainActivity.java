package com.example.sokoban;

import com.example.sokoban.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 主界面的button
	private Button startButton;
	private Button optionButton;
	private Button helpButton;
	private Button exitButton;
	// 菜单
	private static int FIRST = Menu.FIRST;
	private static int SECOND = Menu.FIRST + 1;
	private static int THREE = Menu.FIRST + 2;
	private static int FOUR = Menu.FIRST + 3;

	private static boolean isExit = false;
	private static Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 设置全屏
		Window window = MainActivity.this.getWindow();
		requestWindowFeature(window.FEATURE_NO_TITLE);
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.Btn_Start);
		optionButton = (Button) findViewById(R.id.Btn_Option);
		helpButton = (Button) findViewById(R.id.Btn_Help);
		exitButton = (Button) findViewById(R.id.Btn_Exit);

		// 退出按钮点击事件
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 新建一个对话框提示用户
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("提示")
						.setMessage("确认退出吗?")
						// 点击确认关闭对话框
						.setPositiveButton("确认",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
										MainActivity.this.finish();
									}
								})
						// 点击取消返回
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
									}
								}).create().show();
			}
		});

		// 开始游戏按钮
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), GameActivity.class);
				startActivity(intent);

			}
		});

		// 设置按钮
		optionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), OptionActivity.class);
				startActivity(intent);
			}
		});
	}

	/*
	 * 设置菜单
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, FIRST, 1, "设置");
		menu.add(0, SECOND, 2, "帮助");
		menu.add(1, THREE, 3, "关于");
		menu.add(1, FOUR, 4, "退出");
		return true;
	}

	/*
	 * 监控菜单点击事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST:
			Toast.makeText(getApplicationContext(), "打开设置界面",
					Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 1:
			Toast.makeText(getApplicationContext(), "打开帮助界面",
					Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 2:
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("关于我们")
					.setMessage(
							"推箱子小游戏" + "\n" + "制作者: 李钊&邱俊森" + "\n"
									+ "项目地址https://github.com/Domonlee/Sokoban"
									+ "\n" + "联系方式:viplizhao@gmail.com " + "\n"
									+ "qiujunsen@163.com")
					.setPositiveButton("返回",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).create().show();
			break;
		case Menu.FIRST + 3: {
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("提示")
					.setMessage("确认退出吗?")
					.setPositiveButton("确认",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									MainActivity.this.finish();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).create().show();
		}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * 连续两次退出键 退出程序
	 */
	@Override
	public void onBackPressed() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_LONG).show();
			// 2s判定
			handler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

}
