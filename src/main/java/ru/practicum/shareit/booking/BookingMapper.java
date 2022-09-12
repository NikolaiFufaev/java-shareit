package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import ru.practicum.shareit.Status;
import ru.practicum.shareit.item.*;
import ru.practicum.shareit.user.*;

@RequiredArgsConstructor
public class BookingMapper {

    public static BookingDtoOut toBookingDto(Booking booking) {
        return new BookingDtoOut(
                booking.getId(),
                ItemMapper.toItemDto(booking.getItem()),
                UserMapper.toUserDto(booking.getBooker()),
                booking.getStart(),
                booking.getEnd(),
                booking.getStatus()
        );
    }

    public static BookingDtoShort toBookingDtoShort(Booking booking) {
        return new BookingDtoShort(
                booking.getId(),
                booking.getBooker().getId(),
                booking.getStart(),
                booking.getEnd(),
                booking.getStatus()
        );
    }

    public static Booking toBooking(BookingDto bookingDto, Item item, User booker, Status status) {
        return new Booking(
                item,
                booker,
                bookingDto.getStart(),
                bookingDto.getEnd(),
                status
        );
    }
}