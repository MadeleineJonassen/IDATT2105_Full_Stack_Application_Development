import { apiClient } from "@/api.js";

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

export const refreshAndStoreToken = async (existingToken) => {
    try {
        const response = await apiClient.post('/auth/refresh', { token: existingToken });
        const newToken = response.data.token;
        setToken(newToken); // Lagre det nye tokenet i localStorage
        return newToken;
    } catch (error) {
        throw new Error('Failed to refresh token');
    }
};
