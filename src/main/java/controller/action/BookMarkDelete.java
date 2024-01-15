package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookMarkDAO;
import dao.BookMarkGroupDAO;

public class BookMarkDelete implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	BookMarkDAO bookMarkDAO = BookMarkDAO.getInstance();
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    	bookMarkDAO.deleteBookMark(id);
    	
    	String url="/Servlet?command=bookMark.do";
    	
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('" + "북마크 정보를 삭제하였습니다." + "');");
    	out.println("location.href='" + url + "';");
    	out.println("</script>");
    	out.flush();
	}
    
}


