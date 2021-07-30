package sl063.web.product;

import javax.servlet.*;
import javax.servlet.http.*;
import sl063.domain.Product;
import sl063.domain.ProductService;
import sl063.web.Command;

public class SelectProduct extends Command {
	private String next;
	public SelectProduct(String next) {
		this.next = next;
	}
	/**
	*	������ ��ǰ�� �������� ��ȸ�Ѵ�.
	*	1.  listProduct.jsp�� request�� �޴´�.
	*   2.  request��ü���� "keyword" �� ���� �����´�.
	*	3.  ProductService�� selectProduct(keyword) �� ȣ���Ѵ�.
	*   4.  return  Collection ��ü�� request scope�� �����Ѵ�.
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		System.out.println("SearchKeywordProduct execute called");
		ProductService productservice = new ProductService();	

        String productId=request.getParameter("productId");
		System.out.println("################Select Product name " + productId);
        Product product = (Product)productservice.selectProduct(productId);
        request.setAttribute("product", product);

		return next;
	}
}