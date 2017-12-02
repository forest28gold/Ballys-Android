package com.ballyscolombo.constants;

import java.io.Serializable;

public class MenuInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int menu_name;
	private int menu_back;
	private String menu_url;
	
	
	public int getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(int menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_back() {
		return menu_back;
	}
	public void setMenu_back(int menu_back) {
		this.menu_back = menu_back;
	}
	public String getMenu_url() {
		return menu_url;
	}
	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}
	
	
}
