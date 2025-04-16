// services/api.js

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Replace with your actual backend base URL

export const login = async (email, password) => {
  const response = await axios.post(`${API_BASE_URL}/login`, { email, password });
  return response.data;
};

export const getColumns = async (source) => {
  const token = localStorage.getItem('token');
  const response = await axios.get(`${API_BASE_URL}/columns`, {
    params: { source },
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

export const getRecordCount = async (columns, source) => {
  const token = localStorage.getItem('token');
  const response = await axios.post(`${API_BASE_URL}/report`, {
    columns,
    source,
  }, {
    headers: {
      Authorization: `Bearer ${token}`,
    }
  });
  return response.data;
};