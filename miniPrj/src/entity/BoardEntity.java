package entity;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.*;

import entity.BoardEntity;
import vo.BoardVO;

import javax.naming.*;

public class BoardEntity {
	private static BoardEntity instance = new BoardEntity();
    
    public static BoardEntity getInstance() {
        return instance;
    }
	DataSource ds;
	
	public BoardEntity() {
		try {
            // Retrieve the DataSource from JNDI
            Context ctx = new InitialContext();
            if ( ctx == null ) {
                throw new RuntimeException("JNDI Context could not be found.");
            }
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mallDB");
            if ( ds == null ) {
                throw new RuntimeException("DataSource could not be found.");
            }
        } catch (NamingException ne) {
            throw new RuntimeException("A JNDI error occured. "+ ne.getMessage());
        }		
    }
	
	public void insertBoard(BoardVO board) throws Exception {			
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;

		int num=board.getNum();
		int ref=board.getRef();
		int re_step=board.getRe_step();
		int re_level=board.getRe_level();
		int number=0;
        String sql="";

        try {
            conn = ds.getConnection();

            pstmt = conn.prepareStatement("select max(num) from reviewBoard");
			rs = pstmt.executeQuery();
			
			if (rs.next())
		      number=rs.getInt(1)+1;
		    else
		      number=1; 
		   
		    if (num!=0)   //
		    {  
		      sql="update reviewBoard set re_step=re_step+1 where ref= ? and re_step> ?";
              pstmt = conn.prepareStatement(sql);
              pstmt.setInt(1, ref);
			  pstmt.setInt(2, re_step);
			  pstmt.executeUpdate();
			  re_step=re_step+1;
			  re_level=re_level+1;
		     }else{
		  	  ref=number;
			  re_step=0;
			  re_level=0;
		     }	 
            // 쿼리를 작성
            sql = "insert into reviewBoard(num,writer,email,subject,passwd,reg_date,readcount,";
		    sql+="ref,re_step,re_level,content,ip) values(seq_review.nextval,?,?,?,?,?,?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2, board.getEmail());
            pstmt.setString(3, board.getSubject());
            pstmt.setString(4, board.getPasswd());
			pstmt.setTimestamp(5, board.getReg_date());
			pstmt.setInt(6, board.getReadcount());
            pstmt.setInt(7, ref);
            pstmt.setInt(8, re_step);
            pstmt.setInt(9, re_level);
			pstmt.setString(10, board.getContent());
			pstmt.setString(11, board.getIp());
			
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
	
	public int getBoardCount() throws Exception {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       int x=0;

       try {
           conn = ds.getConnection();
           
           pstmt = conn.prepareStatement("select count(*) from reviewBoard");
           rs = pstmt.executeQuery();

           if (rs.next()) {
              x= rs.getInt(1);
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
           if (rs != null) try { rs.close(); } catch(SQLException ex) {}
           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
       }
		return x;
   }

   public ArrayList getBoardList(int start, int end) throws Exception {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       ArrayList boardList=null;
       try {
           conn = ds.getConnection();           
           pstmt = conn.prepareStatement("select * from (select rownum numrow, A.* from(select * from reviewBoard)A) where numrow >= ? and numrow <= ? order by ref desc, re_step asc");        		   
           pstmt.setInt(1, start-1);
		   pstmt.setInt(2, end);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               boardList = new ArrayList(end);
               do{
                 BoardVO article= new BoardVO();
				  article.setNum(rs.getInt("num"));
				  article.setWriter(rs.getString("writer"));
                 article.setEmail(rs.getString("email"));
                 article.setSubject(rs.getString("subject"));
                 article.setPasswd(rs.getString("passwd"));
			      article.setReg_date(rs.getTimestamp("reg_date"));
				  article.setReadcount(rs.getInt("readcount"));
                 article.setRef(rs.getInt("ref"));
                 article.setRe_step(rs.getInt("re_step"));
				  article.setRe_level(rs.getInt("re_level"));
                 article.setContent(rs.getString("content"));
			      article.setIp(rs.getString("ip")); 
				  
			      boardList.add(article);
			    }while(rs.next());
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
           if (rs != null) try { rs.close(); } catch(SQLException ex) {}
           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
       }
		return (ArrayList)boardList;
   }
   
   public BoardVO getBoard(int num) throws Exception {
       Connection conn = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       BoardVO board=null;
       try {
           conn = ds.getConnection();

           pstmt = conn.prepareStatement(
           	"update reviewBoard set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

           pstmt = conn.prepareStatement(
           	"select * from reviewBoard where num = ?");
           pstmt.setInt(1, num);
           rs = pstmt.executeQuery();

           if (rs.next()) {
               board = new BoardVO();
               board.setNum(rs.getInt("num"));
               board.setWriter(rs.getString("writer"));
               board.setEmail(rs.getString("email"));
               board.setSubject(rs.getString("subject"));
               board.setPasswd(rs.getString("passwd"));
               board.setReg_date(rs.getTimestamp("reg_date"));
               board.setReadcount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setRe_step(rs.getInt("re_step"));
				board.setRe_level(rs.getInt("re_level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));     
			}
       } catch(Exception ex) {
           ex.printStackTrace();
       } finally {
           if (rs != null) try { rs.close(); } catch(SQLException ex) {}
           if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
           if (conn != null) try { conn.close(); } catch(SQLException ex) {}
       }
		return board;
   }
   
   public BoardVO updateGetBoard(int num) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        BoardVO board=null;
	        try {
	            conn = ds.getConnection();

	            pstmt = conn.prepareStatement(
	            	"select * from reviewBoard where num = ?");
	            pstmt.setInt(1, num);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	            	board = new BoardVO();
	            	board.setNum(rs.getInt("num"));
	            	board.setWriter(rs.getString("writer"));
	            	board.setEmail(rs.getString("email"));
	            	board.setSubject(rs.getString("subject"));
	            	board.setPasswd(rs.getString("passwd"));
	            	board.setReg_date(rs.getTimestamp("reg_date"));
				    board.setReadcount(rs.getInt("readcount"));
				    board.setRef(rs.getInt("ref"));
				    board.setRe_step(rs.getInt("re_step"));
	                board.setRe_level(rs.getInt("re_level"));
	                board.setContent(rs.getString("content"));
	                board.setIp(rs.getString("ip"));     
				}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return board;
	    }
   
     public int updateBoard(BoardVO board) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs= null;

	        String dbpasswd="";
	        String sql="";
			int x=-1;
	        try {
	            conn = ds.getConnection();
	            
				pstmt = conn.prepareStatement(
	            	"select passwd from reviewBoard where num = ?");
	            pstmt.setInt(1, board.getNum());
	            rs = pstmt.executeQuery();
	            
				if(rs.next()){
				  dbpasswd= rs.getString("passwd"); 
				  if(dbpasswd.equals(board.getPasswd())){
	                sql="update reviewBoard set writer=?,email=?,subject=?,passwd=?";
				    sql+=",content=? where num=?";
				    pstmt = conn.prepareStatement(sql);
	                pstmt.setString(1, board.getWriter());
	                pstmt.setString(2, board.getEmail());
	                pstmt.setString(3, board.getSubject());
	                pstmt.setString(4, board.getPasswd());
	                pstmt.setString(5, board.getContent());
				    pstmt.setInt(6, board.getNum());
	                pstmt.executeUpdate();
					x= 1;
				  }else{
					x= 0;
				  }
				}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
			return x;
	    }
     
     public int deleteBoard(int num, String passwd)
    	        throws Exception {
    	        Connection conn = null;
    	        PreparedStatement pstmt = null;
    	        ResultSet rs= null;
    	        String dbpasswd="";
    	        int x=-1;
    	        try {
    				conn = ds.getConnection();

    	            pstmt = conn.prepareStatement(
    	            	"select passwd from reviewBoard where num = ?");
    	            pstmt.setInt(1, num);
    	            rs = pstmt.executeQuery();
    	            
    				if(rs.next()){
    					dbpasswd= rs.getString("passwd"); 
    					if(dbpasswd.equals(passwd)){
    						pstmt = conn.prepareStatement(
    	            	      "delete from reviewBoard where num=?");
    	                    pstmt.setInt(1, num);
    	                    pstmt.executeUpdate();
    						x= 1; //글삭제 성공
    					}else
    						x= 0; //비밀번호 틀림
    				}
    	        } catch(Exception ex) {
    	            ex.printStackTrace();
    	        } finally {
    	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
    	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
    	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    	        }
    			return x;
    	    }

}
