import React, { useState } from 'react';
import NotesGrid from './NotesGrid';
import AddNoteComponent from './AddNoteComponent';
import { getAllNotes } from '../services/NoteService';
import '../styles/MainComponent.css';

export default function MainComponent() {
  const [notes, setNotes] = useState([]);

  const fetchNotes = () => {
    getAllNotes()
      .then(response => setNotes(response.data))
      .catch(error => console.log(error));
  }

  return (
    <main className='main-component'>
      <AddNoteComponent onSaveNote={fetchNotes}/>
      <NotesGrid notes={notes} fetchNotes={fetchNotes} />
    </main>
  )
}
