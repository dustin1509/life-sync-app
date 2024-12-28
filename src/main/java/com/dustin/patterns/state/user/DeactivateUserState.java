package com.dustin.patterns.state.user;

import com.dustin.constants.UserStatusChangeAction;
import org.springframework.stereotype.Component;

@Component
public class DeactivateUserState implements UserState {

    @Override
    public UserStatusChangeAction userStateAction() {
        return UserStatusChangeAction.DEACTIVATE;
    }

    @Override
    public void doAction() {
        System.out.println("User is deactivated");
    }
}
