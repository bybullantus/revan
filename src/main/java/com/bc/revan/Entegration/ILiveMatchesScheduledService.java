package com.bc.revan.Entegration;

import java.util.concurrent.ExecutionException;

import com.bc.revan.Entities.Base.BaseResponse;
import com.bc.revan.Entities.Dto.LiveMatchesDto;

public interface ILiveMatchesScheduledService {
	BaseResponse getLiveMatches() throws InterruptedException, ExecutionException;

}
