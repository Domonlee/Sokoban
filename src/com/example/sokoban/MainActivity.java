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
	// �������button
	private Button startButton;
	private Button optionButton;
	private Button helpButton;
	private Button exitButton;
	// �˵�
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

		// ����ȫ��
		Window window = MainActivity.this.getWindow();
		requestWindowFeature(window.FEATURE_NO_TITLE);
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);

		startButton = (Button) findViewById(R.id.Btn_Start);
		optionButton = (Button) findViewById(R.id.Btn_Option);
		helpButton = (Button) findViewById(R.id.Btn_Help);
		exitButton = (Button) findViewById(R.id.Btn_Exit);

		// �˳���ť����¼�
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// �½�һ���Ի�����ʾ�û�
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("��ʾ")
						.setMessage("ȷ���˳���?")
						// ���ȷ�ϹرնԻ���
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										dialog.dismiss();
										MainActivity.this.finish();
									}
								})
						// ���ȡ������
						.setNegativeButton("ȡ��",
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

		// ��ʼ��Ϸ��ť
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), GameActivity.class);
				startActivity(intent);

			}
		});

		// ���ð�ť
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
	 * ���ò˵�
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, FIRST, 1, "����");
		menu.add(0, SECOND, 2, "����");
		menu.add(1, THREE, 3, "����");
		menu.add(1, FOUR, 4, "�˳�");
		return true;
	}

	/*
	 * ��ز˵�����¼�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST:
			Toast.makeText(getApplicationContext(), "�����ý���",
					Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 1:
			Toast.makeText(getApplicationContext(), "�򿪰�������",
					Toast.LENGTH_SHORT).show();
			break;
		case Menu.FIRST + 2:
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("��������")
					.setMessage(
							"������С��Ϸ" + "\n" + "������: ����&��ɭ" + "\n"
									+ "��Ŀ��ַhttps://github.com/Domonlee/Sokoban"
									+ "\n" + "��ϵ��ʽ:viplizhao@gmail.com " + "\n"
									+ "qiujunsen@163.com")
					.setPositiveButton("����",
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
					.setTitle("��ʾ")
					.setMessage("ȷ���˳���?")
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
									MainActivity.this.finish();
								}
							})
					.setNegativeButton("ȡ��",
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
	 * ���������˳��� �˳�����
	 */
	@Override
	public void onBackPressed() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
					Toast.LENGTH_LONG).show();
			// 2s�ж�
			handler.sendEmptyMessageDelayed(0, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

}
