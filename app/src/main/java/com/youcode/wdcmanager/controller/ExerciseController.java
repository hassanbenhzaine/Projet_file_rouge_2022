//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.exercise.GetExerciseDto;
//import com.youcode.wdcmanager.entity.Deal;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/exercise", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
//public class ExerciseController {
//    private final ExerciseService exerciseService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<GetExerciseDto> getAll() {
//        return exerciseService.findAll().stream()
//                .map((p) -> modelMapper.map(p, GetExerciseDto.class)).toList();
//    }
//
//    @PostMapping
//    public GetExerciseDto create(@RequestBody GetExerciseDto exerciseDto) {
//        Deal deal = modelMapper.map(exerciseDto, Deal.class);
//        Deal createdDeal = exerciseService.create(deal);
//        return modelMapper.map(createdDeal, GetExerciseDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public GetExerciseDto update(@PathVariable Long id, @RequestBody Deal deal) {
//        Deal updatedDeal = exerciseService.update(deal);
//        return modelMapper.map(updatedDeal, GetExerciseDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        exerciseService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public GetExerciseDto getOne(@PathVariable Long id) {
//        Deal deal = exerciseService.findById(id);
//        return modelMapper.map(deal, GetExerciseDto.class);
//    }
//}
