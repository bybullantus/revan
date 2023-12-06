package com.bc.revan.DataAccess;

import java.util.List;

import com.bc.revan.Entities.FakeMatches;

public interface IFakeMatchesDal {
	
	List<FakeMatches> getAll();

	FakeMatches add(FakeMatches item);
	
	List<FakeMatches> addAll(List<FakeMatches> items);

	FakeMatches update(FakeMatches item);

	void delete(FakeMatches item);

	FakeMatches getById(long id);
	
	List<FakeMatches> getByHafta(int hafta);
}
