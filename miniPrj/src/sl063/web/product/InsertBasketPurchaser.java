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
	*	장바구니에 상품을 담는다
	*	1.  detailProduct.jsp로 request를 받는다.
	*	2.  Session 객체에서 "Member.id" 에 해당하는 값이 가져와 check한다.
	*     즉 로그인여부를 체크하고 난후에 장바구니에 상품을 담아야 한다.
	*   3. 로그인 하지 않은 상태이면 "로그인을 먼저하세요!! "라는 메세지를 보여주고 
	*      "userError" 를 return 한다.
	*	4.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
	*   5.  request객체에서 "productName" 인 값을 가져온다.
	*	6.  productName을 parameter로 입력 받아 그 제품의 상품정보를 가져오는 listProduct(request, response, productName) 만든다.
	*       ProductDAO의 listProduct(productName)를 호출한다.
	*		결과값을 Basket객체에 저장한다.
	*   7.  상품의 이름과 Basket객체를 argument 로 주어 BasketCart의 add(productName, basket)을 호출한다.
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
		//쇼핑카트 clear
		basketCart.clear();
		return next;
	}
	/**
	* 세션의 정보와 입력받은 사용자 정보를 basket,purchaser테이블에 삽입한다.
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
	* 주문한 사용자의 주문 정보와 delivery정보를 가져와 request객체에 담아 넘겨 준다.
	* @param String memID
	*/
	public void selectBasket(HttpServletRequest request, String memID)
	{
	    
	    Collection collection = basketPurchaserService.listBasketPurchaser(memID);
	    request.setAttribute("theOrder.Collection", collection);
	}

}