package com.example.sokoban;

import com.example.sokoban.R;
import com.example.sokoban.Util.AudioUtil;

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

	private Switch Bg_soundSwitch;
	private Switch soundSwitch;
	private AudioManager mAudioManager;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Window window = OptionActivity.this.getWindow();
		requestWindowFeature(window.FEATURE_NO_TITLE);
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_option);

		Bg_soundSwitch = (Switch) findViewById(R.id.op_sound_btn);
		soundSwitch = (Switch) findViewById(R.id.op_vib_btn);

		Bg_soundSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(), "关闭背景音乐",
							Toast.LENGTH_LONG).show();
					AudioUtil.StopMusic();
				} else {
					Toast.makeText(getApplicationContext(), "打开背景音乐",
							Toast.LENGTH_LONG).show();
					AudioUtil.init(getApplicationContext());
					AudioUtil.PlayMusic();
				}
			}
		});

		soundSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Toast.makeText(getApplicationContext(), "打开音效",
							Toast.LENGTH_LONG).show();
					AudioUtil.setSoundRunning(true);
				} else {
					Toast.makeText(getApplicationContext(), "关闭音效",
							Toast.LENGTH_LONG).show();
					AudioUtil.setSoundRunning(false);
				}
			}
		});

//		if (sound_flag == true && vibrator_flag == true) {
//			// 铃声模式
//			mAudioManager.setMode(AudioManager.RINGER_MODE_NORMAL);
//            volume=c_volume;
//		}
//		if (sound_flag == false && vibrator_flag == true) {
//			// 震动模式
//			mAudioManager.setMode(AudioManager.VIBRATE_SETTING_ON);
//		}
//		if (sound_flag == false && vibrator_flag == false) {
//			// 静音模式
//			mAudioManager.setMode(AudioManager.RINGER_MODE_SILENT);
//		}
//		if (sound_flag == true && vibrator_flag == false) {
//			// 关闭震动
//			mAudioManager.setMode(AudioManager.VIBRATE_SETTING_OFF);
//		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
