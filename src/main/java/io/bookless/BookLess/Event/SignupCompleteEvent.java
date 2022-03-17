package io.bookless.BookLess.Event;

import io.bookless.BookLess.Entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SignupCompleteEvent extends ApplicationEvent {

    private final User user;
    private final String applicationUrl;

    public SignupCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }

}
