package com.fakegroup.repository;

public interface CustomBaseRepository<T> {

    T refresh(T toRefresh);

}
