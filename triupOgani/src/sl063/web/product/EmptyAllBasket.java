package sl063.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import sl063.domain.Basket;
import sl063.domain.BasketCart;

import sl063.web.Command;

public class EmptyAllBasket extends Command {
	private String next;
	public EmptyAllBasket(String next) {
		this.next = next;
	}
	/**
	*	장바구니 전체를 삭제한다.
	*	1.  basketProduct.jsp로 request를 받는다.
	*	3.  Session 객체에서 BasketCart객체의 레퍼런스을 얻어온다
	*   4.  BasketCart의 clear() 을 호출한다.
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		System.out.println("EmptyBasket execute called");

		HttpSession session = request.getSession(false);

		//BasketCart bc = null;
		if(session != null) {
			BasketCart bc = (BasketCart)session.getAttribute("BasketCart");
			bc.clear();
		}

		return next;
	}

}