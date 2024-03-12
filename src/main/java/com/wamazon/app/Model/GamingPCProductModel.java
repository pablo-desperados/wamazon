package com.wamazon.app.Model;

import jakarta.persistence.Entity;

@Entity
public class GamingPCProductModel  extends BaseProductModel {

	public GamingPCProductModel(String name, double price) {
		super(name, price);
	}
	
	public GamingPCProductModel() {};

	@Override
	public String getDescription() {
		
		return """
				Step into the world of unparalleled gaming with our top-of-the-line gaming PCs, engineered to deliver the ultimate gaming experience. 
				From immersive graphics to lightning-fast processing power, our PCs are designed to handle the most demanding games with ease, giving you the competitive edge you need.
				""";
	}

}
