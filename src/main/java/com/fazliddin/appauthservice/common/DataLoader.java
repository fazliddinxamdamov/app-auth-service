package com.fazliddin.appauthservice.common;

import com.fazliddin.library.entity.Permission;
import com.fazliddin.library.entity.PermissionRole;
import com.fazliddin.library.entity.Role;
import com.fazliddin.library.entity.User;
import com.fazliddin.library.enums.PermissionEnum;
import com.fazliddin.library.enums.RoleTypeEnum;
import com.fazliddin.library.repository.PermissionRepository;
import com.fazliddin.library.repository.PermissionRoleRepository;
import com.fazliddin.library.repository.RoleRepository;
import com.fazliddin.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  12:49
 * @project app-fast-food
 */
@Component
//@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionRoleRepository permissionRoleRepository;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    public DataLoader(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository, PermissionRepository permissionRepository, PermissionRoleRepository permissionRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.permissionRoleRepository = permissionRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            RoleTypeEnum[] values = RoleTypeEnum.values();

            Role sysAdminRole = null;

            PermissionEnum[] permissionEnums = PermissionEnum.values();
            List<Permission> permissionList = permissionRepository.saveAll(Arrays.stream(permissionEnums).map(Permission::new).collect(Collectors.toList()));

            List<Role> roles = new ArrayList<>();
            List<PermissionRole> permissionRoleList = new ArrayList<>();
            for (RoleTypeEnum roleTypeEnum : values) {
                if (roleTypeEnum != RoleTypeEnum.OTHER) {
                    Role role = new Role(roleTypeEnum.name(), roleTypeEnum);
                    roles.add(role);

                    List<Permission> collect;
                    if (roleTypeEnum == RoleTypeEnum.SYSTEM_ADMIN) {
                        collect = permissionList;
                        sysAdminRole = role;
                    } else {
                        collect = permissionList.stream().filter(p -> p.getName().getRoleType() == roleTypeEnum).collect(Collectors.toList());
                    }
                    permissionRoleList.addAll(collect.stream().map(p -> new PermissionRole(role, p)).collect(Collectors.toList()));
                }
            }

            roleRepository.saveAll(roles);
            permissionRoleRepository.saveAll(permissionRoleList);

            User adminUser = User.builder()
                    .setEmail("fazliddinxamdamov102@gmail.com")
                    .setFirstName("SupperSupperAdmin")
                    .setPassword(passwordEncoder.encode("690"))
                    .setPhoneNumber("+998991171148")
                    .setRole(sysAdminRole)
                    .setEnabled(true)
                    .setPermissions(new HashSet<>(permissionList))
                    .setAccountNonExpired(true)
                    .setCredentialsNonExpired(true)
                    .setAccountNonLocked(true)
                    .build();
            userRepository.save(adminUser);

        }
    }
}
