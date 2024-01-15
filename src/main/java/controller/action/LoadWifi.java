package controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.Wifi_infoDAO;
import dto.Wifi_infoVO;

public class LoadWifi implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="/load-wifi.jsp";
    	
    	Wifi_infoDAO wifi_infoDAO = Wifi_infoDAO.getInstance();
    	
    	int totalCnt = wifi_infoDAO.getCountList();
    	
    	// 최초 한번만 로드하게 변경
    	if(totalCnt <= 0) {
    		totalCnt = totalCount();
    		insertAllWifiData(totalCnt);
    	}
    	
    	request.setAttribute("total_cnt", totalCnt);
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
    
    public int totalCount() throws IOException{
    	JsonObject object = (JsonObject) JsonParser.parseString(getWifiData(1,1));
    	JsonObject temp = (JsonObject) object.get("TbPublicWifiInfo");
    	int totalCount = Integer.parseInt(temp.get("list_total_count").toString());
    	
    	return totalCount;
    }
    
    public void insertAllWifiData(int totalCnt) throws IOException {
    	Wifi_infoDAO wifi_infoDAO = Wifi_infoDAO.getInstance();
    	
    	int start = -999;
    	int end = 0;
    	int lastPage = totalCnt / 1000;
    	if(totalCnt % 1000 > 0) {
    		lastPage++;
    	}
    	
    	for(int page = 1; page <= lastPage; page++) {
    		start += 1000;
    		end = page * 1000;
    		if(totalCnt < end) {
    			end = totalCnt;
    		}
    		
    		JsonObject object = (JsonObject) JsonParser.parseString(getWifiData(start , end));
        	JsonObject temp = (JsonObject) object.get("TbPublicWifiInfo");
        	JsonArray array = (JsonArray) temp.get("row");
        	
        	ArrayList<Wifi_infoVO> list = new ArrayList<>();
        	
        	for(int i = 0; i < array.size(); i++) {
        		JsonObject data = (JsonObject) array.get(i);
        		Wifi_infoVO vo = new Wifi_infoVO();
        		vo.setMgr_no(data.get("X_SWIFI_MGR_NO").getAsString());
        		vo.setWrdofc(data.get("X_SWIFI_WRDOFC").getAsString());
        		vo.setMain_nm(data.get("X_SWIFI_MAIN_NM").getAsString());
        		vo.setAdres1(data.get("X_SWIFI_ADRES1").getAsString());
        		vo.setAdres2(data.get("X_SWIFI_ADRES2").getAsString());
        		vo.setInstl_floor(data.get("X_SWIFI_INSTL_FLOOR").getAsString());
        		vo.setInstl_ty(data.get("X_SWIFI_INSTL_TY").getAsString());
        		vo.setInstl_mby(data.get("X_SWIFI_INSTL_MBY").getAsString());
        		vo.setSvc_se(data.get("X_SWIFI_SVC_SE").getAsString());
        		vo.setCmcwr(data.get("X_SWIFI_CMCWR").getAsString());
        		vo.setCnstc_year(data.get("X_SWIFI_CNSTC_YEAR").getAsString());
        		vo.setInout_door(data.get("X_SWIFI_INOUT_DOOR").getAsString());
        		vo.setRemars3(data.get("X_SWIFI_REMARS3").getAsString());
        		vo.setLat(Double.parseDouble(data.get("LAT").getAsString()));
        		vo.setLnt(Double.parseDouble(data.get("LNT").getAsString()));
        		vo.setWork_dttm(data.get("WORK_DTTM").getAsString());
        		list.add(vo);
        	}
        	wifi_infoDAO.insertInfoList(list);
    	}
    }
    
    public String getWifiData(int start, int end) throws IOException {
    	
    	StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
    	urlBuilder.append("/" +  URLEncoder.encode("576f694875686f6f35336974625354","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
    	urlBuilder.append("/" +  URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
    	urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
    	urlBuilder.append("/" + URLEncoder.encode(Integer.toString(start),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
    	urlBuilder.append("/" + URLEncoder.encode(Integer.toString(end),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
    	// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
    	
    	URL url = new URL(urlBuilder.toString());
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	conn.setRequestMethod("GET");
    	conn.setRequestProperty("Content-type", "application/json");
    	BufferedReader rd;

    	// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
    	if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
    		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    	} else {
    		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	String line;
    	while ((line = rd.readLine()) != null) {
    		sb.append(line);
    	}
    	
    	rd.close();
    	conn.disconnect();
    	
    	return sb.toString();
    }
    
}


