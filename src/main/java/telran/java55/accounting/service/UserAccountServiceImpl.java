package telran.java55.accounting.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java55.accounting.dao.AccountRepository;
import telran.java55.accounting.dto.RolesDto;
import telran.java55.accounting.dto.UserDto;
import telran.java55.accounting.dto.UserEditDto;
import telran.java55.accounting.dto.UserRegisterDto;
import telran.java55.accounting.dto.exceptions.UserNotFoundException;
import telran.java55.accounting.model.Role;
import telran.java55.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterdto) {
		UserAccount userAccount = new UserAccount(userRegisterdto.getLogin(), userRegisterdto.getPassword(),
				userRegisterdto.getFirstName(), userRegisterdto.getLastName());
		userAccount = accountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto findUserByLogin(String login) {
		UserAccount userAccount = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount userAccount = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		accountRepository.delete(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount userAccount = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount userAccount = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String firstName = userEditDto.getFirstName();
		if (firstName != null) {
			userAccount.setFirstName(firstName);
		}
		String lastName = userEditDto.getLastName();
		if (lastName != null) {
			userAccount.setLastName(lastName);
		}
		userAccount = accountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = accountRepository.findById(login).orElseThrow(UserNotFoundException::new);
		Role roleEnum = Role.valueOf(role.toUpperCase());
		if (isAddRole) {
			userAccount.getRoles().add(roleEnum);
		} else {
			userAccount.getRoles().remove(roleEnum);
		}
		accountRepository.save(userAccount);
		return RolesDto.builder().login(userAccount.getLogin())
				.roles(userAccount.getRoles().stream().map(Role::toString).collect(Collectors.toSet())).build();
	}

	@Override
	public void changePassword(String name, String newPassword) {
		UserAccount userAccount = accountRepository.findById(name).orElseThrow(UserNotFoundException::new);
		userAccount.setPassword(newPassword);
		accountRepository.save(userAccount);

	}

}
