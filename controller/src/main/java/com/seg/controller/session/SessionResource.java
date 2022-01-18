package com.seg.controller.session;

import static com.seg.security.enumeration.Header.AUTHORIZATION;
import static com.seg.security.enumeration.Header.BEARER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.seg.api.user.UserService;
import com.seg.domain.user.dto.UserProperties;
import com.seg.domain.user.dto.UserSummary;
import com.seg.precaution.exception.general.InvalidRequestException;
import com.seg.security.jwt.JwtSupplier;
import com.seg.security.jwt.TokenResponse;
import com.seg.security.user.CustomDetailsService;
import com.seg.security.user.CustomUserDetails;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionResource {
    
    private final CustomDetailsService customDetailsService;
    private final UserService userService;    
    private final JwtSupplier jwtSupplier;
    
    @GetMapping(value = "/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("It is working!", HttpStatus.OK);
    }

    @PostMapping (value = "/new")
    public ResponseEntity<UserSummary> createUser(@RequestBody @Valid UserProperties userProperties) {             
        return new ResponseEntity<UserSummary>(userService.save(userProperties), HttpStatus.CREATED);        
    }

    @GetMapping (value = "/refresh/auth")
    public ResponseEntity<Mono<TokenResponse>> refreshAuth(final HttpServletRequest request, final HttpServletResponse response){
        final String autorizacionCabecera = request.getHeader(AUTHORIZATION.toString());
        if (jwtSupplier.isBearer(autorizacionCabecera)){
            final String userDni = jwtSupplier.getTokenUser(autorizacionCabecera);
            final CustomUserDetails userDetails = (CustomUserDetails) customDetailsService.loadUserByUsername(userDni);
            final List<String> authorities = userDetails.verAutoridades();
            final String refreshToken = autorizacionCabecera.substring(BEARER.toString().length());
            final String accessToken = jwtSupplier.createToken(userDni, authorities);
            final TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
            final Mono<TokenResponse> monoResponse = Mono.just(tokenResponse);
            return new ResponseEntity<Mono<TokenResponse>>(monoResponse, HttpStatus.OK);                  
        }
        throw new InvalidRequestException();
    }
}
