package ru.practicum.shareit.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.booking.BookingDtoShort;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ItemDto {

    private final int id;

    @NotBlank(groups = {Create.class})
    private final String name;

    @NotBlank(groups = {Create.class})
    private final String description;

    @NotNull(groups = {Create.class})
    private final Boolean available;

    private final Integer requestId;

    private BookingDtoShort lastBooking;

    private BookingDtoShort nextBooking;
}
