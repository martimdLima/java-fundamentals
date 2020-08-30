package entity_class;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();

        User user1 = new User();
        User user2 = new User();
        user1.setName("john");
        user1.setAddress("Porto");
        user1.setAge(29);
        user1.setGender("Male");
        user1.setPassword("1234");
        user1.setEmail("mail@mail.com");

        user2.setName("tom");
        user2.setId(2);
        user2.setPassword("3435");
        user2.setGender("Male");
        user2.setAge(33);
        user2.setAddress("Dubai");
        user2.setEmail("mail1@mail.com");

        service.saveOrUpdate(user1);
        service.saveOrUpdate(user2);


        service.getEmf().close();
    }
}