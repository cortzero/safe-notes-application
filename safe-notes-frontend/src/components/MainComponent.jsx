import React from 'react';
import NotesGrid from './NotesGrid';
import '../styles/MainComponent.css';
import CategoriesHorizontalList from './CategoriesHorizontalList';

export default function MainComponent() {
  return (
    <main className='main-component'>
      <CategoriesHorizontalList />
      <NotesGrid />
    </main>
  )
}
