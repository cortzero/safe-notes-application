import api from '../config/AxiosConfig.js';

export const getAllNotes = () => api.get('/api/notes');
export const saveNote = newNote => api.post('/api/notes', newNote);