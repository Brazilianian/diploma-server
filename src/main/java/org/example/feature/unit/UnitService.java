package org.example.feature.unit;

import lombok.RequiredArgsConstructor;
import org.example.feature.image.Image;
import org.example.feature.image.ImageService;
import org.example.feature.order.Order;
import org.example.feature.order.OrderService;
import org.example.feature.unit.exception.UnitWasNotFoundException;
import org.example.feature.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final ImageService imageService;

    private final UnitRepository unitRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(UnitService.class);

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Unit getUnitById(UUID id) {
        return unitRepository.findById(id)
                .orElseThrow(() -> new UnitWasNotFoundException(
                        String.format("Підрозділ з ідентифікатором '%s' не знайдено.", id)
                ));
    }

    public Unit createUnit(Unit unit, User user, String image) {
        unit.getUsers().add(user);
        unit.setImage(imageService.createImage(image));
        Unit savedUnit = unitRepository.save(unit);
        LOGGER.info(
                String.format("Підрозділ %s було збережено.", savedUnit)
        );
        return savedUnit;
    }

    public void deleteUnitById(UUID id) {
        if (!isUnitExistsById(id)) {
            throw new UnitWasNotFoundException(
                    String.format("Не вдалося видалити підрозділ. Підрозділ з ідентифікатором '%s' не знайдено.", id)
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

    public void updateUnit(Unit unit) {
        Unit updatedUnit = unitRepository.save(unit);
        LOGGER.info(String.format(
                "Unit %s was successfully updated", unit
        ));
    }
}
