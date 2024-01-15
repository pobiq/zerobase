package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Location_historyVO;
import dto.Wifi_infoVO;
import util.DBManager;

public class Location_historyDAO {
	
	private Location_historyDAO() {};
	
	private static Location_historyDAO instance = new Location_historyDAO();
	
	public static Location_historyDAO getInstance() { return instance; }
	
	// 내가 입력한 위치정보에 대해서 조회하는 시점에 DB에 히스토리를 저장
	public int insertLocationHistory(Location_historyVO vo) {
		int result = -1;
		String sql = "insert into location_history values (0, ?, ?, now())";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, vo.getLat());
			pstmt.setDouble(2, vo.getLnt());
			result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
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
	
	// 위치 히스토리 목록 검색
	public List<Location_historyVO> getLocationList() {
		String sql = "select id, lat, lnt, regist_time from location_history";
		
		List<Location_historyVO> list = new ArrayList<Location_historyVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Location_historyVO vo = new Location_historyVO();
				
				vo.setId(rs.getInt("id"));
				vo.setLat(rs.getDouble("lat"));
				vo.setLnt(rs.getDouble("lnt"));
				vo.setRegist_time(rs.getString("regist_time"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

}
