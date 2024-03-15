package com.wolfcode.curriculum.controller;

import com.wolfcode.curriculum.dto.units.UnitAssessments;
import com.wolfcode.curriculum.dto.units.UnitMaterials;
import com.wolfcode.curriculum.dto.units.UnitRequest;
import com.wolfcode.curriculum.dto.units.UnitsResponse;
import com.wolfcode.curriculum.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/units")
public class UnitsController {

    private final UnitService unitService;

    @PostMapping("/{courseCode}/addUnit")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUnit(@RequestBody List<UnitRequest> unitRequest, @PathVariable String courseCode) {
        unitService.addUnit(unitRequest,courseCode);
        return "unit added successfully";
    }

    @GetMapping("course/{courseCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<UnitsResponse> getUnitByCourseCode(@PathVariable("courseCode")String courseCode){
        return unitService.getUnitByCourseCode(courseCode);
    }

    @GetMapping("/unitName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public UnitsResponse getUnitByName(@PathVariable("name") String name) {

        return unitService.getUnitByName(name);
    }

    @GetMapping("/unitCode/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public UnitsResponse getUnitByUnitCode(@PathVariable("unitCode") String unitCode) {

        return unitService.getUnitByUnitCode(unitCode);
    }

    @GetMapping("/instructor/{instructor}")
    @ResponseStatus(HttpStatus.OK)
    public List<UnitsResponse> getUnitByInstructor(@PathVariable("instructor") String instructor) {

        return unitService.getUnitByInstructor(instructor);
    }

    @DeleteMapping("/delete/{unitCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteByUnitCode(@PathVariable("unitCode") String unitCode) {
        unitService.deleteByUnitCode(unitCode);
        return "DONE!!";
    }

    @PutMapping("/update/{unitId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UnitsResponse updateUnitDetails(@PathVariable("unitId") Long unitId, @RequestBody UnitRequest unitRequest) {
        return unitService.updateUnitDetails(unitId, unitRequest);
    }

    @GetMapping("/resources/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public UnitMaterials getUnitResources(@PathVariable String unitCode){
        return unitService.getUnitResources(unitCode);
    }

    @GetMapping("/assessment/{unitCode}")
    @ResponseStatus(HttpStatus.OK)
    public UnitAssessments getUnitUnitAssessments(@PathVariable String unitCode){
        return unitService.getUnitUnitAssessments(unitCode);
    }

}