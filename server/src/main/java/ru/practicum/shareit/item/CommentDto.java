package ru.practicum.shareit.item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CommentDto {
    int id;
    String text;
    String authorName;
    LocalDateTime created;
}
