package com.bc.revan.Entities;

public class mahmut implements AutoCloseable {
	String aptal;

	@Override
	public void close() throws Exception {
		this.aptal="";
		
	}
	@Override
	protected void finalize() throws Throwable {
	this.aptal="";
		super.finalize();
	}
}
