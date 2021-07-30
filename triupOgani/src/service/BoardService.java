package service;

import java.util.*;

import entity.BoardEntity;
import vo.BoardVO;

public class BoardService {
	BoardEntity dao;
	public BoardService() {
		dao = new BoardEntity();
	}
	
	public void insertBoard(BoardVO board) throws Exception {	
		dao.insertBoard(board);
    }
	
	public int getBoardCount() throws Exception {
		return dao.getBoardCount();
    }

   public ArrayList getBoardList(int start, int end) throws Exception {
    		return dao.getBoardList(start, end);
   }
   
   public BoardVO getBoard(int num) throws Exception {
      		return dao.getBoard(num);
   }
   
   public BoardVO updateGetBoard(int num) throws Exception  {
			return dao.updateGetBoard(num);
	    }
   
   public int updateBoard(BoardVO board) throws Exception {
			return dao.updateBoard(board);
   }
     
   public int deleteBoard(int num, String passwd) throws Exception {    	   
   			return dao.deleteBoard(num, passwd);
   }
}
