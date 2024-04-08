import axios from 'axios';
import {getToken} from "@/tokenController.js";

export const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    //TODO: set api URL
});
/*
apiClient.interceptors.request.use(
    (config) => {
        const token = getToken();
        if(token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }else
        return config;
    },
    (error) => {
    //return Promise.reject("Problem with token: " + error);
});*/