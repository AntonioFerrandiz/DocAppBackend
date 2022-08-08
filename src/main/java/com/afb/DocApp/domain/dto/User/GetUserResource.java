package com.afb.DocApp.domain.dto.User;

import com.afb.DocApp.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResource {

    private String fullname;
    private Boolean active;

    public GetUserResource(User user) {
        this.fullname = user.getName() + ' ' + user.getLastname();

        this.active = user.getActive();
    }

    public static List<GetUserResource> convert(List<User> users) {
        return users.stream().map(GetUserResource::new).collect(Collectors.toList());
    }
}
