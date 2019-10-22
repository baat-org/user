package org.baat.user.controller;

import org.baat.core.transfer.user.SignupRequest;
import org.baat.core.transfer.user.UserCredentials;
import org.baat.core.transfer.user.UserInfo;
import org.baat.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/authenticate", method = PUT)
    public String authenticate(@RequestBody final UserCredentials credentials) {
        return userService.authenticate(credentials);
    }

    @CrossOrigin
    @RequestMapping(value = "/signup", method = PUT)
    public String signup(@RequestBody final SignupRequest signupRequest) {
        LOGGER.info("Signing up {}", signupRequest.getEmail());
        return userService.signup(signupRequest);
    }

    @CrossOrigin
    @RequestMapping(value = "/validateUserToken/{userToken}", method = GET)
    public boolean validateUserToken(@PathVariable("userToken") final String userToken) {
        return userService.validateUserToken(userToken);
    }

    @CrossOrigin
    @RequestMapping(value = "/userForToken/{userToken}", method = GET)
    public UserInfo userForToken(@PathVariable("userToken") final String userToken) {
        return userService.getUserForToken(userToken);
    }

    @CrossOrigin
    @RequestMapping(value = "/users", method = GET)
    public List<UserInfo> users() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @RequestMapping(value = "/userTokens/{userId}", method = GET)
    public Set<String> userTokens(@PathVariable("userId") final Long userId) {
        return userService.getUserTokens(userId);
    }
}