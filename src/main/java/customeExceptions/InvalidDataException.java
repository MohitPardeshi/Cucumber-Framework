package customeExceptions;

public class InvalidDataException extends Exception{

    public InvalidDataException(String str){
        super(str);
    }
}
