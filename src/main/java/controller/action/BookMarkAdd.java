package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookMarkDAO;
import dao.BookMarkGroupDAO;
import dto.BookMarkGroupVO;

public class BookMarkAdd implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	BookMarkGroupDAO bookMarkGroupDAO = BookMarkGroupDAO.getInstance();
    	BookMarkDAO bookMarkDAO = BookMarkDAO.getInstance();
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	String main_nm = request.getParameter("main_nm");
    	String mgr_no = request.getParameter("mgr_no");
    	
    	
    	BookMarkGroupVO vo = bookMarkGroupDAO.getBookMarkGroup(id);
    	String name = vo.getName();
    	
    	bookMarkDAO.insertBookMark(name, main_nm, mgr_no);
    	
    	String url="/Servlet?command=bookMark.do";
    	
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('" + "북마크 정보를 추가하였습니다." + "');");
    	out.println("location.href='" + url + "';");
    	out.println("</script>");
    	out.flush();
	}
    
}


