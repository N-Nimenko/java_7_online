package module.facade.impl;

import module.dto.UserRequestDto;
import module.dto.UserResponseDto;
import module.entity.User;
import module.facade.UserFacade;
import module.service.UserService;
import module.service.impl.UserServiceImpl;
import java.util.ArrayList;

import java.util.List;

public class UserFacadeImpl implements UserFacade {

    private final UserService userService = new UserServiceImpl();

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        user.setUserName(userRequestDto.userName());
        user.setEmail(userRequestDto.email());
        user.setPassword(String.valueOf(userRequestDto.password()));
        userService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto, Long id) {
        User user = userService.findById(id);
        if (user != null) {
            user.setUserName(userRequestDto.userName());
            user.setEmail(userRequestDto.email());
            user.setPassword(String.valueOf(userRequestDto.password()));
            userService.update(user);
        }
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userService.findById(id);
        if (user != null) {
            return new UserResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getPassword());
        }
        return null;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userService.findAll();
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (User user : users) {
            responseDtos.add(new UserResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getPassword()));
        }
        return responseDtos;
    }
}
