package com.RentBikeSystem.RentalRecordService.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="point_of_rental",uniqueConstraints = {@UniqueConstraint(columnNames = {"nameArea","address"})})
public class PointOfRental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequencePointOfRentalId")
    @SequenceGenerator(name = "SequencePointOfRentalId",initialValue = 1,sequenceName = "POINT_OF_RENTAL_SEQ")
    private  Long id;

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;

    @Column(precision = 9, scale = 6)

    private BigDecimal  latitude;
    @Column(precision = 9, scale = 6)

    private BigDecimal longitude;
    @Column(nullable = false)
    private String nameArea ;

    @OneToMany(mappedBy = "startPointOfRental", cascade = CascadeType.ALL, orphanRemoval = true,
    fetch = FetchType.LAZY)
    private List<Journey> startJourneys;

    @OneToMany(mappedBy = "endPointOfRental", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Journey> endJourneys;

}
