package entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import sl063.exception.RecordDuplicatedException;
import sl063.exception.RecordNotFoundException;
import vo.MemberVO;
/**
 * This Data Access Object performs database operations on Player objects.
 */
public class MemberEntity {
    private DataSource ds;
    /**
     * 디폴트 생성자
     * 같은 domain package 내에서만 접근 가능하도록 Access Modifier를 default 로 하였다.
     * JNDI API를 이용하여 Naming Service에 등록된 DataSource를 Lookup 한다.
     */
    public MemberEntity() {
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
    
    /**
     * 1. DataSource의 getConnection() 를 호출해서 Connection을 얻어온다.
     * 2. prepareStatement 객체를 생성한다.
     * 3. setXXX() 의 argument 에 Member객체의 각 항목을 전달한다.
     * 4. executeUpdate()를 호출해 INSERT SQL문을 실행시킨다.
     * @param MemberVO member
     * @return void
     */
    public void insertMember(MemberVO member) throws RecordDuplicatedException {
        if(  memIDExist( member.getMemID() ) ) throw new RecordDuplicatedException(member.getMemID()+"는 이미 존재하는 MemberID 입니다.");
//    void insertMember(Member member)  {
        String INSERT_STMT
                = "INSERT INTO Member (MemID, password, name, ssn, email, tel,zipcode,address,point,memberdate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("MemberDAO insertMember : "+INSERT_STMT);
        
        Connection connection = null;
        PreparedStatement insert_stmt = null;
        
        try {
            // Get a database connection
            connection = ds.getConnection();
            // Create SQL INSERT statement
            insert_stmt = connection.prepareStatement(INSERT_STMT);
            // Add data fields
            insert_stmt.setString(1, member.memID);
            insert_stmt.setString(2, member.password);
            insert_stmt.setString(3, member.name);
            insert_stmt.setString(4, member.ssn);
            insert_stmt.setString(5, member.email);
            insert_stmt.setString(6, member.tel);
            insert_stmt.setString(7, member.zipcode);
            insert_stmt.setString(8, member.address);
            insert_stmt.setInt(9, member.point);
            insert_stmt.setDate(10, new Date(System.currentTimeMillis()));
            // Perform the SQL INSERT
            insert_stmt.executeUpdate();
            // Handle any SQL errors
        } catch (SQLException se) {
            System.out.println("MemberDAO insert() Error :" + se.getMessage());
            se.printStackTrace();
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if ( insert_stmt != null ) {
                try { insert_stmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            }
            if ( connection != null ) {
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(System.err); }
            }
        }
    }
    
 /*
  * 1. DataSource의 getConnection() 를 호출해서 Connection을 얻어온다.
  * 2. prepareStatement 객체를 생성한다.
  * 3. setXXX() 의 argument 에 memID를 전달한다.
  * 5. executeQuery()를 호출해 SELECT SQL문을 실행시킨다.
  * 4. Member 객체의 setXXX() 메서드의 argument 에 쿼리해온 값들을 전달한다.
  * @param String memId
  * @return Member member
  */
    public MemberVO selectMember(String memId) throws RecordNotFoundException {
        if(  ! memIDExist( memId ) ) throw new RecordNotFoundException(memId+"는 존재하는 않는 MemberID 입니다.");
        
        String RETRIEVE_STMT = "select MEMID,PASSWORD,NAME,SSN,EMAIL,TEL,ZIPCODE,ADDRESS,POINT from MEMBER where MEMID = ?";
        System.out.println("MemberDAO selectMember " + RETRIEVE_STMT);
        
        MemberVO member = null;
        Connection connection=null;
        PreparedStatement select_stmt=null;
        ResultSet rs = null;
        
        try{
            
            // Get a database connection
            connection = ds.getConnection();
            select_stmt=connection.prepareStatement(RETRIEVE_STMT);
            select_stmt.setString(1, memId);
            rs=select_stmt.executeQuery();
            
            if(rs.next()){
                member = new MemberVO();
                member.setMemID( rs.getString(1).trim());
                member.setPassword(rs.getString(2).trim());
                member.setName( rs.getString(3).trim());
                member.setSsn( rs.getString(4).trim());
                member.setEmail( rs.getString(5).trim());
                member.setTel( rs.getString(6).trim());
                member.setZipcode( rs.getString(7).trim());
                member.setAddress( rs.getString(8).trim());
                member.setPoint(rs.getInt(9));
            }
        }catch(SQLException e){
            //e.printStackTrace();
            throw new RuntimeException("A database error occured. " + e.getMessage());
            // Handle no available connection
        } finally {
            if ( rs != null ) {
                try { rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            }
            if ( select_stmt != null ) {
                try { select_stmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            }
            if ( connection != null ) {
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(System.err); }
            }
        }//finally
        return member;
    }	 //select
    /**
     * 1. DataSource의 getConnection() 를 호출해서 Connection을 얻어온다.
     * 2. prepareStatement 객체를 생성한다.
     * 3. setXXX() 의 argument 에 Member 객체를 전달한다.
     * 4. executeUpdate()를 호출해 Update SQL문을 실행시킨다.
     * 5. admin이면 점수를 수정할수 있다.
     * @param MemberVO member, String admin
     * @return void
     */
    public void updateMember(MemberVO member) throws RecordNotFoundException {
        if(  ! memIDExist( member.getMemID() ) ) throw new RecordNotFoundException(member.getMemID()+"는 존재하는 않는 MemberID 입니다.");        
        /*
         *PreparedStatement 에서는 Runtime시에 조건(where)절에 준 값이 보이지 않으므로 Statement에서 사용하는 Query를 작성하였다
         */        
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE MEMBER SET ")
        .append("PASSWORD='"+member.getPassword()+"',")
        .append("NAME='"+member.getName()+"',")
        .append("SSN='"+member.getSsn()+"',")
        .append("EMAIL='"+member.getEmail()+"',")
        .append("TEL='"+member.getTel()+"',")
        .append("ZIPCODE='"+member.getZipcode()+"',")
        .append("ADDRESS='"+member.getAddress()+"'")
        .append(" WHERE MEMID='"+member.getMemID()+"'");        
        String  UPDATE_STMT = buffer.toString();
        System.out.println("MemberDAO updateMember UPDATE_STMT : " + UPDATE_STMT);

        String PrepareUPDATE_STMT=
                "UPDATE MEMBER SET PASSWORD=?, NAME=?, SSN=?, EMAIL=?, TEL= ?, ZIPCODE= ?, ADDRESS= ? WHERE MEMID= ?";
        
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try{          
            // Get a database connection
            connection = ds.getConnection();
            stmt=connection.prepareStatement(PrepareUPDATE_STMT);
            stmt.setString(1, member.getPassword());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getSsn());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getTel());
            stmt.setString(6, member.getZipcode());
            stmt.setString(7, member.getAddress());
            stmt.setString(8, member.getMemID());
            
            int updCount = stmt.executeUpdate();            
            System.out.println("BasketPurchaserDAO deleteBasket delCount :" + updCount);
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("A database error occured. " + e.getMessage());
            // Handle no available connection
        } finally {
            if ( stmt != null ) {
                try { stmt.close(); } catch (SQLException sex) {  sex.printStackTrace(System.err);}
            }
            if ( connection != null ) {
                try { connection.close(); } catch (SQLException e) { e.printStackTrace(System.err); }
            }
        }	//finally
    }	 //update
    
    
    /**
     * 아이디와 패스워드를 입력받아 로그인의 유무를 판별합니다.
     * 1. 아이디가 맞지 않을때 setCheck의 값은 'N'
     * 2. 패스워드가 맞지 않을때 setCheck의 값은 'P'
     * 3. 아이디와 패스워드가 모두 일치 할때 setCheck의 값은 'Y'
     * 4. setCheck의 값이 'y'일때 member객체에 값들을 세팅하고 Member를 리턴한다.
     * @param String usrID,String Passwd
     * @return Member member
     */
    public MemberVO compareID(String memID,String Passwd) {
        String RETRIEVE_STMT = "Select PASSWORD,NAME,EMAIL,TEL,POINT from member where memid = ?";
        System.out.println("MemberDAO compareID : " + RETRIEVE_STMT);
        
        MemberVO member = null;
        Connection connection=null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        
        try {           
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, memID);
            rs = stmt.executeQuery();
            
            String pass = "";
            member = new MemberVO();
            if(rs.next()){
                pass = rs.getString(1).trim();
                if(pass.equals(Passwd)) {
                    member.setName(rs.getString(2));
                    member.setEmail(rs.getString(3));
                    member.setTel(rs.getString(4));
                    member.setPoint(rs.getInt(5));
                    member.setCheck("Y");
                } else {
                    member.setCheck("P");
                }
            } else {
                member.setCheck("N");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            try {if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( stmt != null ) stmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( connection != null ) connection.close(); }catch (SQLException e) { e.printStackTrace(System.err); }
        }
        return member;
    }
    /**
     * 아이디를 입력받아 아이디의 유무를 판별합니다.
     * @param String memID
     * @return boolean
     */
    public boolean memIDExist(String memID) {
        String RETRIEVE_STMT = "Select MEMID from member where memid = ?";
        System.out.println("MemberDAO memIDExist query : " + RETRIEVE_STMT);
        
        MemberVO member = null;
        Connection connection=null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        boolean isExist = false;

        try {
            
            // Get a database connection
            connection = ds.getConnection();
            stmt=connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, memID);
            rs=stmt.executeQuery();
            isExist = rs.next();
            
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("A database error occured. " + e.getMessage());
        } finally {
            try {if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( stmt != null ) stmt.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try {if ( connection != null ) connection.close(); }catch (SQLException e) { e.printStackTrace(System.err); }
        }
        return isExist;
    }
    
}//MemberDAO
