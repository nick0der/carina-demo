package com.nickpopyk;

import com.nickpopyk.api.demo.githubapi.EditAccountMethod;
import com.nickpopyk.api.demo.githubapi.GetUserMethod;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class GitHubAPITest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testGetUser()  {
        String username = "nick0der";
        LOGGER.info("Getting user with username=" + username + "..." );
        GetUserMethod api = new GetUserMethod(username);
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/githubUsers/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testEditUser()  {
        String username = "nick0der";
        LOGGER.info("Editing user with username=" + username + "..." );
        EditAccountMethod api = new EditAccountMethod(username);
        api.callAPIExpectSuccess();
    }
}
