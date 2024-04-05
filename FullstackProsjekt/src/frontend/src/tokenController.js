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

export const getIdByToken = async () => {
    return new Promise((resolve, reject) => {
        apiClient.get('/user/getId/' + getToken())
            .then(response => {
                resolve(response.data.id);
            })
            .catch(error => {
                reject(error);
            });
    });
};