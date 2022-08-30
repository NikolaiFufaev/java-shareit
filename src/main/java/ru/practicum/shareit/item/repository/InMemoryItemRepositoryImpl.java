package ru.practicum.shareit.item.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.model.Item;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryItemRepositoryImpl implements ItemRepository {
    private static Long currentId = 1L;
    private final Map<Long, Item> itemMap = new HashMap<>();

    @Override
    public Item create(Item item) {
        if (itemMap.containsValue(item)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        item.setId(currentId++);
        itemMap.put(item.getId(), item);
        return Optional.of(item).orElseThrow();
    }

    @Override
    public void deleteById(Long id) {
        itemMap.remove(id);
    }

    @Override
    public Item update(Item item) {
        itemMap.put(item.getId(), item);
        return itemMap.get(item.getId());
    }

    @Override
    public Collection<Item> findAll() {
        return itemMap.values();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.of(itemMap.get(id));
    }

    @Override
    public List<Item> searchItemByNameAndDescription(Long ownerId, String text) {
        String preparedQuery = text.toUpperCase(Locale.ROOT).replaceAll("\\s+", "");
        return itemMap.values().stream().filter(i -> (i.getName().replaceAll("\\s+", "") + " "
                + i.getDescription().replaceAll("\\s+", ""))
                .toUpperCase(Locale.ROOT).contains(preparedQuery)
                && i.getAvailable()
        ).collect(Collectors.toList());
    }
}