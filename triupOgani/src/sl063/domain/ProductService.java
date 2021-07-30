package sl063.domain;

import java.util.Collection;
import sl063.exception.RecordNotFoundException;

/**
 * This object performs a variety of procut registration services.
 * I tacts a Facade into the business logic of registering a Product
 */
public class ProductService {
    
    /**
     * This constructor creates a Registration Service object.
     */
    ProductDAO productDataAccess;
    
    public ProductService() {
        productDataAccess = new ProductDAO();
    }
    
    public Product selectProduct(String productId) throws RecordNotFoundException{
        Product product = productDataAccess.selectProduct(productId);
        return product;
    }  

	public Collection productAllList(Product product) {
		System.out.println("ProductService() :: productAllList :: " + product.getCateNum());
		return productDataAccess.productAllList(product);
	}
    
}
