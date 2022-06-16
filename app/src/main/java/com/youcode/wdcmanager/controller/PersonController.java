//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.person.GetPersonDto;
//import com.youcode.wdcmanager.dto.person.UpdatePersonDto;
//import com.youcode.wdcmanager.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/person", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
//public class PersonController {
//    private final UserService userService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<GetPersonDto> getAll() {
//        return userService.findAll().stream()
//                .map((p) -> modelMapper.map(p, GetPersonDto.class)).toList();
//    }
//
//    @PostMapping
//    public GetPersonDto create(@RequestBody GetPersonDto personDto) {
//        Person person = modelMapper.map(personDto, Person.class);
//        Person createdPerson = userService.create(person);
//        return modelMapper.map(createdPerson, GetPersonDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public GetPersonDto update(@PathVariable Long id, @RequestBody UpdatePersonDto personDto) {
//        Person person = modelMapper.map(personDto, Person.class);
//        Person updatedPerson = userService.update(person);
//        return modelMapper.map(updatedPerson, GetPersonDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        userService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public GetPersonDto getOne(@PathVariable Long id) {
//        Person person = userService.findById(id);
//        return modelMapper.map(person, GetPersonDto.class);
//    }
//
//
//}
