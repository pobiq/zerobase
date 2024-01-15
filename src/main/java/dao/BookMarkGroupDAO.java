package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BookMarkGroupVO;
import dto.Location_historyVO;
import dto.Wifi_infoVO;
import util.DBManager;

public class BookMarkGroupDAO {
	
	private BookMarkGroupDAO() {};
	
	private static BookMarkGroupDAO instance = new BookMarkGroupDAO();
	
	public static BookMarkGroupDAO getInstance() { return instance; }
	
	// 북마크 그룹 추가
	public int insertBookMarkGroup(String name, int preference) {
		int result = -1;
		String sql = "insert into bookmark_group (id, name, preference, regist_date) values (0, ?, ?, now())";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, preference);
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
	
	// 북마크 그룹 수정
	public int updateBookMarkGroup(int id, String name, int preference) {
		int result = -1;
		String sql = "update bookmark_group set name = ?, preference = ?, update_date = now()"
				+" where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, preference);
			pstmt.setInt(3, id);
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
	
	// 북마크 그룹 삭제
	public int deleteBookMarkGroup(int id) {
		int result = -1;
		String sql = "delete from bookmark_group where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
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
	
	// 북마크 그룹 상세 조회
	public BookMarkGroupVO getBookMarkGroup(int id) {
		String sql = "select id, name, preference, regist_date, update_date from bookmark_group where id = ?";
		
		BookMarkGroupVO vo = new BookMarkGroupVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPreference(rs.getInt("preference"));
				vo.setRegist_date(rs.getString("regist_date"));
				vo.setUpdate_date(rs.getString("update_date"));;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
		
	}
	
	// 북마크 그룹 목록 조회
	public List<BookMarkGroupVO> getBookMarkList() {
		String sql = "select id, name, preference, regist_date, update_date from bookmark_group order by preference";
		
		List<BookMarkGroupVO> list = new ArrayList<BookMarkGroupVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookMarkGroupVO vo = new BookMarkGroupVO();
				
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setPreference(rs.getInt("preference"));
				vo.setRegist_date(rs.getString("regist_date"));
				vo.setUpdate_date(rs.getString("update_date"));;
				
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
