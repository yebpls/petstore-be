package com.bc03capstone.bc03cs.mapper;

import com.bc03capstone.bc03cs.DTO.PetImageDTO;
import com.bc03capstone.bc03cs.entity.Pet;
import com.bc03capstone.bc03cs.entity.PetImage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetImageMapper {
    @Autowired
    private ModelMapper modelMapper;

    public PetImageDTO convertToDTO(PetImage petImage) {
        PetImageDTO petImageDTO = modelMapper.map(petImage, PetImageDTO.class);
        petImageDTO.setPetId(petImage.getPet().getId());
        return petImageDTO;
    }

    public PetImage revertToEntity(PetImageDTO petImageDTO) {
        PetImage petImage = modelMapper.map(petImageDTO, PetImage.class);
        Pet pet = new Pet();
        pet.setId(petImageDTO.getPetId());
        petImage.setPet(pet);
        pet.setStatus(true);
        return petImage;
    }
}
