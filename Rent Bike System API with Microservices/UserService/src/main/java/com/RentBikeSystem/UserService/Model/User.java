package com.RentBikeSystem.UserService.Model;


import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String fName;
    private  String lName;

   /* @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")

    )*/
   @Enumerated(EnumType.STRING)

   private UserRole role;

    @Column(nullable = false,unique = true)
    private  String email;
    private String password;
}
