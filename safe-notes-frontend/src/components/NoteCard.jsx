import React from 'react'
import '../styles/NoteCard.css';
import CategoryLabel from './CategoryLabel';

export default function NoteCard() {
  const note = {
    title: 'Go for lunch',
    status: 'Active',
    date: 'April 01 2005',
    text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam iaculis  enim quis nisi viverra vestibulum. Duis venenatis vehicula aliquam. Phasellus.',
    categories: [
      <CategoryLabel key='1' category='Category 1' />,
      <CategoryLabel key='2' category='Category 2' />,
      <CategoryLabel key='3' category='Category 3' />,
      <CategoryLabel key='4' category='Category 4' />,
      <CategoryLabel key='5' category='Category 5' />,
      <CategoryLabel key='6' category='Category 6' />
    ]
  };

  return (
    <div className='card'>
      <div className='card-title-div'>
        <p className='title'>{note.title}</p>
        <p className='note-state-label'>{note.status}</p>
      </div>
      <div className='note-date-div'>
        <p className='note-date'>{note.date}</p>
      </div>
      <div className='note-text-div'>
        <p className='note-text'>
          {note.text}
        </p>
      </div>
      <div className='footer-card-div'>
        <div className='note-categories-div'>
          {note.categories}
        </div>
        <div className='delete-button-div'>
          <button className='delete-button'>X</button>
        </div>
      </div>
    </div>
  )
}
