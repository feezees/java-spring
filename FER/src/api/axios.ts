import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const api = axios.create({
  baseURL: API_URL,
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor
api.interceptors.request.use(
  (config) => {
    // You can add any request modifications here
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // Handle common errors here
    if (error.response?.status === 401) {
      // Handle unauthorized access
      console.error('Unauthorized access');
    }
    return Promise.reject(error);
  }
);

// Helper methods for common HTTP methods
export const saxios = {
  get: <T>(url: string) => api.get<T>(url).then(response => response.data),
  post: <T>(url: string, data?: any) => api.post<T>(url, data).then(response => response.data),
  put: <T>(url: string, data?: any) => api.put<T>(url, data).then(response => response.data),
  delete: <T>(url: string) => api.delete<T>(url).then(response => response.data),
}; 