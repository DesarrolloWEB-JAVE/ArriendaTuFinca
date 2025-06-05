package com.arriendatufinca.arriendatufinca.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arriendatufinca.arriendatufinca.DTO.UserDTO;
import com.arriendatufinca.arriendatufinca.Entities.User;
import com.arriendatufinca.arriendatufinca.Enums.StatusEnum;
import com.arriendatufinca.arriendatufinca.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public UserDTO get(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        if(userOptional != null) {
            userDTO = modelMapper.map(userOptional.get(), UserDTO.class);
        }
        return userDTO;
    }

    public UserDTO getByUsername(String username) {
    Optional<User> userOptional = userRepository.findByUsername(username);
    UserDTO userDTO = new UserDTO();
    if (userOptional != null) {
        userDTO = modelMapper.map(userOptional.get(), UserDTO.class);
    }
    return userDTO;
}


    public Iterable<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOs;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO save(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setStatus(StatusEnum.ACTIVE);
        userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    public void createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setStatus(StatusEnum.ACTIVE);
        userRepository.save(user);
    }

   public UserDTO updateUser(Long userId, UserDTO updatedData) {

    User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException(
                    "User not found with id: " + userId));

    /* ─── Campos “simples” ─────────────────────────────────────────── */
    if (updatedData.getName() != null && !updatedData.getName().isBlank()) {
        existingUser.setName(updatedData.getName());
    }

    if (updatedData.getLastName() != null && !updatedData.getLastName().isBlank()) {
        existingUser.setLastName(updatedData.getLastName());
    }

    if (updatedData.getEmail() != null && !updatedData.getEmail().isBlank()) {
        existingUser.setEmail(updatedData.getEmail());
    }


    if (updatedData.getPassword() != null && !updatedData.getPassword().isBlank()) {
        existingUser.setPassword(updatedData.getPassword()); 
    }

    /* ─── Persistimos y devolvemos el DTO actualizado ──────────────── */
    User saved = userRepository.save(existingUser);
    return modelMapper.map(saved, UserDTO.class);
}


}
