package com.fxt.service.basic;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.Basicuser;

public interface UserIService extends BaseIService<Basicuser>
{
	/**
	 * 判断用户名是否符合登录条件
	 * @param username 用户名 String password
	 * @return int 1、用户名不存在  2、密码不正确  3、黑名单用户  4、符合条件  5、访问后台异常
	 */
	int validateUser(String username, String password)throws fxtException;

	/**
	 * 获取用户信息
	 * @param username
	 * @return 用户信息
	 * @throws fxtException
	 */
	public Basicuser getUser(String username) throws fxtException;
	
	/**
	 * 检查用户名称是否存在
	 * @param field 用户属性
	 * @param username 用户名称
	 * @param userID 供应商ID
	 * @return true==存在重名 false==不存在
	 * @throws fxtException
	 */
	Boolean checkIsNameExist(String field, String username, Long userID)throws fxtException;
}
