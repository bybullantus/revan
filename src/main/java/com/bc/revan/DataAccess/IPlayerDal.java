package com.bc.revan.DataAccess;

import com.bc.revan.DataAccess.Base.IBaseRepository;
import com.bc.revan.Entities.Player;

public interface IPlayerDal extends IBaseRepository<Player>{
	public Player getByIdAndSeason(long playerId, int year);
}
