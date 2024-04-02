import axios from 'axios';

export const apiClient = axios.create({
    baseURL: '/api',
    //TODO: set api URL
});