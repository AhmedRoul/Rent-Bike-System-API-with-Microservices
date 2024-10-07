package com.RentBikeSystem.UserService.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name ="users")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private  String fName;
    private  String LName;
    @Enumerated(EnumType.ORDINAL)
    private  UserRole userRole;

    @Column(nullable = false,unique = true,insertable = false)
    private  String email;
    private String password;
}
