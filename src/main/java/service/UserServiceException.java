package service;

public class UserServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
    public UserServiceException(String message) {
        super(message);
    }
    
    public static UserServiceException userNotFoundException(String acc) {
        return new UserServiceException("User not found for account: " + acc);
    }
    
    public static UserServiceException pinMismatchException() {
        return new UserServiceException("Pin does not match");
    }
    
    public static UserServiceException accountNotFoundException() {
        return new UserServiceException("Account not found");
    }
    
    public static UserServiceException invalidInputException() {
        return new UserServiceException("Invalid input");
    }
    
    public static UserServiceException databaseAccessException() {
        return new UserServiceException("Failed to access the database");
    }
}
