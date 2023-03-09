package m.Controller;

import lombok.AllArgsConstructor;
import m.Model.Entity.PasswordResetToken;
import m.Model.Entity.Users;
import m.Model.SendEmail.ProvideSendEmail;
import m.Model.Service.PassResetService;
import m.Model.Service.UserService;
import m.PayLoad.Response.MessageResponse;
import m.Security.CustomUserDetailService;
import m.Security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/m/passReset")
public class PassResetController {
    private PasswordEncoder encoder;
    private ProvideSendEmail provideSendEmail;
    private PassResetService passResetService;
    private UserService userService;
    private CustomUserDetailService customUserDetailsService;

    @GetMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestParam("userEmail") String userEmail, HttpServletRequest request) {


        if (userService.existsByUserEmail(userEmail)) {
            Users users = (Users) userService.findByEmail(userEmail);
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(users.getUsersName());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = UUID.randomUUID().toString();
            PasswordResetToken myToken = new PasswordResetToken();
            myToken.setToken(token);
            String mess = "token is valid for 5 minutes.\n" + "Your token: " + token;
            myToken.setUsers(users);
            Date now = new Date();
            myToken.setStartDate(now);
            passResetService.saveOrUpdate(myToken);
            provideSendEmail.sendSimpleMessage(users.getUserEmail(),
                    "Reset your password", mess);
            return ResponseEntity.ok("Email sent! Please check your email");
        } else {
            return new ResponseEntity<>(new MessageResponse("Email is not already"), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/creatNewPass")
    public ResponseEntity<?> creatNewPass(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PasswordResetToken passwordResetToken = passResetService.getLastTokenByUserId(userDetails.getUsersId());
        long date1 = passwordResetToken.getStartDate().getTime() + 1800000;
        long date2 = new Date().getTime();
        if (date2 > date1) {
            return new ResponseEntity<>(new MessageResponse("Expired Token "), HttpStatus.EXPECTATION_FAILED);
        } else {
            if (passwordResetToken.getToken().equals(token)) {
                Users users = (Users) userService.findById(userDetails.getUsersId());
                users.setUsersPassWord(encoder.encode(newPassword));
                userService.saveAndUpdate(users);
                return new ResponseEntity<>(new MessageResponse("update password successfully "), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("token is fail "), HttpStatus.NO_CONTENT);
            }
        }
    }
}