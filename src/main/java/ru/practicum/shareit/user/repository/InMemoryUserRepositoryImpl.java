package ru.practicum.shareit.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static Long currentId = 1L;
    private final Map<Long, User> userMap = new HashMap<>();

    private final UserMapper userMapper;

    @Autowired
    public InMemoryUserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User create(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        user.setId(currentId++);
        userMap.put(user.getId(), user);
        return Optional.of(user).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }

    @Override
    public User updateById(Long userId, User user) {
        if (!userMap.containsKey(userId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User existUser = userMap.get(userId);
        userMapper.updateUserFromDto(userMapper.toUserDto(user), existUser);
        userMap.put(user.getId(), existUser);
        return userMap.get(userId);

    }

    @Override
    public Collection<User> findAll() {
        return userMap.values();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userMap.get(id));
    }
}