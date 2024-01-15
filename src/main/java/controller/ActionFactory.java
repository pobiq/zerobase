package controller;

import controller.action.*;

public class ActionFactory {
	
private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("main.do")) { // index.jsp
			action = new Main();
		} else if(command.equals("loadwifi.do")) { // load-wifi.jsp
			action = new LoadWifi();
		} else if(command.equals("history.do")) { // location_history.jsp
			action = new History();
		} else if(command.equals("wifiDetail.do")) { // wifi_detail.jsp
			action = new WifiDetail();
		} else if(command.equals("bookMarkGroup.do")) { // bookmark-group.jsp
			action = new BookMarkGroup();
		} else if(command.equals("bookMarkGroupAddForm.do")) { // bookmark-group-add.jsp
			action = new BookMarkGroupAddForm();
		} else if(command.equals("bookMarkGroupAdd.do")) {
			action = new BookMarkGroupAdd();
		} else if(command.equals("bookMarkGroupEditForm.do")) {
			action = new BookMarkGroupEditForm();
		} else if(command.equals("bookMarkGroupEdit.do")) {
			action = new BookMarkGroupEdit();
		} else if(command.equals("bookMarkGroupDeleteForm.do")) {
			action = new BookMarkGroupDeleteForm();
		} else if(command.equals("bookMarkGroupDelete.do")) {
			action = new BookMarkGroupDelete();
		} else if(command.equals("bookMarkAdd.do")) {
			action = new BookMarkAdd();
		} else if(command.equals("bookMark.do")) {
			action = new BookMark();
		} else if(command.equals("bookMarkDeleteForm.do")) {
			action = new BookMarkDeleteForm();
		} else if(command.equals("bookMarkDelete.do")) {
			action = new BookMarkDelete();
		}
	
		return action;
	}

}
