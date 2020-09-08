package com.kennedy.herostory.async;

/**
 * 异步操作接口
 */
public interface IAsyncOperation {

    /**
     * 执行异步操作
     */
    void doAsync();

    /**
     * 执行完成逻辑
     */
    default void doFinish() {
    }

    /**
     * 获取绑定 Id
     * 使得同一个用户操作在一个DB线程里
     * 防止重复访问DB，恶意刷新礼品等
     *
     * @return 绑定 Id
     */
    default int getBindId() {return 0;};
}
