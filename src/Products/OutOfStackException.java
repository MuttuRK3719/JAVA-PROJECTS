package Products;

public class OutOfStackException extends Exception{
    @Override
    public String getMessage() {
        return "Ohoo Sorry there is no enough stack:";
    }
}
