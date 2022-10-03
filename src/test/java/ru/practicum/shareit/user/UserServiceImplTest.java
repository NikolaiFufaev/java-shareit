package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    UserRepository userRepository;
    UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void getUsers() {
        User user = new User(1, "Bob", "bob@mail.com");
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserDto> users = userService.getUsers();
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Bob", users.get(0).getName());
    }

    @Test
    void updateUser() {
        User user = new User(1, "Bob", "bob@mail.com");
        UserDto userDto = new UserDto(1, "Ray", "ray@mail.com");
        User updatedUser = new User(1, "Ray", "ray@mail.com");

        when(userRepository.getReferenceById(anyInt())).thenReturn(user);
        when(userRepository.save(any())).thenReturn(updatedUser);

        UserDto updatedUserDto = userService.updateUser(userDto, 1);
        assertNotNull(updatedUserDto);
        assertEquals(1, updatedUserDto.getId());
        assertEquals("Ray", updatedUserDto.getName());
        assertEquals("ray@mail.com", updatedUserDto.getEmail());

        verify(userRepository, times(1)).getReferenceById(1);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void getUser() {
        User user = new User(1, "Bob", "bob@mail.com");
        when(userRepository.getReferenceById(anyInt())).thenReturn(user);
        UserDto userDto = userService.getUser(1);
        assertEquals(1, userDto.getId());
        assertEquals("Bob", userDto.getName());
        assertEquals("bob@mail.com", userDto.getEmail());
    }
}