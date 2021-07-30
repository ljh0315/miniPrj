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
	*	��ٱ��� ��ü�� �����Ѵ�.
	*	1.  basketProduct.jsp�� request�� �޴´�.
	*	3.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
	*   4.  BasketCart�� clear() �� ȣ���Ѵ�.
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