package com.afb.DocApp.service;

import com.afb.DocApp.domain.model.User;
import com.afb.DocApp.domain.dto.User.CreateUserResource;
import com.afb.DocApp.domain.dto.User.GetUserResource;
import com.afb.DocApp.domain.repository.UserRepository;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String ENTITY = "Usuario";
    @Autowired
    private UserRepository userRepository;

    public User save(CreateUserResource resource){
        User user = resource.convert();
        return userRepository.save(user);
    }

    public List<GetUserResource> getAllUsers(){
        List<User> users;
        users = userRepository.findAll();
        return GetUserResource.convert(users);
    }

    public GetUserResource getUser(Long id){
        Optional <User> user = userRepository.findById(id);
        if (!user.isPresent()){
            throw new ResourceNotFoundException(ENTITY ,id);
        }
        return new GetUserResource(user.get());
    }
    public Integer getTotalOfMedicalHistoriesByUser(Long userId){
        Integer total;
        total = userRepository.getTotalOfMedicalHistoriesByUser(userId);
        return total;
    }
    public void deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            throw new ResourceNotFoundException(ENTITY ,id);
        }
        userRepository.deleteById(id);
    }
}
