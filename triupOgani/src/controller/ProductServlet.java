package controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sl063.domain.Product;
import sl063.domain.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Product product;
	private ProductService service;
	
    public ProductServlet() {
        super();
        service = new ProductService();
        System.out.println("ProductServlet()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cateNum = 0;
		RequestDispatcher view = null;
		
		if(request.getParameter("choice").equals("seoul")) {
			cateNum = 1;
    	} else if(request.getParameter("choice").equals("kangwon")) {  
    		cateNum = 2;
    	}
		
		product = new Product(cateNum);
		System.out.println("ProductServlet :: processRequest :: " + product.getCateNum());
		Collection collection = service.productAllList(product);
		
		try {			
			request.setAttribute("SearchListProduct.collection", collection);
			view = request.getRequestDispatcher("product/area/area.jsp");
			view.forward(request, response);
		}catch(Exception e) {
            request.setAttribute("javax.servlet.error.exception", e);
            request.setAttribute("javax.servlet.error.request_uri",request.getRequestURI());
            view = request.getRequestDispatcher("exceptionDisplay.jsp");
            view.forward(request, response);
        }
	}
	
}
