
package sl063.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * �� ������ ��ü�� Basket(����īƮ) ���̺��
 * Purchaser(����) ���̺��� ����Ÿ�� �׼��� �ϴ� ��ü�̴�.
 */
public class BasketPurchaserDAO {
    private DataSource ds;
    /**
     * ����Ʈ ������
     * ���� domain package �������� ���� �����ϵ��� Access Modifier�� default �� �Ͽ���.
     * JNDI API�� �̿��Ͽ� Naming Service�� ��ϵ� DataSource�� Lookup �Ѵ�.
     */
    BasketPurchaserDAO() {
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
     * Basket��ü�� MemberID�� parameter�� �޾� Basket ���̺� ������ ��Ͻ�Ų��.
     * @param Basket basket,String MEMID
     */
    public void insertBasketPurchaser(Basket basket,Purchaser purchaser,String MEMID) {
        String INSERTBasket_STMT = "INSERT INTO basket (ORDERNUM, PRODUCTID, MEMID, QUANTITY, PRICE) VALUES (?, ?, ?, ?, ?)";
        System.out.println("BasketDAO insertBasket : "+INSERTBasket_STMT);
        
        String INSERTPurchaser_STMT
                = "INSERT INTO Purchaser "
                +"(ORDERNUM, PLACE, MEMID, NAME, ADDRESS, TEL, EMAIL, PAYTYPE, TCOUNT, AMOUNT, PAYSTATUS, PURCHASEDATE, "
                +"CARDTYPE, CARDNUMBER) "
                +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("BasketDAO insertPurchaser : "+INSERTPurchaser_STMT);
        
        Connection connection = null;
        PreparedStatement stmt_b = null;
        PreparedStatement stmt_p = null;
        
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            
            stmt_b = connection.prepareStatement(INSERTBasket_STMT);
            stmt_b.setString(1, basket.getOrderNum().trim());
            stmt_b.setString(2, basket.getProductId().trim());
            stmt_b.setString(3, MEMID.trim());
            stmt_b.setInt(4, basket.getQuantity());
            stmt_b.setInt(5, basket.getPrice());
            stmt_b.executeUpdate();
            
            stmt_p = connection.prepareStatement(INSERTPurchaser_STMT);
            stmt_p.setString(1, purchaser.getOrderNum());
            stmt_p.setString(2, purchaser.getPlace());
            stmt_p.setString(3, purchaser.getMemID());
            stmt_p.setString(4, purchaser.getName());
            stmt_p.setString(5, purchaser.getAddress());
            stmt_p.setString(6, purchaser.getTel());
            stmt_p.setString(7, purchaser.getEmail());
            stmt_p.setString(8, purchaser.getPayType());
            stmt_p.setInt(9, purchaser.getTcount());
            stmt_p.setInt(10, purchaser.getAmount());
            stmt_p.setString(11, purchaser.getPayStatus());
            stmt_p.setDate(12, new Date(System.currentTimeMillis()));
            stmt_p.setString(13, purchaser.getCardType());
            stmt_p.setString(14, purchaser.getCardNumber());
            stmt_p.executeUpdate();
            
            connection.commit();
            
            
        } catch(SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(stmt_b != null) stmt_b.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt_p != null) stmt_p.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
    }
     /**
     * �ֹ���ȣ�� ���� �߰��ϱ� ���� �ֹ���ȣ�� ���� ū���� ��ȸ�Ͽ� 1 ������Ų ���� ��ȯ�Ѵ�.
     * @param
     * @return int
     */
    public int OrderMaxNo() {
        String query = "select max(ordernum) from purchaser";
        System.out.println("OrderMaxNo() : "+query);
        
        int maxno=0;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            
            connection = ds.getConnection();            
            stmt=connection.prepareStatement(query);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                maxno = rs.getInt(1)+1;
            }
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if ( rs != null ) rs.close(); } catch (SQLException sex) {sex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) { _ex.printStackTrace();}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
        return maxno;
    }
    /**
     * ���� �Ϸ��Ŀ� MemberID�� parameter�� �޾� Basket ���̺� ������ ������Ų��.
     * @param String MEMID
     */
    public void deleteBasket( String MEMID ) {
        String DELT_STMT = "DELETE FROM BASKET WHERE MEMID = ?";
        System.out.println("BasketPurchaserDAO deleteBasket : "+ DELT_STMT + " " + MEMID);
        
        Connection connection = null;
        PreparedStatement pstmt = null;
        
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            pstmt = connection.prepareStatement(DELT_STMT);
            pstmt.setString(1, MEMID);
            int delCount = pstmt.executeUpdate();
            System.out.println("BasketPurchaserDAO deleteBasket delCount :" + delCount);
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
    }
    /**
     * MemeberID�� Parameter�� �޾� ���� �ֹ� ������ Purchaser , Basket, Product ���̺��� �о�ͼ� Collection���� �����Ѵ�.
     * @param String memID
     * @return Collection
     */
    public Collection listBasketPurchaser(String memID) {
        String SELT_STMT =
                "select a.ordernum, c.PRODUCTNAME, b.PRICE, b.QUANTITY, a.ADDRESS, a.PAYTYPE, a.EMAIL, a.TEL "
                +"from purchaser a,basket b,product c "
                +"where a.MEMID= ? and a.PAYSTATUS='N' "
                +"and a.ORDERNUM = b.ORDERNUM and b.PRODUCTID=c.PRODUCTID order by ordernum";
        System.out.println("BasketPurchaserDAO listBasketPurchaser query : "+ SELT_STMT);
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList arrayList=null;
        TheOrder theOrder=null;
        
        try {
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(SELT_STMT);
            stmt.setString(1, memID);
            rs = stmt.executeQuery();
            
            arrayList=new ArrayList();
            while(rs.next()) {
                theOrder=new TheOrder();
                
                theOrder.setOrderNum(rs.getString(1));
                theOrder.setProductName(rs.getString(2));
                theOrder.setPrice(rs.getInt(3));
                theOrder.setQuantity(rs.getInt(4));
                theOrder.setAddress(rs.getString(5));
                theOrder.setPaytype(rs.getString(6));
                theOrder.setEmail(rs.getString(7));
                theOrder.setTel(rs.getString(8));
                arrayList.add(theOrder);
            }
            
        } catch(SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(connection != null) connection.close(); } catch(SQLException e){ e.printStackTrace(System.err);}
        }
        return arrayList;
    }
    
}