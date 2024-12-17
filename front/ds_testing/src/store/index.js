import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            isAuthenticated: false, // 登录状态
            user: null,             // 用户信息
        };
    },
    mutations: {
        login(state, user) {
            state.isAuthenticated = true; // 设置为已登录
            state.user = user;           // 存储用户信息
        },
        logout(state) {
            state.isAuthenticated = false; // 设置为未登录
            state.user = null;            // 清除用户信息
        },
    },
    actions: {
        login({ commit }, user) {
            // 模拟登录逻辑（如实际项目中可调用 API 验证）
            commit('login', user);
        },
        logout({ commit }) {
            commit('logout');
        },
    },
    getters: {
        isAuthenticated: (state) => state.isAuthenticated,
        getUser: (state) => state.user,
    },
});

export default store;