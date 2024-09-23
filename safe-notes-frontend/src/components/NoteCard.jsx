import React from 'react'
import '../styles/NoteCard.css';
import CategoryLabel from './CategoryLabel';

export default function NoteCard(props) {
  const categoryComponents = props.note.categories.map(
    category => <CategoryLabel key={category} category={category} />
  );
  const note = props.note;

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
          {note.text.substring(0, 181)}{note.text.length > 182 && '...'}
        </p>
      </div>
      <div className='footer-card-div'>
        <div className='note-categories-div'>
          {categoryComponents}
        </div>
        <div className='delete-button-div'>
          <button className='delete-button'>X</button>
        </div>
      </div>
    </div>
  )
}
