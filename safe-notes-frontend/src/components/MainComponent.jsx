import React from 'react';
import NotesGrid from './NotesGrid';
import '../styles/MainComponent.css';
import AddNoteComponent from './AddNoteComponent';

export default function MainComponent() {
  return (
    <main className='main-component'>
      <AddNoteComponent />
      <NotesGrid />
    </main>
  )
}
