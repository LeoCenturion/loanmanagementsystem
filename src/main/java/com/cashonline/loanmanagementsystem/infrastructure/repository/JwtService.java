package com.cashonline.loanmanagementsystem.infrastructure.repository;

import java.util.Optional;

public interface JwtService {
    Optional<String> getSubFromToken(String token);
    String toToken(Registration registration);
}
