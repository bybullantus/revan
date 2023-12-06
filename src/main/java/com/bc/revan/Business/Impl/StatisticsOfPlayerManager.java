package com.bc.revan.Business.Impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.bc.revan.Business.IStatisticsOfPlayerService;
import com.bc.revan.Business.Base.GenericManager;
import com.bc.revan.DataAccess.ISquadDal;
import com.bc.revan.DataAccess.IStatisticsOfPlayerDal;
import com.bc.revan.Entities.Squad;
import com.bc.revan.Entities.StatisticsOfPlayer;

@Service
public class StatisticsOfPlayerManager  extends GenericManager<StatisticsOfPlayer, IStatisticsOfPlayerDal> implements IStatisticsOfPlayerService {

	
}
