import { createStore } from 'vuex'

const store = createStore({
    state() {
        return {
            isAuthenticated: false, // 登录状态
            user: null,             // 用户名
            role: null,             // 用户角色
        };
    },
    mutations: {
        login(state, userInfo) {
            state.isAuthenticated = true; // 设置为已登录
            state.user = userInfo.user;           // 存储用户信息
            state.role = userInfo.role;           // 存储用户角色
        },
        logout(state) {
            state.isAuthenticated = false; // 设置为未登录
            state.user = null;            // 清除用户信息
            state.role = null;            // 清除用户角色
        },
    },
    actions: {
        login({ commit }, { user, role }) {
            // 模拟登录逻辑（如实际项目中可调用 API 验证）
            commit('login', { user, role });
        },
        logout({ commit }) {
            commit('logout');
        },
    },
    getters: {
        isAuthenticated: (state) => state.isAuthenticated,
        getUser: (state) => state.user,
        getRole: (state) => state.role,
    },
});

export default store;