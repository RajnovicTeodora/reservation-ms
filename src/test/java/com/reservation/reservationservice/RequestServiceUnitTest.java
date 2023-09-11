package com.reservation.reservationservice;

import org.springframework.boot.test.context.SpringBootTest;
import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.repository.GuestRepository;
import com.reservation.reservationservice.repository.RequestRepository;
import com.reservation.reservationservice.service.RequestService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.BDDMockito.given;
import static com.reservation.reservationservice.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequestServiceUnitTest {

    @Autowired
    public RequestService requestService;

    @MockBean
    public RequestRepository requestRepository;

    @MockBean
    public GuestRepository guestRepository;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testApproveRequestOk() {

        List<Request> requestList = new ArrayList<>();
        requestList.add(REQUEST_1);
        requestList.add(REQUEST_2);
        requestList.add(REQUEST_3);
        requestList.add(REQUEST_4);

        given(requestRepository.findById("1")).willReturn(Optional.of(REQUEST_1));
        given(requestRepository.findAllByAccomodationId("1")).willReturn(requestList);
        given(guestRepository.findById("1")).willReturn(Optional.of(GUEST_1));
        try {
            ReservationDTO reservationDTO = requestService.approveRequest("1");
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testApproveRequest_InvalidId_ThrowsBadRequestException() {
        String requestId = "nonExistentId";
        given(requestRepository.findById(requestId)).willReturn(Optional.empty());
        assertThrows(BadRequestException.class, () -> requestService.approveRequest(requestId));
    }
    @Test
    public void testChackDaysRangeDateRange(){
        assertFalse(requestService.chackDaysRangeDateRange(REQUEST_1, REQUEST_2));
    }
    @Test
    public void testChackDaysRangeDateRange1(){
        assertFalse(requestService.chackDaysRangeDateRange(REQUEST_1, REQUEST_3));
    }
    @Test
    public void testChackDaysRangeDateRange2(){
        assertFalse(requestService.chackDaysRangeDateRange(REQUEST_1, REQUEST_4));
    }
    @Test
    public void testChackDaysRangeDateRange3(){
        assertTrue(requestService.chackDaysRangeDateRange(REQUEST_1, REQUEST_5));
    }
}