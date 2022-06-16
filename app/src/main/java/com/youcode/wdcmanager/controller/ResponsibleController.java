//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.responsible.CreateResponsibleDto;
//import com.youcode.wdcmanager.dto.responsible.GetResponsibleDto;
//import com.youcode.wdcmanager.dto.responsible.UpdateResponsibleDto;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/responsible", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
////@Secured({"ROLE_ADMIN"})
//public class ResponsibleController {
//    private final ResponsibleService responsibleService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<GetResponsibleDto> getAll() {
//        return responsibleService.findAll().stream()
//                .map((p) -> modelMapper.map(p, GetResponsibleDto.class)).toList();
//    }
//
//    @PostMapping
//    public CreateResponsibleDto create(@RequestBody CreateResponsibleDto responsibleDto) {
//        Responsible responsible = modelMapper.map(responsibleDto, Responsible.class);
//        Responsible createdResponsible = responsibleService.create(responsible);
//        return modelMapper.map(createdResponsible, CreateResponsibleDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public GetResponsibleDto update(@PathVariable Long id, @RequestBody UpdateResponsibleDto updateResponsibleDto) {
//        Responsible responsible = modelMapper.map(updateResponsibleDto, Responsible.class);
//        Responsible updatedResponsible = responsibleService.update(responsible);
//        return modelMapper.map(updatedResponsible, GetResponsibleDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        responsibleService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public GetResponsibleDto getOne(@PathVariable Long id) {
//        Responsible responsible = responsibleService.findById(id);
//        return modelMapper.map(responsible, GetResponsibleDto.class);
//    }
//}
