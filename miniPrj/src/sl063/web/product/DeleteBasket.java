package sl063.web.product;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import sl063.domain.BasketCart;
import sl063.domain.BasketPurchaserService;
import sl063.web.Command;

public class DeleteBasket extends Command {
    private String next;
    
    public DeleteBasket(String next) {
        this.next = next;
    }
    /**
     *	��ٱ��Ͽ� ��ǰ�� ��´�
     *	1.  detailProduct.jsp�� request�� �޴´�.
     *	2.  Session ��ü���� "Member.id" �� �ش��ϴ� ���� ������ check�Ѵ�.
     *     �� �α��ο��θ� üũ�ϰ� ���Ŀ� ��ٱ��Ͽ� ��ǰ�� ��ƾ� �Ѵ�.
     *   3. �α��� ���� ���� �����̸� "�α����� �����ϼ���!! "��� �޼����� �����ְ�
     *      "userError" �� return �Ѵ�.
     *	4.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
     *   5.  request��ü���� "productName" �� ���� �����´�.
     *	6.  productName�� parameter�� �Է� �޾� �� ��ǰ�� ��ǰ������ �������� listProduct(request, response, productName) �����.
     *       ProductDAO�� listProduct(productName)�� ȣ���Ѵ�.
     *		������� Basket��ü�� �����Ѵ�.
     *   7.  ��ǰ�� �̸��� Basket��ü�� argument �� �־� BasketCart�� add(productName, basket)�� ȣ���Ѵ�.
     */
    public String execute(HttpServletRequest request,HttpServletResponse response)
    throws Exception {
        System.out.println("DeleteBasket execute called");
        List errorMsgs = new ArrayList();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        request.setAttribute("errorMsgs", errorMsgs);
        
        HttpSession session = request.getSession(false);
        String memID = null;
        if(session != null) {
            memID =  (String)session.getAttribute("Member.id");
            System.out.println("memID : " + memID);
            if( memID == null  || memID.equals("") ) {
                errorMsgs.add("�α����� �����ϼ���!!");
                //System.out.println("login ERROR");
            }
        }
        
        //����īƮ clear
        //BasketCart basketCart = (BasketCart)session.getAttribute("BasketCart");
        //basketCart.clear();
        
        BasketPurchaserService basketPurchaserService = new BasketPurchaserService();
        
        basketPurchaserService.deleteBasket(memID);
        
        return next;
    }
    
    
}