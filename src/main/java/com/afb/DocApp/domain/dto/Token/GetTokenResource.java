package com.afb.DocApp.domain.dto.Token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTokenResource {
    private String token;
    private String type;

    public GetTokenResource(String token, String type){
        this.token = token;
        this.type = type;
    }


}
