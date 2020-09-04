package com.kennedy.herostory.login.db;

/**
 * 用户 DAO
 */
public interface IUserDao {

    /**
     * 根据用户名称获取用户实体
     *
     * @param userName 用户名称
     * @return 用户实体
     */
    UserEntity getUserByName(String userName);

    /**
     * 添加用户实体
     *
     * @param newUserEntity 用户实体
     */
    void insertInto(UserEntity newUserEntity);
}
