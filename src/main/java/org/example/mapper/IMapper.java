package org.example.mapper;

import java.util.List;

public interface IMapper<O, D> {
    O fromDtoToObject(D dto);
    D fromObjectToDto(O object);

    List<O> fromDtoListToObjectList(List<D> dtoList);
    List<D> fromObjectListToDtoList(List<O> objectList);
}
