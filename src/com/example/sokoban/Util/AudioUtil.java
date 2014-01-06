package com.example.sokoban.Util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.sokoban.R;

public class AudioUtil {
	/*
	 * ����Ϊ������֮һ��ר�Ŵ�����Ϸ�����е������������� ���÷�ʽΪ�����ӷ��� ��һ����AudioUtil.init(this) ��ʼ��
	 * �ڶ��������ó�Ա����AudioUtil.PlayMusic()��PauseMusic(),StopMusic()���Ʊ��������ļ���
	 * ��������������Ч�ļ��ķ���AudioUtil.PlaySoundPool(R.raw.back) ���ֿ���
	 * AudioUtil.soundControl()
	 */
	
	// ����ý�岥�������
	private static MediaPlayer mMediaPlayer;
	// ���������������
	private static SoundPool soundPool;
	// ��ȡϵͳ�������������
	private static AudioManager mgr;

	// ý�岥�ſ��ƿ���
	private static boolean musicRunning = true;
	// �����ز��ſ���
	private static boolean soundRunning = true;
	private static boolean ON = true;
	// �����Ķ���
	private static Context mContext;

	// �����ؼ�ֵ�� ������ԴID��
	private static Map<Integer, Integer> soundPoolMap;
	// ������ԴID����
	private static final int musicId[] = { R.raw.backgroung };

	// ��ʼ����Դ
	public static void init(Context context) {
		mContext = context;
		initMusic();
		initSoundPool();
	}

	// ��ʼ��music
	public static void initMusic() {
		mMediaPlayer = MediaPlayer.create(mContext, musicId[0]);
		mMediaPlayer.setLooping(true);
	}

	// ��ʼ����Ч��
	public static void initSoundPool() {
		mgr = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();
		soundPoolMap.put(R.raw.btns, soundPool.load(mContext, R.raw.btns, 1));
	}

	// ������Ч
	public static void PlaySoundPool(int resid) {
		if (soundRunning == false) {
			return;
		}
		Integer soundId = soundPoolMap.get(resid);
		if (soundId != null && soundRunning) {
			soundPool
					.play(soundId,
							mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
							mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
							1, 0, 1f);
			System.out.println("1--->" + resid);
		}
	}

	// ��ͣ����
	public static void PauseMusic() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
		}
	}

	// ֹͣ����
	public static void StopMusic() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
	}

	// ��ʼ����
	public static void PlayMusic() {
		if (mMediaPlayer != null && musicRunning) {
			mMediaPlayer.start();
		}
	}

	// �������ֲ��ſ���
	public static void setMusicRunning(boolean musicRunning) {
		AudioUtil.musicRunning = musicRunning;
	}

	// ������Ч�ؿ���
	public static void setSoundRunning(boolean soundRunning) {
		AudioUtil.soundRunning = soundRunning;
	}

	// ��ȡ���ֲ��ſ���״̬
	public static boolean getMusicRunning() {
		return AudioUtil.musicRunning;
	}

	// ��ȡ��Ч�ز��ſ���״̬
	public static boolean getSoundRunning() {
		return AudioUtil.soundRunning;
	}

	// ��������
	public static void soundControl() {
		ON = !ON;
		if (ON == false) {
			AudioUtil.setMusicRunning(false);
			AudioUtil.setSoundRunning(false);
			AudioUtil.PauseMusic();
		} else {
			AudioUtil.setMusicRunning(true);
			AudioUtil.setSoundRunning(true);
			AudioUtil.PlayMusic();
		}
	}
}
