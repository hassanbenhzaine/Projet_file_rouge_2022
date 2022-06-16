//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.activity.UpdateActivityDto;
//import com.youcode.wdcmanager.dto.activityType.CreateActivityTypeDto;
//import com.youcode.wdcmanager.dto.activityType.GetActivityTypeDto;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/activityType", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
////@Secured({"ROLE_ADMIN"})
//public class ActivityTypeController {
//    private final ActivityTypeService activityTypeService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<GetActivityTypeDto> getAll() {
//        return activityTypeService.findAll().stream()
//                .map((p) -> modelMapper.map(p, GetActivityTypeDto.class)).toList();
//    }
//
//    @PostMapping
//    public GetActivityTypeDto create(@RequestBody CreateActivityTypeDto activityTypeDto) {
//        ActivityType activityType = modelMapper.map(activityTypeDto, ActivityType.class);
//        ActivityType createdActivityType = activityTypeService.create(activityType);
//        return modelMapper.map(createdActivityType, GetActivityTypeDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public GetActivityTypeDto update(@PathVariable Integer id, @RequestBody UpdateActivityDto updateActivityDto) {
//        ActivityType activityType = modelMapper.map(updateActivityDto, ActivityType.class);
//        ActivityType updatedActivityType = activityTypeService.update(activityType);
//        return modelMapper.map(updatedActivityType, GetActivityTypeDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        activityTypeService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public GetActivityTypeDto getOne(@PathVariable Integer id) {
//        ActivityType foundActivityType = activityTypeService.findById(id);
//        return modelMapper.map(foundActivityType, GetActivityTypeDto.class);
//    }
//
//
//}
