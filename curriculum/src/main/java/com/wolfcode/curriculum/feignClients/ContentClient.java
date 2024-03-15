package com.wolfcode.curriculum.feignClients;

import com.wolfcode.curriculum.dto.ContentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "CONTENT", path = "/content/")
public interface ContentClient {
    @GetMapping("unit/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> getContentByUnitCode(@PathVariable String unitCode);

}
