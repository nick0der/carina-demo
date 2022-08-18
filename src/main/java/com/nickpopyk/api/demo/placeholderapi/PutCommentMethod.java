package com.nickpopyk.api.demo.placeholderapi;

import com.nickpopyk.api.demo.placeholderapi.pojo.Comment;
import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

import java.util.Properties;

@Endpoint(url = "${base_url}/comments/${id}", methodType = HttpMethodType.PUT)
@RequestTemplatePath(path = "api/comments/_put/rq.json")
@ResponseTemplatePath(path = "api/comments/_put/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class PutCommentMethod extends AbstractApiMethodV2 {
    public PutCommentMethod(Comment comment){
        super("api/comments/_put/rq.json", "api/comments/_put/rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("id", comment.getId().toString());
        Properties properties = new Properties();
        properties.putIfAbsent("id", comment.getId());
        properties.putIfAbsent("postId", comment.getPostId());
        properties.putIfAbsent("name", comment.getName());
        properties.putIfAbsent("email", comment.getEmail());
        properties.putIfAbsent("body", comment.getBody());
        setProperties(properties);
    }
}
