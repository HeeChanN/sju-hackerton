package hackerton.saver.exception;



import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public String handleHttpRequestMethodNotSupportedException(RuntimeException e) {
        return e.getMessage();
    }
}