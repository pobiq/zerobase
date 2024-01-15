package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Location_historyDAO;
import dao.Wifi_infoDAO;
import dto.Location_historyVO;
import dto.Wifi_infoVO;

public class Main implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String url="/index.jsp";
    	
    	String lnt = request.getParameter("lnt");
    	String lat = request.getParameter("lat");
    	
    	Wifi_infoDAO wifi_infoDAO = Wifi_infoDAO.getInstance();
    	Location_historyDAO location_historyDAO = Location_historyDAO.getInstance();
    	
    	if(lnt != null && lat != null && !lnt.equals("0.0") && !lat.equals("0.0") && !lnt.equals("") && !lat.equals("")) {
    		List<Wifi_infoVO> wifiInfoList = wifi_infoDAO.getAroundWifiInfoList(lnt, lat);
    		Location_historyVO vo = new Location_historyVO();
    		vo.setLnt(Double.parseDouble(lnt));
    		vo.setLat(Double.parseDouble(lat));
    		location_historyDAO.insertLocationHistory(vo);
    		request.setAttribute("wifiInfoList", wifiInfoList);
    		request.setAttribute("lnt", lnt);
    		request.setAttribute("lat", lat);
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	

}
