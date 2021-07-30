package service;

import java.util.Collection;

import entity.SearchEntity;
import sl063.domain.Product;
import sl063.exception.RecordNotFoundException;

public class SearchService {
	
	private SearchEntity entity;
	
	public SearchService() {
		entity = new SearchEntity();
		System.out.println("SearchService");
	}
	
	public Product selectProduct(String keyword) throws RecordNotFoundException{
        Product product = entity.selectProduct(keyword);
        return product;
    }
	
	public Collection listProduct() {
        return entity.listProduct();
    }
}
