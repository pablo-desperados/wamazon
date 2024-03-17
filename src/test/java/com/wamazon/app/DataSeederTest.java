package com.wamazon.app;

import com.wamazon.app.Model.BaseProductFactory;
import com.wamazon.app.Model.BaseProductModel;
import com.wamazon.app.Model.BaseProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class DataSeederTest {

    @Mock
    private BaseProductRepository baseProductRepository;

    private DataSeeder dataSeeder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        dataSeeder = new DataSeeder(baseProductRepository);
    }

    @Test
    public void testSeedData() {

    	BaseProductFactory factory = new BaseProductFactory();
    	BaseProductModel stereo = factory.createProduct("stereo", null, 0, null, null);
    	BaseProductModel tv = factory.createProduct("tv", null, 0, null,null);
    	BaseProductModel tablet = factory.createProduct("tablet", null, 0, null,null);
    	BaseProductModel pc = factory.createProduct("pc", null, 0, null,null);


        when(baseProductRepository.save(any(BaseProductModel.class))).thenReturn(stereo);

        // Call the method to be tested
        dataSeeder.seedData();

        verify(baseProductRepository, times(4)).save(any(BaseProductModel.class));
    }
}
