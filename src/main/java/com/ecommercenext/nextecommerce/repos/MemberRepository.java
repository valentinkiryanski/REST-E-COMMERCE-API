package com.ecommercenext.nextecommerce.repos;

import com.ecommercenext.nextecommerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    boolean existsByUsername(String username);

    Optional<Member> findByUsername(String username);
}

