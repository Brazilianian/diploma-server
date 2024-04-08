package org.example.feature.unit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exception.ValidationException;
import org.example.feature.image.Image;
import org.example.feature.image.ImageService;
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

import java.security.Principal;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/units")
public class UnitRestController {

    private final UnitMapper unitMapper;
    private final UnitService unitService;
    private final UserService userService;
    private final ImageService imageService;

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
    public UnitDto createUnit(Principal principal,
                              @RequestBody @Valid UnitCreateRequestDto unitCreateRequestDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ValidationUtil.getErrors(bindingResult);
            throw new ValidationException("Не вдалося створити підрозділ", errors);
        }

        Unit unit = unitMapper.fromCreateRequestDtoToObject(unitCreateRequestDto);
//        Image image = imageService.createImage(unitCreateRequestDto.image().content());
        User user = userService.getUserFromUserPrincipal(principal);

        unit = unitService.createUnit(unit, user, unitCreateRequestDto.image().content());
        return unitMapper.fromObjectToDto(unit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUnit(@PathVariable("id") String id) {
        unitService.deleteUnitById(UUID.fromString(id));
    }
}
