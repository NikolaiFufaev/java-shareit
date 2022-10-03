package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    @Test
    void toUserDto() {
        User user = new User(1, "Bob", "bob@mail.com");
        Object object = UserMapper.toUserDto(user);
        assertEquals(UserDto.class, object.getClass());
        UserDto userDto = (UserDto) object;
        assertEquals(1, userDto.getId());
        assertEquals("Bob", userDto.getName());
        assertEquals("bob@mail.com", userDto.getEmail());
    }

    @Test
    void toUser() {
        UserDto userDto = new UserDto(1, "Bob", "bob@mail.com");
        Object object = UserMapper.toUser(userDto);
        assertEquals(User.class, object.getClass());
        User user = (User) object;
        assertEquals(1, user.getId());
        assertEquals("Bob", user.getName());
        assertEquals("bob@mail.com", user.getEmail());
    }
}