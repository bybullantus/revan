package com.bc.revan.Entities.Base;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	boolean isActive = true;
	boolean isDeleted = false;
}
