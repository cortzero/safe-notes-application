import api from '../config/AxiosConfig.js';

export const getAllNotes = () => api.get('/api/notes');