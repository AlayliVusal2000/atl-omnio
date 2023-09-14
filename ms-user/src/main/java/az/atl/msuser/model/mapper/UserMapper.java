package az.atl.msuser.model.mapper;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.model.dto.AdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AdminDto buildEntityToDto(UserEntity userEntity);

    List<AdminDto> buildEntityToDtoList(List<UserEntity> userEntity);


}
