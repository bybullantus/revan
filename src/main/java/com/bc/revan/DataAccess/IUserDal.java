package com.bc.revan.DataAccess;

import java.util.Optional;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.User;

public interface IUserDal extends IBaseRepository<User> {
	public Optional<User> getByEmail(String email);
}
