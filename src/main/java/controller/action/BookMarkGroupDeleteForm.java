package controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookMarkGroupDAO;
import dto.BookMarkGroupVO;

public class BookMarkGroupDeleteForm implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String url="/bookmark-group-delete.jsp";
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    	BookMarkGroupDAO bookMarkGroupDAO = BookMarkGroupDAO.getInstance();
    	
    	BookMarkGroupVO detail = bookMarkGroupDAO.getBookMarkGroup(id);
    	
    	request.setAttribute("detail", detail);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
    
}


