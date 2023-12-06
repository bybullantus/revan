package com.bc.revan.Business;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bc.revan.Business.Base.IBaseService;
import com.bc.revan.Entities.Venue;

public interface IVenueService extends IBaseService<Venue> {
	CompletableFuture<List<Venue>> getByName(String name);
}
