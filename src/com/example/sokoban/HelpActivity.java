package com.example.sokoban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class HelpActivity extends Activity {
	private int step = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_help);
		final ImageView setp_1 = (ImageView) findViewById(R.id.step_1);
		final ImageView setp_2 = (ImageView) findViewById(R.id.step_2);
		final ImageView setp_3 = (ImageView) findViewById(R.id.step_3);
		final ImageView setp_4 = (ImageView) findViewById(R.id.step_4);
		final Button btn_next = (Button) findViewById(R.id.btn_next);
		setp_2.setVisibility(View.INVISIBLE);
		setp_3.setVisibility(View.INVISIBLE);
		setp_4.setVisibility(View.INVISIBLE);

		btn_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (step == 0) {
					setp_1.setVisibility(View.INVISIBLE);
					setp_2.setVisibility(View.VISIBLE);
				}
				if (step == 1) {
					setp_2.setVisibility(View.INVISIBLE);
					setp_3.setVisibility(View.VISIBLE);
				}
				if (step == 2) {
					setp_3.setVisibility(View.INVISIBLE);
					setp_4.setVisibility(View.VISIBLE);
				}
				if (step == 3) {
					Intent intent = new Intent();
					intent.setClass(HelpActivity.this, GameActivity.class);
					startActivity(intent);
					finish();
				}
				step++;
			}
		});
	}
}
