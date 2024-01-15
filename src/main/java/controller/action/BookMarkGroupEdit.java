package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookMarkGroupDAO;

public class BookMarkGroupEdit implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	BookMarkGroupDAO bookMarkGroupDAO = BookMarkGroupDAO.getInstance();
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
    	int preference = Integer.parseInt(request.getParameter("preference"));
    	
    	bookMarkGroupDAO.updateBookMarkGroup(id, name, preference);
    	
    	String url="/Servlet?command=bookMarkGroup.do";
    	
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('" + "북마크 그룹 정보를 수정하였습니다." + "');");
    	out.println("location.href='" + url + "';");
    	out.println("</script>");
    	out.flush();
	}
    
}


