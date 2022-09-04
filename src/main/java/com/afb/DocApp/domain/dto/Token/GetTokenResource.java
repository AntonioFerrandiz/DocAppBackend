package com.afb.DocApp.domain.dto.Token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTokenResource {
    private String token;
    public GetTokenResource(String token){
        this.token = token;
    }


}
