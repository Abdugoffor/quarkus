package org.acme.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mehmonxonalar")
public class Mehmonxona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // FK YO‘Q!
    @NotNull(message = "Ariza ID majburiy")
    public Long arizaId;

    @NotBlank(message = "Mehmonxona nomi majburiy")
    public String nomi;

    @NotBlank(message = "Xona turi majburiy")
    public String xonaTuri;

    @NotBlank(message = "Xona raqami majburiy")
    public String xonaRaqami;

    @Min(value = 1, message = "Kamida 1 kun turishi kerak")
    public Integer kunSoni;

    public Long getId() { return id; }
}
