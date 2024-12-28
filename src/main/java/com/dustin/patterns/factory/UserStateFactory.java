package com.dustin.patterns.factory;

import com.dustin.constants.UserStatusChangeAction;
import com.dustin.patterns.state.user.UserState;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserStateFactory {
    private final List<UserState> userStates;

    private final Map<UserStatusChangeAction, UserState> userStateMap = new HashMap<>();

    @PostConstruct
    private void initUserStateMap() {
        for (UserState userState: userStates) {
            userStateMap.put(userState.userStateAction(), userState);
        }
    }

    public UserState getUserStateService(UserStatusChangeAction action) {
        return userStateMap.get(action);
    }
}
