package com.pluralsight.conference.service;

import com.pluralsight.conference.entity.Type;
import com.pluralsight.conference.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getTypes(){
        return typeRepository.findAll();
    }

    public Type findByTypeId(int id) {
        return typeRepository.findByTypeId(id);
    }
}
