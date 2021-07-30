package service;

import java.util.*;

import entity.QnAEntity;
import vo.QnAVO;

public class QnAService {
	QnAEntity dao;
	public QnAService() {
		dao = new QnAEntity();
	}
	
	public void insertBoard(QnAVO board) throws Exception {	
		dao.insertBoard(board);
    }
	
	public int getBoardCount() throws Exception {
		return dao.getBoardCount();
    }

   public ArrayList getBoardList(int start, int end) throws Exception {
    		return dao.getBoardList(start, end);
   }
   
   public QnAVO getBoard(int num) throws Exception {
      		return dao.getBoard(num);
   }
   
   public QnAVO updateGetBoard(int num) throws Exception  {
			return dao.updateGetBoard(num);
	    }
   
   public int updateBoard(QnAVO board) throws Exception {
			return dao.updateBoard(board);
   }
     
   public int deleteBoard(int num, String passwd) throws Exception {    	   
   			return dao.deleteBoard(num, passwd);
   }
}
