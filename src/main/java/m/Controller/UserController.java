package m.Controller;

import m.JWT.JwtTokenProvider;
import m.Model.DTO.UserDTO;
import m.Model.Entity.Users;
import m.Model.Service.UserService;
import m.Model.ServiceImp.UserServiceImp;
import m.PayLoad.Request.ForgotPassWordRequest;
import m.PayLoad.Request.LoginRequest;
import m.PayLoad.Request.ResetPasswordRequest;
import m.PayLoad.Request.SignupRequest;
import m.PayLoad.Response.JwtResponse;
import m.PayLoad.Response.MessageResponse;
import m.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/m/auth/userController")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userService.existsByUsersName(signupRequest.getUsersName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userService.existsByUserEmail(signupRequest.getUserEmail())) {
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
        userService.saveAndUpdate(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Users users=userService.findByUsersName(loginRequest.getUsersName());
        if (users.isUserStatus()){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsersName(), loginRequest.getUsersPassWord())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetail = (CustomUserDetails) authentication.getPrincipal();

            String jwt = jwtTokenProvider.generateToken(customUserDetail);


            return ResponseEntity.ok(new JwtResponse(jwt, customUserDetail.getUsername(), customUserDetail.getUserEmail(),
                    customUserDetail.getUserPhone(), customUserDetail.getPermission()));
        } else {
         return ResponseEntity.ok("Tài khoản bạn đã bị vô hiệu hoá");
        }


    }

    @GetMapping()
    public List<UserDTO> getAllUser() {
        List<Users> usersList = userService.getAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        String permission = "";
        for (Users user : usersList) {
            if (user.getPermission() == 1) {
                permission = "Quản trị";
            } else {
                permission = "Người dùng";
            }
            UserDTO userDTO = new UserDTO(
                    user.getUsersName(),
                    user.getUserEmail(),
                    user.getUserPhone(),
                    user.getUserCompany(),
                    user.getBillingAddress(),
                    user.getShippingAdress(),
                    permission,
                    (user.isUserStatus() ? "Hoạt Động" : "Block")
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


    @PostMapping("resetPassWord")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check = passwordEncoder.matches(resetPasswordRequest.getUsersPassWord(), userDetails.getPassword());

        if (check) {
            if (resetPasswordRequest.getNewUsersPassWord().equals(resetPasswordRequest.getConfirmPassWord())) {
                Users users = userService.findByUsersName(userDetails.getUsersName());
                users.setUsersPassWord(passwordEncoder.encode(resetPasswordRequest.getNewUsersPassWord()));
                userService.saveAndUpdate(users);
                return ResponseEntity.ok(new MessageResponse("Reset password successfully"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu mới không trùng khớp, vui lòng thử lại!"));
            }
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu không trùng khớp, vui lòng thử lại!"));
        }
    }

    @GetMapping("/SearchUserByName")
    public List<Users> searchUserByName(@RequestParam("usersName") String usersName) {
        return userService.findByUsersNameContaining(usersName);
    }

    @GetMapping("/SearchUserByEmail")
    public List<Users> searchUserByEmail(@RequestParam("email") String email) {
        return userService.findByUserEmailContaining(email);
    }

    @GetMapping("/SearchUserByShipping")
    public List<Users> searchUserByShipping(@RequestParam("shipping") String shipping) {
        return userService.findByShippingAdressContaining(shipping);
    }

    @GetMapping("/SearchUserByCompany")
    public List<Users> searchUserByCompany(@RequestParam("company") String company) {
        return userService.findByUserCompanyContaining(company);
    }

    @GetMapping("/sortUserByName")
    public List<Users> sortUserByName(@RequestParam("diraction") String diraction) {
        return userService.sortUserByUserName(diraction);
    }

    @GetMapping("/paginationUserAsc")
    public ResponseEntity<Map<String, Object>> paggingUser(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("usersName").ascending());
        Page<Users> users = userService.paggingUser(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Users", users.getContent());
        data.put("Size", users.getSize());
        data.put("TotalElement", users.getTotalElements());
        data.put("TotalPage", users.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/paginationUsedesc")
    public ResponseEntity<Map<String, Object>> paggingUserDsec(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("usersName").descending());
        Page<Users> users = userService.paggingUser(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("Users", users.getContent());
        data.put("Size", users.getSize());
        data.put("TotalElement", users.getTotalElements());
        data.put("TotalPage", users.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/changeAccess")
    public ResponseEntity<?> changeAccountPermissions(@RequestParam("usersName") String usersName) {
        CustomUserDetails usersChangePass = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (usersChangePass!=null){
            if (usersChangePass.getPermission()==1){
                Users users = userService.findByUsersName(usersName);
                users.setUserStatus(!users.isUserStatus());
                userService.saveAndUpdate(users);
                return ResponseEntity.ok("Cập nhật quyền thành công!");
            }else {
                return ResponseEntity.ok("Bạn không có quyền thực hiện thao tác này!");
            }
        }else {
            return ResponseEntity.ok("Bạn cần thực hiện đăng nhập trước khi thực hiện thao tác này!");
        }
    }

   @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam("filterBy")String filterName,@RequestParam("name")String name){
        List<Users> listUser = userService.filter(filterName,name);
       List<UserDTO> userDTOS = new ArrayList<>();

       for (Users user : listUser) {

           UserDTO userDTO = new UserDTO(
                   user.getUsersName(),
                   user.getUserEmail(),
                   user.getUserPhone(),
                   user.getUserCompany(),
                   user.getBillingAddress(),
                   user.getShippingAdress(),
                   ((user.getPermission()==1)?"Quản trị":"Người dùng"),
                   (user.isUserStatus() ? "Hoạt Động" : "Block")
           );
           userDTOS.add(userDTO);
       }

        return ResponseEntity.ok(userDTOS);
   }



}
