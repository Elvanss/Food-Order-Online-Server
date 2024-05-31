package com.service.foodorderserviceserver.System;


import com.service.foodorderserviceserver.Entity.*;
import com.service.foodorderserviceserver.Entity.Type.CuisineType;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.Type.Rating;
import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.*;
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
    private final MembershipRepository membershipRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final FeedbackRepository feedbackRepository;
    private final CartLineItemRepository cartLineItemRepository;

    public DBInitializer(UserRepository userRepository,
                         UserService userService,
                         MembershipRepository membershipRepository, 
                         RestaurantRepository restaurantRepository,
                         AddressRepository addressRepository, 
                         ItemRepository itemRepository, 
                         CartRepository cartRepository, 
                         FeedbackRepository feedbackRepository, 
                         CartLineItemRepository cartLineItemRepository) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.feedbackRepository = feedbackRepository;
        this.cartLineItemRepository = cartLineItemRepository;
    }

    private static String URL = "/photos/";

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

//         /*
//          ====================Create Address for Customers=====================
//          */
//         Address personalAddress1 = new Address();
//         personalAddress1.setBname("Home Building");
//         personalAddress1.setStreet("123 Main St");
//         personalAddress1.setSuburb("Wollongong");
//         personalAddress1.setState("State");
//         personalAddress1.setPostCode("2500");
//         personalAddress1.setLatitude(personalAddress1.getLatitude());
//         personalAddress1.setLongitude(personalAddress1.getLongitude());
// //        CustomerAddress savedCustomerAddress1 = addressRepository.save(personalAddress1);

//         Address personalAddress2 = new Address();
//         personalAddress2.setBname("Office Building");
//         personalAddress2.setStreet("456 Main St");
//         personalAddress2.setSuburb("Wollongong");
//         personalAddress2.setState("State");
//         personalAddress2.setPostCode("2500");
// //        CustomerAddress savedCustomerAddress2 = addressRepository.save(personalAddress2);

//         Address personalAddress3 = new Address();
//         personalAddress3.setBname("Apartment Building");
//         personalAddress3.setStreet("789 Main St");
//         personalAddress3.setSuburb("Wollongong");
//         personalAddress3.setState("State");
//         personalAddress3.setPostCode("2500");
// //        CustomerAddress savedCustomerAddress3 = addressRepository.save(personalAddress3);

//         Address personalAddress4 = new Address();
//         personalAddress4.setBname("Condo Building");
//         personalAddress4.setStreet("321 Main St");
//         personalAddress4.setSuburb("Wollongong");
//         personalAddress4.setState("State");
//         personalAddress4.setPostCode("2500");
// //        CustomerAddress savedCustomerAddress4 = addressRepository.save(personalAddress4);

//         Address personalAddress5 = new Address();
//         personalAddress5.setBname("Townhouse Building");
//         personalAddress5.setStreet("654 Main St");
//         personalAddress5.setSuburb("Wollongong");
//         personalAddress5.setState("State");
//         personalAddress5.setPostCode("2500");
// //        CustomerAddress savedCustomerAddress5 = addressRepository.save(personalAddress5);

        Address personalAddress1 = new Address("Home Building", "123 Main St", "Wollongong", "State", "2500");
        Address personalAddress2 = new Address("Office Building", "456 Main St", "Wollongong", "State", "2500");
        Address personalAddress3 = new Address("Apartment Building", "789 Main St", "Wollongong", "State", "2500");
        Address personalAddress4 = new Address("Condo Building", "321 Main St", "Wollongong", "State", "2500");
        Address personalAddress5 = new Address("Townhouse Building", "654 Main St", "Wollongong", "State", "2500");
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
        user1.addAddress(personalAddress1);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setUserName("janefoodie");
        user2.setPassword("password");
        user2.setEmail("jane@gmail.com");
        user2.setPhoneNumber("0987654321");
        user2.addAddress(personalAddress2);

        User user3 = new User();
        user3.setFirstName("Alice");
        user3.setLastName("Smith");
        user3.setUserName("smithfoodie");
        user3.setPassword("password");
        user3.setEmail("alice@gmail.com");
        user3.setPhoneNumber("1122334455");
        user3.addAddress(personalAddress3);

        User user4 = new User();
        user4.setFirstName("Bob");
        user4.setLastName("Johnson");
        user4.setUserName("bobfoodie");
        user4.setPassword("password");
        user4.setEmail("bob@gmail.com");
        user4.setPhoneNumber("2233445566");
        user4.addAddress(personalAddress4);

        User user5 = new User();
        user5.setFirstName("Charlie");
        user5.setLastName("Brown");
        user5.setPassword("password");
        user5.setUserName("charliefoodie");
        user5.setEmail("charlie@gmail.com");
        user5.setPhoneNumber("3344556677");
        user5.addAddress(personalAddress5);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        Cart cart1 = new Cart();
        cart1.setUser(user1);
        cart1.setTotalPrice(0.0);

        Cart cart2 = new Cart();
        cart2.setUser(user2);
        cart2.setTotalPrice(0.0);

        Cart cart3 = new Cart();
        cart3.setUser(user3);
        cart3.setTotalPrice(0.0);

        Cart cart4 = new Cart();
        cart4.setUser(user4);
        cart4.setTotalPrice(0.0);

        Cart cart5 = new Cart();
        cart5.setUser(user5);
        cart5.setTotalPrice(0.0);

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

        // // Create address for the first restaurant
        // Address restaurantAddress = new Address();
        // restaurantAddress.setBname("Victoria Building");
        // restaurantAddress.setStreet("456 Collins St");
        // restaurantAddress.setSuburb("Melbourne");
        // restaurantAddress.setState("State");
        // restaurantAddress.setPostCode("3000");
        // restaurantAddress.setLatitude(personalAddress1.getLatitude());
        // restaurantAddress.setLongitude(personalAddress1.getLongitude());

        // // Create address for the second restaurant
        // Address address2 = new Address();
        // address2.setBname("Building 2");
        // address2.setStreet("2 Main St");
        // address2.setSuburb("Wollongong");
        // address2.setState("State");
        // address2.setPostCode("2500");
        // address2.setLatitude(address2.getLatitude());
        // address2.setLongitude(address2.getLongitude());

        // // Create address for the third restaurant
        // Address address3 = new Address();
        // address3.setBname("Building 3");
        // address3.setStreet("3 Main St");
        // address3.setSuburb("Wollongong");
        // address3.setState("State");
        // address3.setPostCode("2500");
        // address3.setLatitude(address3.getLatitude());
        // address3.setLongitude(address3.getLongitude());

        // // Create address for the fourth restaurant
        // Address address4 = new Address();
        // address4.setBname("Building 4");
        // address4.setStreet("4 Main St");
        // address4.setSuburb("Wollongong");
        // address4.setState("State");
        // address4.setPostCode("2500");

        // // Create address for the fifth restaurant
        // Address address5 = new Address();
        // address5.setBname("Building 5");
        // address5.setStreet("5 Main St");
        // address5.setSuburb("Wollongong");
        // address5.setState("State");
        // address5.setPostCode("2500");

        // // Create address for the sixth restaurant
        // Address address6 = new Address();
        // address6.setBname("Building 6");
        // address6.setStreet("6 Main St");
        // address6.setSuburb("Wollongong");
        // address6.setState("State");
        // address6.setPostCode("2500");

        // // Create address for the seventh restaurant
        // Address address7 = new Address();
        // address7.setBname("Building 7");
        // address7.setStreet("7 Main St");
        // address7.setSuburb("Wollongong");
        // address7.setState("State");
        // address7.setPostCode("2500");

        // // Create address for the eighth restaurant
        // Address address8 = new Address();
        // address8.setBname("Building 8");
        // address8.setStreet("8 Main St");
        // address8.setSuburb("Wollongong");
        // address8.setState("State");
        // address8.setPostCode("2500");


        // // Create address for the ninth restaurant
        // Address address9 = new Address();
        // address9.setBname("Building 9");
        // address9.setStreet("9 Main St");
        // address9.setSuburb("Wollongong");
        // address9.setState("State");
        // address9.setPostCode("2500");

        // // Create address for the tenth restaurant
        // Address address10 = new Address();
        // address10.setBname("Building 10");
        // address10.setStreet("10 Main St");
        // address10.setSuburb("Wollongong");
        // address10.setState("State");
        // address10.setPostCode("2500");

        // Create address for the first restaurant
        Address restaurantAddress = new Address("Victoria Building", "456 Collins St", "Melbourne", "State", "3000");
        // Create address for the second restaurant
        Address address2 = new Address("Building 2", "2 Main St", "Wollongong", "State", "2500");
        // Create address for the third restaurant
        Address address3 = new Address("Building 3", "3 Main St", "Wollongong", "State", "2500");
        // Create address for the fourth restaurant
        Address address4 = new Address("Building 4", "4 Main St", "Wollongong", "State", "2500");
        // Create address for the fifth restaurant
        Address address5 = new Address("Building 5", "5 Main St", "Wollongong", "State", "2500");
        // Create address for the sixth restaurant
        Address address6 = new Address("Building 6", "6 Main St", "Wollongong", "State", "2500");
        // Create address for the seventh restaurant
        Address address7 = new Address("Building 7", "7 Main St", "Wollongong", "State", "2500");
        // Create address for the eighth restaurant
        Address address8 = new Address("Building 8", "8 Main St", "Wollongong", "State", "2500");
        // Create address for the ninth restaurant
        Address address9 = new Address("Building 9", "9 Main St", "Wollongong", "State", "2500");
        // Create address for the tenth restaurant
        Address address10 = new Address("Building 10", "10 Main St", "Wollongong", "State", "2500");

        Address address11 = new Address("Building 11", "11 Main St", "Wollongong", "NSW", "2500");

        Address address12 = new Address("Building 12", "12 Main St", "Wollongong", "NSW", "2500");

               // Save the addresses
        // Address savedRestaurantAddress1 = addressRepository.save(restaurantAddress);
        // Address savedRestaurantAddress2 = addressRepository.save(address2);
        // Address savedRestaurantAddress3 = addressRepository.save(address3);
        // Address savedRestaurantAddress4 = addressRepository.save(address4);
        // Address savedRestaurantAddress5 = addressRepository.save(address5);
        // Address savedRestaurantAddress6 = addressRepository.save(address6);
        // Address savedRestaurantAddress7 = addressRepository.save(address7);
        // Address savedRestaurantAddress8 = addressRepository.save(address8);
        // Address savedRestaurantAddress9 = addressRepository.save(address9);
        // Address savedRestaurantAddress10 = addressRepository.save(address10);

        // Create first restaurant
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRestaurantName("Ciao Cucina");
        restaurant1.setEmail("restaurant1@gmail.com");
        restaurant1.setPassword("password");
        restaurant1.setPhone("1234567890");
        restaurant1.setCuisine(CuisineType.Italian);
        restaurant1.setOpenTime(Time.valueOf("08:00:00"));
        restaurant1.setCloseTime(Time.valueOf("22:00:00"));
        restaurant1.setDescription("Italian cuisine");
        restaurant1.addAddress(restaurantAddress);
        restaurant1.setImageUrl(URL + "restaurant1.jpg");

        // Create second restaurant
        Restaurant restaurant2 = new Restaurant();
        restaurant2.setRestaurantName("Chef's Choice");
        restaurant2.setEmail("restaurant2@gmail.com");
        restaurant2.setPassword("password");
        restaurant2.setPhone("0987654321");
        restaurant2.setCuisine(CuisineType.Chinese);
        restaurant2.setOpenTime(Time.valueOf("10:00:00"));
        restaurant2.setCloseTime(Time.valueOf("23:00:00"));
        restaurant2.setDescription("Chinese cuisine");
        restaurant2.addAddress(address2);
        restaurant2.setImageUrl(URL + "static/photos/restaurant2.jpg");

        // Create third restaurant
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setRestaurantName("Guzman y Gomez");
        restaurant3.setEmail("restaurant3@gmail.com");
        restaurant3.setPassword("password");
        restaurant3.setPhone("1234567891");
        restaurant3.setCuisine(CuisineType.Mexican);
        restaurant3.setOpenTime(Time.valueOf("09:00:00"));
        restaurant3.setCloseTime(Time.valueOf("23:00:00"));
        restaurant3.setDescription("Mexican cuisine");
        restaurant3.addAddress(address3);
        restaurant2.setImageUrl(URL + "static/photos/restaurant3.jpg");

        // Create fourth restaurant
        Restaurant restaurant4 = new Restaurant();
        restaurant4.setRestaurantName("Crown Indian");
        restaurant4.setEmail("restaurant4@gmail.com");
        restaurant4.setPassword("password");
        restaurant4.setPhone("1234567892");
        restaurant4.setCuisine(CuisineType.Indian);
        restaurant4.setOpenTime(Time.valueOf("10:00:00"));
        restaurant4.setCloseTime(Time.valueOf("22:00:00"));
        restaurant4.setDescription("Indian cuisine");
        restaurant4.addAddress(address4);
        restaurant4.setImageUrl(URL + "static/photos/restaurant4.jpg");

        // Create fifth restaurant
        Restaurant restaurant5 = new Restaurant();
        restaurant5.setRestaurantName("Sakura Sushi");
        restaurant5.setEmail("restaurant5@gmail.com");
        restaurant5.setPassword("password");
        restaurant5.setPhone("1234567894");
        restaurant5.setCuisine(CuisineType.Japanese);
        restaurant5.setOpenTime(Time.valueOf("11:00:00"));
        restaurant5.setCloseTime(Time.valueOf("23:00:00"));
        restaurant5.setDescription("Japanese cuisine");
        restaurant5.addAddress(address5);
        restaurant5.setImageUrl(URL + "static/photos/restaurant5.jpg");

        // Create sixth restaurant
        Restaurant restaurant6 = new Restaurant();
        restaurant6.setRestaurantName("Debutant");
        restaurant6.setEmail("restaurant6@gmail.com");
        restaurant6.setPassword("password");
        restaurant6.setPhone("1234567895");
        restaurant6.setCuisine(CuisineType.French);
        restaurant6.setOpenTime(Time.valueOf("12:00:00"));
        restaurant6.setCloseTime(Time.valueOf("22:00:00"));
        restaurant6.setDescription("French cuisine");
        restaurant6.addAddress(address6);
        restaurant6.setImageUrl(URL + "static/photos/restaurant6.jpg");

        // Create seventh restaurant
        Restaurant restaurant7 = new Restaurant();
        restaurant7.setRestaurantName("Jasmine Rice");
        restaurant7.setEmail("restaurant7@gmail.com");
        restaurant7.setPassword("password");
        restaurant7.setPhone("1234567896");
        restaurant7.setCuisine(CuisineType.Thai);
        restaurant7.setOpenTime(Time.valueOf("7:00:00"));
        restaurant7.setCloseTime(Time.valueOf("12:00:00"));
        restaurant7.setDescription("Thai cuisine");
        restaurant7.addAddress(address7);
        restaurant7.setImageUrl(URL + "static/photos/restaurant7.jpg");

        // Create eighth restaurant
        Restaurant restaurant8 = new Restaurant();
        restaurant8.setRestaurantName("Carnation Thai");
        restaurant8.setEmail("restaurant8@gmail.com");
        restaurant8.setPassword("password");
        restaurant8.setPhone("1234567897");
        restaurant8.setCuisine(CuisineType.Thai);
        restaurant8.setOpenTime(Time.valueOf("14:00:00"));
        restaurant8.setCloseTime(Time.valueOf("23:00:00"));
        restaurant8.setDescription("Greek cuisine");
        restaurant8.addAddress(address8);
        restaurant8.setImageUrl(URL + "static/photos/restaurant8.jpg");

        // Create ninth restaurant
        Restaurant restaurant9 = new Restaurant();
        restaurant9.setRestaurantName("Bull And Bear");
        restaurant9.setEmail("restaurant9@gmail.com");
        restaurant9.setPassword("password");
        restaurant9.setPhone("1234567898");
        restaurant9.setCuisine(CuisineType.Spanish);
        restaurant9.setOpenTime(Time.valueOf("15:00:00"));
        restaurant9.setCloseTime(Time.valueOf("23:00:00"));
        restaurant9.setDescription("Spanish cuisine");
        restaurant9.addAddress(address9);
        restaurant9.setImageUrl(URL + "static/photos/restaurant9.jpg");


        // Create tenth restaurant
        Restaurant restaurant10 = new Restaurant();
        restaurant10.setRestaurantName("His Boy Elroy");
        restaurant10.setEmail("restaurant10@gmail.com");
        restaurant10.setPassword("password");
        restaurant10.setPhone("1234567899");
        restaurant10.setCuisine(CuisineType.American);
        restaurant10.setOpenTime(Time.valueOf("16:00:00"));
        restaurant10.setCloseTime(Time.valueOf("23:00:00"));
        restaurant10.setDescription("American cuisine");
        restaurant10.addAddress(address10);
        restaurant10.setImageUrl(URL + "static/photos/restaurant10.jpg");

        Restaurant restaurant11 = new Restaurant();
        restaurant11.setRestaurantName("Wok In Wok");
        restaurant11.setEmail("restaurant11@gmail.com");
        restaurant11.setPassword("password");
        restaurant11.setPhone("1234567898");
        restaurant11.setCuisine(CuisineType.Chinese);
        restaurant11.setOpenTime(Time.valueOf("17:00:00"));
        restaurant11.setCloseTime(Time.valueOf("23:00:00"));
        restaurant11.setDescription("Chinese cuisine");
        restaurant11.addAddress(address11);
        restaurant11.setImageUrl(URL + "static/photos/restaurant11.jpg");

        Restaurant restaurant12 = new Restaurant();
        restaurant12.setRestaurantName("HongKong Chef");
        restaurant12.setEmail("restaurant12@gmail.com");
        restaurant12.setPassword("password");
        restaurant12.setPhone("1234567899");
        restaurant12.setCuisine(CuisineType.Chinese);
        restaurant12.setOpenTime(Time.valueOf("17:00:00"));
        restaurant12.setCloseTime(Time.valueOf("23:00:00"));
        restaurant12.setDescription("Chinese cuisine");
        restaurant12.addAddress(address12);
        restaurant12.setImageUrl(URL + "static/photos/restaurant12.jpg");

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
        restaurantRepository.save(restaurant11);
        restaurantRepository.save(restaurant12);
        /*
         ====================Create 10 restaurants (End)=====================
         */

        /*
         ====================Create Items for Restaurant=====================
         */
        Item item1 = new Item();
        item1.setItemName("Hawaiian Pizza");
        item1.setDescription("Pizza with pineapple");
        item1.setAvailable(true);
        item1.setPrice(15.5);
        item1.setItemCategory(ItemCategory.Main);
        item1.setImageURL(URL + "HawaiianPizza.png");
        item1.setRestaurantId(restaurant5);

        Item item2 = new Item();
        item2.setItemName("Spaghetti");
        item2.setDescription("Spaghetti with meatballs");
        item2.setAvailable(true);
        item2.setPrice(12.5);
        item2.setItemCategory(ItemCategory.Main);
        item2.setImageURL(URL + "spaghetti.png");
        item2.setRestaurantId(restaurant6);

        Item item3 = new Item();
        item3.setItemName("Fried Rice");
        item3.setDescription("Fried rice with chicken");
        item3.setAvailable(true);
        item3.setPrice(10.5);
        item3.setItemCategory(ItemCategory.Main);
        item3.setImageURL(URL + "FriedRice.png");
        item3.setRestaurantId(restaurant7);

        Item item4 = new Item();
        item4.setItemName("Sushi");
        item4.setDescription("California roll");
        item4.setAvailable(true);
        item4.setPrice(8.5);
        item4.setItemCategory(ItemCategory.Main);
        item4.setImageURL(URL + "sushi.png");
        item4.setRestaurantId(restaurant8);

        Item item5 = new Item();
        item5.setItemName("Udon");
        item5.setDescription("Tasty Japanese noodle");
        item5.setAvailable(true);
        item5.setPrice(7.5);
        item5.setItemCategory(ItemCategory.Noodle);
        item5.setImageURL(URL + "udon.png");
        item5.setRestaurantId(restaurant9);

        Item item6 = new Item();
        item6.setItemName("Burger");
        item6.setDescription("Cheeseburger");
        item6.setAvailable(true);
        item6.setPrice(6.5);
        item6.setItemCategory(ItemCategory.Main);
        item6.setImageURL(URL + "burger.png");
        item6.setRestaurantId(restaurant10);

        Item item7 = new Item();
        item7.setItemName("Pizza");
        item7.setDescription("Delicious Pizza with various toppings");
        item7.setAvailable(true);
        item7.setPrice(8.5);
        item7.setItemCategory(ItemCategory.Main);
        item7.setImageURL(URL + "HawaiianPizza.png");
        item7.setRestaurantId(restaurant1);

        Item item8 = new Item();
        item8.setItemName("Pasta");
        item8.setDescription("Classic Italian Pasta");
        item8.setAvailable(true);
        item8.setPrice(12.0);
        item8.setItemCategory(ItemCategory.Main);
        item8.setImageURL(URL + "pasta.png");
        item8.setRestaurantId(restaurant2);

        Item item9 = new Item();
        item9.setItemName("Chicken Wings");
        item9.setDescription("Spicy Chicken Wings");
        item9.setAvailable(true);
        item9.setPrice(7.0);
        item9.setItemCategory(ItemCategory.Starter);
        item9.setImageURL(URL + "chickenWing.png");
        item9.setRestaurantId(restaurant3);

        Item item10 = new Item();
        item10.setItemName("Steak");
        item10.setDescription("Juicy grilled Steak");
        item10.setAvailable(true);
        item10.setPrice(15.0);
        item10.setItemCategory(ItemCategory.Main);
        item10.setImageURL(URL + "steak.png");
        item10.setRestaurantId(restaurant4);

        Item item11 = new Item();
        item11.setItemName("Tacos");
        item11.setDescription("Tasty Tacos with salsa");
        item11.setAvailable(true);
        item11.setPrice(9.0);
        item11.setItemCategory(ItemCategory.Main);
        item11.setImageURL(URL + "tacos.png");
        item11.setRestaurantId(restaurant5);

        Item item12 = new Item();
        item12.setItemName("Sashimi");
        item12.setDescription("Fresh sashimi platter");
        item12.setAvailable(true);
        item12.setPrice(14.0);
        item12.setItemCategory(ItemCategory.Main);
        item12.setImageURL(URL + "sashimi.png");
        item12.setRestaurantId(restaurant6);

        Item item13 = new Item();
        item13.setItemName("Ramen");
        item13.setDescription("Hearty Ramen bowl");
        item13.setAvailable(true);
        item13.setPrice(10.0);
        item13.setItemCategory(ItemCategory.Noodle);
        item13.setImageURL(URL + "ramen.png");
        item13.setRestaurantId(restaurant7);

        Item item14 = new Item();
        item14.setItemName("Ice Cream");
        item14.setDescription("Creamy Ice Cream");
        item14.setAvailable(true);
        item14.setPrice(5.0);
        item14.setItemCategory(ItemCategory.Dessert);
        item14.setImageURL(URL + "iceCream.png");
        item14.setRestaurantId(restaurant8);

        Item item15 = new Item();
        item15.setItemName("Coffee");
        item15.setDescription("Hot brewed Coffee");
        item15.setAvailable(true);
        item15.setPrice(3.0);
        item15.setItemCategory(ItemCategory.Drink);
        item15.setImageURL(URL + "coffee.png");
        item15.setRestaurantId(restaurant9);

        Item item16 = new Item();
        item16.setItemName("Sandwich");
        item16.setDescription("Healthy Sandwich");
        item16.setAvailable(true);
        item16.setPrice(6.0);
        item16.setItemCategory(ItemCategory.Main);
        item16.setImageURL(URL + "sandwich.png");
        item16.setRestaurantId(restaurant10);

        Item item17 = new Item();
        item17.setItemName("Fries");
        item17.setDescription("Crispy Fries");
        item17.setAvailable(true);
        item17.setPrice(4.0);
        item17.setItemCategory(ItemCategory.Side);
        item17.setImageURL(URL + "fries.png");
        item17.setRestaurantId(restaurant1);

        Item item18 = new Item();
        item18.setItemName("Salad");
        item18.setDescription("Fresh Garden Salad");
        item18.setAvailable(true);
        item18.setPrice(6.5);
        item18.setItemCategory(ItemCategory.Salad);
        item18.setImageURL(URL + "salad.png");
        item18.setRestaurantId(restaurant2);

        Item item19 = new Item();
        item19.setItemName("Soup");
        item19.setDescription("Warm comforting Soup");
        item19.setAvailable(true);
        item19.setPrice(5.5);
        item19.setItemCategory(ItemCategory.Soup);
        item19.setImageURL(URL + "soup.png");
        item19.setRestaurantId(restaurant3);

        Item item20 = new Item();
        item20.setItemName("Noodles");
        item20.setDescription("Authentic Asian Noodles");
        item20.setAvailable(true);
        item20.setPrice(11.0);
        item20.setItemCategory(ItemCategory.Noodle);
        item20.setImageURL(URL + "noodle.png");
        item20.setRestaurantId(restaurant4);

        Item item21 = new Item();
        item21.setItemName("Pie");
        item21.setDescription("Sweet Fruit Pie");
        item21.setAvailable(true);
        item21.setPrice(7.0);
        item21.setItemCategory(ItemCategory.Dessert);
        item21.setImageURL(URL + "pie.png");
        item21.setRestaurantId(restaurant5);

        Item item22 = new Item();
        item22.setItemName("Brownie");
        item22.setDescription("Chocolate Brownie");
        item22.setAvailable(true);
        item22.setPrice(4.5);
        item22.setItemCategory(ItemCategory.Dessert);
        item22.setImageURL(URL + "brownie.png");
        item22.setRestaurantId(restaurant6);

        Item item23 = new Item();
        item23.setItemName("Smoothie");
        item23.setDescription("Refreshing Smoothie");
        item23.setAvailable(true);
        item23.setPrice(6.0);
        item23.setItemCategory(ItemCategory.Drink);
        item23.setImageURL(URL + "smoothie.png");
        item23.setRestaurantId(restaurant7);

        Item item24 = new Item();
        item24.setItemName("Dumplings");
        item24.setDescription("Steamed Dumplings");
        item24.setAvailable(true);
        item24.setPrice(7.5);
        item24.setItemCategory(ItemCategory.Starter);
        item24.setImageURL(URL + "dumpling.png");
        item24.setRestaurantId(restaurant8);

        Item item25 = new Item();
        item25.setItemName("Curry");
        item25.setDescription("Spicy Indian Curry");
        item25.setAvailable(true);
        item25.setPrice(9.5);
        item25.setItemCategory(ItemCategory.Main);
        item25.setImageURL(URL + "curry.png");
        item25.setRestaurantId(restaurant9);

        Item item26 = new Item();
        item26.setItemName("Fish and Chips");
        item26.setDescription("Classic Fish and Chips");
        item26.setAvailable(true);
        item26.setPrice(10.0);
        item26.setItemCategory(ItemCategory.Main);
        item26.setImageURL(URL + "fishAndChip.png");
        item26.setRestaurantId(restaurant10);

        Item item27 = new Item();
        item27.setItemName("Burrito");
        item27.setDescription("Loaded Burrito");
        item27.setAvailable(true);
        item27.setPrice(8.0);
        item27.setItemCategory(ItemCategory.Main);
        item27.setImageURL(URL + "burrito.png");
        item27.setRestaurantId(restaurant1);

        Item item28 = new Item();
        item28.setItemName("Lasagna");
        item28.setDescription("Cheesy Lasagna");
        item28.setAvailable(true);
        item28.setPrice(12.5);
        item28.setItemCategory(ItemCategory.Main);
        item28.setImageURL(URL + "lasagna.png");
        item28.setRestaurantId(restaurant2);

        Item item29 = new Item();
        item29.setItemName("Pancakes");
        item29.setDescription("Fluffy Pancakes");
        item29.setAvailable(true);
        item29.setPrice(6.5);
        item29.setItemCategory(ItemCategory.Dessert);
        item29.setImageURL(URL + "pancake.png");
        item29.setRestaurantId(restaurant3);

        Item item30 = new Item();
        item30.setItemName("Muffin");
        item30.setDescription("Blueberry Muffin");
        item30.setAvailable(true);
        item30.setPrice(3.5);
        item30.setItemCategory(ItemCategory.Dessert);
        item30.setImageURL(URL + "muffin.png");
        item30.setRestaurantId(restaurant4);

        Item item31 = new Item();
        item31.setItemName("Bagel");
        item31.setDescription("Toasted Bagel");
        item31.setAvailable(true);
        item31.setPrice(2.5);
        item31.setItemCategory(ItemCategory.Main);
        item31.setImageURL(URL + "bagel.png");
        item31.setRestaurantId(restaurant5);

        Item item32 = new Item();
        item32.setItemName("Omelette");
        item32.setDescription("Cheese Omelette");
        item32.setAvailable(true);
        item32.setPrice(5.5);
        item32.setItemCategory(ItemCategory.Main);
        item32.setImageURL(URL + "omelette.png");
        item32.setRestaurantId(restaurant6);

        Item item33 = new Item();
        item33.setItemName("Waffles");
        item33.setDescription("Belgian Waffles");
        item33.setAvailable(true);
        item33.setPrice(7.0);
        item33.setItemCategory(ItemCategory.Dessert);
        item33.setImageURL(URL + "waffles.png");
        item33.setRestaurantId(restaurant7);

        Item item34 = new Item();
        item34.setItemName("Hot Dog");
        item34.setDescription("Grilled Hot Dog");
        item34.setAvailable(true);
        item34.setPrice(3.0);
        item34.setItemCategory(ItemCategory.Main);
        item34.setImageURL(URL + "hotDog.png");
        item34.setRestaurantId(restaurant8);

        Item item35 = new Item();
        item35.setItemName("Margarita");
        item35.setDescription("Refreshing Margarita");
        item35.setAvailable(true);
        item35.setPrice(6.0);
        item35.setItemCategory(ItemCategory.Drink);
        item35.setImageURL(URL + "margarita.png");
        item35.setRestaurantId(restaurant9);

        Item item36 = new Item();
        item36.setItemName("Nachos");
        item36.setDescription("Cheesy Nachos");
        item36.setAvailable(true);
        item36.setPrice(5.5);
        item36.setItemCategory(ItemCategory.Starter);
        item36.setImageURL(URL + "nachos.png");
        item36.setRestaurantId(restaurant10);

        Item item37 = new Item();
        item37.setItemName("Falafel");
        item37.setDescription("Crispy Falafel");
        item37.setAvailable(true);
        item37.setPrice(4.0);
        item37.setItemCategory(ItemCategory.Starter);
        item37.setImageURL(URL + "falafel.png");
        item37.setRestaurantId(restaurant1);

        Item item38 = new Item();
        item38.setItemName("Quiche");
        item38.setDescription("Savory Quiche");
        item38.setAvailable(true);
        item38.setPrice(8.0);
        item38.setItemCategory(ItemCategory.Starter);
        item38.setImageURL(URL + "quiche.png");
        item38.setRestaurantId(restaurant2);

        Item item39 = new Item();
        item39.setItemName("Tiramisu");
        item39.setDescription("Delicate Tiramisu");
        item39.setAvailable(true);
        item39.setPrice(6.0);
        item39.setItemCategory(ItemCategory.Dessert);
        item39.setImageURL(URL + "tiramisu.png");
        item39.setRestaurantId(restaurant3);

        Item item40 = new Item();
        item40.setItemName("Bruschetta");
        item40.setDescription("Garlic Bruschetta");
        item40.setAvailable(true);
        item40.setPrice(5.5);
        item40.setItemCategory(ItemCategory.Starter);
        item40.setImageURL(URL + "bruschetta.png");
        item40.setRestaurantId(restaurant4);

        Item item41 = new Item();
        item41.setItemName("Ceviche");
        item41.setDescription("Fresh Ceviche");
        item41.setAvailable(true);
        item41.setPrice(12.0);
        item41.setItemCategory(ItemCategory.Starter);
        item41.setImageURL(URL + "ceviche.png");
        item41.setRestaurantId(restaurant5);

        Item item42 = new Item();
        item42.setItemName("Paella");
        item42.setDescription("Flavorful Paella");
        item42.setAvailable(true);
        item42.setPrice(13.0);
        item42.setItemCategory(ItemCategory.Main);
        item42.setImageURL(URL + "paella.png");
        item42.setRestaurantId(restaurant6);

        Item item43 = new Item();
        item43.setItemName("BBQ Ribs");
        item43.setDescription("Smokey BBQ Ribs");
        item43.setAvailable(true);
        item43.setPrice(11.5);
        item43.setItemCategory(ItemCategory.Main);
        item43.setImageURL(URL + "bbqRib.png");
        item43.setRestaurantId(restaurant7);

        Item item44 = new Item();
        item44.setItemName("Clam Chowder");
        item44.setDescription("Creamy Clam Chowder");
        item44.setAvailable(true);
        item44.setPrice(8.0);
        item44.setItemCategory(ItemCategory.Soup);
        item44.setImageURL(URL + "clamChowder.png");
        item44.setRestaurantId(restaurant8);

        Item item45 = new Item();
        item45.setItemName("Vegan Fried Rice");
        item45.setDescription("Vegetable Fried Rice");
        item45.setAvailable(true);
        item45.setPrice(6.5);
        item45.setItemCategory(ItemCategory.Side);
        item45.setImageURL(URL + "FriedRice.png");
        item45.setRestaurantId(restaurant9);

        Item item46 = new Item();
        item46.setItemName("Chili");
        item46.setDescription("Hearty Chili");
        item46.setAvailable(true);
        item46.setPrice(9.0);
        item46.setItemCategory(ItemCategory.Main);
        item46.setImageURL(URL + "chili.png");
        item46.setRestaurantId(restaurant10);

        Item item47 = new Item();
        item47.setItemName("Gyoza");
        item47.setDescription("Pan-fried Gyoza");
        item47.setAvailable(true);
        item47.setPrice(7.5);
        item47.setItemCategory(ItemCategory.Starter);
        item47.setImageURL(URL + "gyoza.png");
        item47.setRestaurantId(restaurant1);

        Item item48 = new Item();
        item48.setItemName("Samosa");
        item48.setDescription("Spiced Samosa");
        item48.setAvailable(true);
        item48.setPrice(4.0);
        item48.setItemCategory(ItemCategory.Starter);
        item48.setImageURL(URL + "samosa.png");
        item48.setRestaurantId(restaurant2);

        Item item49 = new Item();
        item49.setItemName("Mac and Cheese");
        item49.setDescription("Creamy Mac and Cheese");
        item49.setAvailable(true);
        item49.setPrice(6.5);
        item49.setItemCategory(ItemCategory.Main);
        item49.setImageURL(URL + "macAndCheese.png");
        item49.setRestaurantId(restaurant3);

        Item item50 = new Item();
        item50.setItemName("Poke Bowl");
        item50.setDescription("Healthy Poke Bowl");
        item50.setAvailable(true);
        item50.setPrice(9.0);
        item50.setItemCategory(ItemCategory.Main);
        item50.setImageURL(URL + "pokeBowl.png");
        item50.setRestaurantId(restaurant4);

        Item item51 = new Item();
        item51.setItemName("Crab Cakes");
        item51.setDescription("Savory Crab Cakes");
        item51.setAvailable(true);
        item51.setPrice(10.5);
        item51.setItemCategory(ItemCategory.Starter);
        item51.setImageURL(URL + "crabCake.png");
        item51.setRestaurantId(restaurant5);

        Item item52 = new Item();
        item52.setItemName("Mozzarella Sticks");
        item52.setDescription("Fried Mozzarella Sticks");
        item52.setAvailable(true);
        item52.setPrice(6.5);
        item52.setItemCategory(ItemCategory.Starter);
        item52.setImageURL(URL + "mozzarellaStick.png");
        item52.setRestaurantId(restaurant6);

        Item item53 = new Item();
        item53.setItemName("Spring Rolls");
        item53.setDescription("Crunchy Spring Rolls");
        item53.setAvailable(true);
        item53.setPrice(5.0);
        item53.setItemCategory(ItemCategory.Starter);
        item53.setImageURL(URL + "springRoll.png");
        item53.setRestaurantId(restaurant7);

        Item item54 = new Item();
        item54.setItemName("Lamb Chops");
        item54.setDescription("Tender Lamb Chops");
        item54.setAvailable(true);
        item54.setPrice(13.5);
        item54.setItemCategory(ItemCategory.Main);
        item54.setImageURL(URL + "lambChop.png");
        item54.setRestaurantId(restaurant8);

        Item item55 = new Item();
        item55.setItemName("French Toast");
        item55.setDescription("Sweet French Toast");
        item55.setAvailable(true);
        item55.setPrice(7.5);
        item55.setItemCategory(ItemCategory.Dessert);
        item55.setImageURL(URL + "frenchToast.png");
        item55.setRestaurantId(restaurant9);

        Item item56 = new Item();
        item56.setItemName("Milkshake");
        item56.setDescription("Thick Milkshake");
        item56.setAvailable(true);
        item56.setPrice(5.5);
        item56.setItemCategory(ItemCategory.Drink);
        item56.setImageURL(URL + "milkshake.png");
        item56.setRestaurantId(restaurant10);

        Item item57 = new Item();
        item57.setItemName("Pita Bread");
        item57.setDescription("Warm Pita Bread");
        item57.setAvailable(true);
        item57.setPrice(4.5);
        item57.setItemCategory(ItemCategory.Main);
        item57.setImageURL(URL + "pita.png");
        item57.setRestaurantId(restaurant1);

        Item item58 = new Item();
        item58.setItemName("Goulash");
        item58.setDescription("Rich Goulash");
        item58.setAvailable(true);
        item58.setPrice(10.0);
        item58.setItemCategory(ItemCategory.Main);
        item58.setImageURL(URL + "goulash.png");
        item58.setRestaurantId(restaurant2);

        Item item59 = new Item();
        item59.setItemName("Peking Duck");
        item59.setDescription("Crispy Peking Duck");
        item59.setAvailable(true);
        item59.setPrice(14.5);
        item59.setItemCategory(ItemCategory.Main);
        item59.setImageURL(URL + "pekingDuck.png");
        item59.setRestaurantId(restaurant3);

        Item item60 = new Item();
        item60.setItemName("Gelato");
        item60.setDescription("Italian Gelato");
        item60.setAvailable(true);
        item60.setPrice(6.5);
        item60.setItemCategory(ItemCategory.Dessert);
        item60.setImageURL(URL + "gelato.png");
        item60.setRestaurantId(restaurant4);

        Item item61 = new Item();
        item61.setItemName("Churros");
        item61.setDescription("Sweet Churros");
        item61.setAvailable(true);
        item61.setPrice(9.0);
        item61.setItemCategory(ItemCategory.Dessert);
        item61.setImageURL(URL + "churros.png");
        item61.setRestaurantId(restaurant5);

        Item item62 = new Item();
        item62.setItemName("Moussaka");
        item62.setDescription("Greek Moussaka");
        item62.setAvailable(true);
        item62.setPrice(11.0);
        item62.setItemCategory(ItemCategory.Main);
        item62.setImageURL(URL + "moussaka.png");
        item62.setRestaurantId(restaurant6);

        Item item63 = new Item();
        item63.setItemName("Beef Stew");
        item63.setDescription("Hearty Beef Stew");
        item63.setAvailable(true);
        item63.setPrice(7.5);
        item63.setItemCategory(ItemCategory.Main);
        item63.setImageURL(URL + "beefStew.png");
        item63.setRestaurantId(restaurant7);

        Item item64 = new Item();
        item64.setItemName("Crepes");
        item64.setDescription("Delicate Crepes");
        item64.setAvailable(true);
        item64.setPrice(12.0);
        item64.setItemCategory(ItemCategory.Dessert);
        item64.setImageURL(URL + "crepe.png");
        item64.setRestaurantId(restaurant8);

        Item item65 = new Item();
        item65.setItemName("Pho");
        item65.setDescription("Vietnamese Pho");
        item65.setAvailable(true);
        item65.setPrice(8.5);
        item65.setItemCategory(ItemCategory.Noodle);
        item65.setImageURL(URL + "pho.png");
        item65.setRestaurantId(restaurant9);

        Item item66 = new Item();
        item66.setItemName("Egg Roll");
        item66.setDescription("Crispy Egg Roll");
        item66.setAvailable(true);
        item66.setPrice(4.5);
        item66.setItemCategory(ItemCategory.Starter);
        item66.setImageURL(URL + "eggRoll.png");
        item66.setRestaurantId(restaurant10);

        Item item67 = new Item();
        item67.setItemName("Guacamole");
        item67.setDescription("Fresh Guacamole");
        item67.setAvailable(true);
        item67.setPrice(5.0);
        item67.setItemCategory(ItemCategory.Starter);
        item67.setImageURL(URL + "guacamole.png");
        item67.setRestaurantId(restaurant1);

        Item item68 = new Item();
        item68.setItemName("Lobster Bisque");
        item68.setDescription("Rich Lobster Bisque");
        item68.setAvailable(true);
        item68.setPrice(15.0);
        item68.setItemCategory(ItemCategory.Soup);
        item68.setImageURL(URL + "lobsterBisque.png");
        item68.setRestaurantId(restaurant2);

        Item item69 = new Item();
        item69.setItemName("Pulled Pork");
        item69.setDescription("Savory Pulled Pork");
        item69.setAvailable(true);
        item69.setPrice(9.0);
        item69.setItemCategory(ItemCategory.Main);
        item69.setImageURL(URL + "pulledPork.png");
        item69.setRestaurantId(restaurant3);

        Item item70 = new Item();
        item70.setItemName("Chicken Tikka");
        item70.setDescription("Spicy Chicken Tikka");
        item70.setAvailable(true);
        item70.setPrice(13.0);
        item70.setItemCategory(ItemCategory.Main);
        item70.setImageURL(URL + "chickenTikka.png");
        item70.setRestaurantId(restaurant4);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);
        itemRepository.save(item6);
        itemRepository.save(item7);
        itemRepository.save(item8);
        itemRepository.save(item9);
        itemRepository.save(item10);
        itemRepository.save(item11);
        itemRepository.save(item12);
        itemRepository.save(item13);
        itemRepository.save(item14);
        itemRepository.save(item15);
        itemRepository.save(item16);
        itemRepository.save(item17);
        itemRepository.save(item18);
        itemRepository.save(item19);
        itemRepository.save(item20);
        itemRepository.save(item21);
        itemRepository.save(item22);
        itemRepository.save(item23);
        itemRepository.save(item24);
        itemRepository.save(item25);
        itemRepository.save(item26);
        itemRepository.save(item27);
        itemRepository.save(item28);
        itemRepository.save(item29);
        itemRepository.save(item30);
        itemRepository.save(item31);
        itemRepository.save(item32);
        itemRepository.save(item33);
        itemRepository.save(item34);
        itemRepository.save(item35);
        itemRepository.save(item36);
        itemRepository.save(item37);
        itemRepository.save(item38);
        itemRepository.save(item39);
        itemRepository.save(item40);
        itemRepository.save(item41);
        itemRepository.save(item42);
        itemRepository.save(item43);
        itemRepository.save(item44);
        itemRepository.save(item45);
        itemRepository.save(item46);
        itemRepository.save(item47);
        itemRepository.save(item48);
        itemRepository.save(item49);
        itemRepository.save(item50);
        itemRepository.save(item51);
        itemRepository.save(item52);
        itemRepository.save(item53);
        itemRepository.save(item54);
        itemRepository.save(item55);
        itemRepository.save(item56);
        itemRepository.save(item57);
        itemRepository.save(item58);
        itemRepository.save(item59);
        itemRepository.save(item60);
        itemRepository.save(item61);
        itemRepository.save(item62);
        itemRepository.save(item63);
        itemRepository.save(item64);
        itemRepository.save(item65);
        itemRepository.save(item66);
        itemRepository.save(item67);
        itemRepository.save(item68);
        itemRepository.save(item69);
        itemRepository.save(item70);

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

        Feedback feedback3 = new Feedback();
        feedback3.setUser(user1);
        feedback3.setRestaurant(restaurant2);
        feedback3.setContent("Ok");
        feedback3.setRating(Rating.FourStar);
        feedback3.setPostDateTime(LocalDateTime.now());

        Feedback feedback4 = new Feedback();
        feedback4.setUser(user1);
        feedback4.setRestaurant(restaurant4);
        feedback4.setContent("Ok");
        feedback4.setRating(Rating.ThreeStar);
        feedback4.setPostDateTime(LocalDateTime.now());

        Feedback feedback5 = new Feedback();
        feedback5.setUser(user1);
        feedback5.setRestaurant(restaurant5);
        feedback5.setContent("Ok");
        feedback5.setRating(Rating.FiveStar);
        feedback5.setPostDateTime(LocalDateTime.now());

        feedbackRepository.save(feedback1);
        feedbackRepository.save(feedback2);
        feedbackRepository.save(feedback3);
        feedbackRepository.save(feedback4);
        feedbackRepository.save(feedback5);



    }


}
