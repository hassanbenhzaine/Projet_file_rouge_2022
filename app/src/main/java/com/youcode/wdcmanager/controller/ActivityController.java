//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.activity.CreateActivityDto;
//import com.youcode.wdcmanager.dto.activity.GetActivityDto;
//import com.youcode.wdcmanager.dto.activity.UpdateActivityDto;
//import com.youcode.wdcmanager.entity.Proposal;
//import com.youcode.wdcmanager.service.LeadGroupService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.*;
//
//@RestController
//@RequestMapping(value = "/api/activity", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
////@Secured({"ROLE_ADMIN"})
//public class ActivityController {
//    private final LeadGroupService activityService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<GetActivityDto> getAll() {
//        return activityService.findAll().stream()
//                .map((p) -> modelMapper.map(p, GetActivityDto.class)).toList();
//    }
//
//    @PostMapping
//    public GetActivityDto create(@RequestBody CreateActivityDto createActivityDto) {
//        Proposal proposal = modelMapper.map(createActivityDto, Proposal.class);
//        Proposal createdProposal = activityService.create(proposal);
//        return modelMapper.map(createdProposal, GetActivityDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public GetActivityDto update(@PathVariable Long id, @RequestBody UpdateActivityDto updateActivityDto) {
//        Proposal proposal = modelMapper.map(updateActivityDto, Proposal.class);
//        Proposal updatedProposal = activityService.update(proposal);
//        return modelMapper.map(updatedProposal, GetActivityDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        activityService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public GetActivityDto getOne(@PathVariable Long id) {
//        Proposal foundProposal = activityService.findById(id);
//        return modelMapper.map(foundProposal, GetActivityDto.class);
//    }
//
//
//}
