package com.fakegroup.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public abstract class BaseProjectEntity extends Auditable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

}
