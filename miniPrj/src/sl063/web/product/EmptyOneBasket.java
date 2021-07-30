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
	*	��ٱ����� ��ǰ�� �����Ѵ�.
	*	1.  basketProduct.jsp�� request�� �޴´�.
	*   2.  request��ü���� "productName" �� ���� �����´�.
	*	3.  Session ��ü���� BasketCart��ü�� ���۷����� ���´�
	*   4.  ��ǰ�� �̸��� argument �� �־� BasketCart�� removeAll(productName)�� ȣ���Ѵ�.
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