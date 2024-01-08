package francisco.joao.gerenciamentodeclientes.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TratamentoExcecao extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    private ResponseEntity<String> nullPointerExceptionHandler(NullPointerException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifique se a requisição está correta");
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> runtimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifique se a requisição está correta");
    }

}
