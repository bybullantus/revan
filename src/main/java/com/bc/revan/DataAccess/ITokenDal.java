package com.bc.revan.DataAccess;

import java.util.List;
import java.util.Optional;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Token;

public interface ITokenDal extends IBaseRepository<Token> {

	  List<Token> getTokenbyUserId(Long id);

	  Optional< Token> getByToken(String token);
	  
	  boolean getByEmail(String Email);
}
