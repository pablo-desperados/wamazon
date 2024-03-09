package com.wamazon.app.Model;

public class BaseProductFactory {

	public BaseProductModel getProduct(String type) {
		BaseProductModel newProduct;
		BaseProductRepository repository = null;

		if ("stereo".equalsIgnoreCase(type)) {
			newProduct = new StereoProductModel("Wamazon Stereo", 20);
			repository.save(newProduct);
			return newProduct;
		} else if ("pc".equalsIgnoreCase(type)) {

			newProduct = new GamingPCProductModel("Wamazon Gaming PC", 600);
			repository.save(newProduct);
			return newProduct;

		} else if ("tv".equalsIgnoreCase(type)) {

			newProduct = new PlasmaTvModel("Wamazon 80-inch TV", 900);
			repository.save(newProduct);
			return newProduct;

		} else if ("tablet".equalsIgnoreCase(type)) {
			newProduct = new TabletProductModel("Wamazon Android table", 900);
			repository.save(newProduct);
			return newProduct;
		} else {
			newProduct=null;
			return newProduct;
			
		}

	}

}
