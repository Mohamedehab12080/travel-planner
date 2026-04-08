package com.fawry.user.repository.jpa;

import com.fawry.common.repository.jpa.BaseJPARepository;
import com.fawry.user.model.entity.Token;
import com.fawry.user.model.enums.TokenTypes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenJPARepository extends BaseJPARepository<Token, Long> {

    Optional<Token> findByToken(String token);

    void deleteByUserId(Long userId);

    @Modifying
    @Query(value = """
            DELETE FROM Token t
        WHERE t.user.id = :userId AND t.tokenType = :tokenType
    """)
    void deleteByUserIdAndTokenType(@Param("userId") Long userId,@Param("tokenType") TokenTypes tokenType);

    Optional<Token> findByTokenAndTokenType(String token, TokenTypes tokenType);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.expiryDate < :dateTime")
    int deleteByExpiryDateBefore(@Param("dateTime") LocalDateTime dateTime);
}
