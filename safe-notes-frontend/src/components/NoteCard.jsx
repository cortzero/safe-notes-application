import React from 'react'
import '../styles/NoteCard.css';
import CategoryLabel from './CategoryLabel';

export default function NoteCard(props) {
  // const note = {
  //   title: 'Go for lunch',
  //   status: 'Active',
  //   date: 'April 01 2005',
  //   text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum lacinia justo at mi malesuada, a tempus massa viverra. Ut egestas suscipit euismod. Praesent viverra varius sodales. Suspendisse dictum orci diam, sed consequat tortor hendrerit a. Sed sodales sapien sit amet lectus porta aliquet. Maecenas euismod mauris ut enim sagittis consequat.',
  //   categories: [
  //     <CategoryLabel key='1' category='Category 1' />,
  //     <CategoryLabel key='2' category='Category 2' />,
  //     <CategoryLabel key='3' category='Category 3' />,
  //     <CategoryLabel key='4' category='Category 4' />,
  //     <CategoryLabel key='5' category='Category 5' />,
  //     <CategoryLabel key='6' category='Category 6' />
  //   ]
  // };
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
