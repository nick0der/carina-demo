package com.nickpopyk.api.demo.githubapi;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.RequestTemplatePath;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/user", methodType = HttpMethodType.PATCH)
@RequestTemplatePath(path = "api/githubUsers/_patch/rq.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class EditAccountMethod extends AbstractApiMethodV2 {
    public EditAccountMethod(String username){
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("github_api_url"));
        setHeaders("Authorization=Bearer " + Configuration.getEnvArg("github_token"));
    }
}