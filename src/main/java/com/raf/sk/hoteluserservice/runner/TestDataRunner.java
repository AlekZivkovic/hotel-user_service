package com.raf.sk.hoteluserservice.runner;

import com.raf.sk.hoteluserservice.domain.*;
import com.raf.sk.hoteluserservice.repository.UserRatingRepositroy;
import com.raf.sk.hoteluserservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.raf.sk.hoteluserservice.repository.RoleRepositroy;

import java.util.Date;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepositroy roleRepository;
    private UserRepository userRepository;
    private UserRatingRepositroy userRatingRepository;

    public TestDataRunner(RoleRepositroy roleRepository, UserRepository userRepository,
                          UserRatingRepositroy userStatusRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRatingRepository = userStatusRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER", "User role");
        Role roleAdmin = new Role("ROLE_ADMIN", "Admin role");
        Role roleManager = new Role("ROLE_MANAGER", "Manager role");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        roleRepository.save(roleManager);
        //Insert admin
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        admin.setAccess(true);
        //admin.setNumberOfReservations(7);
        userRepository.save(admin);
        //Insert manager and user
        User manager = new User();
        manager.setUsername("manager");
        manager.setPassword("manager");
        manager.setRole(roleManager);
        manager.setEmail("manager@gmail.com");
        manager.setManagersInfo(new ManagersInfo("hotel1", new Date()));
        userRepository.save(manager);

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRole(roleUser);
        user.setEmail("user@gmail.com");
        user.setClientsInfo(new ClientsInfo(11234, 0));
        userRepository.save(user);
        //User statuses
        userRatingRepository.save(new UserRating(0, 5, 0));
        userRatingRepository.save(new UserRating(6, 10, 10));
        userRatingRepository.save(new UserRating(11, 20, 20));
    }
}
