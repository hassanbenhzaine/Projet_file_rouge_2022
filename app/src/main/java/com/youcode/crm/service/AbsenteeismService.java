package com.youcode.crm.service;

import com.youcode.crm.entity.Absenteeism;
import com.youcode.crm.entity.Product;
import com.youcode.crm.entity.dto.AbsenteeismDTO;
import com.youcode.crm.entity.dto.ProductDTO;
import com.youcode.crm.mapper.AbsenteeismMapper;
import com.youcode.crm.repository.AbsenteeismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mateusz Milewczyk (agiklo)
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AbsenteeismService implements CurrentTimeInterface{

    /**
     * were injected by the constructor using the lombok @AllArgsContrustor annotation
     */
    private final AbsenteeismRepository absenteeismRepository;
    private final AbsenteeismMapper absenteeismMapper;

    /**
     * The method is to retrieve all absenteeisms from the database and display them.
     *
     * After downloading all the data about the absenteeism,
     * the data is mapped to dto which will display only those needed
     * @return list of all absenteeisms with specification of data in AbsenteeismsDTO
     */
    @Transactional(readOnly = true)
    public List<AbsenteeismDTO> getAllAbsenteeisms(Pageable pageable){
        return absenteeismRepository.findAllBy(pageable)
                .stream()
                .map(absenteeismMapper::mapAbsenteeismToDto)
                .collect(Collectors.toList());
    }

    /**
     * The method is to download a specific absenteeism from the database and display it.
     * After downloading all the data about the absenteeism,
     * the data is mapped to dto which will display only those needed
     *
     * @param id id of the absenteeism to be searched for
     * @throws ResponseStatusException if the id of the absenteeism you are looking for does not exist
     * @return detailed data about a specific absenteeism
     */
    @Transactional(readOnly = true)
    public AbsenteeismDTO getAbsenteeismById(Long id){
        Absenteeism absenteeism = absenteeismRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Absenteeism cannot be found, the specified id does not exist"));
        return absenteeismMapper.mapAbsenteeismToDto(absenteeism);
    }

    /**
     * The task of the method is to add a absenteeism to the database.
     * @param absenteeism requestbody of the absenteeism to be saved
     * @return saving the absenteeism to the database
     */
    public AbsenteeismDTO addNewAbsenteeism(AbsenteeismDTO absenteeism) {
        Absenteeism addedAbsenteeism = absenteeismRepository.save(absenteeismMapper.mapAbsenteeismDtoToAbsenteeism(absenteeism));
        return absenteeismMapper.mapAbsenteeismToDto(addedAbsenteeism);
    }

    /**
     * Method deletes the selected absenteeism by id
     * @param id id of the absenteeism to be deleted
     * @throws ResponseStatusException if id of the absenteeism is incorrect throws 404 status with message
     */
    public void deleteAbsenteeismById(Long id) {
        try{
            absenteeismRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Absenteeism cannot be found with id: " + id);
        }
    }

    public List<Absenteeism> addNewAbsenteeisms(Iterable<Absenteeism> absenteeisms) {
        return absenteeismRepository.saveAll(absenteeisms);
    }

    @Transactional
    public AbsenteeismDTO editAbsenteeism(AbsenteeismDTO absenteeism){
        Absenteeism mappedAbsenteeism = absenteeismMapper.mapAbsenteeismDtoToAbsenteeism(absenteeism);
        Absenteeism editedAbsenteeism = absenteeismRepository.findById(mappedAbsenteeism.getId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Absenteeism cannot be found"));

        editedAbsenteeism.setEmployee(mappedAbsenteeism.getEmployee());
        editedAbsenteeism.setReasonOfAbsenteeismCode(mappedAbsenteeism.getReasonOfAbsenteeismCode());
        editedAbsenteeism.setDateFrom(mappedAbsenteeism.getDateFrom());
        editedAbsenteeism.setDateTo(mappedAbsenteeism.getDateTo());

        return absenteeismMapper.mapAbsenteeismToDto(editedAbsenteeism);
    }
}
