package org.acme.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MehmonxonaCreateDTO {
    @NotNull(message = "Ariza ID majburiy")
    public Long arizaId;    

    @NotBlank
    public String nomi;

    @NotBlank
    public String xonaTuri;

    @NotBlank
    public String xonaRaqami;

    @Min(1)
    public Integer kunSoni;
}
