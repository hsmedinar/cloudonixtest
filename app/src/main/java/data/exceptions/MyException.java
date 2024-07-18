package data.exceptions;

public class MyException extends Exception {

    public String message;

    public MyException(String message){
        this.message = message;
    }

    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }

    // Testing
    public static void main(String[] args){
        MyException e = new MyException("Ocurrio un problema, por favor revisa tu conexion");
        System.out.println(e.getMessage());
    }


}