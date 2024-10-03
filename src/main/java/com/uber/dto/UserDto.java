package com.uber.dto;

import java.util.Set;

import com.uber.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private Set<Role> roles;
}
