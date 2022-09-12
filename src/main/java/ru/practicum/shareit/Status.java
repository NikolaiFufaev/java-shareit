package ru.practicum.shareit;

public enum Status {
    WAITING,    // ожидает одобрения
    APPROVED,   // подтверждено владельцем
    REJECTED,   // отклонено владельцем
    CANCELED    // отменено создателем
}