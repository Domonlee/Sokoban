package com.example.sokoban;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
	int counter = 0;
	int[][] matrix = new int[8][11];
	int people_x, people_y;
	int level, money;
	int step = 0;
	final ImageView[][] imageViews = new ImageView[8][11];
	SharedPreferences mPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		level = mPreferences.getInt("level", 0);
		money = mPreferences.getInt("money", 100);
		super.onCreate(savedInstanceState);
		// res用来加载关卡数据 箱子坐标 围墙坐标 人物坐标 目的坐标
		Resources res = getResources();
		final String[] myString = res.getStringArray(R.array.my_array);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game);
		int k = 0;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 11; j++) {
				imageViews[i][j] = (ImageView) findViewById(R.id.ImageView0 + k);
				k++;
			}
		initMap(myString);
		final TextView btn_step = (TextView) findViewById(R.id.step_text);
		final TextView timeView = (TextView) findViewById(R.id.time_text);
		final Button btn_up = (Button) findViewById(R.id.btn_up);
		final Button btn_down = (Button) findViewById(R.id.btn_down);
		final Button btn_right = (Button) findViewById(R.id.btn_right);
		final Button btn_left = (Button) findViewById(R.id.btn_left);
		final Button btn_restrat = (Button) findViewById(R.id.restartBtn);
		
		
		btn_restrat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(GameActivity.this)
						.setTitle("提示")
						.setMessage("你想重新开始本关还是开始新游戏?")
						// 点击确认关闭对话框
						.setPositiveButton("重新本关",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
										Intent intent = new Intent();
										intent.setClass(GameActivity.this, GameActivity.class);
										startActivity(intent);
										finish();
									}
								})
						// 点击取消返回
						.setNegativeButton("新游戏",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										SharedPreferences.Editor editor = mPreferences
												.edit();
										editor.putInt("level", 0); // 更新Level
										editor.putInt("money", 100);
										editor.commit();
										Intent intent = new Intent();
										intent.setClass(GameActivity.this,
												GameActivity.class);
										startActivity(intent);
										finish();
										dialog.dismiss();
									}
								}).create().show();
			}
		});

		btn_up.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (matrix[people_x - 1][people_y] == 1) {
					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x - 1][people_y]
							.setImageResource(R.drawable.r);
					matrix[people_x][people_y] = 1;
					people_x--;
					matrix[people_x][people_y] = 4;
					btn_step.setText(++step + "步");
				} else if ((matrix[people_x - 1][people_y] == 2)
						&& (matrix[people_x - 2][people_y] == 1)) {

					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x - 1][people_y]
							.setImageResource(R.drawable.r);
					imageViews[people_x - 2][people_y]
							.setImageResource(R.drawable.xz);
					matrix[people_x][people_y] = 1;
					people_x--;
					matrix[people_x][people_y] = 4;
					matrix[people_x - 1][people_y] = 2;
					if (checkPassOrNot(myString))
						nextLevel(myString);
					btn_step.setText(++step + "步");
				}

			}
		});
		btn_down.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (matrix[people_x + 1][people_y] == 1) {
					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x + 1][people_y]
							.setImageResource(R.drawable.r);
					matrix[people_x][people_y] = 1;
					people_x++;
					matrix[people_x][people_y] = 4;
					btn_step.setText(++step + "步");
				} else if ((matrix[people_x + 1][people_y] == 2)
						&& (matrix[people_x + 2][people_y] == 1)) {

					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x + 1][people_y]
							.setImageResource(R.drawable.r);
					imageViews[people_x + 2][people_y]
							.setImageResource(R.drawable.xz);
					matrix[people_x][people_y] = 1;
					people_x++;
					matrix[people_x][people_y] = 4;
					matrix[people_x + 1][people_y] = 2;
					if (checkPassOrNot(myString))
						nextLevel(myString);
					btn_step.setText(++step + "步");
				}

			}
		});
		btn_right.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (matrix[people_x][people_y + 1] == 1) {
					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y + 1]
							.setImageResource(R.drawable.r);
					matrix[people_x][people_y] = 1;
					people_y++;
					matrix[people_x][people_y] = 4;
					btn_step.setText(++step + "步");
				} else if ((matrix[people_x][people_y + 1] == 2)
						&& (matrix[people_x][people_y + 2] == 1)) {

					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y + 1]
							.setImageResource(R.drawable.r);
					imageViews[people_x][people_y + 2]
							.setImageResource(R.drawable.xz);
					matrix[people_x][people_y] = 1;
					people_y++;
					matrix[people_x][people_y] = 4;
					matrix[people_x][people_y + 1] = 2;
					if (checkPassOrNot(myString))
						nextLevel(myString);
					btn_step.setText(++step + "步");
				}

			}
		});
		btn_left.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (matrix[people_x][people_y - 1] == 1) {
					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y - 1]
							.setImageResource(R.drawable.r);
					matrix[people_x][people_y] = 1;
					people_y--;
					matrix[people_x][people_y] = 4;
					btn_step.setText(++step + "步");
				} else if ((matrix[people_x][people_y - 1] == 2)
						&& (matrix[people_x][people_y - 2] == 1)) {

					imageViews[people_x][people_y]
							.setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y - 1]
							.setImageResource(R.drawable.r);
					imageViews[people_x][people_y - 2]
							.setImageResource(R.drawable.xz);
					matrix[people_x][people_y] = 1;
					people_y--;
					matrix[people_x][people_y] = 4;
					matrix[people_x][people_y - 1] = 2;
					if (checkPassOrNot(myString))
						nextLevel(myString);
					btn_step.setText(++step + "步");
				}
			}
		});

		MyHandler handler = new MyHandler(timeView);
		MyThread thread = new MyThread(handler);
		new Thread(thread).start();

	}

	public class MyHandler extends Handler {
		private TextView textView;

		public MyHandler(TextView tv) {
			this.textView = tv;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			textView.setText(bundle.getString("time") + "S");
		}

	}

	public class MyThread implements Runnable {

		MyHandler handler;

		public MyThread(MyHandler handler) {
			this.handler = handler;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while (true) {
				try {
					Thread.sleep(1000);
					counter++;
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				Message msg = new Message();
				Bundle bundle = new Bundle();
				bundle.putString("time", String.valueOf(counter));
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
		}

	}

	private boolean checkPassOrNot(String[] myStrings) {
		char[] c = myStrings[level + 2].toCharArray();
		for (int i = 0; i < myStrings[level + 2].length(); i++) {
			int x = c[i++] - 48;
			int y = c[i] - 48;
			if (matrix[x][y] != 2)
				return false;
		}
		Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		long[] pattern = { 100, 400, 100, 400 };
		vib.vibrate(pattern, -1);
		return true;
	}

	private void nextLevel(String[] mystring) {
		if ((level / 4) > (mystring.length) / 4 - 2)
			DisplayToast("恭喜通关");
		else {
			SharedPreferences.Editor editor = mPreferences.edit();
			editor.putInt("level", level + 4); // 更新Level
			editor.putInt("money", money + 10);
			editor.commit();
			Intent intent = new Intent();
			intent.setClass(GameActivity.this, GameActivity.class);
			startActivity(intent);
			finish();
		}
	}

	public void DisplayToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}

	private void initMap(String[] myString) {
		// 0代表围墙 1代表路 2代表箱子 1代表目的地 4代表人
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 11; j++)
				matrix[i][j] = 1;
		// 围墙坐标
		char[] c = myString[level].toCharArray();
		for (int i = 0; i < myString[level].length(); i++) {
			int x = c[i++] - 48;
			int y = c[i] - 48;
			imageViews[x][y].setBackgroundResource(R.drawable.q);
			matrix[x][y] = 0;
		}
		// 箱子坐标
		c = myString[level + 1].toCharArray();
		for (int i = 0; i < myString[level + 1].length(); i++) {
			int x = c[i++] - 48;
			int y = c[i] - 48;
			imageViews[x][y].setImageResource(R.drawable.xz);
			matrix[x][y] = 2;
		}
		// 目的地坐标
		c = myString[level + 2].toCharArray();
		for (int i = 0; i < myString[level + 2].length(); i++) {
			int x = c[i++] - 48;
			int y = c[i] - 48;
			imageViews[x][y].setBackgroundResource(R.drawable.m);
			matrix[x][y] = 1;
		}
		// 人坐标
		c = myString[level + 3].toCharArray();
		for (int i = 0; i < myString[level + 3].length(); i++) {
			people_x = c[i++] - 48;
			people_y = c[i] - 48;
			imageViews[people_x][people_y].setImageResource(R.drawable.r);
			matrix[people_x][people_y] = 4;
		}
	}

}
