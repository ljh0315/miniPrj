package sl063.web.product;

import javax.servlet.*;
import javax.servlet.http.*;

import sl063.domain.Basket;
import sl063.domain.BasketCart;
import sl063.web.Command;

public class EmptyOneBasket extends Command {
	private String next;
	public EmptyOneBasket(String next) {
		this.next = next;
	}
	/**
	*	장바구니의 상품을 삭제한다.
	*	1.  basketProduct.jsp로 request를 받는다.
	*   2.  request객체에서 "productName" 인 값을 가져온다.
	*	3.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
	*   4.  상품의 이름을 argument 로 주어 BasketCart의 removeAll(productName)을 호출한다.
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		System.out.println("DeleteBasket execute called");

		HttpSession session = request.getSession(false);

		//BasketCart bc = null;
		if(session != null) {
			BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
                        String productId=request.getParameter("productId");
			bc.remove(productId);
		}
		return next;
	}

}