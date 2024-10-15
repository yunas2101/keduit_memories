import axios from 'axios';
import useAuthStore from '../store/store';


export const host = "http://192.168.1.15";


export const api = axios.create({
    baseURL: `${host}`
});

api.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem("token"); //sessionStorage에서 사용자가 로그인할 때 서버로부터 받은 JWT 토큰을 가져옴
        if (token) { // 가져온 token이 있으면 헤더에 추가. 요청마다 서버에 인증정보 전달 가능
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        return config;
    }
);


api.interceptors.response.use(
    (response) => response, // axios 응답이 정상응답일 때는 then으로 가게 방치
    (error) => {
        switch (error.response.status) {
            case 401:
                sessionStorage.removeItem("token");
                useAuthStore.getState().logout(); // useAuthStore에 있는 state값을 가져와서 logout 시키겠다.
                break;
        }
    }
);
