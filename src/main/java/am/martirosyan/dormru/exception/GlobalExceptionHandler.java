package am.martirosyan.dormru.exception;

import am.martirosyan.dormru.dto.request.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserRequest());
        log.error("User already exists: {}", ex.getMessage());
        return "register";
    }

    @ExceptionHandler(RoomNotExistException.class)
    public String handleRoomNotExist(RoomNotExistException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserRequest());
        log.error("Room does not exist: {}", ex.getMessage());
        return "register";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException ignoredEx,
                                         RedirectAttributes redirectAttributes,
                                         HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Файл слишком большой!");
        String referer = request.getHeader("Referer");
        log.error("File size exceeded: {}", ignoredEx.getMessage());
        return "redirect:" + (referer != null ? referer : "/profile");
    }
}
