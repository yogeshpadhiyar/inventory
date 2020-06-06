package com.neosoft.repositry;

import org.hibernate.Session;

public interface Repo<T> extends GenericRepo<T , Session> {

}
