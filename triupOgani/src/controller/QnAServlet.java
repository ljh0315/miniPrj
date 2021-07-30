package controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.QnAService;
import vo.QnAVO;

import java.sql.Timestamp;
import java.util.*;

public class QnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QnAService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(){
        service=new QnAService();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	        request.setCharacterEncoding("euc-kr");         
    	        
    	        String choice = request.getParameter("choice");        
    	        if(choice.equals("getAll-board")) {    	        	
    	        	getAllBoard(request,response);    	        	
    	        } else if(choice.equals("get-board")) {
    	            getBoard(request,response);
    	        } else if(choice.equals("insert-board")) {
    	            insertBoard(request,response);
    	        } else if(choice.equals("updateGet-board")) {
    	            updateGetBoard(request,response);
    	        } else if(choice.equals("update-board")) {
    	            updateBoard(request,response);
    	        } else if(choice.equals("delete-board")) {
    	            deleteBoard(request,response);
    	        }
    }
    
    public void getAllBoard(HttpServletRequest request,HttpServletResponse response) throws Exception {             
    	int pageNum = request.getParameter("pageNum")!= null ? Integer.parseInt(request.getParameter("pageNum")) : 1;
    	int count = service.getBoardCount();    	
    	ArrayList list = service.getBoardList((pageNum - 1) * 10 + 1, pageNum * 10);
    	RequestDispatcher dispatcher=request.getRequestDispatcher("qnaBoard/getAllBoard.jsp");
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("count",count);
        request.setAttribute("list",list);
        dispatcher.forward(request,response);
    }

    public void getBoard(HttpServletRequest request,HttpServletResponse response) throws Exception{                	
    	int num = Integer.parseInt(request.getParameter("num"));    
    	String pageNum = request.getParameter("pageNum");
    	QnAVO board = service.getBoard(num);            	
        RequestDispatcher dispatcher=request.getRequestDispatcher("qnaBoard/getBoard.jsp");
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("board",board);
        dispatcher.forward(request,response);
    }

    public void insertBoard(HttpServletRequest request,HttpServletResponse response) throws Exception{
        int num=Integer.parseInt(request.getParameter("num"));
        int ref=Integer.parseInt(request.getParameter("ref"));
        int re_step=Integer.parseInt(request.getParameter("re_step"));
        int re_level=Integer.parseInt(request.getParameter("re_level"));    
        String writer=request.getParameter("writer");
        String subject=request.getParameter("subject");
        String email=request.getParameter("email");
        String content=request.getParameter("content");
        String passwd=request.getParameter("passwd");
        Timestamp reg_date=new Timestamp(System.currentTimeMillis());
    	String ip=request.getRemoteAddr();
        QnAVO board=new QnAVO(num,ref,re_step,re_level,writer,subject,email,content,passwd,reg_date,ip);
        
        service.insertBoard(board);
        
        getAllBoard(request,response);
    }
    
    public void updateGetBoard(HttpServletRequest request,HttpServletResponse response) throws Exception{         
    	int num = Integer.parseInt(request.getParameter("num"));
    	String pageNum = request.getParameter("pageNum");
    	
        QnAVO board=service.updateGetBoard(num);
        
        RequestDispatcher dispatcher=request.getRequestDispatcher("qnaBoard/updateForm.jsp");
        request.setAttribute("num",num);
        request.setAttribute("pageNum",pageNum);
        request.setAttribute("board",board);
        dispatcher.forward(request,response);
    }
    
    public void updateBoard(HttpServletRequest request,HttpServletResponse response) throws Exception{         
    	int num = Integer.parseInt(request.getParameter("num"));    	
    	String pageNum = request.getParameter("pageNum");
    	String writer = request.getParameter("writer");
    	String subject = request.getParameter("subject");
    	String email = request.getParameter("email");
    	String content = request.getParameter("content");
    	String password = request.getParameter("passwd");
    	
        QnAVO board=new QnAVO(num,writer,subject,email,content,password);
        int check=service.updateBoard(board);
        
        if(check==1){
        	getAllBoard(request,response);        	
        }else {
        	RequestDispatcher dispatcher=request.getRequestDispatcher("qnaBoard/updateForm.jsp");        	
        	dispatcher.forward(request,response);
        }
    }
    
    public void deleteBoard(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	int num = Integer.parseInt(request.getParameter("num"));
    	String pageNum = request.getParameter("pageNum");
    	String passwd = request.getParameter("passwd");
    	
    	int check=service.deleteBoard(num, passwd);
    	if(check==1){
    		request.setAttribute("num",num);
    		request.setAttribute("pageNum",pageNum);
    		getAllBoard(request,response);
        }else {
        	request.setAttribute("num",num);
    		request.setAttribute("pageNum",pageNum);    		
        	deleteBoard(request,response);
        }
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
