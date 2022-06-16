//package com.youcode.wdcmanager.controller;
//
//import com.youcode.wdcmanager.dto.participant.CreateParticipantDto;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@RequestMapping(value = "/api/participant", consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//@RequiredArgsConstructor
//public class ParticipantController {
//    private final ParticipantService participantService;
//    private final ModelMapper modelMapper;
//
//    @GetMapping
//    public List<CreateParticipantDto> getAll() {
//        return participantService.findAll().stream()
//                .map((p) -> modelMapper.map(p, CreateParticipantDto.class)).toList();
//    }
//
//    @PostMapping
//    public CreateParticipantDto create(@RequestBody CreateParticipantDto participantDto) {
//        Participant participant = modelMapper.map(participantDto, Participant.class);
//        Participant createdParticipant = participantService.create(participant);
//        return modelMapper.map(createdParticipant, CreateParticipantDto.class);
//    }
//
//    @PatchMapping("/{id}")
//    public CreateParticipantDto update(@PathVariable Long id, @RequestBody Participant participant) {
//        Participant updatedParticipant = participantService.update(participant);
//        return modelMapper.map(updatedParticipant, CreateParticipantDto.class);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        participantService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public CreateParticipantDto getOne(@PathVariable Long id) {
//        Participant participant = participantService.findById(id);
//        return modelMapper.map(participant, CreateParticipantDto.class);
//    }
//}
