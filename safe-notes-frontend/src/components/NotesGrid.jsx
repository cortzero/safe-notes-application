import React from 'react';
import NoteCard from './NoteCard';
import notes from '../data/notes-data.json';
import '../styles/NotesGrid.css';

export default function NotesGrid() {
  const noteCardElements = notes.map(
    note => <NoteCard key={note.id} note={note} />
  );

  return (
    <div className='notes-grid'>
      {noteCardElements}
    </div>
  )
}
