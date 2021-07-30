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
	*	상품의 리스트를 조회한다.
	*   1.  상품보기를 선택하는 request를 받는다.
	*   2.  return  Collection 객체를 request scope에 저장한다.
	*/
	public String execute(HttpServletRequest request,HttpServletResponse response) 
	throws Exception {
		ProductService productservice = new ProductService();	
		Collection collection = productservice.listProduct();
		request.setAttribute("ListProduct.collection", collection);

		return next;
	}
}