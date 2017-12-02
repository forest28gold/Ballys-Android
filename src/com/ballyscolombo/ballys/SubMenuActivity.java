package com.ballyscolombo.ballys;

import com.ballyscolombo.constants.Global;
import com.ballyscolombo.constants.MenuInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class SubMenuActivity extends Activity {
	
	private static final String TAG = "SubMenuActivity";
	
	Button btn_back;
	Button btn_forward;
	Button btn_home;
	Button btn_refresh;

	WebView webView;
	
	private int scrWid;
	private int scrHei;
	private static Dialog dia_waiting;
	
	int selected_index;
	
    @SuppressLint({ "SetJavaScriptEnabled", "NewApi" }) @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sub_menu);
        
        Intent intent = getIntent();
		String selected_menu = intent.getStringExtra("selected_menu");
		selected_index = Integer.valueOf(selected_menu);
        
        btn_back = (Button)findViewById(R.id.button_back);
        btn_forward = (Button)findViewById(R.id.button_forward);
        btn_home = (Button)findViewById(R.id.button_home);
        btn_refresh = (Button)findViewById(R.id.button_refresh);
        
        webView = (WebView)findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        
        DisplayMetrics mec = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mec);
		scrWid = mec.widthPixels;
		scrHei = mec.heightPixels;
        
		dia_waiting = new Dialog(this, R.style.CustomTheme);
		dia_waiting.setContentView(R.layout.dialog_wait);
		Window drawWin_waiting = dia_waiting.getWindow();
		WindowManager.LayoutParams diaParam_waiting = drawWin_waiting.getAttributes();
		diaParam_waiting.gravity = Gravity.CENTER;
		drawWin_waiting.setAttributes(diaParam_waiting);
		dia_waiting.getWindow().setLayout(scrWid, scrHei / 10 * 20 / 5);
		dia_waiting.getWindow().getAttributes().windowAnimations = R.drawable.slide_up;
		
		btn_forward.setBackgroundResource(R.drawable.icon_common_foward_selected);
		
        btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (webView.canGoBack()) {
					webView.goBack();
	            } else {
	                finish();
	            }
				
				if (webView.canGoForward()) {
					btn_forward.setEnabled(true);
					btn_forward.setBackgroundResource(R.drawable.button_forward);
	            } else {
	            	btn_forward.setEnabled(false);
	            	btn_forward.setBackgroundResource(R.drawable.icon_common_foward_selected);
	            }
			}
		});
        
        btn_forward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (webView.canGoForward()) {
					webView.goForward();
					btn_forward.setBackgroundResource(R.drawable.button_forward);
	            } else {
	            	btn_forward.setEnabled(false);
	            	btn_forward.setBackgroundResource(R.drawable.icon_common_foward_selected);
	            }

			}
		});
        
        btn_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (selected_index == 9) {
					Intent intent = new Intent( SubMenuActivity.this, MainActivity.class );
					intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
					startActivity(intent);
					finish();
				} else {
					finish();
				}
			}
		});
        
        btn_refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dia_waiting.show();
				webView.reload();
				
				if (webView.canGoForward()) {
					btn_forward.setEnabled(true);
					btn_forward.setBackgroundResource(R.drawable.button_forward);
	            } else {
	            	btn_forward.setEnabled(false);
	            	btn_forward.setBackgroundResource(R.drawable.icon_common_foward_selected);
	            }
			}
		});
        
        btn_forward.setEnabled(false);
		
        dia_waiting.show();
        
        webView.setWebViewClient(new WebViewClient() {
        	@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
        		super.shouldOverrideUrlLoading(view, url);
                Log.i(TAG, "Processing webview url click...");
                dia_waiting.show();
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
            	super.onPageFinished(view, url);
                Log.i(TAG, "Finished loading URL: " +url);
                if (dia_waiting.isShowing()) {
                	dia_waiting.dismiss();
                }
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            	
                Log.e(TAG, "Error: " + description);
                if (dia_waiting.isShowing()) {
                	dia_waiting.dismiss();
                }
            }
            
        });
        
        if (selected_index == 9) {
        	webView.loadUrl(Global.MENU_ABOUTUS_URL);
		} else {
			MenuInfo menu_info = Global.menu_list.get(selected_index);
	        webView.loadUrl(menu_info.getMenu_url());
		}
    }
    
    
}
