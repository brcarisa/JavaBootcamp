package ex02;

public class UsersArrayList implements UsersList{
    private int currentSize = 0;
    private int constSize = 10;
    private User[] usersArray = new User[constSize];

    @Override
    public void addUser(User user) {
        if(usersArray[usersArray.length - 1] != null){
            constSize *= 2;
            User[] newArray = new User[constSize];
            System.arraycopy(usersArray, 0, newArray, 0, Math.min(usersArray.length, constSize));
            usersArray = newArray;
        }
        usersArray[currentSize++] = user;
    }

    @Override
    public User searchUserById(int identifier){
        for (int i = 0; i < currentSize; i++) {
            if(usersArray[i].getIdentifier() == identifier){
                return usersArray[i];
            }
        }
        throw new UserNotFoundException("User with identifier " + identifier + " not found");
    }

    @Override
    public User searchUserByIndex(int index) {
        return usersArray[index];
    }

    @Override
    public int getAmountUsers() {
        return currentSize;
    }
}
