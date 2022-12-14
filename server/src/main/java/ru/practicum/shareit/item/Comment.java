package ru.practicum.shareit.item;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments", schema = "public")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @Column(name = "text", nullable = false, length = 255)
    String text;

    @NonNull
    @ManyToOne @JoinColumn(name = "item_id", nullable = false)
    Item item;

    @NonNull
    @ManyToOne @JoinColumn(name = "author_id", nullable = false)
    User author;

    @NonNull
    @Column(name = "created")
    LocalDateTime created;
}