package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingDto;
import ru.practicum.shareit.booking.BookingService;
import ru.practicum.shareit.user.UserDto;
import ru.practicum.shareit.user.UserService;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest(
        properties = "db.name=test",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ItemServiceImplIntTest {
    private final EntityManager em;
    private final ItemService itemService;
    private final UserService userService;
    private final BookingService bookingService;

    @Test
    void getItems() {
        UserDto userDto = userService.addUser(new UserDto(1, "Ray", "ray@mail.com"));
        ItemDto itemDto = new ItemDto(1, "Hammer", "Bob's hammer",
                true,null,null,null);
        itemService.addItem(itemDto,userDto.getId());

        TypedQuery<Item> query = em.createQuery("Select item from Item item where item.owner.id = :id", Item.class);
        List<Item> items = query.setParameter("id", userDto.getId()).getResultList();
        assertEquals(1, items.size());
        assertEquals("Hammer", items.get(0).getName());
        assertEquals("Bob's hammer", items.get(0).getDescription());
    }

    @Test
    void getComments() throws InterruptedException {
        UserDto userDto1 = new UserDto(1, "Bob", "bob@mail.com");
        userService.addUser(userDto1);
        UserDto userDto2 = new UserDto(2, "Ray", "ray@mail.com");
        userService.addUser(userDto2);

        ItemDto itemDto1 = new ItemDto(1, "Hammer", "Bob's hammer",
                true,null,null,null);
        itemService.addItem(itemDto1,1);
        ItemDto itemDto2 = new ItemDto(2, "Screwdriver", "Ray's screwdriver",
                true,null,null,null);
        itemService.addItem(itemDto2,2);

        BookingDto bookingDto1 = new BookingDto(1, 2, LocalDateTime.now().plusSeconds(1),
                LocalDateTime.now().plusSeconds(2), Booking.Status.APPROVED);
        bookingService.addBooking(bookingDto1,2);
        BookingDto bookingDto2 = new BookingDto(2, 1, LocalDateTime.now().plusSeconds(1),
                LocalDateTime.now().plusSeconds(2), Booking.Status.APPROVED);
        bookingService.addBooking(bookingDto2,1);

        Thread.sleep(2000);

        CommentDto commentDto1 = new CommentDto(1,"Very heavy hammer!", "Ray", LocalDateTime.now());
        itemService.addComment(commentDto1, 1, 2);
        CommentDto commentDto2 = new CommentDto(2,"Good screwdriver!", "Bob", LocalDateTime.now());
        itemService.addComment(commentDto2, 2, 1);

        TypedQuery<Comment> query = em.createQuery("Select comment from Comment comment where comment.item.id = :id", Comment.class);
        List<Comment> comments = query.setParameter("id", itemDto1.getId()).getResultList();
        assertEquals(1, comments.size());
        assertEquals("Very heavy hammer!", comments.get(0).getText());
        assertEquals("Ray", comments.get(0).getAuthor().getName());
    }
}
