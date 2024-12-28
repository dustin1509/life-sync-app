package com.dustin.patterns.state.user;

import com.dustin.constants.UserStatusChangeAction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserContext implements UserState {

    private UserState userState;

    @Override
    public UserStatusChangeAction userStateAction() {
        return null;
    }

    @Override
    public void doAction() {
        this.userState.doAction();
    }



}
