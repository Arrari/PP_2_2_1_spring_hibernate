package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        userService.add(new User("User5", "Lastname1", "user1@mail.ru", new Car("Model1", 1111)));
        userService.add(new User("User6", "Lastname2", "user2@mail.ru", new Car("Model2", 2222)));
        userService.add(new User("User7", "Lastname3", "user3@mail.ru", new Car("Model3", 3333)));
        userService.add(new User("User8", "Lastname4", "user4@mail.ru", new Car("Model4", 4444)));

        System.out.println(userService.listUsers());
        System.out.println(userService.getUserByModelandSeries("Model1", 1111));

        context.close();
    }
}
