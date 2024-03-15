package com.wolfcode.curriculum.feignClients;

import com.wolfcode.curriculum.dto.units.TestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ASSESSMENT",path = "/assess/tests/")
public interface AssessmentClient {
    @GetMapping("unit/{unitCode}")
    public List<TestResponse> getAssessmentTest(@PathVariable("unitCode") String unitCode);
}
