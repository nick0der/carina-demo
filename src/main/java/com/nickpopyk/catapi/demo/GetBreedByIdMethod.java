package com.nickpopyk.catapi.demo;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.qaprosoft.carina.core.foundation.api.annotation.Endpoint;
import com.qaprosoft.carina.core.foundation.api.annotation.SuccessfulHttpStatus;
import com.qaprosoft.carina.core.foundation.api.http.HttpMethodType;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.Configuration;

@Endpoint(url = "${base_url}/v1/breeds/${id}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetBreedByIdMethod extends AbstractApiMethodV2 {
    public GetBreedByIdMethod(String id){
        setHeaders("x-api-key=" +  Configuration.getEnvArg("cat_api_key"));
        replaceUrlPlaceholder("base_url", Configuration.getEnvArg("cat_api_url"));
        replaceUrlPlaceholder("id", id);
    }
}