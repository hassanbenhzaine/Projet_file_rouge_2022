//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.administrator.CreateAdministratorDto;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/administrator", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
////@Secured({"ROLE_ADMIN"})
//public class AdministratorController {
//    private final AdministratorService administratorService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<CreateAdministratorDto> getAll() {
//        return administratorService.findAll().stream()
//                .map((p) -> modelMapper.map(p, CreateAdministratorDto.class)).toList();
//    }
//
//    @PostMapping
//    public Administrator create(@RequestBody CreateAdministratorDto administratorDto) {
//        Administrator administrator = modelMapper.map(administratorDto, Administrator.class);
//        return administratorService.create(administrator);
//    }
//
//    @PatchMapping("/{id}")
//    public Administrator update(@PathVariable Long id, @RequestBody CreateAdministratorDto administratorDto) {
//        Administrator administrator = modelMapper.map(administratorDto, Administrator.class);
//        return administratorService.update(administrator);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        administratorService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public Administrator getOne(@PathVariable Long id) {
//        return administratorService.findById(id);
//    }
//}
