package com.wamazon.app;

import com.wamazon.app.Model.BaseProductRepository;
import com.wamazon.app.Model.BaseProductFactory;
import com.wamazon.app.Model.BaseProductModel;


public class DataSeeder {

    private final BaseProductRepository baseProductRepository;

    public DataSeeder(BaseProductRepository baseProductRepository) {
        this.baseProductRepository = baseProductRepository;
    }

    public void seedData() {
    	BaseProductFactory factory = new BaseProductFactory();
    	BaseProductModel stereo = factory.createProduct("stereo", null, 0, null, null);
    	BaseProductModel tv = factory.createProduct("tv", null, 0, null,null);
    	BaseProductModel tablet = factory.createProduct("tablet", null, 0, null,null);
    	BaseProductModel pc = factory.createProduct("pc", null, 0, null,null);
        
    	baseProductRepository.save(stereo);
    	baseProductRepository.save(tv);
    	baseProductRepository.save(tablet);
    	baseProductRepository.save(pc);
    	System.out.println("Data Seeded!");
    }
}