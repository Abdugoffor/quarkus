package org.acme.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "safarlar")
public class Safar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // FK YO‘Q!
    @NotNull(message = "Ariza ID majburiy")
    public Long arizaId;

    @NotNull(message = "Kelish vaqti majburiy")
    public LocalDateTime kelishVaqti;

    @NotBlank(message = "Kelish aeroporti majburiy")
    public String kelishAeroporti;

    @NotNull(message = "Ketish vaqti majburiy")
    public LocalDateTime ketishVaqti;

    @NotBlank(message = "Ketish aeroporti majburiy")
    public String ketishAeroporti;
    
    public Long getId() { return id; }
}
