package sl063.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Collection;
import sl063.domain.ProductService;
import sl063.web.Command;

public class SelectAllProduct extends Command {
	private String next;
	public SelectAllProduct(String next) {
		this.next = next;
	}
	/**
	*	��ǰ�� ����Ʈ�� ��ȸ�Ѵ�.
	*   1.  ��ǰ���⸦ �����ϴ� request�� �޴´�.
	*   2.  return  Collection ��ü�� request scope�� �����Ѵ�.
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		ProductService productservice = new ProductService();	
		Collection collection = productservice.listProduct();
		request.setAttribute("ListProduct.collection", collection);

		return next;
	}
}