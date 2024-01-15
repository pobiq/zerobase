package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookMarkDAO;
import dao.BookMarkGroupDAO;
import dto.BookMarkVO;

public class BookMarkDeleteForm implements Action {
	
	private static final long serialVersionUID = 1L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String url="/bookmark-delete.jsp";
    	
    	BookMarkDAO bookMarkDAO = BookMarkDAO.getInstance();
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	
    	BookMarkVO detail = bookMarkDAO.getBookMark(id);
    	
    	request.setAttribute("detail", detail);
    	RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
    	
    	
	}
    
}


