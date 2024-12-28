package com.dustin.controller;

import com.dustin.constants.Status;
import com.dustin.constants.UserConstants;
import com.dustin.constants.UserStatusChangeAction;
import com.dustin.model.response.PagingResponseModel;
import com.dustin.model.response.UserResponseDTO;
import com.dustin.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Search users")
    @GetMapping("search")
    @SecurityRequirement(name = "Bearer Authentication")
    ResponseEntity<PagingResponseModel<UserResponseDTO>> search(
            @RequestParam(value = "q", required = false) String searchValue,
            Status status,
            @PageableDefault(size = 10)
            @SortDefault(sort = UserConstants.FILED_USERNAME) Pageable pageable) {
        return ResponseEntity.ok(userService.search(searchValue, status, pageable));
    }

    @GetMapping("change-status")
    ResponseEntity<UserResponseDTO> changUserStatus(@RequestParam UserStatusChangeAction action,
    @RequestParam Long userId) {
        return ResponseEntity.ok(userService.changeUserStatus(userId, action));
    }

    @GetMapping("get-detail")
    ResponseEntity<UserResponseDTO> changUserStatus(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("check-username-exist")
    public ResponseEntity<Boolean> checkUsernameExist(@RequestParam String username) {
        return ResponseEntity.ok(userService.checkUsername(username));
    }
}
