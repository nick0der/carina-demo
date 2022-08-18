package com.nickpopyk.web.demo;

import com.nickpopyk.api.demo.GetCommentByIdMethod;
import com.nickpopyk.api.demo.GetCommentMethod;
import com.nickpopyk.api.demo.PostCommentMethod;
import com.nickpopyk.api.demo.pojo.Comment;
import com.nickpopyk.catapi.demo.GetBreedByIdMethod;
import com.nickpopyk.catapi.demo.GetBreedsMethod;
import com.nickpopyk.catapi.demo.SearchForBreedsByNameMethod;
import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class CatApiTest implements IAbstractTest {

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
