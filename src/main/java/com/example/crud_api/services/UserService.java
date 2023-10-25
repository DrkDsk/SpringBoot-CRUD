package com.example.crud_api.services;

import com.example.crud_api.models.UserModel;
import com.example.crud_api.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public UserModel save(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public UserModel updateById(UserModel request, Long id){
        UserModel user = userRepository.findById(id).get();

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return user;
    }

    public Boolean delete(Long id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
