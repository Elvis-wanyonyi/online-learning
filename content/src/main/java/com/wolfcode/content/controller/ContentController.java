package com.wolfcode.content.controller;

import com.wolfcode.content.dto.ContentRequest;
import com.wolfcode.content.dto.ContentResponse;
import com.wolfcode.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content/")
public class ContentController {

    private final ContentService contentService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addCourseContent(@RequestParam("file") MultipartFile file,
                                   @RequestParam("courseCode") String courseCode,
                                   @RequestParam("instructor") String instructor,
                                   @RequestParam("unitCode") String unitCode,
                                   @RequestParam("instructions") String instructions) throws IOException {
        contentService.addCourseContent(file, ContentRequest.builder()
                .unitCode(unitCode)
                        .courseCode(courseCode).instructions(instructions).instructor(instructor)
                .build());
        return "Course material saved Successfully";
    }


    @GetMapping("unit/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> getContentByUnitCode(@PathVariable String unitCode) {
        return contentService.getContentByUnitCode(unitCode);
    }

    @GetMapping("instructor/{instructor}")
    @ResponseStatus(HttpStatus.OK)
    public List<ContentResponse> getContentByInstructor(@PathVariable String instructor) {
        return contentService.getContentByInstructor(instructor);
    }

    @DeleteMapping("delete/{contentCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteContentByContentCode(@PathVariable String contentCode) {
        contentService.deleteContentByContentCode(contentCode);
        return "Done!!";
    }
}
