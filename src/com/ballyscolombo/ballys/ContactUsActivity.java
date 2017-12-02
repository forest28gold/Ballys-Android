package com.ballyscolombo.ballys;

import com.ballyscolombo.constants.Global;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View.OnClickListener;


public class ContactUsActivity extends Activity {
	
	RelativeLayout btn_about_us;
	RelativeLayout btn_phone;
	RelativeLayout btn_email;
	RelativeLayout btn_map;
	
	Button btn_back;
	Button btn_home;
	
	private int scrWid;
	private int scrHei;
	private static Dialog dia_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact_us);
        
        btn_about_us = (RelativeLayout)findViewById(R.id.relativeLayout_about_us);
        btn_phone = (RelativeLayout)findViewById(R.id.relativeLayout_phone);
        btn_email = (RelativeLayout)findViewById(R.id.relativeLayout_email);
        btn_map = (RelativeLayout)findViewById(R.id.relativeLayout_map);
        
        btn_back = (Button)findViewById(R.id.button_back);
        btn_home = (Button)findViewById(R.id.button_home);
        
        DisplayMetrics mec = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mec);
		scrWid = mec.widthPixels;
		scrHei = mec.heightPixels;

		dia_phone = new Dialog(this, R.style.CustomTheme);
		dia_phone.setContentView(R.layout.popup_menu_phone);
		Window drawWin = dia_phone.getWindow();
		WindowManager.LayoutParams diaParam = drawWin.getAttributes();
		diaParam.gravity = Gravity.BOTTOM;
		drawWin.setAttributes(diaParam);
		dia_phone.getWindow().setLayout(scrWid, scrHei  / 10 * 20 / 5);
		dia_phone.getWindow().getAttributes().windowAnimations = R.drawable.slide_up;
		
		((Button) dia_phone.findViewById(R.id.dm_phone1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia_phone.dismiss();
				
				String number = Global.PHONE_HOTELLINE1.replace("+", "");
				String phone_number = "tel:" + number.replace(" ", "");
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + phone_number));
				startActivity(callIntent);
			}
		});
		
		((Button) dia_phone.findViewById(R.id.dm_phone2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia_phone.dismiss();
				
				String number = Global.PHONE_HOTELLINE2.replace("+", "");
				String phone_number = "tel:" + number.replace(" ", "");
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + phone_number));
				startActivity(callIntent);
			}
		});

		((Button) dia_phone.findViewById(R.id.dmcancel)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia_phone.dismiss();
			}
		});
        
        btn_about_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(ContactUsActivity.this, SubMenuActivity.class);
            	intent.putExtra("selected_menu", "9");
    			startActivity(intent);
				
			}
		});
        
        btn_phone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				dia_phone.show();
			}
		});
        
        btn_email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setType("text/plain");
				emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Global.BALLYS_EMAIL});
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "");
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});
        
        btn_map.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ContactUsActivity.this, MapLocationActivity.class);
    			startActivity(intent);
			}
		});
        
        btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        btn_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        
    }
    
    
}
