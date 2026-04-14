package com.api.pojo;

import groovy.lang.GString;

public record UserCredentials (
    String username,
    String password
) {
}

