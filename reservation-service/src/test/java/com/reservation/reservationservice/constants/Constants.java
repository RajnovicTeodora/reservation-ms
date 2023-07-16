package com.reservation.reservationservice.constants;

<<<<<<< HEAD
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.enums.RequestStatus;

import javax.xml.crypto.Data;
import java.util.Date;

public class Constants {

    public static final Request REQUEST_1 = new Request(
            "1",
            new Date(2024,10,10),
            new Date(2024, 10, 15),
            2,
            RequestStatus.PANDING,
            false,
            "1",
            "1"

           );

    public static final Request REQUEST_2 = new Request(
            "2",
            new Date(2024,10,11),
            new Date(2024, 10, 14),
            2,
            RequestStatus.PANDING,
            true,
            "1",
            "1");

    public static final Request REQUEST_3 = new Request(
            "3",
            new Date(2024,10,7),
            new Date(2024, 10, 12),
            2,
            RequestStatus.PANDING,
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

    public static final Guest GUEST_1 = new Guest("1",3);
=======
import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Unavilability;

import java.util.Date;

public class Constants {
    public static final Unavilability UNAVILABILITY_1 = new Unavilability(
            "1",
            "1",
            new Date(2024,10,10),
            new Date(2024, 10, 15)
    );
    public static final Unavilability EXISTING_UNAVILABILITY = new Unavilability(
            "1",
            "1",
            new Date(2024,10,9),
            new Date(2024, 10, 16)
    );

    public static final Unavilability UNAVILABILITY_2 = new Unavilability(
            "2",
            "2",
            new Date(2024,10,11),
            new Date(2024, 10, 14)
    );
    public static final Unavilability UNAVILABILITY_3 = new Unavilability(
            "3",
            "3",
            new Date(2024,10,7),
            new Date(2024, 10, 12)
    );
    public static final Unavilability UNAVILABILITY_4 = new Unavilability(
            "4",
            "4",
            new Date(2024,10,13),
            new Date(2024, 10, 17)
    );
    public static final Unavilability UNAVILABILITY_5 = new Unavilability(
            "5",
            "5",
            new Date(2024,10,17),
            new Date(2024, 10, 20)
    );

    public static final UnavilabilityDTO UNAVILABILITY_DTO_1 = new UnavilabilityDTO(
            "1",
            "1",
            new Date(2024,10,10),
            new Date(2024, 10, 15)
    );
    public static final UnavilabilityDTO UNAVILABILITY_DTO_2 = new UnavilabilityDTO(
            "1",
            "1",
            new Date(2024,10,17),
            new Date(2024, 10, 20)
    );
    public static final UnavilabilityDTO UNAVILABILITY_NON_EXISTING = new UnavilabilityDTO(
            "1",
            "nonExistentId",
            new Date(2024,10,10),
            new Date(2024, 10, 15)
    );

    public static final Accomodation ACCOMODATION = new Accomodation(
            "1",
            "accomodation",
            null,
            null,
            false,
            "",
            30,
            1
    );
    public static final UnavilabilityDTO NEW_UNAVILABILITY_DTO_1 = new UnavilabilityDTO(
            null,
            "648ebee63073a2143b4f95bf",
            new Date(2024,10,17),
            new Date(2024, 10, 20)
    );
    public static final UnavilabilityDTO NEW_UNAVILABILITY_DTO_NO_ACCOMODATION = new UnavilabilityDTO(
            null,
            "sdadada",
            new Date(2024,10,17),
            new Date(2024, 10, 20)
    );
    public static final UnavilabilityDTO NEW_UNAVILABILITY_DTO_2 = new UnavilabilityDTO(
            null,
            "648ebee63073a2143b4f95bf",
            new Date(2024,11,18),
            new Date(2024, 11, 27)
    );
    public static final UnavilabilityDTO NEW_UNAVILABILITY_DTO_3 = new UnavilabilityDTO(
            null,
            "648ebee63073a2143b4f95bf",
            new Date(2024,11,19),
            new Date(2024, 11, 29)
    );
>>>>>>> develop

}
