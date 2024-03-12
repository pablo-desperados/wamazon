package com.wamazon.app.Model;

import jakarta.persistence.Entity;

@Entity
public class PlasmaTvModel extends BaseProductModel {

	public PlasmaTvModel(String name, double price) {
		super(name, price);
	}
	
	public PlasmaTvModel() {};
	@Override
	public String getDescription() {
		return """
				Transform your living room into a home theater with our stunning Plasma TVs, designed to deliver unrivaled picture quality and immersive entertainment experiences. 
				Whether you're watching the latest blockbuster movie or cheering for your favorite sports team, our Plasma TVs bring every detail to life with vibrant colors and deep contrasts.
				""";
	}

}
