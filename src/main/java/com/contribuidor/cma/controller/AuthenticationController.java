package com.contribuidor.cma.controller;

import com.contribuidor.cma.config.security.jwt.JwtTokenUtil;
import com.contribuidor.cma.dto.JwtAuthenticationRequestDto;
import com.contribuidor.cma.dto.JwtAuthenticationResponseDto;
import com.contribuidor.cma.service.auth.AuthService;
import com.contribuidor.cma.util.constants.ExchangeConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping(ExchangeConstants.URL_VERSION)
@Api(value = "Authentication", description = "Authentication")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Aauthentication for useradm", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved token"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(value = ExchangeConstants.URL_LOGIN, method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequestDto authenticationRequest,
                                                       @RequestParam(required = false, value = "lang") String lang){

            // filtro vai na classe UserAuth e valida os dados de usuario e recupera as authoritys
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication.getName(), authService.getUser(), authentication.getAuthorities());
            return ResponseEntity.ok(new JwtAuthenticationResponseDto(token));
    }

    @RequestMapping(value = ExchangeConstants.URL_REFRESH_TOKEN, method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponseDto(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
