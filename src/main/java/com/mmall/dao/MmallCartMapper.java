package com.mmall.dao;

import  com.mmall.dao.MmallCart;

public interface MmallCartMapper {

    public MmallCart selectCartById(Integer id) throws Exception;
}

