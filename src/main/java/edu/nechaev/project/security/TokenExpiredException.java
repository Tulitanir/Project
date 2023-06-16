package edu.nechaev.project.security;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
    }

    public TokenExpiredException(String message) {
        super(message);
    }
}
