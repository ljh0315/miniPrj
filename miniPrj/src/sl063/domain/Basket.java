package sl063.domain;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * �� ������ ��ü�� ��ǰ �Ѱ��� ������ ���� �� �ִ� Basket ��ü�̴�.
 */
public class Basket {
    String      orderNum;
    String      productId;
    String	mallId;
    String	productName;
    String	company;
    int		quantity;
    int		price;
    String      price_S;
    int         qtyPrice;
    String      qtyPrice_S;
    private NumberFormat nf;
    
    /**
     * ����Ʈ ������
     */
    public Basket() {
        this("","",0,0);
    }
    
    /**
     * �ֹ���ȣ�� �ƱԸ�Ʈ�� �޴� ������
     */
    public Basket(String orderNum) {
        this(orderNum, "", 0, 0);
    }
    
    /**
     * �ֹ���ȣ,��ǰID, ��ǰ������ �ƱԸ�Ʈ�� �޴� ������
     */
    public Basket(String orderNum, String productId, int quantity,int price) {
        nf = NumberFormat.getInstance(Locale.KOREA);
        this.orderNum = orderNum;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public void setMallId(String mallId) {
        this.mallId = mallId;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setSprice(String price_S) {
        this.price_S = price_S;
    }
    
    public String getOrderNum() {
        return orderNum;
    }
    public String getProductId() {
        return productId;
    }
    public String getMallId() {return mallId;}
    public String getProductName() {
        return productName;
    }
    public String getCompany() {return company;}
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public String getPrice_S() {
        return nf.format(getPrice());
    }
    
    /**
     * quantity�� ���� ���� ���� �ش�.
     */
    public void incrementQuantity() {
        int aaa = getQuantity();
        aaa++;
        setQuantity(aaa);
    }
    /**
     * parameter�� ���� ������ quantity�� ���� ���� ���� �ش�.
     * @param int Quantity
     */
    public void incrementQuantity(int Quantity) {
        int aaa = getQuantity();
        aaa = aaa + Quantity;
        setQuantity(aaa);
    }
    
    /**
     * quantity�� ���� ���� ���� �ش�.
     */
    public void decrementQuantity() {
        int aaa = getQuantity();
        aaa--;
        setQuantity(aaa);
    }

    public int getQtyPrice() {
        return qtyPrice;
    }

    public void setQtyPrice(int qtyPrice) {
        this.qtyPrice = qtyPrice;
    }

    public String getQtyPrice_S() {
        return nf.format(getQtyPrice());
    }

    public void setQtyPrice_S(String qtyPrice_S) {
        this.qtyPrice_S = qtyPrice_S;
    }
    
}//
