import {apiClient} from "@/api.js";

export const getToken = () => {
    return localStorage.getItem('token');
}

export const setToken = (token) => {
    localStorage.setItem('token', token);
}

export const removeToken = () => {
    localStorage.removeItem('token');
}

export const getIdByToken= () => {
    //const token = getToken();
    //return apiClient.get('/user/getId');
    return 'UserName';
}