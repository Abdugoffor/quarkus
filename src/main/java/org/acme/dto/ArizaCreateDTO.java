package org.acme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ArizaCreateDTO {
    @NotBlank(message = "FIO majburiy")
    public String fio;

    @NotBlank(message = "Passport majburiy")
    @Pattern(regexp = "\\d{14}", message = "Passport 14 ta raqam")
    public String passport;

    @NotBlank(message = "Manzil majburiy")
    @Size(min = 3, max = 100, message = "Aeroport nomi 3-200 oraligida")
    public String address;

    @Email(message = "Email notogri")
    @NotBlank(message = "Email majburiy")
    public String email;

    @Size(max = 500)
    public String izoh;

    @NotBlank(message = "Telefon majburiy 1. Namuna: 998901234567")
    @Pattern(regexp = "^998\\d{9}$", message = "Telefon notogri 2. Namuna: 998901234567")
    public String phone;

}
