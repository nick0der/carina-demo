package com.nickpopyk.api.demo.placeholderapi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.ResponseTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/comments/${id}", methodType = HttpMethodType.DELETE)
@ResponseTemplatePath(path = "api/comments/_delete/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class DeleteCommentMethod extends AbstractApiMethodV2 {
    public DeleteCommentMethod(Integer id){
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("api_url"));
        replaceUrlPlaceholder("id", id.toString());
    }
}