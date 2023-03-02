package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Model;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.dto.ModelDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.ModelRepository;
import com.eshop.eShop.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepository modelRepository,ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public ModelDto save(ModelDto modelDto) {
        Model dupModel = modelRepository.findByName(modelDto.getName());
        if(dupModel!=null){
            if(!dupModel.getIsActive()){
                dupModel.setIsActive(true);
                return toDto(modelRepository.save(dupModel));
            }
            throw new RecordAlreadyExistException(String.format("Record Already active => $s",modelDto));
        }
        modelDto.setIsActive(true);
        return toDto(modelRepository.save(toDo(modelDto)));
    }
    @Override
    public ModelDto inActive(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if(model.isPresent()){
            model.get().setIsActive(false);
            return toDto(modelRepository.save(model.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }
    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAllByIsActive(Boolean.TRUE).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ModelDto getById(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if(model.isPresent()){
            return toDto(model.get());
        } else{
          throw new RecordNotFoundException(String.format("Model not found against id ==> %d",id));
        }
    }

    @Override
    public ModelDto updateByField(Long id, Map<String, Object> fields) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Model.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, model.get(), value);
            });
            return toDto(modelRepository.save(model.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public List<ModelDto> getBySearch(String name) {
        String sName = "%"+name+"%";
        return modelRepository.findAllByIsActiveWithSearch(sName).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ModelDto> getAllWithInActive() {
        return modelRepository.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ModelDto activeCategory(Long id) {
        Optional<Model> model = modelRepository.findById(id);
        if(model.isPresent()){
            model.get().setIsActive(true);
            return toDto(modelRepository.save(model.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    public Model toDo(ModelDto modelDto){
        return modelMapper.map(modelDto,Model.class);
    }
    public ModelDto toDto(Model model){
        return modelMapper.map(model,ModelDto.class);
    }
}
