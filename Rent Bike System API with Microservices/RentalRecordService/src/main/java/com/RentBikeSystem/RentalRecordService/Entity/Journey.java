package com.RentBikeSystem.RentalRecordService.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="journey")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceJouneryId")
    @SequenceGenerator(name = "SequenceJouneryId",initialValue = 1,sequenceName = "Jounery_SEQ")
    private  Long id;
    @Column(nullable = false,name = "id_user")
    private  int idUser;
    @Column(nullable = false,name = "id_bike")
    private  int idBike;

    @Enumerated(EnumType.STRING)
    private StateJounery stateJounery;
    

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "start_point_of_rental_id",nullable = false)
    private PointOfRental startPointOfRental;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "end_point_of_rental_id")
    private PointOfRental endPointOfRental;

    private LocalDateTime  startJounery;

    private LocalDateTime endJounery;

    @Transient
    private float totalHours;




}
