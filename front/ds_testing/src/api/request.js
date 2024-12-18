import axios from 'axios'

// 创建一个axios对象出来
const request = axios.create({
    baseURL: 'http://192.168.168.214:8082',
    // baseURL: 'http://localhost:8082',
    timeout: 5000
})

// request 拦截器
// 可以在请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    const user=localStorage.getItem("user");
    if(user){
        console.log(JSON.parse(user).token);
        config.headers['token']=JSON.parse(user).token;
    }

    return config
}, error => {
    return Promise.reject(error)
});

/// response 拦截器
request.interceptors.response.use(response => {
    let res = response.data;

    // 如果是字符串但不是有效的 JSON 格式，直接返回原始数据
    try {
        if (typeof res === 'string') {
            res = JSON.parse(res);
        }
    } catch (e) {
        // JSON.parse 失败，说明返回的就是普通字符串，直接返回
        console.warn('Response is not a JSON, returning raw response:', res);
    }

    return res;
}, error => {
    console.error('Error in response interceptor:', error);
    return Promise.reject(error);
});
export default request;