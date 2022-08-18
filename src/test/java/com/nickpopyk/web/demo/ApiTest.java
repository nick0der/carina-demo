package com.nickpopyk.web.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nickpopyk.api.demo.*;
import com.nickpopyk.api.demo.pojo.Comment;
import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class ApiTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testCreateComment() {
        Comment comment = new Comment(1, "John", "johntest@gmail.com", "some random text");
        LOGGER.info("Creating comment...");
        PostCommentMethod api = new PostCommentMethod(comment);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testCreateCommentMissingSomeFields() {
        Comment comment = new Comment(1, "John", "johntest@gmail.com", "some random text");
        LOGGER.info("Creating comment without \"body\"...");
        PostCommentMethod api = new PostCommentMethod(comment);
        api.removeProperty("body");
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testGetAllComments() throws JsonProcessingException {
        LOGGER.info("Getting all comments...");
        GetCommentMethod api = new GetCommentMethod();
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testGetCommentById() {
        Integer id = 1;
        LOGGER.info("Getting comment by id=" + id + "..." );
        GetCommentByIdMethod api = new GetCommentByIdMethod(id);
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/comments/_getById/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testUpdateComment() throws JsonProcessingException {
        Comment comment = new Comment(1,1, "John", "johntest@gmail.com", "some random text");
        LOGGER.info("Updating comment with id=" + comment.getId() + "..." );
        PutCommentMethod api = new PutCommentMethod(comment);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "nick0der")
    public void testDeleteComment() {
        Integer id = 1;
        DeleteCommentMethod api = new DeleteCommentMethod(id);
        api.callAPIExpectSuccess();
        api.validateResponse();
    }
}
