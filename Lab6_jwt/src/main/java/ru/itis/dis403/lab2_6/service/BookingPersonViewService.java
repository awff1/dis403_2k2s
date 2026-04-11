package ru.itis.dis403.lab2_6.service;

import org.springframework.stereotype.Service;
import ru.itis.dis403.lab2_6.dto.BookingDto;
import ru.itis.dis403.lab2_6.dto.BookingPersonViewDto;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.model.BookingPersonView;
import ru.itis.dis403.lab2_6.model.User;
import ru.itis.dis403.lab2_6.repository.BookingPersonViewRepository;

import java.util.List;

@Service
public class BookingPersonViewService {

    private final BookingPersonViewRepository bookingPersonViewRepository;

    public BookingPersonViewService(BookingPersonViewRepository bookingPersonViewRepository) {
        this.bookingPersonViewRepository = bookingPersonViewRepository;
    }

    public BookingPersonViewDto getBookingByIdAndHotelId(Long id, Long hotelId) {
        BookingPersonView booking = bookingPersonViewRepository.findByIdAndHotelId(id, hotelId);

        if (booking != null) {
            BookingPersonViewDto dto = new BookingPersonViewDto();
            dto.setId(booking.getId());
            dto.setArrivaldate(booking.getArrivaldate());
            dto.setStayingdate(booking.getStayingdate());
            dto.setBirthdate(booking.getBirthdate());
            dto.setGender(booking.getGender());
            dto.setHotelId(booking.getHotelId());
            dto.setName(booking.getName());
            dto.setRoom(booking.getRoom());
            return dto;
        }

        return null;
    }
    public List<BookingPersonViewDto> findByHotelId(Long hotelId) {
        return bookingPersonViewRepository.findByHotelId(hotelId).stream()
                .map(b ->
                    BookingPersonViewDto.builder()
                        .id(b.getId())
                        .arrivaldate(b.getArrivaldate())
                        .stayingdate(b.getStayingdate())
                            .room(b.getRoom())
                            .name(b.getName())
                            .birthdate(b.getBirthdate())
                            .hotelId(b.getHotelId())
                            .gender(b.getGender())
                            .build()
                ).toList();
    }
}
