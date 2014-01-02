package com.example.sokoban;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
	int[][] matrix=new int[8][11];
	int people_x,people_y;
	int level,money;
	final ImageView[][] imageViews = new ImageView[8][11];
	SharedPreferences mPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		level=mPreferences.getInt("j", 0);
		money=mPreferences.getInt("money", 100);
		super.onCreate(savedInstanceState);
		//res用来加载关卡数据   箱子坐标      围墙坐标     人物坐标    目的坐标
		Resources res = getResources();
		final String[] myString = res.getStringArray(R.array.my_array);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_game);
		int k=0;
	    for(int i=0;i<8;i++)
	    	for(int j=0;j<11;j++){
	    		imageViews[i][j]=(ImageView)findViewById(R.id.ImageView0+k);
	    		k++;
	    	}
	    initMap(myString);
	    
	    final Button btn_up = (Button)findViewById(R.id.btn_up);
	    final Button btn_down = (Button)findViewById(R.id.btn_down);
	    final Button btn_right = (Button)findViewById(R.id.btn_right);
	    final Button btn_left = (Button)findViewById(R.id.btn_left);
	    btn_up.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(matrix[people_x-1][people_y]==1){
					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x-1][people_y].setImageResource(R.drawable.r);
					matrix[people_x][people_y]=1;
					people_x--;
					matrix[people_x][people_y]=4;
				}
				else if((matrix[people_x-1][people_y]==2)&&(matrix[people_x-2][people_y]==1)){

					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x-1][people_y].setImageResource(R.drawable.r);
					imageViews[people_x-2][people_y].setImageResource(R.drawable.xz);
					matrix[people_x][people_y]=1;
					people_x--;
					matrix[people_x][people_y]=4;
					matrix[people_x-1][people_y]=2;
					if(checkPassOrNot(myString))
						nextLevel();
				}
				
					
			}
		});
	    btn_down.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(matrix[people_x+1][people_y]==1){
					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x+1][people_y].setImageResource(R.drawable.r);
					matrix[people_x][people_y]=1;
					people_x++;
					matrix[people_x][people_y]=4;
				}
				else if((matrix[people_x+1][people_y]==2)&&(matrix[people_x+2][people_y]==1)){

					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x+1][people_y].setImageResource(R.drawable.r);
					imageViews[people_x+2][people_y].setImageResource(R.drawable.xz);
					matrix[people_x][people_y]=1;
					people_x++;
					matrix[people_x][people_y]=4;
					matrix[people_x+1][people_y]=2;
					if(checkPassOrNot(myString))
						nextLevel();
				}
				
			}
		});
	    btn_right.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(matrix[people_x][people_y+1]==1){
					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y+1].setImageResource(R.drawable.r);
					matrix[people_x][people_y]=1;
					people_y++;
					matrix[people_x][people_y]=4;
				}
				else if((matrix[people_x][people_y+1]==2)&&(matrix[people_x][people_y+2]==1)){

					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y+1].setImageResource(R.drawable.r);
					imageViews[people_x][people_y+2].setImageResource(R.drawable.xz);
					matrix[people_x][people_y]=1;
					people_y++;
					matrix[people_x][people_y]=4;
					matrix[people_x][people_y+1]=2;
					if(checkPassOrNot(myString))
						nextLevel();
				}
				
			}
		});
	    btn_left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if(matrix[people_x][people_y-1]==1){
					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y-1].setImageResource(R.drawable.r);
					matrix[people_x][people_y]=1;
					people_y--;
					matrix[people_x][people_y]=4;
				}
				else if((matrix[people_x][people_y-1]==2)&&(matrix[people_x][people_y-2]==1)){

					imageViews[people_x][people_y].setImageResource(R.drawable.im_null);
					imageViews[people_x][people_y-1].setImageResource(R.drawable.r);
					imageViews[people_x][people_y-2].setImageResource(R.drawable.xz);
					matrix[people_x][people_y]=1;
					people_y--;
					matrix[people_x][people_y]=4;
					matrix[people_x][people_y-1]=2;
					if(checkPassOrNot(myString))
						nextLevel();
				}
			}
		});

	
	}
	
	private boolean checkPassOrNot(String[] myStrings){
		char[] c = myStrings[level+2].toCharArray();
		for(int i=0;i<myStrings[level+2].length();i++){
			int x = c[i++]-48;
			int y = c[i]-48;
			if(matrix[x][y]!=2)
				return false;
			}
		return true;
	}
	
	private void nextLevel() {
		if((level/4)>-1) DisplayToast("恭喜通关");
		else{
			SharedPreferences.Editor editor = mPreferences.edit();
			editor.putInt("level", level+4);      //更新Level
			editor.putInt("money", money+10);
			editor.commit();
			Intent intent = new Intent();
			intent.setClass(GameActivity.this, GameActivity .class);
			startActivity(intent);
			finish();
		}
	}
	
	public void DisplayToast(String str) {
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
	
	private void initMap(String[] myString)
	{
		//0代表围墙  1代表路    2代表箱子 3代表目的地 4代表人
		for(int i=0;i<8;i++)
	    	for(int j=0;j<11;j++)
	    		matrix[i][j]=1;
		//围墙坐标
		char[] c = myString[level].toCharArray();
		for(int i=0;i<myString[level].length();i++){
			int x = c[i++]-48;
			int y = c[i]-48;
			imageViews[x][y].setBackgroundResource(R.drawable.q);
			matrix[x][y]=0;
			}
		//箱子坐标
		c = myString[level+1].toCharArray();
		for(int i=0;i<myString[level+1].length();i++){
			int x = c[i++]-48;
			int y = c[i]-48;
			imageViews[x][y].setImageResource(R.drawable.xz);
			matrix[x][y]=2;
			}
		//目的地坐标
		c = myString[level+2].toCharArray();
		for(int i=0;i<myString[level+2].length();i++){
			int x = c[i++]-48;
			int y = c[i]-48;
			imageViews[x][y].setBackgroundResource(R.drawable.m);
			matrix[x][y]=1;
			}
		//人坐标
		c = myString[level+3].toCharArray();
		for(int i=0;i<myString[level+3].length();i++){
			people_x = c[i++]-48;
			people_y = c[i]-48;
			imageViews[people_x][people_y].setImageResource(R.drawable.r);
			matrix[people_x][people_y]=4;
			}
	}

}

