package org.example.feature.unit;

import lombok.RequiredArgsConstructor;
import org.example.feature.unit.exception.UnitWasNotFoundException;
import org.example.feature.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UnitService.class);

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Unit getUnitById(UUID id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new UnitWasNotFoundException(
                        String.format("Unit with id '%s' was not found", id)
                ));
    }

    public Unit createUnit(Unit unit, User user) {
        unit.getUsers().add(user);
        Unit savedUnit = unitRepository.save(unit);
        LOGGER.info(
                String.format("Unit %s was saved", savedUnit)
        );
        return savedUnit;
    }

    public void deleteUnitById(UUID id) {
        if (!isUnitExistsById(id)) {
            throw new UnitWasNotFoundException(
                    String.format("Failed to delete unit. Unit with id '%s' was not found", id)
            );
        }

        unitRepository.deleteById(id);
        LOGGER.info(String.format(
                "Unit with id '%s' was successfully deleted", id
        ));
    }

    private boolean isUnitExistsById(UUID uuid) {
        return unitRepository.existsById(uuid);
    }
}
