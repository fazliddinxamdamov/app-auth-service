package com.fazliddin.appauthservice.service;

import com.fazliddin.appauthservice.common.MessageService;
import com.fazliddin.appauthservice.exception.RestException;
import com.fazliddin.appauthservice.mapper.UserMapper;
import com.fazliddin.appauthservice.payload.AddStaffDto;
import com.fazliddin.appauthservice.payload.EditUserDto;
import com.fazliddin.appauthservice.payload.UserPrincipal;
import com.fazliddin.appauthservice.payload.UserReqDto;
import com.fazliddin.library.entity.*;
import com.fazliddin.library.enums.RoleTypeEnum;
import com.fazliddin.library.payload.ApiResult;
import com.fazliddin.library.payload.UserDto;
import com.fazliddin.library.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  08:58
 * @project app-fast-food
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;
    private final RoleRepository roleRepository;
    private final BranchRepository branchRepository;
    private final PermissionRoleRepository permissionRoleRepository;


    @Override
    public ApiResult<UserDto> me(UserPrincipal userPrincipal) {
        return ApiResult.successResponse(userMapper.toDto(userPrincipal.getUser()));
    }

    @Override
    public ApiResult<UserDto> checkAuth(UserPrincipal userPrincipal) {

        UserDto userDto = userMapper.toDto(userPrincipal.getUser());

        Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();
        Set<String> collect = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        userDto.setAuthorities(collect);
        return ApiResult.successResponse(userDto);
    }


    @Override
    public ApiResult<?> edit(UUID id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("USER"));
        District district = districtRepository.findById(userReqDto.getDistrictId()).orElseThrow(() -> RestException.notFound("DISTRICT"));
        Attachment attachment = attachmentRepository.findById(userReqDto.getPhotoId()).orElseThrow(() -> RestException.notFound("ATTACHMENT"));
        List<Address> addressList = addressRepository.findAllById(userReqDto.getDefaultAddressIds());
        if (addressList.isEmpty())
            throw RestException.notFound("ADDRESS");
        if (addressList.size() > 3)
            throw RestException.restThrow(MessageService.getMessage("DEFAULT_ADDRESS_MUST_BE_THREE"), HttpStatus.BAD_REQUEST);
        user.setFirstName(userReqDto.getFirstName());
        user.setLastName(userReqDto.getLastName());
        user.setPhoneNumber(userReqDto.getPhoneNumber());
        user.setBirthDate(userReqDto.getBirthDate());
        user.setLanguage(userReqDto.getLanguage());
        user.setPhoto(attachment);
        user.setDistrict(district);
        user.setDefaultAddress(addressList);

        userRepository.save(user);
        return ApiResult.successResponse(MessageService.successEdit("USER"));
    }

    @Override
    public ApiResult<EditUserDto> editUser(User user, EditUserDto dto) {
        User editingUser = userRepository.findById(user.getId()).orElseThrow(() -> RestException.notFound("USER"));
        editingUser.setFirstName(dto.getFirstName());
        editingUser.setLastName(dto.getLastName());
        editingUser.setPhoneNumber(dto.getPhoneNumber());
        editingUser.setBirthDate(dto.getBirthDate());
        Attachment photo = attachmentRepository.findById(dto.getPhotoId()).orElseThrow(() -> RestException.notFound("PHOTO"));
        editingUser.setPhoto(photo);
        userRepository.save(editingUser);

        return ApiResult.successResponse("Edited!");
    }

    @Override
    public ApiResult<?> addStaff(AddStaffDto staffDto) {
        Role role = roleRepository.findById(staffDto.getRoleId()).orElseThrow(() -> RestException.notFound("ROLE"));
        if (role.getRoleType().equals(RoleTypeEnum.OPERATOR)) {
            if (staffDto.getBranchIdList().size() != 1)
                throw RestException.restThrow(MessageService.getMessage("STAFF_LIST_MUST_BE_ONE"), HttpStatus.BAD_REQUEST);

        }
        List<PermissionRole> permissionRoleList = permissionRoleRepository.findAllByRoleId(role.getId());
        if (permissionRoleList.isEmpty())
            throw RestException.notFound("PERMISSION");
        List<Permission> permissions = permissionRoleList.stream().map(PermissionRole::getPermission).collect(Collectors.toList());

        List<Branch> branchList = branchRepository.findAllById(staffDto.getBranchIdList());

        User user = User.builder()
                .setFirstName(staffDto.getFirstName())
                .setLastName(staffDto.getLastName())
                .setPhoneNumber(staffDto.getPhoneNumber())
                .setRole(role)
                .setPermissions(new HashSet<>(permissions))
                .setBranches(new HashSet<>(branchList))
                .build();
        userRepository.save(user);
        return ApiResult.successResponse(MessageService.successSave("STAFF"));
    }

    @Override
    public String returnIsmi(String ismi) {

        return ismi;
    }
}
