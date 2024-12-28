package com.dustin.patterns.state.user;

import com.dustin.constants.UserStatusChangeAction;

public interface UserState {
    public UserStatusChangeAction userStateAction();

    public void doAction();
}
