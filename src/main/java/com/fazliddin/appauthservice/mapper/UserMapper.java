package com.fazliddin.appauthservice.mapper;

import com.fazliddin.library.entity.User;
import com.fazliddin.library.payload.UserDto;
import org.mapstruct.Mapper;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  09:02
 * @project app-fast-food
 */

@Mapper(componentModel = "spring", imports = DistrictMapper.class)
public interface UserMapper {

    UserDto toDto(User user);
}
