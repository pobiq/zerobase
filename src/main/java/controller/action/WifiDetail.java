package controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.BookMarkGroupDAO;
import dao.Location_historyDAO;
import dao.Wifi_infoDAO;
import dto.BookMarkGroupVO;
import dto.Location_historyVO;
import dto.Wifi_infoVO;

public class WifiDetail implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="/wifi_detail.jsp";
    	
    	Wifi_infoDAO wifi_infoDAO = Wifi_infoDAO.getInstance();
    	
    	Wifi_infoVO input = new Wifi_infoVO();
    	
    	String mgr_no = request.getParameter("mgr_no");
    	String lat = request.getParameter("lat");
    	String lnt = request.getParameter("lnt");
    	
    	input.setMgr_no(mgr_no);
    	input.setLat(Double.parseDouble(lat));
    	input.setLnt(Double.parseDouble(lnt));
    	
    	Wifi_infoVO detail = wifi_infoDAO.getWifiInfoDetail(input);
    	
    	request.setAttribute("detail", detail);
    	
    	BookMarkGroupDAO bookMarkGroupDAO = BookMarkGroupDAO.getInstance();
    	
    	List<BookMarkGroupVO> groupList = bookMarkGroupDAO.getBookMarkList();
    	
    	request.setAttribute("groupList", groupList);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
    
}


