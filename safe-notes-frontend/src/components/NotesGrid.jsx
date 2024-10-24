import React, { useEffect } from 'react';
import NoteCard from './NoteCard';
import '../styles/NotesGrid.css';

export default function NotesGrid({ notes, fetchNotes }) {
  useEffect(() => {
    fetchNotes();
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
