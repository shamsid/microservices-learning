package com.shamsid.microservices.rest.webservices.restfulwebservices.user;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    static {
        User adam = new User();
        adam.setId(10454);
        adam.setName("Adam Jones");
        adam.setBirthDate(LocalDate.now().minusYears(20));

        User chris = new User();
        chris.setId(10455);
        chris.setName("Chris Prat");
        chris.setBirthDate(LocalDate.now().minusYears(25));

        User john = new User();
        john.setId(10456);
        john.setName("John Gates");
        john.setBirthDate(LocalDate.now().minusYears(28));

        User jacob = new User();
        jacob.setId(10457);
        jacob.setName("Jacob Millers");
        jacob.setBirthDate(LocalDate.now().minusYears(23));

        users.add(adam);
        users.add(chris);
        users.add(john);
        users.add(jacob);
    }

    public List<User> findAll(){
        return users;
    }

    public Integer save(User user){
        Integer userId = new Random(10).nextInt();
        user.setId(userId);
        users.add(user);
        return userId;
    }

    public User findOne(Integer userId){
        return users.stream().filter(
                user-> user.getId()==userId
        ).findFirst().orElse(null);
    }

    public List<User> findByName(String name){
        return users.stream().filter(
                user-> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
