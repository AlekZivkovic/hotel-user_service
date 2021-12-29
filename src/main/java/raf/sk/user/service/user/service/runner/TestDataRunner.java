package raf.sk.user.service.user.service.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.sk.user.service.user.service.domain.Admin;
import raf.sk.user.service.user.service.domain.Role;
import raf.sk.user.service.user.service.domain.User;
import raf.sk.user.service.user.service.repository.AdminRepository;
import raf.sk.user.service.user.service.repository.RoleRepositroy;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepositroy roleRepository;
    private AdminRepository adminRepository;

    public TestDataRunner(RoleRepositroy roleRepository, AdminRepository adminRepository) {
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager= new Role("ROLE_MANAGER", "Manager role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        //Insert admin
        Admin admin = new Admin();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        adminRepository.save(admin);
    }
}
