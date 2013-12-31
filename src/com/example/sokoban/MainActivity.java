package com.example.sokoban;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	// �������button
	private Button startButton;
	private Button optionButton;
	private Button helpButton;
	private Button exitButton;

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
