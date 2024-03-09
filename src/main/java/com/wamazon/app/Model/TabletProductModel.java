package com.wamazon.app.Model;

public class TabletProductModel extends BaseProductModel {

	public TabletProductModel(String name, double price) {
		super(name, price);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return """
				Experience the perfect blend of versatility and portability with our range of Android tablets. 
				Whether you're browsing the web, watching your favorite shows, or getting work done on-the-go, our tablets offer the power and convenience you need to stay connected and productive.
				""";
	}

}
