package org.acme.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SafarCreateDTO {
    @NotNull
    public Long arizaId;

    @NotNull
    public LocalDateTime kelishVaqti;

    @NotBlank
    public String kelishAeroporti;

    @NotNull
    public LocalDateTime ketishVaqti;

    @NotBlank
    public String ketishAeroporti;
}
