package com.nickpopyk;

import com.nickpopyk.api.demo.catapi.GetBreedByIdMethod;
import com.nickpopyk.api.demo.catapi.GetBreedsMethod;
import com.nickpopyk.api.demo.catapi.SearchForBreedsByNameMethod;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class CatAPITest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testGetBreeds() {
        LOGGER.info("Getting all breeds...");
        GetBreedsMethod api = new GetBreedsMethod();
        api.addParameter("limit", "5");
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/breeds/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testGetBreedById() {
        String id = "abys";
        LOGGER.info("Searching for breed with id=" + id + "'...");
        GetBreedByIdMethod api = new GetBreedByIdMethod(id);
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/breeds/_getById/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testSearchBreedsByName() {
        String breed = "Siberian";
        LOGGER.info("Searching for '" + breed + "' breeds...");
        SearchForBreedsByNameMethod api = new SearchForBreedsByNameMethod(breed);
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/breeds/_get/rs.schema");
    }
}
