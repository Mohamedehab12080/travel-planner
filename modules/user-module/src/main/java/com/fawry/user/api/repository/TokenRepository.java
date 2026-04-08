package com.fawry.user.api.repository;

import com.fawry.common.api.repository.BaseRepository;
import com.fawry.user.model.entity.Token;
import com.fawry.user.model.enums.TokenTypes;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token, Long> {

    Token insert(Token entity);
    void update(Token entity);

    Optional<Token> findByToken(String token);

    void deleteByUserIdAndTokenType(Long userId,TokenTypes tokenType);

    Optional<Token> findByTokenAndTokenType(String token, TokenTypes tokenType);

    int deleteByExpiryDateBefore(LocalDateTime dateTime);
}