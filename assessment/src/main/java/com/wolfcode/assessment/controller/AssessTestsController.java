package com.wolfcode.assessment.controller;

import com.wolfcode.assessment.dto.TestAttempt;
import com.wolfcode.assessment.dto.TestRequest;
import com.wolfcode.assessment.dto.TestResponse;
import com.wolfcode.assessment.service.AssessTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assess/tests/")
public class AssessTestsController {

    private final AssessTestService assessTestService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createAssessTest(@RequestBody TestRequest testRequest){
        assessTestService.createAssessTest(testRequest);
        return "Assessment Created please add questions to it ";
    }

    @GetMapping("unit/{unitCode}")
    public List<TestResponse> getAssessmentTest(@PathVariable String unitCode){
        return assessTestService.getAssessmentTest(unitCode);
    }

    @GetMapping("{testTitle}")
    public TestResponse getAssessmentTestByTestTitle(@PathVariable String testTitle){
        return assessTestService.getAssessmentTestByTestTitle(testTitle);
    }

    //impl method to do the test providing answers and check correct answer
    @PostMapping("attempt/{id}")
    public TestAttempt attemptTest(@PathVariable("id") Long id, @RequestParam Map<Long, String> userSelections) {
        return assessTestService.attemptTest(id, userSelections);
    }

}
