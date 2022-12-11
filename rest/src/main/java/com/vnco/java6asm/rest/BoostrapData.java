package com.vnco.java6asm.rest;

import com.github.javafaker.Faker;
import com.vnco.common.model.image.ProductImage;
import com.vnco.java6asm.rest.entity.category.Category;
import com.vnco.java6asm.rest.entity.order.Order;
import com.vnco.java6asm.rest.entity.order.OrderDetail;
import com.vnco.java6asm.rest.entity.product.Product;
import com.vnco.java6asm.rest.entity.user.Role;
import com.vnco.java6asm.rest.entity.user.User;
import com.vnco.java6asm.rest.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Profile ("local")
public class BoostrapData {
    @Bean
    CommandLineRunner run(CategoryRepository categoryRepository, ProductRepository productRepository,
                          OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
                          UserRepository userRepository, PasswordEncoder encoder
    ) {
        return args -> {
            Faker fakerVN = new Faker(new Locale("vi"));
            Faker faker   = new Faker();
            Random random  = new Random();
            
            List<Category> categories = IntStream.range(0, 5)
                                                 .mapToObj(idx -> Category.builder()
                                                                          .name(fakerVN.commerce().department())
                                                                          .build())
                                                 .collect(Collectors.toList());
            categoryRepository.saveAll(categories);
            List<ProductImage> images = IntStream.range(100, 200)
                                                 .mapToObj(idx -> ProductImage.builder()
                                                                              .url("https://picsum.photos/" + idx)
                                                                              .build()).collect(Collectors.toList());
            List<Product> products = IntStream.range(1, 21)
                                              .mapToObj(id -> Product.builder().id((long) id)
                                                                     .name(fakerVN.commerce().productName())
                                                                     .quantity(random.nextInt(0, 10))
                                                                     .price(random.nextDouble(50_000, 100_000))
                                                                     .available(fakerVN.bool().bool())
                                                                     .createDate(fakerVN.date()
                                                                                        .between(
                                                                                                new Date(
                                                                                                        1609459200000L),
                                                                                                new Date(1669852800000L)
                                                                                        ).getTime())
                                                                     .category(categories.get(
                                                                             random.nextInt(categories.size() - 1)))
                                                                     .build())
                                              .toList();
            
            products.forEach(product -> {
                ProductImage image = images.get(faker.random().nextInt(0, images.size() - 1));
                product.addImage(image);
            });
            
            productRepository.saveAll(products);
            
            
            List<Role> roles = Arrays.asList(
                    new Role("DIRECTOR"),
                    new Role("STAFF"),
                    new Role("CUSTOMER")
            );
            List<User> userList = IntStream.rangeClosed(0, 10)
                                           .mapToObj(idx -> User.builder()
                                                                .username(faker.name().username())
                                                                .fullName(fakerVN.name().fullName())
                                                                .phone(fakerVN.phoneNumber().cellPhone())
                                                                .email(fakerVN.internet().emailAddress())
                                                                .address(fakerVN.address().fullAddress())
                                                                .password(encoder.encode(fakerVN.internet().password()))
                                                                .photo("/assets/images/user.png")
                                                                .build())
                                           .peek(user -> {
                                               user.getRoles().add(roles.get(random.nextInt(roles.size() - 1)));
                                           })
                                           .collect(Collectors.toList());
            User user1 = userList.get(0);
            user1.setUsername("vntruong");
            user1.setPassword(encoder.encode("123"));
            user1.setRoles(new HashSet<>(roles));
            
            User user2 = userList.get(1);
            user2.setUsername("haonx");
            user2.setPassword(encoder.encode("123"));
            user2.getRoles().add(roles.get(1));
            
            User user3 = userList.get(2);
            user3.setUsername("anvqt");
            user3.setPassword(encoder.encode("123"));
            user3.getRoles().add(roles.get(2));
            
            User user4 = userList.get(3);
            user3.setUsername("tult");
            user3.setPassword(encoder.encode("123"));
            user3.getRoles().add(roles.get(0));
            userRepository.saveAll(userList);
            
                        List<Order> orders = IntStream.range(0, 100).mapToObj(idx -> {
                            User customer = userList.get(faker.random().nextInt(0, userList.size() - 1));
                            Order order = Order.builder().customer(customer)
                                               .username(customer.getUsername())
                                               .email(customer.getEmail())
                                               .address(customer.getAddress())
                                               .phone(customer.getPhone())
                                               .fullName(customer.getFullName())
                                               .createTime(faker.date()
                                                                .between(new Date(1641013200000L), new Date
                                                                (1664600400000L))
                                                                .getTime())
                                               .build();

                            List<OrderDetail> orderDetails = IntStream.range(1, faker.random().nextInt(2, 10))
                                                                      .mapToObj(
                                    index -> {
                                        Product product = products.get(faker.random().nextInt(0, products.size() -
                                        1));
                                        return OrderDetail
                                                       .builder()
                                                       .order(order)
                                                       .price(product.getPrice())
                                                       .quantity(faker.random().nextInt(1, 9))
                                                       .product(product)
                                                       .productId(product.getId())
                                                       .build();
                                    }).toList();
                            order.setOrderDetails(orderDetails);
                            return order;
                        }).toList();
                        orderRepository.saveAll(orders);
        };
        
    }
}
