package az.atl.msuser.service.impl;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.exception.IncorrectPasswordException;
import az.atl.msuser.exception.OldAndNewPasswordException;
import az.atl.msuser.exception.PasswordNotMatchesException;
import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.model.dto.UserDto;
import az.atl.msuser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static az.atl.msuser.model.mapper.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    @Override
    public AdminDto getMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        return INSTANCE.buildEntityToDto(entity);
    }

    @Override
    public void deleteMyAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByUsername(authentication.getName()).get();
        userRepository.delete(entity);
        log.info("Your account has been deleted.");
    }

    @Override
    public void updateMyAccount(UserDto userDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(auth.getName()).get();
        INSTANCE.buildEntityToDto(userEntity);
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setJobTitle(userDto.getJobTitle());

        userRepository.save(userEntity);

        log.info("Your account information has been updated.");
    }


    @Override
    public void updateMyPassword(UpdateDto updateDto) {
        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder.getName()).get();
        if (!userEntity.getPassword().equals(updateDto.getOldPassword())) {
            throw new IncorrectPasswordException(messageSource);
        }
        if (!updateDto.getNewPassword().equals(updateDto.getNewPasswordAgain())) {
            throw new PasswordNotMatchesException(messageSource);
        }
        if (userEntity.getPassword().equals(updateDto.getNewPassword())) {
            throw new OldAndNewPasswordException(messageSource);
        }
        String encodedPassword = passwordEncoder.encode(updateDto.getNewPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        log.info("Your password has been updated.");
    }


}
