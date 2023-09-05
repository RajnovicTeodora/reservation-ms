package com.reservation.reservationservice.constants;

import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.enums.RequestStatus;

import java.util.Date;

public class Constants {
    public static final Request  REQUEST_1 = new Request(
            "1",
            new Date(2024,10,10),
            new Date(2024, 10, 15),
            2,
            RequestStatus.PENDING,
            false,
            "1",
            "1"

    );

    public static final Request REQUEST_2 = new Request(
            "2",
            new Date(2024,10,11),
            new Date(2024, 10, 14),
            2,
            RequestStatus.PENDING,
            true,
            "1",
            "1");

    public static final Request REQUEST_3 = new Request(
            "3",
            new Date(2024,10,7),
            new Date(2024, 10, 12),
            2,
            RequestStatus.PENDING,
            false,
            "1",
            "1");
    public static final Request REQUEST_4 = new Request(
            "4",
            new Date(2024,10,13),
            new Date(2024, 10, 17),
            2,
            RequestStatus.DECLINED,
            false,
            "1",
            "1");

    public static final Request REQUEST_5 = new Request(
            "5",
            new Date(2024,10,17),
            new Date(2024, 10, 20),
            2,
            RequestStatus.APPROVED,
            false,
            "1",
            "1");

    public static final Guest GUEST_1 = new Guest("1", "guest1", 3);
}
