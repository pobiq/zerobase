package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BookMarkGroupVO;
import dto.BookMarkVO;
import dto.Location_historyVO;
import dto.Wifi_infoVO;
import util.DBManager;

public class BookMarkDAO {
	
	private BookMarkDAO() {};
	
	private static BookMarkDAO instance = new BookMarkDAO();
	
	public static BookMarkDAO getInstance() { return instance; }
	
	// 북마크 추가
	public int insertBookMark(String name, String main_nm, String mgr_no) {
		int result = -1;
		String sql = "insert into bookmark (id, name, main_nm, regist_date, mgr_no) values (0, ?, ?, now(), ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, main_nm);
			pstmt.setString(3, mgr_no);
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
	
	// 북마크 삭제
	public int deleteBookMark(int id) {
		int result = -1;
		String sql = "delete from bookmark where id = ?";
		
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
	
	// 북마크 상세 조회
	public BookMarkVO getBookMark(int id) {
		String sql = "select id, name, main_nm, regist_date, mgr_no from bookmark where id = ?";
		
		BookMarkVO vo = new BookMarkVO();
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
				vo.setMain_nm(rs.getString("main_nm"));
				vo.setRegist_date(rs.getString("regist_date"));
				vo.setMgr_no("mgr_no");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return vo;
		
	}
	
	// 북마크 그룹 목록 조회
	public List<BookMarkVO> getBookMarkList() {
		String sql = "select id, name, main_nm, regist_date, mgr_no from bookmark order by id";
		
		List<BookMarkVO> list = new ArrayList<BookMarkVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BookMarkVO vo = new BookMarkVO();
				
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				vo.setMain_nm(rs.getString("main_nm"));
				vo.setRegist_date(rs.getString("regist_date"));
				vo.setMgr_no(rs.getString("mgr_no"));
				
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
