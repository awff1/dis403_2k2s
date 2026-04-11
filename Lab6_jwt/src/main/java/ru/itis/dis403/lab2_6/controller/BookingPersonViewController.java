package ru.itis.dis403.lab2_6.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.dis403.lab2_6.dto.BookingDto;
import ru.itis.dis403.lab2_6.dto.BookingPersonViewDto;
import ru.itis.dis403.lab2_6.dto.BookingsResponse;
import ru.itis.dis403.lab2_6.dto.BookingsViewResponse;
import ru.itis.dis403.lab2_6.model.Booking;
import ru.itis.dis403.lab2_6.model.BookingPersonView;
import ru.itis.dis403.lab2_6.repository.BookingPersonViewRepository;
import ru.itis.dis403.lab2_6.repository.BookingRepository;
import ru.itis.dis403.lab2_6.service.BookingPersonViewService;
import ru.itis.dis403.lab2_6.service.UserDetailImpl;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingPersonViewController {

    private final BookingPersonViewService bookingPersonViewService;
    private final BookingPersonViewRepository bookingPersonViewRepository;

    public BookingPersonViewController(BookingPersonViewService bookingPersonViewService, BookingPersonViewRepository bookingPersonViewRepository) {
        this.bookingPersonViewService = bookingPersonViewService;
        this.bookingPersonViewRepository = bookingPersonViewRepository;
    }

    @GetMapping("/allview")
    public ResponseEntity<BookingsViewResponse> getBookings() {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        List<BookingPersonViewDto> bookings = bookingPersonViewService.findByHotelId(userDetails.getUser().getHotel().getId());

        bookings.forEach(b-> System.out.println(b.getId()));

        return ResponseEntity.ok(new BookingsViewResponse(bookings));
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<BookingPersonViewDto> getBookingById(@PathVariable Long id) {

        UserDetailImpl userDetails =
                (UserDetailImpl) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        Long hotelId = userDetails.getUser().getHotel().getId();
        System.out.println(userDetails.getUser());
        BookingPersonViewDto booking = bookingPersonViewService.getBookingByIdAndHotelId(id, hotelId);
        System.out.println(booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody BookingPersonViewDto dto) {
        // 1. Ищем бронирование в базе
        // Если у тебя в контроллере есть доступ к репозиторию:
        BookingPersonView booking = bookingPersonViewRepository.findById(id).orElse(null);

        if (booking != null) {
            // 2. Обновляем поля из dto (те, что ты разрешил менять)
            booking.setArrivaldate(dto.getArrivaldate());
            booking.setBirthdate(dto.getBirthdate());
            booking.setGender(dto.getGender());
            booking.setHotelId(dto.getHotelId());
            booking.setName(dto.getName());
            booking.setRoom(dto.getRoom());
            booking.setStayingdate(dto.getStayingdate());

            // 3. Сохраняем в БД
            bookingPersonViewRepository.save(booking);
            return ResponseEntity.ok("{\"status\":\"saved\"}");
        }

        return ResponseEntity.status(404).build();
    }
}
