package sl063.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Iterator;
import java.util.Collection;

import sl063.domain.Purchaser;
import sl063.domain.Basket;
import sl063.domain.BasketPurchaserService;
import sl063.domain.BasketCart;
import sl063.web.Command;

public class InsertBasketPurchaser extends Command {
	private String next;
    BasketPurchaserService basketPurchaserService = null;
    Basket basket = null;

	public InsertBasketPurchaser(String next) {
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
		String memID = request.getParameter("memid");

		HttpSession session = request.getSession();		
		BasketCart basketCart = (BasketCart)session.getAttribute("BasketCart");
		Collection collection = basketCart.getItems();
		Iterator iterator = collection.iterator();
		
		while(iterator.hasNext()) {
		    basket = (Basket)iterator.next();
		    orderInsert(request, response, basket, memID);
		}
		
		selectBasket(request, memID);
		//����īƮ clear
		basketCart.clear();
		return next;
	}
	/**
	* ������ ������ �Է¹��� ����� ������ basket,purchaser���̺� �����Ѵ�.
	*
	* @param Basket basket,String memID
	*
	*/
	public void orderInsert(HttpServletRequest request,HttpServletResponse response,Basket basket, String memID)
	{
	    basketPurchaserService = new BasketPurchaserService();
	    Purchaser purchaser = new Purchaser();
	    
	    java.text.DecimalFormat nf2 = new java.text.DecimalFormat("#0000");
	    
	    String OrderNo = nf2.format(basketPurchaserService.OrderMaxNo());
	    String CardNumber = request.getParameter("cardnumber");
	    String CardType = request.getParameter("cardtype");
	    
	    if(CardNumber == "" | CardType== ""){
	        CardNumber = "N";
	        CardType = "N";
	    }
	    
	    System.out.println(OrderNo);	    
	    basket.setOrderNum(OrderNo);
	    
	    purchaser.setOrderNum(OrderNo);
	    purchaser.setMemID(memID);
	    purchaser.setAddress(request.getParameter("address"));
	    purchaser.setName(request.getParameter("name"));
	    purchaser.setEmail(request.getParameter("email"));
	    purchaser.setTel(request.getParameter("tel"));
	    purchaser.setPlace(request.getParameter("place"));
	    purchaser.setCardNumber(CardNumber);
	    purchaser.setCardType(CardType);
	    purchaser.setPayStatus("N");
	    purchaser.setPayType(request.getParameter("paytype"));
	    purchaser.setAmount(basket.getQuantity()*basket.getPrice());
	    purchaser.setTcount(1);
	    
	    basketPurchaserService.insertBasketPurchaser(basket,purchaser,memID);
	}
	
	/**
	* �ֹ��� ������� �ֹ� ������ delivery������ ������ request��ü�� ��� �Ѱ� �ش�.
	* @param String memID
	*/
	public void selectBasket(HttpServletRequest request, String memID)
	{
	    
	    Collection collection = basketPurchaserService.listBasketPurchaser(memID);
	    request.setAttribute("theOrder.Collection", collection);
	}

}