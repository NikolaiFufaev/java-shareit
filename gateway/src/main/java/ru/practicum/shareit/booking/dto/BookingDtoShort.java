package ru.practicum.shareit.booking.dto;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDtoShort {
    int id;
    int bookerId;
    LocalDateTime start;
    LocalDateTime end;
    BookingState status;
}
