package com.ballyscolombo.ballys;

import com.ballyscolombo.constants.Global;
import com.ballyscolombo.constants.MenuInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("NewApi") public class FragmentListView extends Fragment {
	
	private static final String TAG = "FragmentListView";
	
	public View view_list;
	public Context context;
	Button btn_nav_cardview;
	
	public int temp_number;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		view_list = inflater.inflate(R.layout.fragment_listview, container, false);
		
		context = getActivity().getApplicationContext();
		
		btn_nav_cardview = (Button) view_list.findViewById(R.id.button_nav_cardview);
        
		btn_nav_cardview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				getFragmentManager().popBackStack();
				Global.is_card_view = false;
			}
		});
		
		onShowlistMenuView();
        
		return view_list;
	}
	
	public void removeContent() {
		LinearLayout list = (LinearLayout) view_list.findViewById(R.id.linearlayout_gridview);
		list.removeAllViews();
    }
	
	public void onShowlistMenuView() {
		
		temp_number = 0;
		
		removeContent();
		int length = 2;
		for (int number = 0; number <= length; number++) {
			appendListMenu(number);
		}
	}
	
	public void appendListMenu(final int number) {
		
		LinearLayout list = (LinearLayout) view_list.findViewById(R.id.linearlayout_gridview);
		final LinearLayout newCell = (LinearLayout) (View.inflate(getActivity(), R.layout.cell_list_menu, null));
		
		if (number == 0) {
			temp_number = number;
		} else if (number == 1) {
			temp_number = number + 2;
		} else if (number == 2) {
			temp_number = number + 4;
		}
		
		MenuInfo menu_info1 = Global.menu_list.get(temp_number);
		MenuInfo menu_info2 = Global.menu_list.get(temp_number + 1);
		MenuInfo menu_info3 = Global.menu_list.get(temp_number + 2);
		
		((ImageView) (newCell.findViewById(R.id.imageView_menu1))).setBackgroundResource(menu_info1.getMenu_back());
		((ImageView) (newCell.findViewById(R.id.imageView_menu2))).setBackgroundResource(menu_info2.getMenu_back());
		((ImageView) (newCell.findViewById(R.id.imageView_menu3))).setBackgroundResource(menu_info3.getMenu_back());
		
		((TextView) (newCell.findViewById(R.id.textView_menu1))).setText(menu_info1.getMenu_name());
		((TextView) (newCell.findViewById(R.id.textView_menu2))).setText(menu_info2.getMenu_name());
		((TextView) (newCell.findViewById(R.id.textView_menu3))).setText(menu_info3.getMenu_name());
		
		((ImageView) (newCell.findViewById(R.id.imageView_menu1))).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				String menu_number = String.valueOf(temp_number);
				
				Intent intent = new Intent(getActivity(), SubMenuActivity.class);
            	intent.putExtra("selected_menu", menu_number);
    			startActivity(intent);
    			Log.d(TAG, " Selected SubMenu : " + menu_number);
			}
		
		});
		
		((ImageView) (newCell.findViewById(R.id.imageView_menu2))).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				String menu_number = String.valueOf(temp_number + 1);
				
				Intent intent = new Intent(getActivity(), SubMenuActivity.class);
            	intent.putExtra("selected_menu", menu_number);
    			startActivity(intent);
    			Log.d(TAG, " Selected SubMenu : " + menu_number);
			}
		
		});
		
		((ImageView) (newCell.findViewById(R.id.imageView_menu3))).setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				
				int position = temp_number + 2;
				
				if (position == 8) {
					
            		Intent intent = new Intent(getActivity(), ContactUsActivity.class);
        			startActivity(intent);
        			Log.d(TAG, " Selected SubMenu : " + position);
            		
				} else {
					
					Intent intent = new Intent(getActivity(), SubMenuActivity.class);
	            	intent.putExtra("selected_menu", String.valueOf(position));
	    			startActivity(intent);
	    			Log.d(TAG, " Selected SubMenu : " + position);
					
				}
			}
		
		});
		
		newCell.setTag(number);
		registerForContextMenu(newCell);
		list.addView(newCell);
		
	}
	
}
