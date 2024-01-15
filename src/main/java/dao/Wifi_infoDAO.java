package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Wifi_infoVO;
import util.DBManager;

public class Wifi_infoDAO {
	
	private Wifi_infoDAO() {};
	
	private static Wifi_infoDAO instance = new Wifi_infoDAO();
	
	public static Wifi_infoDAO getInstance() { return instance; }
	
	// Open API 와이파이 정보 가져와서 DB에 입력하기
	public int insertInfoList(ArrayList<Wifi_infoVO> list) {
		int result = -1;
		String sql = "insert into wifi_info values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for(Wifi_infoVO vo : list) {
				pstmt.setString(1, vo.getMgr_no());
				pstmt.setString(2, vo.getWrdofc());
				pstmt.setString(3, vo.getMain_nm());
				pstmt.setString(4, vo.getAdres1());
				pstmt.setString(5, vo.getAdres2());
				pstmt.setString(6, vo.getInstl_floor());
				pstmt.setString(7, vo.getInstl_ty());
				pstmt.setString(8, vo.getInstl_mby());
				pstmt.setString(9, vo.getSvc_se());
				pstmt.setString(10, vo.getCmcwr());
				pstmt.setString(11, vo.getCnstc_year());
				pstmt.setString(12, vo.getInout_door());
				pstmt.setString(13, vo.getRemars3());
				pstmt.setDouble(14, vo.getLat());
				pstmt.setDouble(15, vo.getLnt());
				pstmt.setString(16, vo.getWork_dttm());
				result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getCountList() {
		int result = 0;
		String sql = "select count(*) as count from wifi_info";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
	
	// 근처 WIPI 정보 보기 20개
	public List<Wifi_infoVO> getAroundWifiInfoList(String lnt, String lat) {
		String sql = "select mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lat, lnt, work_dttm"
				+ ", round(st_distance_sphere(point(lnt, lat), point(?, ?))/1000, 4) as km"
				+ " from wifi_info"
				+ " where lat between -90 and 90"
				+ " and lnt between -180 and 180"
				+ " order by km"
				+ " limit 20";
		
		List<Wifi_infoVO> list = new ArrayList<Wifi_infoVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, Double.parseDouble(lnt));
			pstmt.setDouble(2, Double.parseDouble(lat));
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Wifi_infoVO vo = new Wifi_infoVO();
				
				vo.setMgr_no(rs.getString("mgr_no"));
				vo.setWrdofc(rs.getString("wrdofc"));
				vo.setMain_nm(rs.getString("main_nm"));
				vo.setAdres1(rs.getString("adres1"));
				vo.setAdres2(rs.getString("adres2"));
				vo.setInstl_floor(rs.getString("instl_floor"));
				vo.setInstl_ty(rs.getString("instl_ty"));
				vo.setInstl_ty(rs.getString("instl_mby"));
				vo.setSvc_se(rs.getString("svc_se"));
				vo.setCmcwr(rs.getString("cmcwr"));
				vo.setCnstc_year(rs.getString("cnstc_year"));
				vo.setInout_door(rs.getString("inout_door"));
				vo.setRemars3(rs.getString("remars3"));
				vo.setLat(rs.getDouble("lat"));
				vo.setLnt(rs.getDouble("lnt"));
				vo.setWork_dttm(rs.getString("work_dttm"));
				vo.setKm(rs.getString("km"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// WIPI 상세 정보 보기
	public Wifi_infoVO getWifiInfoDetail(Wifi_infoVO input) {
		String sql = "select mgr_no, wrdofc, main_nm, adres1, adres2, instl_floor, instl_ty, instl_mby, svc_se, cmcwr, cnstc_year, inout_door, remars3, lat, lnt, work_dttm,"
				+ " round(st_distance_sphere(point(lnt, lat), point(?, ?))/1000, 4) as km"
				+ " from wifi_info"
				+ " where lat between -90 and 90"
				+ " and lnt between -180 and 180"
				+ " and mgr_no = ?";
		
		Wifi_infoVO vo = new Wifi_infoVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, input.getLnt());
			pstmt.setDouble(2, input.getLat());
			pstmt.setString(3, input.getMgr_no());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				vo.setMgr_no(rs.getString("mgr_no"));
				vo.setWrdofc(rs.getString("wrdofc"));
				vo.setMain_nm(rs.getString("main_nm"));
				vo.setAdres1(rs.getString("adres1"));
				vo.setAdres2(rs.getString("adres2"));
				vo.setInstl_floor(rs.getString("instl_floor"));
				vo.setInstl_ty(rs.getString("instl_ty"));
				vo.setInstl_ty(rs.getString("instl_mby"));
				vo.setSvc_se(rs.getString("svc_se"));
				vo.setCmcwr(rs.getString("cmcwr"));
				vo.setCnstc_year(rs.getString("cnstc_year"));
				vo.setInout_door(rs.getString("inout_door"));
				vo.setRemars3(rs.getString("remars3"));
				vo.setLat(rs.getDouble("lat"));
				vo.setLnt(rs.getDouble("lnt"));
				vo.setWork_dttm(rs.getString("work_dttm"));
				vo.setKm(rs.getString("km"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
	}

}
