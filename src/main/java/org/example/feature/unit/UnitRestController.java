package org.example.feature.unit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exception.ValidationException;
import org.example.feature.unit.dto.UnitDto;
import org.example.feature.unit.dto.create.UnitCreateRequestDto;
import org.example.feature.user.Role;
import org.example.feature.user.User;
import org.example.service.UserService;
import org.example.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/units")
public class UnitRestController {

    private final UnitMapper unitMapper;
    private final UnitService unitService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @ResponseBody
    public List<UnitDto> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return unitMapper.fromObjectListToDtoList(units);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UnitDto getUnitById(@PathVariable("id") String id) {
        Unit unit = unitService.getUnitById(UUID.fromString(id));
        return unitMapper.fromObjectToDto(unit);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UnitDto createUnit(@RequestBody @Valid UnitCreateRequestDto unitCreateRequestDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ValidationUtil.getErrors(bindingResult);
            throw new ValidationException("Failed to create unit", errors);
        }

        Unit unit = unitMapper.fromCreateRequestDtoToObject(unitCreateRequestDto);

        // TODO: pass user through function
        User user = userService.getRandomUser();

        unit = unitService.createUnit(unit, user);
        return unitMapper.fromObjectToDto(unit);
    }
}
