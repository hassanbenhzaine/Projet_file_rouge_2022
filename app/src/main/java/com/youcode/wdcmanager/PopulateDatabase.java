package com.youcode.wdcmanager;

import com.youcode.wdcmanager.entity.*;
import com.youcode.wdcmanager.entity.enums.Gender;
import com.youcode.wdcmanager.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class PopulateDatabase {
    private final UserService userService;
    private final LeadGroupService leadGroupService;
    private final OrganizationService organizationService;
    private final RoleService roleService;
    private final PersonService personService;

    @Bean
    public CommandLineRunner populate(){
        return args -> {
//            Roles
            Role adminRole = Role.builder()
                    .name("ADMIN")
                    .description("Administrator")
                    .build();

            Role managerRole = Role.builder()
                    .name("MANAGER")
                    .description("Manager")
                    .build();

            Role agentRole = Role.builder()
                    .name("AGENT")
                    .description("Agent")
                    .build();

            Role clientRole = Role.builder()
                    .name("CLIENT")
                    .description("Client")
                    .build();

            roleService.createAll(List.of(adminRole, managerRole, agentRole, clientRole));

//            USERS

            List<User> users = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                User admin = User.builder()
                        .birthDay(LocalDate.now())
                        .firstName("Laraine " + i)
                        .email("LaraineRRamirez" + i + "@jourrapide.com")
                        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
                        .isActive(new Random().nextBoolean())
                        .lastName("Ramirez " + i)
                        .loginExpireAt(Instant.now().plus(new Random().nextInt(15), ChronoUnit.DAYS))
                        .password("123")
                        .phone("323-238-164 " + new Random().nextInt(9))
                        .role(adminRole)
                        .build();

                users.add(admin);
            }

            for (int i = 0; i < 5; i++) {
                User manager = User.builder()
                        .birthDay(LocalDate.now())
                        .firstName("Sabrina " + i)
                        .email("SabrinaMAdams" + i + "@teleworm.us")
                        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
                        .isActive(new Random().nextBoolean())
                        .lastName("Adams " + i)
                        .loginExpireAt(Instant.now().plus(new Random().nextInt(15), ChronoUnit.DAYS))
                        .password("123")
                        .phone("914-419-964 " + new Random().nextInt(9))
                        .role(managerRole)
                        .build();

                users.add(manager);
            }

            for (int i = 0; i < 12; i++) {
                User agent = User.builder()
                        .birthDay(LocalDate.now())
                        .firstName("Joel " + i)
                        .email("JoelDPryor" + i + "@teleworm.us")
                        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
                        .isActive(new Random().nextBoolean())
                        .lastName("Pryor " + i)
                        .loginExpireAt(Instant.now().plus(new Random().nextInt(15), ChronoUnit.DAYS))
                        .password("123")
                        .phone("772-288-135 " + new Random().nextInt(9))
                        .role(agentRole)
                        .build();

                users.add(agent);
            }

            for (int i = 0; i < 25; i++) {
                User agent = User.builder()
                        .birthDay(LocalDate.now())
                        .firstName("Priscilla " + i)
                        .email("PriscillaPSilvey" + i + "@jourrapide.com")
                        .gender(Gender.values()[new Random().nextInt(Gender.values().length)])
                        .isActive(new Random().nextBoolean())
                        .lastName("Silvey " + i)
                        .loginExpireAt(Instant.now().plus(new Random().nextInt(15), ChronoUnit.DAYS))
                        .password("123")
                        .phone("641-566-623 " + new Random().nextInt(9))
                        .role(clientRole)
                        .build();

                users.add(agent);
            }

            userService.createAll(users);

//            ORGANIZATIONS

            List<Organization> organizationList = new ArrayList<>();

            for (int i = 0; i < 17; i++) {
                Organization organization = Organization.builder()
                        .name("organization " + i)
                        .owner(users.get(new Random().nextInt(users.size())))
                        .build();

                organizationList.add(organization);
            }

            organizationService.createAll(organizationList);

//            LEAD GROUPS

            List<LeadGroup> leadGroupList = new ArrayList<>();

            LeadGroup customer = LeadGroup.builder()
                    .name("CUSTOMER")
                    .build();

            LeadGroup lead = LeadGroup.builder()
                    .name("LEAD")
                    .build();

            leadGroupList.add(customer);
            leadGroupList.add(lead);

            leadGroupService.createAll(leadGroupList);

//            PERSONS

            List<Person> personList = new ArrayList<>();

            for (int i = 0; i < 25; i++) {
                Person person = Person.builder()
                        .address("2302 Chipmunk Lane North Deering, ME 0410" + i)
                        .email("OmerDWhitehurst"+ i +"@dayrep.com")
                        .leadGroup(leadGroupList.get(new Random().nextInt(leadGroupList.size())))
                        .name("Omer D. Whitehurst " + i)
                        .organization(organizationList.get(new Random().nextInt(organizationList.size())))
                        .phone("207-797-897" + i)
                        .build();

                personList.add(person);
            }

            personService.createAll(personList);
        };
    }

}
