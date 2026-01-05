package org.acme.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ArizaFullDTO {
    public Long id;
    public String fio;
    public String passport;
    public String address;
    public String email;
    public String izoh;
    public String phone;

    public List<MehmonhonaDTO> mehmonhonalar;
    public List<SafarDTO> safarlar;

    public static class MehmonhonaDTO {
        public Long id;
        public String nomi;
        public String turi;        // DB ustuni: xonaturi
        public String xonaRaqami;  // DB ustuni: xonaRaqami
        public Integer nechKun;    // DB ustuni: kunSoni
    }

    public static class SafarDTO {
        public Long id;
        public LocalDateTime kelishVaqti;       // DB ustuni: kelishVaqti
        public String kelishAeroporti;          // DB ustuni: kelishAeroporti
        public LocalDateTime ketishVaqti;       // DB ustuni: ketishVaqti
        public String ketishAeroporti;          // DB ustuni: ketishAeroporti
    }
}
