package com.example.sokoban;

import com.example.sokoban.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class OptionActivity extends Activity {

	private Switch soundSwitch;
	private Switch vibsSwitch;
	private AudioManager mAudioManager;
	private boolean sound_flag = true;
	private boolean vibrator_flag = false;
	private int volume = 0;
	private int mode = 0;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = OptionActivity.this.getWindow();
		requestWindowFeature(window.FEATURE_NO_TITLE);
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_option);

		soundSwitch = (Switch) findViewById(R.id.op_sound_btn);
		vibsSwitch = (Switch) findViewById(R.id.op_vib_btn);

		Vibrator mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		soundSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				if (isChecked) {
					// ѡ��ʱ do some thing
					Toast.makeText(getApplicationContext(), "���þ����ɹ�",
							Toast.LENGTH_LONG).show();
					mAudioManager.setStreamMute(BIND_ADJUST_WITH_ACTIVITY,
							isChecked);
					sound_flag = false;
				} else {
					// ��ѡ��ʱ do some thing
					mAudioManager.setStreamMute(BIND_ADJUST_WITH_ACTIVITY,
							isChecked);
					Toast.makeText(getApplicationContext(), "������",
							Toast.LENGTH_LONG).show();
					sound_flag = true;

				}
			}
		});

		vibsSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// ѡ��ʱ do some thing
					Toast.makeText(getApplicationContext(), "����",
							Toast.LENGTH_LONG).show();
					vibrator_flag = true;

				} else {
					// ��ѡ��ʱ do some thing
					Toast.makeText(getApplicationContext(), "�ر���",
							Toast.LENGTH_LONG).show();
					vibrator_flag = false;
				}
			}
		});

//		if (sound_flag == true && vibrator_flag == true) {
//			// ����ģʽ
//			mAudioManager.setMode(AudioManager.RINGER_MODE_NORMAL);
//            volume=c_volume;
//		}
//		if (sound_flag == false && vibrator_flag == true) {
//			// ��ģʽ
//			mAudioManager.setMode(AudioManager.VIBRATE_SETTING_ON);
//		}
//		if (sound_flag == false && vibrator_flag == false) {
//			// ����ģʽ
//			mAudioManager.setMode(AudioManager.RINGER_MODE_SILENT);
//		}
//		if (sound_flag == true && vibrator_flag == false) {
//			// �ر���
//			mAudioManager.setMode(AudioManager.VIBRATE_SETTING_OFF);
//		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
