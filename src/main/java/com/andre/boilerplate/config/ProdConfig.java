package com.andre.boilerplate.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andre.boilerplate.exceptions.ProjectException;
import com.andre.boilerplate.models.Roles;
import com.andre.boilerplate.models.User;
import com.andre.boilerplate.service.RolesService;
import com.andre.boilerplate.service.UserService;
import com.andre.boilerplate.utils.Constants;

@Configuration
@Profile({ "prod" })
@ComponentScan({ "com.andre.boilerplate.controller" })
public class ProdConfig {

	@Autowired
	private UserService userService;

	@Autowired
	private RolesService rolesService;

	@Bean
	public void startDatabase() {
		this.initialUser();
	}

	private void initialUser() {

		String[] rolesName = { Constants.ROLE_ADMIN, Constants.ROLE_VIEW_USER, Constants.ROLE_CREATE_USER,
				Constants.ROLE_UPDATE_USER, Constants.ROLE_UPDATE_ROLES_USER, Constants.ROLE_DELETE_USER,
				Constants.ROLE_DISABLE_USER };
		
		Roles roles = null;
		for (String roleName : rolesName) {
			try {
				rolesService.findByName(roleName);
			} catch (ProjectException e) {
				roles = new Roles(roleName);
				rolesService.save(roles);
			}
		}
		
		String supportMail = "admin@email.com";
		try {
			rolesService.findByName(supportMail);
		} catch (ProjectException e) {
			roles = rolesService.findByName(Constants.ROLE_ADMIN);
			User user = new User("Admin", supportMail, "password@1234", Arrays.asList(roles));
			userService.save(user);
		}

	}
}
