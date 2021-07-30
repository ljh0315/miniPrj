
package sl063.domain;

import java.util.Collection;
/**
 * �� ������ ��ü�� Controller�� ��û�� �޾Ƽ� DAO���� �Ѱ��ִ� Facade ������ �Ѵ�.
 * 
 */
public class BasketPurchaserService {
    private BasketPurchaserDAO basketPurchaserDao;
   /**
     * ����Ʈ ������
     *
     */    
    public BasketPurchaserService() {
        basketPurchaserDao = new BasketPurchaserDAO();
    }
        
    public void insertBasketPurchaser(Basket basket,Purchaser purchaser,String MEMBERID) {
        basketPurchaserDao.insertBasketPurchaser(basket, purchaser,MEMBERID);
    }
    
    public void deleteBasket(String MEMBERID) {
        basketPurchaserDao.deleteBasket(MEMBERID);
    }

    public Collection listBasketPurchaser(String memID) {
        Collection coll = basketPurchaserDao.listBasketPurchaser(memID);        
        return coll;
    }
      
    public int OrderMaxNo() {
        int maxno = basketPurchaserDao.OrderMaxNo();
        return maxno;
    }    
    
}
