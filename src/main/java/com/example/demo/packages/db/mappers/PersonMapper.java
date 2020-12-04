package com.example.demo.packages.db.mappers;

import com.example.demo.packages.db.dto.PersonDTO;
import com.example.demo.packages.db.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );

    PersonDTO personToDTO (Person person);
    Person personDTOToPerson(PersonDTO personDTO);
}
