package ru.itis.dis403.lab2_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.dis403.lab2_6.model.BookingPersonView;
import ru.itis.dis403.lab2_6.model.Hotel;
import java.util.List;

public interface BookingPersonViewRepository extends JpaRepository<BookingPersonView, Long> {
    List<BookingPersonView> findByHotelId(Long hotelId);

    @Query("SELECT b FROM BookingPersonView b WHERE b.id = :id AND b.hotelId = :hotelId")
    BookingPersonView findByIdAndHotelId(@Param("id") Long id, @Param("hotelId") Long hotelId);
}


