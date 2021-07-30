package service;

import java.util.*;

import entity.QnAEntity;
import vo.QnAVO;

public class QnAService {
	QnAEntity entity ;
	public QnAService() {
		entity = new QnAEntity();
	}
	
	public void insertBoard(QnAVO board) throws Exception {	
		entity.insertBoard(board);
    }
	
	public int getBoardCount() throws Exception {
		return entity.getBoardCount();
    }

   public ArrayList getBoardList(int start, int end) throws Exception {
    		return entity.getBoardList(start, end);
   }
   
   public QnAVO getBoard(int num) throws Exception {
      		return entity.getBoard(num);
   }
   
   public QnAVO updateGetBoard(int num) throws Exception  {
			return entity.updateGetBoard(num);
	    }
   
   public int updateBoard(QnAVO board) throws Exception {
			return entity.updateBoard(board);
   }
     
   public int deleteBoard(int num, String passwd) throws Exception {    	   
   			return entity.deleteBoard(num, passwd);
   }
}
