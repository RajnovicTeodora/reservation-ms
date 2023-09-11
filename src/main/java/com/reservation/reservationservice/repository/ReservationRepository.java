package com.reservation.reservationservice.repository;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Host;
import com.reservation.reservationservice.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

import java.util.List;

@Repository
public interface ReservationRepository  extends MongoRepository<Reservation, String> {

    //List<Reservation> findByAccomodation(String id);

    List<Reservation> findByAccomodationId(String accomodationId);

    Reservation findByAccomodationIdAndDateFromAndDateTo(String id, Date dateFrom, Date dateTo);

    List<Reservation> findByGuestIdAndIsCancledFalse(String guestId);

    List<Reservation>  findByAccomodationIdAndIsCancledFalse(String accomodationId);

    List<Reservation>  findByAccomodationIdAndGuestIdAndIsCancledFalse(String accomodationId, String guestId);

//    @Query(value ="{accomodation: ?0, guest: ?1, dateTo:  { $lt: ?2 }, isCancled: ?3 }", count=true)
//    Integer getCountByAccommodationAndGuestAndDateToAndNotCancelledFinished(Accomodation accomodation, Guest guest,
//                                                                            Date dateTo, boolean cancelled);

    List<Reservation> findAllByGuestId(String id);
}
