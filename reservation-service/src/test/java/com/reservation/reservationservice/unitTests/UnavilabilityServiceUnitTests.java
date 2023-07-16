package com.reservation.reservationservice.unitTests;

import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.DateRange;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.model.Unavilability;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import com.reservation.reservationservice.repository.UnavilabilityRepository;
import com.reservation.reservationservice.service.UnavilabilityService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.reservation.reservationservice.constants.Constants.*;
import static com.reservation.reservationservice.constants.Constants.UNAVILABILITY_4;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnavilabilityServiceUnitTests {

    @Autowired
    public UnavilabilityService unavilabilityService;

    @MockBean
    public UnavilabilityRepository unavilabilityRepository;

    @MockBean
    public AccomodationRepository accomodationRepository;
    @MockBean
    public ReservationRepository reservationRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testsaveOk() {

        List<Unavilability> unavilabilityList = new ArrayList<>();
        unavilabilityList.add(UNAVILABILITY_1);
        given(accomodationRepository.findById("1")).willReturn(Optional.of(ACCOMODATION));
        given(unavilabilityRepository.findAllByAccomodationId("1")).willReturn(unavilabilityList);//pazi na listu
        given(reservationRepository.findByAccomodation("1")).willReturn(new ArrayList<Reservation>());
        given(unavilabilityRepository.save(any())).willReturn(UNAVILABILITY_5);
        try {
            UnavilabilityDTO unavilabilityDTO = unavilabilityService.save(UNAVILABILITY_DTO_2);
            assertTrue(unavilabilityDTO.getDateFrom().equals(UNAVILABILITY_DTO_2.getDateFrom()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_save_bad_request() {

        List<Unavilability> unavilabilityList = new ArrayList<>();
        unavilabilityList.add(UNAVILABILITY_2);

        given(accomodationRepository.findById("1")).willReturn(Optional.of(ACCOMODATION));
        given(unavilabilityRepository.findAllByAccomodationId("1")).willReturn(unavilabilityList);
        given(reservationRepository.findByAccomodation("1")).willReturn(new ArrayList<Reservation>());
        given(unavilabilityRepository.save(any())).willReturn(UNAVILABILITY_1);
        assertThrows(BadRequestException.class, () -> unavilabilityService.save(UNAVILABILITY_DTO_1));
    }

    @Test
    public void test_save_InvalidId_ThrowsBadRequestException() {
        String id = "nonExistentId";
        given(accomodationRepository.findById(id)).willReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> unavilabilityService.save(UNAVILABILITY_NON_EXISTING));
    }
    @Test
    public void testChackDaysRangeDateRange(){
        assertFalse(unavilabilityService.chackDaysRange(UNAVILABILITY_DTO_1, UNAVILABILITY_2));
    }
    @Test
    public void testChackDaysRangeDateRange1(){
        assertFalse(unavilabilityService.chackDaysRange(UNAVILABILITY_DTO_1, UNAVILABILITY_3));
    }
    @Test
    public void testChackDaysRangeDateRange2(){
        assertFalse(unavilabilityService.chackDaysRange(UNAVILABILITY_DTO_1, UNAVILABILITY_4));
    }
    @Test
    public void testChackDaysRangeDateRange3(){
        assertTrue(unavilabilityService.chackDaysRange(UNAVILABILITY_DTO_1, UNAVILABILITY_5));
    }
}
