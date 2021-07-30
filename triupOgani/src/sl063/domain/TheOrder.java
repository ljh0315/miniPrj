package sl063.domain;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This domain object represents a Basket.
 * The data attributes are all package-private to allow access to them
 * in the {@link PurchaseService} class.
 */
public class TheOrder {
    String              orderNum;
    String		mallId;
    String		productId;
    String		productName;
    String		memId;
    int			quantity;
    int			price;
    String              deliveryStatus;
    String		name;
    String		address;
    String		tel;
    
    //theOrder ���̺��� Į���� �ִ� ������ �ƴ�����
    //�ֹ�����(orderProduct.jsp)���� ���Ǿ�����  paytype,email �� �߰��Ȱ���.
    String		paytype;
    String		email;
   
    /**
     * This is the constructor.  I tis package-private to prevent
     * misuse.  The {@link PurchaserService} method should be used to
     * create a new Basket object.
     */
    
    TheOrder(){       
    };
    /**
     * This is the full constructor.
     */
    TheOrder(String orderNum, String mallId, String productName,int quantity,int price,String memId,String name,String address,String tel) {
        this.orderNum = orderNum;
        this.mallId = mallId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.memId = memId;
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
    /**
     * �ֹ���ȣ
     * @param String orderNum
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    /**
     * �����̵�
     * @param String mallId
     */
    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
    /**
     * ��ǰ��
     * @param String productName
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * ��ǰ��
     * @param String productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * �ֹ�����
     * @param int quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * �ֹ���ǰ����
     * @param int price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * �ֹ��ھ��̵�
     * @param String memId
     */
    public void setMemId(String memId) {
        this.memId = memId;
    }
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    /**
     * �ֹ����̸�
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * �ֹ����ּ�
     * @param String address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * �ֹ��ڿ���ó
     * @param String tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    
    /**
     * ����Ÿ��(�¶����Ա�/ī��)
     * @param String paytype
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    /**
     * �̸����ּ�
     * @param String email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    public String getOrderNum() {return orderNum;}
    public String getMallId() {return mallId;}
    public String getProductId() {return productId;}
    public String getProductName() {return productName;}
    public int getQuantity() {return quantity;}
    public int getPrice() {return price;}
    public String getPrice_S() {
        return NumberFormat.getInstance(Locale.KOREA).format(getPrice());
    }
    public String getMemId() {return memId;}
    public String getDeliveryStatus() {return deliveryStatus;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getTel() {return tel;}  
    public String getPaytype() { return paytype; }
    public String getEmail() { return email; }
    
    
}//
