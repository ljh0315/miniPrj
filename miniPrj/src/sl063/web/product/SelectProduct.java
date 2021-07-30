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
	*	구매한 상품의 상세정보를 조회한다.
	*	1.  listProduct.jsp로 request를 받는다.
	*   2.  request객체에서 "keyword" 인 값을 가져온다.
	*	3.  ProductService의 selectProduct(keyword) 를 호출한다.
	*   4.  return  Collection 객체를 request scope에 저장한다.
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