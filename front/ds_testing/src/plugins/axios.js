import axios from 'axios';

let config = {
  // 配置项，例如 baseURL，timeout 等
};

const _axios = axios.create(config);

// 请求拦截器
_axios.interceptors.request.use(
  function(config) {
    return config;
  },
  function(error) {
    return Promise.reject(error);
  }
);

// 响应拦截器
_axios.interceptors.response.use(
  function(response) {
    return response;
  },
  function(error) {
    return Promise.reject(error);
  }
);

// 不需要使用 Vue.use()
export default _axios;
