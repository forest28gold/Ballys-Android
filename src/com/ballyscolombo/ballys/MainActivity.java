package com.ballyscolombo.ballys;

import java.util.ArrayList;

import com.ballyscolombo.constants.Global;
import com.ballyscolombo.constants.MenuInfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


@SuppressLint("NewApi") public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        onGetMenuList();
        
        Global.fragmentManager = getSupportFragmentManager();
        
        getFragmentManager()
	        .beginTransaction()
	        .add(R.id.container, new FragmentCardView())
	        .commit();
        
        Global.is_card_view = false;
        
    }
    
    @Override
	public void onBackPressed() {
    	
    	if (Global.is_card_view) {
			
    		Global.is_card_view = false;
    		getFragmentManager().popBackStack();
    		
		} else {
			
			finish();
		}
	}
    
    public void onGetMenuList() {
    	
    	int[] name_list = { R.string.str_casino, R.string.str_entertainment, R.string.str_packages,
    							R.string.str_hotel_spa, R.string.str_restaurant, R.string.str_table_game,
    							R.string.str_make_mytrip, R.string.str_sign_up, R.string.str_contact_us};
    	int[] back_list = { R.drawable.menu_casino, R.drawable.menu_entertainment, R.drawable.menu_package,
    							R.drawable.menu_hotel_spa, R.drawable.menu_restaurant, R.drawable.menu_table_game,
    							R.drawable.menu_make_mytrip, R.drawable.menu_signup, R.drawable.menu_contactus };
    	String[] url_list = { Global.MENU_CASINO_URL, Global.MENU_ENTERTAINMMENT_URL, Global.MENU_PACKAGES_URL, 
    							Global.MENU_HOTEL_SPA_URL, Global.MENU_RESTAURANT_URL, Global.MENU_TABLE_GAMES_URL,
								Global.MENU_MAKE_MYTRIP_URL, Global.MENU_SIGNUP_URL, Global.MENU_CONTACTUS_URL };
    	
    	Global.menu_list = new ArrayList<MenuInfo>();
    	
    	for (int i = 0; i < name_list.length; i++) {
			
    		MenuInfo menu_info = new MenuInfo();
    		
    		menu_info.setMenu_name(name_list[i]);
    		menu_info.setMenu_back(back_list[i]);
    		menu_info.setMenu_url(url_list[i]);
    		
    		Global.menu_list.add(menu_info);
		}
    	
    }

    
}
