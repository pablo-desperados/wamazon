package com.wamazon.app.Model;

import jakarta.persistence.Entity;

@Entity
public class StereoProductModel extends BaseProductModel {

	public StereoProductModel (String name, double price) {
		super(name, price);
	}
	
	public StereoProductModel() {};

	@Override
	public String getDescription() {
		return """
				Immerse yourself in crystal-clear sound and powerful beats with our range of cutting-edge stereo systems. 
				Whether you're hosting a party, enjoying a cozy night in, or revamping your home entertainment setup, our stereos are designed to deliver exceptional audio quality every time.
				""";
	}
	
	
}


