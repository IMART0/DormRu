package am.martirosyan.dormru.exception;

import am.martirosyan.dormru.dto.UserDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @ExceptionHandler(RoomNotExistException.class)
    public String handleRoomNotExist(RoomNotExistException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserDto());
        return "register";
    }
}
