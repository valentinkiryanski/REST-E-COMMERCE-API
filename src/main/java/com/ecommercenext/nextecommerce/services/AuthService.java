package com.ecommercenext.nextecommerce.services;

import com.ecommercenext.nextecommerce.dtos.RegisterRequest;
import com.ecommercenext.nextecommerce.entity.Member;
import com.ecommercenext.nextecommerce.entity.Role;
import com.ecommercenext.nextecommerce.repos.MemberRepository;
import com.ecommercenext.nextecommerce.repos.RoleRepository;
import com.ecommercenext.nextecommerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("User already exists");
        }

        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setActive(true);

        memberRepository.save(member);

        Role role = new Role();
        role.setUserId(request.getUsername());
        role.setRole("ROLE_CUSTOMER");
        roleRepository.save(role);
    }

    public String login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(username);
    }

}
