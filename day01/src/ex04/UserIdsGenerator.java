package ex04;

public class UserIdsGenerator {
        private static int id = 0;
        public static UserIdsGenerator userIdsGenerator;

    private UserIdsGenerator(){};

        public static UserIdsGenerator getInstance(){
        if(userIdsGenerator == null){
            userIdsGenerator = new UserIdsGenerator();
        }
        return userIdsGenerator;
    }

        public static int getUserId() {
        return id;
    }

        public int createNewId(){
        return ++id;
    }
}
