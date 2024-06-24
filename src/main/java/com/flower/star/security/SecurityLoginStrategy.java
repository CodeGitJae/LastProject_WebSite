package com.flower.star.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.flower.star.entity.Member;
import com.flower.star.entity.Role;
import com.flower.star.repository.MemberRepository;
import com.flower.star.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityLoginStrategy implements UserDetailsService{

	private final MemberRepository mRepository;
	private final RoleRepository rRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Member data = mRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("NotFoundUsername"));
		
		List<Role> roles = rRepository.findAllByMember_Id(data.getId());
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		roles.forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role.getRole().toString()));
		});
		
		return User.builder().username(username)
							 .password(data.getPassword())
							 .authorities(authorities)
							 .build();
	}
}
