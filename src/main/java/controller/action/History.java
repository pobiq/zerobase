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

import dao.Location_historyDAO;
import dao.Wifi_infoDAO;
import dto.Location_historyVO;
import dto.Wifi_infoVO;

public class History implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="/location_history.jsp";
    	
    	Location_historyDAO location_historyDAO = Location_historyDAO.getInstance();
    	
    	List<Location_historyVO> locationList = location_historyDAO.getLocationList();
    	
    	request.setAttribute("locationList", locationList);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
    
}


