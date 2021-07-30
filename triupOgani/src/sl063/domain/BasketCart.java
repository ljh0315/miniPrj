package sl063.domain;

import java.text.NumberFormat;
import java.util.*;

/**
 * �� ������ ��ü�� ��ǰ �Ѱ��� ������ ��� Basket ��ü�� ������ ���� �� �ִ� ��ü�̴�.
 */
public class BasketCart {
    HashMap items = null;
    int numberOfItems = 0;
    int quantity = 0;
    private int totalPrice = 0;
    private NumberFormat nf;
    /**
     * ����Ʈ ������
     */
    public BasketCart() {
        nf = NumberFormat.getInstance(Locale.KOREA);
        items = new HashMap();
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� �ش�.
     * ProductID�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * �����ϸ� quantity�� ���� ���� ��Ų��.
     * �������� ������ basket��ü�� �����Ѵ�.
     *
     * @param String productID,Basket basket
     */
    public void add(String productID,Basket basket) {
        if(items.containsKey(productID)) {
            //�����ϸ� ������ ������Ų��
            basket = (Basket) items.get(productID);
            basket.incrementQuantity();
        } else {
            //�������� ������ basket��ü�� �����Ѵ�.
            items.put(productID, basket);
        }
        numberOfItems++;
        //basket.setTotPrice(basket.getTotPrice() + basket.getPrice());
        
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� �ش�.
     * ProductID�� Ű�� �Ͽ� ���縦 ������ Ȯ���ϰ�
     * ���� �Ҷ� quantity�� ���� parameter�� �޾� ���� ��Ų��.
     *
     * @param String productID,Basket basket,int quantity
     */
    public void add(String productID,Basket basket,int quantity) {
        
        if(items.containsKey(productID)) {
            basket = (Basket) items.get(productID);
            basket.incrementQuantity(quantity);
            basket.setQtyPrice(basket.getQuantity() * basket.getPrice());
            setTotalPrice( getTotalPrice()  +  ( basket.getQuantity() * basket.getPrice() ) );
        } else {
            items.put(productID, basket);
            basket.setQtyPrice(quantity * basket.getPrice());
            setTotalPrice(getTotalPrice() + (quantity * basket.getPrice()) );
        }

        numberOfItems++;
        
        System.out.println("<<>>productID, quantity " + productID + " " + quantity + " " + basket.getQuantity());
    }
    
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� ���ش�.
     * ProductID�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * ���� �ϸ� HashMap���� ��� �����Ѵ�.
     *
     * @param String productID
     */
    public void removeAll(String productID) {
        if(items.containsKey(productID)) {
            Basket basket = (Basket) items.get(productID);
            
            totalPrice = 0;
            items.remove(productID);
            numberOfItems--;
        }
    }
    
    /**
     * BasketCart�� HashMap�� basket��ü�� ���� ���ش�.
     * ProductID�� Ű�� �Ͽ� ���� ������ Ȯ���ϰ�
     * ���� �Ҷ� quantity�� ���� ���� ��Ų��.
     *
     * @param String productID
     */
    public void remove(String productID) {
        if(items.containsKey(productID)) {
            Basket basket = (Basket) items.get(productID);
            System.out.println("������ :" + basket.getQuantity() + " " + getTotalPrice());
            
            basket.decrementQuantity();
            basket.setQtyPrice(basket.getQtyPrice() - basket.getPrice());
            setTotalPrice( getTotalPrice() - basket.getPrice() );

            System.out.println("���� �� : " + getTotalPrice());
            
            if(basket.getQuantity() <= 0)
                items.remove(productID);
            
            numberOfItems--;
        }
    }
    
    
    
    /**
     * HashMap�� ��� Values�� ��ȯ�Ѵ�
     */
    public Collection getItems() {
        return items.values();
    }
    
    protected void finalize() {
        items.clear();
    }
    
    /**
     * HashMap�� ���� ���� �����Ѵ�.
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }
    
    /**
     * HashMap�� ��� ������ �����Ѵ�.
     */
    public void clear() {
        totalPrice = 0;
        items.clear();
        numberOfItems = 0;
    }
    
    /**
     * HashMap�� ProductID key���� ��ȯ�Ѵ�.
     * @return String[] keySet
     */
    public String[] keySet() {
        Collection col = items.keySet();
        int SIZE = col.size();
        String[] keySet = new String[SIZE];
        Iterator iter = col.iterator();
        
        int i = 0;
        
        while(iter.hasNext()) {
            keySet[i] = (String)iter.next();
            i++;
        }
        
        return keySet;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }
    
    public String getTotalPrice_S() {
        return nf.format(totalPrice);
    }
    
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
