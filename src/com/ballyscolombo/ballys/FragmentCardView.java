package com.ballyscolombo.ballys;


import com.ballyscolombo.constants.Global;
import com.ballyscolombo.constants.MenuInfo;
import com.ballyscolombo.coverflow.CoverFlow;
import com.ballyscolombo.coverflow.ReflectingImageAdapter;
import com.ballyscolombo.coverflow.ResourceImageAdapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;


@SuppressLint("NewApi") public class FragmentCardView extends Fragment {
	
	private static final String TAG = "FragmentCardView";
	
	public static View view_card;
	Button btn_nav_listview;
	private TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		if (view_card != null) {
	        ViewGroup parent = (ViewGroup) view_card.getParent();
	        if (parent != null)
	            parent.removeView(view_card);
	    } 

		try {
			view_card = inflater.inflate(R.layout.fragment_cardview, container, false);
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		btn_nav_listview = (Button) view_card.findViewById(R.id.button_nav_listview);
        
		btn_nav_listview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				flipCard();
			}
		});
		
		textView = (TextView) view_card.findViewById(R.id.textView_cover_menu);
		
        CoverFlow reflectingCoverFlow = (CoverFlow) view_card.findViewById(R.id.coverflowReflect);
        setupCoverFlow(reflectingCoverFlow, true);
		
		return view_card;
	}
	
	public void flipCard() {
		
		Global.is_card_view = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(R.id.container, new FragmentListView())
                .addToBackStack(null)
                .commit();
    }
	
	private void setupCoverFlow(final CoverFlow mCoverFlow, final boolean reflect) {
        BaseAdapter coverImageAdapter;
        if (reflect) {
            coverImageAdapter = new ReflectingImageAdapter(new ResourceImageAdapter(getActivity()));
        } else {
            coverImageAdapter = new ResourceImageAdapter(getActivity());
        }
        mCoverFlow.setAdapter(coverImageAdapter);
        mCoverFlow.setSelection(0, true);
        setupListeners(mCoverFlow);
    }

    /**
     * Sets the up listeners.
     * 
     * @param mCoverFlow
     *            the new up listeners
     */
    private void setupListeners(final CoverFlow mCoverFlow) {
        mCoverFlow.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView< ? > parent, final View view, final int position, final long id) {
            	
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
        mCoverFlow.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView< ? > parent, final View view, final int position, final long id) {
            	
            	MenuInfo menu_info = Global.menu_list.get(position);
                textView.setText(menu_info.getMenu_name());
            }

            @Override
            public void onNothingSelected(final AdapterView< ? > parent) {
                textView.setText("Nothing clicked!");
            }
        });
    }
	
}
