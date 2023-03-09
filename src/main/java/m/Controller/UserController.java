package m.Controller;

import m.JWT.JwtTokenProvider;
import m.Model.DTO.UserDTO;
import m.Model.Entity.Users;
import m.Model.ServiceImp.UserServiceImp;
import m.PayLoad.Request.ForgotPassWordRequest;
import m.PayLoad.Request.LoginRequest;
import m.PayLoad.Request.ResetPasswordRequest;
import m.PayLoad.Request.SignupRequest;
import m.PayLoad.Response.JwtResponse;
import m.PayLoad.Response.MessageResponse;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/auth/userController")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userServiceImp.existsByUsersName(signupRequest.getUsersName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userServiceImp.existsByUserEmail(signupRequest.getUserEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));
        }
        Users user = new Users();
        user.setUsersName(signupRequest.getUsersName());
        user.setUsersPassWord(passwordEncoder.encode(signupRequest.getUsersPassWord()));
        user.setUserEmail(signupRequest.getUserEmail());
        user.setUserPhone(signupRequest.getUserPhone());
        user.setUserCompany(signupRequest.getUserCompany());
        user.setBillingAddress(signupRequest.getBillingAddress());
        user.setShippingAdress(signupRequest.getShippingAdress());
        user.setPermission(signupRequest.getPermission());
        user.setUserStatus(true);
        userServiceImp.saveAndUpdate(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsersName(), loginRequest.getUsersPassWord())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();
        //Sinh JWT tra ve client
        String jwt = jwtTokenProvider.generateToken(customUserDetail);


        return ResponseEntity.ok(new JwtResponse(jwt, customUserDetail.getUsername(), customUserDetail.getUserEmail(),
                customUserDetail.getUserPhone(), customUserDetail.getPermission()));
    }

    @GetMapping()
    public List<UserDTO> getAllUser(){
        List<Users> usersList=userServiceImp.getAll();
        List<UserDTO> userDTOS=new ArrayList<>();
        String permission="";
        for (Users user:usersList) {
            if (user.getPermission()==1){
                permission="Quản trị";
            }else {
                permission="Người dùng";
            }
            UserDTO userDTO=new UserDTO(
                    user.getUsersName(),
                    user.getUserEmail(),
                    user.getUserPhone(),
                    user.getUserCompany(),
                    user.getBillingAddress(),
                    user.getShippingAdress(),
                    permission,
                    (user.isUserStatus()?"Hoạt Động":"Block")
            );
            userDTOS.add(userDTO);
        }
    return userDTOS;
    }

    @GetMapping("/logOut")
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        // Clear the authentication from server-side (in this case, Spring Security)
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("You have been logged out.");
    }

//    @PostMapping("/forgotPassword")
//    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassWordRequest forgotPassWordRequest) {
//        Users users = userServiceImp.findByUsersName(forgotPassWordRequest.getUsersName());
//        if (users != null) {
//            users.setUsersPassWord(passwordEncoder.encode(forgotPassWordRequest.getNewUsersPassWord()));
//            userServiceImp.saveAndUpdate(users);
//        } else {
//            return ResponseEntity.ok("Account does not exist!");
//        }
//        return ResponseEntity.ok("Change password successfully!");
//
//    }

    @PostMapping("resetPassWord")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check = passwordEncoder.matches(resetPasswordRequest.getUsersPassWord(), userDetails.getPassword());

        if (check) {
            if (resetPasswordRequest.getNewUsersPassWord().equals(resetPasswordRequest.getConfirmPassWord())) {
                Users users=userServiceImp.findById(userDetails.getUsersId());
                users.setUsersPassWord(passwordEncoder.encode(resetPasswordRequest.getNewUsersPassWord()));
               userServiceImp.saveAndUpdate(users);
                return ResponseEntity.ok(new MessageResponse("Reset password successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu mới không trùng khớp, vui lòng thử lại!"));
            }
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu không trùng khớp, vui lòng thử lại!"));
        }
    }
}
