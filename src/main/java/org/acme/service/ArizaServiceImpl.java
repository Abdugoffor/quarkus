package org.acme.service;

import java.util.List;
import java.util.Map;

import org.acme.dto.ArizaCreateDTO;
import org.acme.dto.ArizaFullDTO;
import org.acme.entity.Ariza;
import org.acme.repository.ArizaRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ArizaServiceImpl implements ArizaService {
    private final ArizaRepository repository;

    public ArizaServiceImpl(ArizaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public ArizaFullDTO getArizaFull(Long id) {
        Map<String, Object> row = repository.findArizaFull(id);
        if (row == null || row.isEmpty()) {
            throw new NotFoundException("Ariza topilmadi id=" + id);
        }

        ArizaFullDTO dto = new ArizaFullDTO();
        dto.id = ((Number) row.get("id")).longValue();
        dto.fio = (String) row.get("fio");
        dto.passport = (String) row.get("passport");
        dto.address = (String) row.get("address");
        dto.email = (String) row.get("email");
        dto.izoh = (String) row.get("izoh");
        dto.phone = (String) row.get("phone");

        // ✅ LocalDateTime uchun Jackson module
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            dto.mehmonhonalar = mapper.readValue(
                    row.get("mehmonhonalar").toString(),
                    new TypeReference<List<ArizaFullDTO.MehmonhonaDTO>>() {}
            );
            dto.safarlar = mapper.readValue(
                    row.get("safarlar").toString(),
                    new TypeReference<List<ArizaFullDTO.SafarDTO>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("JSON parse xatosi", e);
        }

        return dto;
    }

    @Override
    public List<Ariza> getAll() {
        return repository.listAll();
    }

    @Override
    public Ariza getById(Long id) {
        return repository.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Ariza topilmadi: id=" + id));
    }

    @Override
    @Transactional
    public Ariza create(ArizaCreateDTO dto) {
        Ariza a = new Ariza();
        a.fio = dto.fio;
        a.passport = dto.passport;
        a.address = dto.address;
        a.email = dto.email;
        a.izoh = dto.izoh;
        a.phone = dto.phone;
        repository.persist(a);
        return a;
    }

    @Override
    @Transactional
    public Ariza update(Long id, ArizaCreateDTO dto) {
        Ariza a = getById(id);
        a.fio = dto.fio;
        a.passport = dto.passport;
        a.address = dto.address;
        a.email = dto.email;
        a.izoh = dto.izoh;
        a.phone = dto.phone;
        return a;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
