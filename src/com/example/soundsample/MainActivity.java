package com.example.soundsample;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/*
 * 特記事項： activity_main.xml および values/styles.xml で android:soundEffectsEnabled="false"を設定することで、
 * デフォルトのボタン音を鳴らさないようにしている。前者はボタン個別個別の設定用。後者はテーマ一括の設定用。
 */
public class MainActivity extends Activity {

	private SoundPool mSoundPool;
	private static final int SOUND_COUNT = 6;
	private int[] mSoundIds = new int[SOUND_COUNT + 1];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume() {
		// サウンドのロード
		// 同時再生数、ストリームタイプ、クオリティ（未使用）
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		
		// Context, ResourceID, Priority
		mSoundIds[1] = mSoundPool.load(this, R.raw.b1, 1);
		mSoundIds[2] = mSoundPool.load(this, R.raw.b2, 1);
		mSoundIds[3] = mSoundPool.load(this, R.raw.b3, 1);
		mSoundIds[4] = mSoundPool.load(this, R.raw.b4, 1);
		mSoundIds[5] = mSoundPool.load(this, R.raw.b5, 1);
		mSoundIds[6] = mSoundPool.load(this, R.raw.b6, 1);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// サウンド解放
		mSoundPool.release();
		mSoundPool = null;
		super.onPause();
	}

	// ボタン押下時
	public void buttonMethod(View button){
		// 押されたボタンから数値を取得（1～6）
		int number = Integer.parseInt(((Button)button).getText().toString());
		
		// 音量の決定
		float fVolume = 1;
		SeekBar seek2 = (SeekBar)findViewById(R.id.seekBar1);
		int p2 = seek2.getProgress(); //0～100
		fVolume = p2 / 100.0f; // 0.0～1.0

		// 再生速度決定
		SeekBar seek = (SeekBar)findViewById(R.id.seekBar2);
		int p = seek.getProgress(); // 0～100
		float fSpeed = 0.5f + (p / 100.0f); // 0.5～1.5
		
		// 左音量、右音量、優先度、ループ再生回数(-1で無限)、再生速度
		mSoundPool.play(mSoundIds[number], fVolume, fVolume, 0, 0, fSpeed);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
