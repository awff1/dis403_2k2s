package ru.itis.dis403.lab2_6.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_6.dto.BookingDto;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.model.User;
import ru.itis.dis403.lab2_6.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingDto getBookingById(Long bookingId, User user) {
        Booking b = bookingRepository.findByIdAndHotelId(bookingId, user.getHotel().getId());

        BookingDto dto = new BookingDto();
        dto.setId(b.getId());
        dto.setArrivaldate(b.getArrivaldate());
        dto.setStayingdate(b.getStayingdate());
        dto.setDeparturedate(b.getDeparturedate());
        dto.setPersonId(b.getPerson().getId());
        dto.setName(b.getPerson().getName());

        return dto;
    }
}
