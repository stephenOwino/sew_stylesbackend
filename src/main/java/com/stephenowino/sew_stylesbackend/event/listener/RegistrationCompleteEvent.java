package com.stephenowino.sew_stylesbackend.event.listener;

import com.stephenowino.sew_stylesbackend.Model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
        private User user;
        private String applicationUrl;

        public RegistrationCompleteEvent(User user, String applicationUrl) {
                super(user);
                this.user = user;
                this.applicationUrl = applicationUrl;
        }
}
