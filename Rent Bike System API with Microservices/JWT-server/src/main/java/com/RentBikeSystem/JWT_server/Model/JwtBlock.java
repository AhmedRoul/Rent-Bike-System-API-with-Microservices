package com.RentBikeSystem.JWT_server.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="jwt_block")
public class JwtBlock {

    @Id
    private String token;

    private String email;

    @CreationTimestamp
    private Timestamp timestamp;

    public JwtBlock(String token, String email) {
        this.token=token;
        this.email= email;
    }
}
