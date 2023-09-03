package az.atl.msuser.service.impl;

import az.atl.msuser.dao.entity.UserEntity;
import az.atl.msuser.dao.repo.UserRepository;
import az.atl.msuser.exception.IncorrectPasswordException;
import az.atl.msuser.exception.OldAndNewPasswordException;
import az.atl.msuser.exception.PasswordNotMatchesException;
import az.atl.msuser.exception.UserNotFoundException;
import az.atl.msuser.model.dto.AdminDto;
import az.atl.msuser.model.dto.RoleDto;
import az.atl.msuser.model.dto.UpdateDto;
import az.atl.msuser.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static az.atl.msuser.model.consts.OperationMessage.USER_NOT_FOUND;
import static az.atl.msuser.model.mapper.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    Locale locale = LocaleContextHolder.getLocale();
    Object[] objects = new Object[1];

    @Override
    public List<AdminDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return INSTANCE.buildEntityToDtoList(userEntities);
    }

    @Override
    public AdminDto getUserById(Long id) {
        objects[0] = id;
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity
                .map(INSTANCE::buildEntityToDto)
                .orElseThrow(() -> new UserNotFoundException(messageSource));
//                        USER_NOT_FOUND.getMessage(), objects, locale));
//                        messageSource.getMessage(
//                        USER_NOT_FOUND.getMessage(), objects, locale)));
    }

    @Override
    public void deleteUserById(Long id) {
        objects[0] = id;
        userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(messageSource));
//                        messageSource.getMessage
//                        (USER_NOT_FOUND.getMessage(), objects, locale)));
        userRepository.deleteById(id);
        log.info("The user has been deleted id: " + id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("All users have been deleted.");
    }

    @Override
    public void updateAccount(AdminDto adminDto) {
        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder.getName()).get();

        userEntity.setName(adminDto.getName());
        userEntity.setSurname(adminDto.getSurname());
        userEntity.setUsername(adminDto.getUsername());
        userEntity.setEmail(adminDto.getEmail());
        userEntity.setJobTitle(adminDto.getJobTitle());
        userEntity.setRole(adminDto.getRole());

        userRepository.save(userEntity);
    }

    @Override
    public void updatePassword(UpdateDto updateDto) {

        Authentication contextHolder = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findByUsername(contextHolder.getName()).get();
        if (!Objects.equals(userEntity.getPassword(), updateDto.getOldPassword())) {
            throw new IncorrectPasswordException(messageSource);
        }
        if (!Objects.equals(updateDto.getNewPassword(), (updateDto.getNewPasswordAgain()))) {
            throw new PasswordNotMatchesException(messageSource);
        }
        if (userEntity.getPassword().equals(updateDto.getNewPassword())) {
            throw new OldAndNewPasswordException(messageSource);
        }
        String encodedPassword = passwordEncoder.encode(updateDto.getNewPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.save(userEntity);
        log.info("User password has been changed.");
    }

    @Override
    public void updateRole(String username, RoleDto roleDto) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(messageSource));
//                        messageSource.getMessage(
//                        USER_NOT_FOUND.getMessage(), objects, locale)));
        userEntity.setRole(roleDto.getNewRole());
        userRepository.save(userEntity);


    }
}

