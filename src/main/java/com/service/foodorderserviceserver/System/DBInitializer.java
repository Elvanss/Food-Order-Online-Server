package com.service.foodorderserviceserver.System;

import com.service.foodorderserviceserver.Entity.Address.CustomerAddress;
import com.service.foodorderserviceserver.Entity.Address.RestaurantAddress;
import com.service.foodorderserviceserver.Entity.Item;
import com.service.foodorderserviceserver.Entity.Restaurant;
import com.service.foodorderserviceserver.Entity.Type.ItemCategory;
import com.service.foodorderserviceserver.Entity.Type.MembershipType;
import com.service.foodorderserviceserver.Entity.Type.Roles;
import com.service.foodorderserviceserver.Entity.User.Membership;
import com.service.foodorderserviceserver.Entity.User.User;
import com.service.foodorderserviceserver.Repository.Address.AddressRepository;
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

@Component
public class DBInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserService userService;
    private final MembershipRepository membershipRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;

    public DBInitializer(UserRepository userRepository,
                         UserService userService,
                         MembershipRepository membershipRepository, RestaurantRepository restaurantRepository,
                         AddressRepository addressRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.membershipRepository = membershipRepository;
        this.restaurantRepository = restaurantRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
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
        
        // Create 5 users
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUserName("johnfoodie");
        user1.setEmail("john@gmail.com");
        user1.setPassword("password");
        user1.setPhoneNumber("1234567890");
        user1.setType(Roles.USER);

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setUserName("janefoodie");
        user2.setPassword("password");
        user2.setEmail("jane@gmail.com");
        user2.setPhoneNumber("0987654321");
        user2.setType(Roles.USER);

        User user3 = new User();
        user3.setFirstName("Alice");
        user3.setLastName("Smith");
        user3.setUserName("janefoodie");
        user3.setPassword("password");
        user3.setEmail("alice@gmail.com");
        user3.setPhoneNumber("1122334455");
        user3.setType(Roles.USER);

        User user4 = new User();
        user4.setFirstName("Bob");
        user4.setLastName("Johnson");
        user4.setUserName("bobfoodie");
        user4.setPassword("password");
        user4.setEmail("bob@gmail.com");
        user4.setPhoneNumber("2233445566");
        user4.setType(Roles.USER);

        User user5 = new User();
        user5.setFirstName("Charlie");
        user5.setLastName("Brown");
        user5.setPassword("password");
        user5.setUserName("charliefoodie");
        user5.setEmail("charlie@gmail.com");
        user5.setPhoneNumber("3344556677");
        user5.setType(Roles.USER);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        // Create first restaurant
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setRestaurantName("Restaurant 1");
        restaurant1.setEmail("restaurant1@gmail.com");
        restaurant1.setPassword("password");
        restaurant1.setPhone("1234567890");
        restaurant1.setCuisine("Italian");
        restaurant1.setOpenTime(Time.valueOf("08:00:00"));
        restaurant1.setCloseTime(Time.valueOf("22:00:00"));
//        restaurant1.setOpened(true);
        restaurant1.setDescription("Italian cuisine");
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
//        restaurant2.setOpened(true);
        restaurant2.setDescription("Chinese cuisine");
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
//        restaurant3.setOpened(true);
        restaurant3.setDescription("Mexican cuisine");
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
//        restaurant4.setOpened(true);
        restaurant4.setDescription("Indian cuisine");
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
//        restaurant5.setOpened(true);
        restaurant5.setDescription("Japanese cuisine");
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
//        restaurant6.setOpened(true);
        restaurant6.setDescription("French cuisine");
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
//        restaurant7.setOpened(true);
        restaurant7.setDescription("Thai cuisine");
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
//        restaurant8.setOpened(true);
        restaurant8.setDescription("Greek cuisine");
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
//        restaurant9.setOpened(true);
        restaurant9.setDescription("Spanish cuisine");
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
//        restaurant10.setOpened(true);
        restaurant10.setDescription("American cuisine");
        restaurant10.setRoles(Roles.RESTAURANT);

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

        CustomerAddress personalAddress1 = new CustomerAddress();
        personalAddress1.setBname("Home Building");
        personalAddress1.setStreet("123 Main St");
        personalAddress1.setSuburb("Wollongong");
        personalAddress1.setState("State");
        personalAddress1.setPostCode("2500");
        personalAddress1.setUser(user1);
        addressRepository.save(personalAddress1);

        CustomerAddress personalAddress2 = new CustomerAddress();
        personalAddress2.setBname("Office Building");
        personalAddress2.setStreet("456 Main St");
        personalAddress2.setSuburb("Wollongong");
        personalAddress2.setState("State");
        personalAddress2.setPostCode("2500");
        personalAddress2.setUser(user2);
        addressRepository.save(personalAddress2);

        CustomerAddress personalAddress3 = new CustomerAddress();
        personalAddress3.setBname("Apartment Building");
        personalAddress3.setStreet("789 Main St");
        personalAddress3.setSuburb("Wollongong");
        personalAddress3.setState("State");
        personalAddress3.setPostCode("2500");
        personalAddress3.setUser(user3);
        addressRepository.save(personalAddress3);

        CustomerAddress personalAddress4 = new CustomerAddress();
        personalAddress4.setBname("Condo Building");
        personalAddress4.setStreet("321 Main St");
        personalAddress4.setSuburb("Wollongong");
        personalAddress4.setState("State");
        personalAddress4.setPostCode("2500");
        personalAddress4.setUser(user4);
        addressRepository.save(personalAddress4);

        CustomerAddress personalAddress5 = new CustomerAddress();
        personalAddress5.setBname("Townhouse Building");
        personalAddress5.setStreet("654 Main St");
        personalAddress5.setSuburb("Wollongong");
        personalAddress5.setState("State");
        personalAddress5.setPostCode("2500");
        personalAddress5.setUser(user5);
        addressRepository.save(personalAddress5);

        RestaurantAddress restaurantAddress = new RestaurantAddress();
        restaurantAddress.setBname("Victoria Building");
        restaurantAddress.setStreet("456 Collins St");
        restaurantAddress.setSuburb("Melbourne");
        restaurantAddress.setState("State");
        restaurantAddress.setPostCode("3000");
        restaurantAddress.setRestaurant(restaurant1);
        addressRepository.save(restaurantAddress);

        // Create address for the second restaurant
        RestaurantAddress address2 = new RestaurantAddress();
        address2.setBname("Building 2");
        address2.setStreet("2 Main St");
        address2.setSuburb("Wollongong");
        address2.setState("State");
        address2.setPostCode("2500");
        address2.setRestaurant(restaurant2);
        addressRepository.save(address2);

        // Create address for the third restaurant
        RestaurantAddress address3 = new RestaurantAddress();
        address3.setBname("Building 3");
        address3.setStreet("3 Main St");
        address3.setSuburb("Wollongong");
        address3.setState("State");
        address3.setPostCode("2500");
        address3.setRestaurant(restaurant3);
        addressRepository.save(address3);

        // Create address for the fourth restaurant
        RestaurantAddress address4 = new RestaurantAddress();
        address4.setBname("Building 4");
        address4.setStreet("4 Main St");
        address4.setSuburb("Wollongong");
        address4.setState("State");
        address4.setPostCode("2500");
        address4.setRestaurant(restaurant4);
        addressRepository.save(address4);

        // Create address for the fifth restaurant
        RestaurantAddress address5 = new RestaurantAddress();
        address5.setBname("Building 5");
        address5.setStreet("5 Main St");
        address5.setSuburb("Wollongong");
        address5.setState("State");
        address5.setPostCode("2500");
        address5.setRestaurant(restaurant5);
        addressRepository.save(address5);

        // Create address for the sixth restaurant
        RestaurantAddress address6 = new RestaurantAddress();
        address6.setBname("Building 6");
        address6.setStreet("6 Main St");
        address6.setSuburb("Wollongong");
        address6.setState("State");
        address6.setPostCode("2500");
        address6.setRestaurant(restaurant6);
        addressRepository.save(address6);

        // Create address for the seventh restaurant
        RestaurantAddress address7 = new RestaurantAddress();
        address7.setBname("Building 7");
        address7.setStreet("7 Main St");
        address7.setSuburb("Wollongong");
        address7.setState("State");
        address7.setPostCode("2500");
        address7.setRestaurant(restaurant7);
        addressRepository.save(address7);

        // Create address for the eighth restaurant
        RestaurantAddress address8 = new RestaurantAddress();
        address8.setBname("Building 8");
        address8.setStreet("8 Main St");
        address8.setSuburb("Wollongong");
        address8.setState("State");
        address8.setPostCode("2500");
        address8.setRestaurant(restaurant8);
        addressRepository.save(address8);

        // Create address for the ninth restaurant
        RestaurantAddress address9 = new RestaurantAddress();
        address9.setBname("Building 9");
        address9.setStreet("9 Main St");
        address9.setSuburb("Wollongong");
        address9.setState("State");
        address9.setPostCode("2500");
        address9.setRestaurant(restaurant9);
        addressRepository.save(address9);

        // Create address for the tenth restaurant
        RestaurantAddress address10 = new RestaurantAddress();
        address10.setBname("Building 10");
        address10.setStreet("10 Main St");
        address10.setSuburb("Wollongong");
        address10.setState("State");
        address10.setPostCode("2500");
        address10.setRestaurant(restaurant10);
        addressRepository.save(address10);

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

        itemRepository.save(item1);
        itemRepository.save(item2);


        // Create 10 restaurants
        // Create 10 restaurants addresses (1 address per restaurant is acceptable)
        // Create 20 customer addresses (less or equal to 4 addresses per user is acceptable)
        // Create 200 menu items (20 menu items per restaurant is acceptable)

        /*
        * Save the data to the database
        * */






    }
}
