package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveAdmin(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }


    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void deleteById(ObjectId Id) {
        userRepository.deleteById(Id);
        return;
    }

    public Users findByUserName(String username){
        return userRepository.findByUserName(username);
    }
}
