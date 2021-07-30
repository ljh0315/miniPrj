package sl063.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import sl063.exception.RecordNotFoundException;
import vo.MemberVO;

/**
 * �� ������ ��ü�� Product(��ǰ) ���̺��� ����Ÿ�� �׼��� �ϴ� ��ü�̴�.
 */
class ProductDAO {
    private DataSource ds;
    /**
     * ����Ʈ ������
     * ���� domain package �������� ���� �����ϵ��� Access Modifier�� default �� �Ͽ���.
     * JNDI API�� �̿��Ͽ� Naming Service�� ��ϵ� DataSource�� Lookup �Ѵ�.
     */
    ProductDAO() {
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
     * ��ǰID�� parameter�� �Ѱ� �޾� �˻��� ��ǰ�� ������ ��ȯ�մϴ�.
     * @param String productId
     * @return Product product
     */
    public Product selectProduct(String productId) throws RecordNotFoundException {
        if(  ! productIDExist( productId ) ) throw new RecordNotFoundException(productId+"�� �����ϴ� �ʴ� ProductID �Դϴ�.");
        
        String RETRIEVE_STMT = "Select ProductID,MallID,ProductName,Company,Price1,Price2,Install,keyword,Detail,ProductDate,PhotoDir "+
                "from product where productId= ?";
        System.out.println("ProductDAO selectProduct RETRIEVE_STMT : "+RETRIEVE_STMT);
        
        Product product=null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                product = new Product();
                product.setProductId(rs.getString(1));
                product.setMallId(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setCompany(rs.getString(4));
                product.setPrice1(rs.getInt(5));
                product.setPrice2(rs.getInt(6));
                product.setInstall(rs.getString(7));
                product.setKeyword(rs.getString(8));
                product.setDetail(rs.getString(9));
                product.setProductDate(rs.getDate(10));
                product.setPhotoDir(rs.getString(11));
            }
            
        } catch(SQLException se) {
            se.printStackTrace();
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) {_ex.printStackTrace(System.err); }
            try { if(connection != null) connection.close(); } catch(Exception e){ e.printStackTrace(System.err);}
        }
        return product;
    }
    
    /**
     * ��ǰID�� parameter�� �Ѱ� �޾� �˻��� ��ǰ�� ������ ��ȯ�մϴ�.
     * @param String productId
     * @return Product product
     */  
    public Collection productAllList(Product product) {
		System.out.println("ProductDAO() :: productAllList ::");
		
		Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Collection productlist = new ArrayList();
        System.out.println("ProductDAO() :: productAllList :: " + product.getCateNum());
        
        String RETRIEVE_STMT ="select ProductID,MallID,ProductName,Company,Price1,Price2,Install,keyword,Detail,ProductDate,PHOTODIR from product where cateNum="+product.getCateNum()+"";
        System.out.println("ProductDAO listProduct RETRIEVE_STMT : "+RETRIEVE_STMT);
        
        try {
            
            // Get a database connection
            connection = ds.getConnection();
            stmt = connection.prepareStatement(RETRIEVE_STMT);
            rs = stmt.executeQuery();
            
            for(int i=1;rs.next();i++) {             
                
                product.setProductId(rs.getString(1));
                product.setMallId(rs.getString(2));
                product.setProductName(rs.getString(3));
                product.setCompany(rs.getString(4));
                product.setPrice1(rs.getInt(5));
                product.setPrice2(rs.getInt(6));
                product.setInstall(rs.getString(7));
                product.setKeyword(rs.getString(8));
                product.setDetail(rs.getString(9));
                product.setProductDate(rs.getDate(10));
                product.setPhotoDir(rs.getString(11));
                product.setSeq(i);
                productlist.add(product);
                System.out.println(product.getProductId() + product.getPrice2() + product.getPhotoDir());
            }
                      
        } catch(SQLException se) {
            se.printStackTrace();
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(SQLException _ex) { _ex.printStackTrace(System.err);}
            try { if(stmt != null) stmt.close(); } catch(SQLException _ex) {_ex.printStackTrace(System.err); }
            try { if(connection != null) connection.close(); } catch(Exception e){ e.printStackTrace(System.err);}
        }
        return productlist;
	}
    
    
    /**
     * ��ǰID�� �Է¹޾� ��ǰID�� ������ �Ǻ��մϴ�.
     * @param String memID
     * @return boolean
     */
    public boolean productIDExist(String productId) {
        String RETRIEVE_STMT = "Select productid from product where productId = ?";
        System.out.println("ProductDAO productIDExist RETRIEVE_STMT : " + RETRIEVE_STMT);
        
        MemberVO member = null;
        Connection connection=null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        boolean isExist = false;
        
        try {           
            // Get a database connection
            connection = ds.getConnection();
            stmt=connection.prepareStatement(RETRIEVE_STMT);
            stmt.setString(1, productId);
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

}//ProductDAO

