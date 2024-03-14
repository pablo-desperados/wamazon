package com.wamazon.app.Model;


public class BaseProductFactory {

	public BaseProductModel createProduct(String type, String name, double price, String description, String image) {
		BaseProductModel newProduct;
		

		if ("stereo".equalsIgnoreCase(type)) {
			newProduct = new StereoProductModel("Wamazon Stereo", 20);
			return newProduct;
		} else if ("pc".equalsIgnoreCase(type)) {

			newProduct = new GamingPCProductModel("Wamazon Gaming PC", 600);
			return newProduct;

		} else if ("tv".equalsIgnoreCase(type)) {

			newProduct = new PlasmaTvModel("Wamazon 80-inch TV", 900);
			return newProduct;

		} else if ("tablet".equalsIgnoreCase(type)) {
			newProduct = new TabletProductModel("Wamazon Android table", 900);
			return newProduct;
		} else {
			newProduct = new BaseProductModel(name, price, description, image);
			return newProduct;
			
		}

	}

}
