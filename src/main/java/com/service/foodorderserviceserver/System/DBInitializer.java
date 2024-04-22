package com.service.foodorderserviceserver.System;

import com.service.foodorderserviceserver.Entity.*;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.Type.Rating;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
import com.service.foodorderserviceserver.Repository.CartRepository;
import com.service.foodorderserviceserver.Repository.FeedbackRepository;
import com.service.foodorderserviceserver.Repository.ItemRepository;
import com.service.foodorderserviceserver.Repository.RestaurantRepository;
import com.service.foodorderserviceserver.Repository.User.MembershipRepository;
import com.service.foodorderserviceserver.Repository.User.UserRepository;
import com.service.foodorderserviceserver.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DBInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;
    private final MembershipRepository membershipRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final FeedbackRepository feedbackRepository;

    public DBInitializer(UserRepository userRepository,
                         UserService userService,
                         MembershipRepository membershipRepository, RestaurantRepository restaurantRepository,
                         AddressRepository addressRepository, ItemRepository itemRepository, CartRepository cartRepository, FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.membershipRepository = membershipRepository;
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
        * Initialize the database with the following data:
        * 2 membership packages (monthly and annually)
        * 5 users
        * 10 restaurants
        * 10 restaurant addresses
        * 20 customer addresses
        * 200 menu items (20 menu items per restaurant)
        * */

        /*
         ====================Create Address for Customers=====================
         */
        Address personalAddress1 = new Address();
        personalAddress1.setBname("Home Building");
        personalAddress1.setStreet("123 Main St");
        personalAddress1.setSuburb("Wollongong");
        personalAddress1.setState("State");
        personalAddress1.setPostCode("2500");
//        CustomerAddress savedCustomerAddress1 = addressRepository.save(personalAddress1);

        Address personalAddress2 = new Address();
        personalAddress2.setBname("Office Building");
        personalAddress2.setStreet("456 Main St");
        personalAddress2.setSuburb("Wollongong");
        personalAddress2.setState("State");
        personalAddress2.setPostCode("2500");
//        CustomerAddress savedCustomerAddress2 = addressRepository.save(personalAddress2);

        Address personalAddress3 = new Address();
        personalAddress3.setBname("Apartment Building");
        personalAddress3.setStreet("789 Main St");
        personalAddress3.setSuburb("Wollongong");
        personalAddress3.setState("State");
        personalAddress3.setPostCode("2500");
//        CustomerAddress savedCustomerAddress3 = addressRepository.save(personalAddress3);

        Address personalAddress4 = new Address();
        personalAddress4.setBname("Condo Building");
        personalAddress4.setStreet("321 Main St");
        personalAddress4.setSuburb("Wollongong");
        personalAddress4.setState("State");
        personalAddress4.setPostCode("2500");
//        CustomerAddress savedCustomerAddress4 = addressRepository.save(personalAddress4);

        Address personalAddress5 = new Address();
        personalAddress5.setBname("Townhouse Building");
        personalAddress5.setStreet("654 Main St");
        personalAddress5.setSuburb("Wollongong");
        personalAddress5.setState("State");
        personalAddress5.setPostCode("2500");
//        CustomerAddress savedCustomerAddress5 = addressRepository.save(personalAddress5);
        /*
         ====================Create Address for Customers (End)=====================
         */
        /*
         ====================Create 5 users=====================
         */
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUserName("johnfoodie");
        user1.setEmail("john@gmail.com");
        user1.setPassword("password");
        user1.setPhoneNumber("1234567890");
        user1.setType(Roles.USER);
        user1.addAddress(personalAddress1);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setUserName("janefoodie");
        user2.setPassword("password");
        user2.setEmail("jane@gmail.com");
        user2.setPhoneNumber("0987654321");
        user2.setType(Roles.USER);
        user2.addAddress(personalAddress2);

        User user3 = new User();
        user3.setFirstName("Alice");
        user3.setLastName("Smith");
        user3.setUserName("janefoodie");
        user3.setPassword("password");
        user3.setEmail("alice@gmail.com");
        user3.setPhoneNumber("1122334455");
        user3.setType(Roles.USER);
        user3.addAddress(personalAddress3);

        User user4 = new User();
        user4.setFirstName("Bob");
        user4.setLastName("Johnson");
        user4.setUserName("bobfoodie");
        user4.setPassword("password");
        user4.setEmail("bob@gmail.com");
        user4.setPhoneNumber("2233445566");
        user4.setType(Roles.USER);
        user4.addAddress(personalAddress4);

        User user5 = new User();
        user5.setFirstName("Charlie");
        user5.setLastName("Brown");
        user5.setPassword("password");
        user5.setUserName("charliefoodie");
        user5.setEmail("charlie@gmail.com");
        user5.setPhoneNumber("3344556677");
        user5.setType(Roles.USER);
        user5.addAddress(personalAddress5);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        Cart cart1 = new Cart();
        cart1.setUser(user1);
        cart1.setTotalPrice(0.0);
        cart1.setCreatedDate(Date.valueOf(LocalDate.now()));

        Cart cart2 = new Cart();
        cart2.setUser(user2);
        cart2.setTotalPrice(0.0);
        cart2.setCreatedDate(Date.valueOf(LocalDate.now()));

        Cart cart3 = new Cart();
        cart3.setUser(user3);
        cart3.setTotalPrice(0.0);
        cart3.setCreatedDate(Date.valueOf(LocalDate.now()));

        Cart cart4 = new Cart();
        cart4.setUser(user4);
        cart4.setTotalPrice(0.0);
        cart4.setCreatedDate(Date.valueOf(LocalDate.now()));

        Cart cart5 = new Cart();
        cart5.setUser(user5);
        cart5.setTotalPrice(0.0);
        cart5.setCreatedDate(Date.valueOf(LocalDate.now()));

        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
        cartRepository.save(cart4);
        cartRepository.save(cart5);

        /*
         ====================Create 5 users (End)=====================
         */


        /*
         ====================Create Membership for Member 4 & 5=====================
         */
        Membership membership4 = new Membership();
        membership4.setUser(user4);
        membership4.setMembershipType(MembershipType.ANNUALLY);
        membership4.setStartDate(Date.valueOf(LocalDate.now()));
        membership4.setExpirationDate(Date.valueOf(LocalDate.now().plusYears(1)));
        membershipRepository.save(membership4);

        Membership membership5 = new Membership();
        membership5.setUser(user5);
        membership5.setMembershipType(MembershipType.MONTHLY);
        membership5.setStartDate(Date.valueOf(LocalDate.now()));
        membership5.setExpirationDate(Date.valueOf(LocalDate.now().plusMonths(1)));
        membershipRepository.save(membership5);
        /*
         ====================Create Membership for Member 4 & 5 (End)=====================
         */


//        RestaurantAddress restaurantAddress1 = new RestaurantAddress();
//        restaurantAddress1.setBname("Building Name");
//        restaurantAddress1.setStreet("123 Main Street");
//        restaurantAddress1.setSuburb("Suburb Name");
//        restaurantAddress1.setState("State Name");
//        restaurantAddress1.setPostCode("12345");
//        RestaurantAddress savedRestaurantAddress1 = addressRepository.save(restaurantAddress1);
//
//        Restaurant restaurant = new Restaurant();
//        restaurant.setRestaurantName("Delicious Restaurant");
//        restaurant.setEmail("delicious@example.com");
//        restaurant.setPassword("password");
//        restaurant.setPhone("1234567890");
//        restaurant.setCuisine("Italian");
//        restaurant.setOpenTime(Time.valueOf("09:00:00")); // Assuming open time is 09:00:00 AM
//        restaurant.setCloseTime(Time.valueOf("22:00:00")); // Assuming close time is 10:00:00 PM
//        restaurant.setOpened(true);
//        restaurant.setDescription("A cozy Italian restaurant");
//        restaurant.setRoles(Roles.RESTAURANT);
//        restaurant.setAddress(savedRestaurantAddress1);
//        restaurantRepository.save(restaurant);

        /*
         ====================Create 10 restaurants=====================
         */

        // Create address for the first restaurant
        Address restaurantAddress = new Address();
        restaurantAddress.setBname("Victoria Building");
        restaurantAddress.setStreet("456 Collins St");
        restaurantAddress.setSuburb("Melbourne");
        restaurantAddress.setState("State");
        restaurantAddress.setPostCode("3000");

        // Create address for the second restaurant
        Address address2 = new Address();
        address2.setBname("Building 2");
        address2.setStreet("2 Main St");
        address2.setSuburb("Wollongong");
        address2.setState("State");
        address2.setPostCode("2500");

        // Create address for the third restaurant
        Address address3 = new Address();
        address3.setBname("Building 3");
        address3.setStreet("3 Main St");
        address3.setSuburb("Wollongong");
        address3.setState("State");
        address3.setPostCode("2500");

        // Create address for the fourth restaurant
        Address address4 = new Address();
        address4.setBname("Building 4");
        address4.setStreet("4 Main St");
        address4.setSuburb("Wollongong");
        address4.setState("State");
        address4.setPostCode("2500");

        // Create address for the fifth restaurant
        Address address5 = new Address();
        address5.setBname("Building 5");
        address5.setStreet("5 Main St");
        address5.setSuburb("Wollongong");
        address5.setState("State");
        address5.setPostCode("2500");

        // Create address for the sixth restaurant
        Address address6 = new Address();
        address6.setBname("Building 6");
        address6.setStreet("6 Main St");
        address6.setSuburb("Wollongong");
        address6.setState("State");
        address6.setPostCode("2500");

        // Create address for the seventh restaurant
        Address address7 = new Address();
        address7.setBname("Building 7");
        address7.setStreet("7 Main St");
        address7.setSuburb("Wollongong");
        address7.setState("State");
        address7.setPostCode("2500");

        // Create address for the eighth restaurant
        Address address8 = new Address();
        address8.setBname("Building 8");
        address8.setStreet("8 Main St");
        address8.setSuburb("Wollongong");
        address8.setState("State");
        address8.setPostCode("2500");


        // Create address for the ninth restaurant
        Address address9 = new Address();
        address9.setBname("Building 9");
        address9.setStreet("9 Main St");
        address9.setSuburb("Wollongong");
        address9.setState("State");
        address9.setPostCode("2500");

        // Create address for the tenth restaurant
        Address address10 = new Address();
        address10.setBname("Building 10");
        address10.setStreet("10 Main St");
        address10.setSuburb("Wollongong");
        address10.setState("State");
        address10.setPostCode("2500");

        // Save the addresses
        Address savedRestaurantAddress1 = addressRepository.save(restaurantAddress);
        Address savedRestaurantAddress2 = addressRepository.save(address2);
        Address savedRestaurantAddress3 = addressRepository.save(address3);
        Address savedRestaurantAddress4 = addressRepository.save(address4);
        Address savedRestaurantAddress5 = addressRepository.save(address5);
        Address savedRestaurantAddress6 = addressRepository.save(address6);
        Address savedRestaurantAddress7 = addressRepository.save(address7);
        Address savedRestaurantAddress8 = addressRepository.save(address8);
        Address savedRestaurantAddress9 = addressRepository.save(address9);
        Address savedRestaurantAddress10 = addressRepository.save(address10);

        // Create first restaurant
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRestaurantName("Restaurant 1");
        restaurant1.setEmail("restaurant1@gmail.com");
        restaurant1.setPassword("password");
        restaurant1.setPhone("1234567890");
        restaurant1.setCuisine("Italian");
        restaurant1.setOpenTime(Time.valueOf("08:00:00"));
        restaurant1.setCloseTime(Time.valueOf("22:00:00"));
        restaurant1.setDescription("Italian cuisine");
        restaurant1.setAddress(savedRestaurantAddress1);
        restaurant1.setRoles(Roles.RESTAURANT);

        // Create second restaurant
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRestaurantName("Restaurant 2");
        restaurant2.setEmail("restaurant2@gmail.com");
        restaurant2.setPassword("password");
        restaurant2.setPhone("0987654321");
        restaurant2.setCuisine("Chinese");
        restaurant2.setOpenTime(Time.valueOf("10:00:00"));
        restaurant2.setCloseTime(Time.valueOf("23:00:00"));
        restaurant2.setDescription("Chinese cuisine");
        restaurant2.setAddress(savedRestaurantAddress2);
        restaurant2.setRoles(Roles.RESTAURANT);

        // Create third restaurant
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setRestaurantName("Restaurant 3");
        restaurant3.setEmail("restaurant3@gmail.com");
        restaurant3.setPassword("password");
        restaurant3.setPhone("1234567891");
        restaurant3.setCuisine("Mexican");
        restaurant3.setOpenTime(Time.valueOf("09:00:00"));
        restaurant3.setCloseTime(Time.valueOf("23:00:00"));
        restaurant3.setDescription("Mexican cuisine");
        restaurant3.setAddress(savedRestaurantAddress3);
        restaurant3.setRoles(Roles.RESTAURANT);

        // Create fourth restaurant
        Restaurant restaurant4 = new Restaurant();
        restaurant4.setRestaurantName("Restaurant 4");
        restaurant4.setEmail("restaurant4@gmail.com");
        restaurant4.setPassword("password");
        restaurant4.setPhone("1234567892");
        restaurant4.setCuisine("Indian");
        restaurant4.setOpenTime(Time.valueOf("10:00:00"));
        restaurant4.setCloseTime(Time.valueOf("22:00:00"));
        restaurant4.setDescription("Indian cuisine");
        restaurant4.setAddress(savedRestaurantAddress4);
        restaurant4.setRoles(Roles.RESTAURANT);

        // Create fifth restaurant
        Restaurant restaurant5 = new Restaurant();
        restaurant5.setRestaurantName("Restaurant 5");
        restaurant5.setEmail("restaurant5@gmail.com");
        restaurant5.setPassword("password");
        restaurant5.setPhone("1234567894");
        restaurant5.setCuisine("Japanese");
        restaurant5.setOpenTime(Time.valueOf("11:00:00"));
        restaurant5.setCloseTime(Time.valueOf("23:00:00"));
        restaurant5.setDescription("Japanese cuisine");
        restaurant5.setAddress(savedRestaurantAddress5);
        restaurant5.setRoles(Roles.RESTAURANT);

        // Create sixth restaurant
        Restaurant restaurant6 = new Restaurant();
        restaurant6.setRestaurantName("Restaurant 6");
        restaurant6.setEmail("restaurant6@gmail.com");
        restaurant6.setPassword("password");
        restaurant6.setPhone("1234567895");
        restaurant6.setCuisine("French");
        restaurant6.setOpenTime(Time.valueOf("12:00:00"));
        restaurant6.setCloseTime(Time.valueOf("22:00:00"));
        restaurant6.setDescription("French cuisine");
        restaurant6.setAddress(savedRestaurantAddress6);
        restaurant6.setRoles(Roles.RESTAURANT);

        // Create seventh restaurant
        Restaurant restaurant7 = new Restaurant();
        restaurant7.setRestaurantName("Restaurant 7");
        restaurant7.setEmail("restaurant7@gmail.com");
        restaurant7.setPassword("password");
        restaurant7.setPhone("1234567896");
        restaurant7.setCuisine("Thai");
        restaurant7.setOpenTime(Time.valueOf("7:00:00"));
        restaurant7.setCloseTime(Time.valueOf("12:00:00"));
        restaurant7.setDescription("Thai cuisine");
        restaurant7.setAddress(savedRestaurantAddress7);
        restaurant7.setRoles(Roles.RESTAURANT);


        // Create eighth restaurant
        Restaurant restaurant8 = new Restaurant();
        restaurant8.setRestaurantName("Restaurant 8");
        restaurant8.setEmail("restaurant8@gmail.com");
        restaurant8.setPassword("password");
        restaurant8.setPhone("1234567897");
        restaurant8.setCuisine("Greek");
        restaurant8.setOpenTime(Time.valueOf("14:00:00"));
        restaurant8.setCloseTime(Time.valueOf("23:00:00"));
        restaurant8.setDescription("Greek cuisine");
        restaurant8.setAddress(savedRestaurantAddress8);
        restaurant8.setRoles(Roles.RESTAURANT);

        // Create ninth restaurant
        Restaurant restaurant9 = new Restaurant();
        restaurant9.setRestaurantName("Restaurant 9");
        restaurant9.setEmail("restaurant9@gmail.com");
        restaurant9.setPassword("password");
        restaurant9.setPhone("1234567898");
        restaurant9.setCuisine("Spanish");
        restaurant9.setOpenTime(Time.valueOf("15:00:00"));
        restaurant9.setCloseTime(Time.valueOf("23:00:00"));
        restaurant9.setDescription("Spanish cuisine");
        restaurant9.setAddress(savedRestaurantAddress9);
        restaurant9.setRoles(Roles.RESTAURANT);


        // Create tenth restaurant
        Restaurant restaurant10 = new Restaurant();
        restaurant10.setRestaurantName("Restaurant 10");
        restaurant10.setEmail("restaurant10@gmail.com");
        restaurant10.setPassword("password");
        restaurant10.setPhone("1234567899");
        restaurant10.setCuisine("American");
        restaurant10.setOpenTime(Time.valueOf("16:00:00"));
        restaurant10.setCloseTime(Time.valueOf("23:00:00"));
        restaurant10.setDescription("American cuisine");
        restaurant10.setAddress(savedRestaurantAddress10);
        restaurant10.setRoles(Roles.RESTAURANT);

        // Save the restaurant
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);
        restaurantRepository.save(restaurant4);
        restaurantRepository.save(restaurant5);
        restaurantRepository.save(restaurant6);
        restaurantRepository.save(restaurant7);
        restaurantRepository.save(restaurant8);
        restaurantRepository.save(restaurant9);
        restaurantRepository.save(restaurant10);
        /*
         ====================Create 10 restaurants (End)=====================
         */

        /*
         ====================Create Items for Restaurant=====================
         */
        Item item1 = new Item();
        item1.setItemName("Pizza");
        item1.setDescription("Pepperoni pizza");
        item1.setAvailable(true);
        item1.setPrice(15.5);
        item1.setItemCategory(ItemCategory.Main);
        item1.setRestaurantId(restaurant1);

        Item item2 = new Item();
        item2.setItemName("Pasta");
        item2.setDescription("Spaghetti with meatballs");
        item2.setAvailable(true);
        item2.setPrice(12.5);
        item2.setItemCategory(ItemCategory.Main);
        item2.setRestaurantId(restaurant1);

        Item item3 = new Item();
        item3.setItemName("Fried Rice");
        item3.setDescription("Fried rice with chicken");
        item3.setAvailable(true);
        item3.setPrice(10.5);
        item3.setItemCategory(ItemCategory.Main);
        item3.setRestaurantId(restaurant1);

        Item item4 = new Item();
        item4.setItemName("Sushi");
        item4.setDescription("California roll");
        item4.setAvailable(true);
        item4.setPrice(8.5);
        item4.setItemCategory(ItemCategory.Main);
        item4.setRestaurantId(restaurant1);

        Item item5 = new Item();
        item5.setItemName("Tacos");
        item5.setDescription("Beef tacos");
        item5.setAvailable(true);
        item5.setPrice(7.5);
        item5.setItemCategory(ItemCategory.Main);
        item5.setRestaurantId(restaurant2);

        Item item6 = new Item();
        item6.setItemName("Burger");
        item6.setDescription("Cheeseburger");
        item6.setAvailable(true);
        item6.setPrice(6.5);
        item6.setItemCategory(ItemCategory.Main);
        item6.setRestaurantId(restaurant2);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
        /*
         ====================Create Items for Restaurant=====================
         */


        Feedback feedback1 = new Feedback();
        feedback1.setUser(user1);
        feedback1.setRestaurant(restaurant1);
        feedback1.setContent("Tam tam");
        feedback1.setRating(Rating.TwoStar);
        feedback1.setPostDateTime(LocalDateTime.now());

        Feedback feedback2 = new Feedback();
        feedback2.setUser(user1);
        feedback2.setRestaurant(restaurant1);
        feedback2.setContent("Ok");
        feedback2.setRating(Rating.ThreeStar);
        feedback2.setPostDateTime(LocalDateTime.now());

        feedbackRepository.save(feedback1);
        feedbackRepository.save(feedback2);

    }


}
