import React, { useEffect, useState } from 'react';
import NoteCard from './NoteCard';
import { getAllNotes } from '../services/NoteService';
import '../styles/NotesGrid.css';

export default function NotesGrid() {
  const [notes, setNotes] = useState([]);

  useEffect(() => {
    getAllNotes()
      .then(response => setNotes(response.data))
      .catch(error => console.log(error));
  }, []);

  const noteCardElements = notes.map(
    note => <NoteCard key={note.id} note={note} />
  );

  return (
    <div className='notes-grid'>
      {noteCardElements}
    </div>
  )
}
