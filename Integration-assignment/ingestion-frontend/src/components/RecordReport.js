// services/api.js

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // or your backend URL

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

export default getRecordCount;