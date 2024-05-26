package com.ewallet.mapper;

import com.ewallet.dto.UserDto;
import com.ewallet.model.User;
import com.ewallet.request.PurchaseRequest;

public class UserMapper {
    public static User mapDtoToUserEntity(PurchaseRequest request) {
        UserDto userDto = request.getUser();
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getAge());
    }
}
