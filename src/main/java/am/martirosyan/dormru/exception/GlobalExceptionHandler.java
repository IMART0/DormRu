package am.martirosyan.dormru.exception;

import am.martirosyan.dormru.dto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExists(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserRequest());
        return "register";
    }

    @ExceptionHandler(RoomNotExistException.class)
    public String handleRoomNotExist(RoomNotExistException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", new UserRequest());
        return "register";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException ex,
                                         RedirectAttributes redirectAttributes,
                                         HttpServletRequest request) {
        redirectAttributes.addFlashAttribute("error", "Файл слишком большой!");
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/profile");
    }
}
