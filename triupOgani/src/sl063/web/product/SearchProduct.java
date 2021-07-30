package sl063.web.product;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SearchService;

public class SearchProduct {
	private String next;
	
	public SearchProduct(String next) {
		this.next = next;
		System.out.println("SearchProduct :: " + next);
	}

	public String execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("SearchProduct :: execute");

		SearchService service = new SearchService();
		System.out.println("SearchService :: execute");
		
		Collection collection = service.listProduct();
		request.setAttribute("ListProduct.collection", collection);

		return next;
	}
}
