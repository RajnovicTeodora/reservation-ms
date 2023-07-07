package com.reservation.reservationservice.constants;

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
}
