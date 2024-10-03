import React, { useState } from 'react';
import '../../styles/NoteCreationModal.css';
import CategoriesHorizontalList from '../CategoriesHorizontalList';
import Button from '../Button';

export default function NoteCreationModal(props) {
  const [noteTitle, setNoteTitle] = useState('');
  const [noteText, setNoteText] = useState('');
  const [noteCategories, setNoteCategories] = useState([]);

  const handleOnChangeTitleInput = e => setNoteTitle(e.target.value);
  const handleOnChangeTextInput = e => setNoteText(e.target.value);
  const handleOnSelectCategory = categories => setNoteCategories(categories);

  const handleOnClickCreate = e => {
    e.preventDefault();
    const currentDate = new Date();
    const currentDateFormated = `${currentDate.getFullYear()}-${currentDate.getMonth() + 1}-${currentDate.getDate()}`;

    const note = {
      title: noteTitle,
      status: 'Active',
      date: currentDateFormated,
      text: noteText,
      categories: noteCategories
    };
  }

  const handleOnClickCancel = e => {
    e.preventDefault();
    props.onClickCloseModal();
  }

  return (
    <div style={{display: `${props.displayModal ? 'block' : 'none'}`}}>
      <div className='modal-background' onClick={props.onClickCloseModal}>
      </div>
      <form className='modal-form'>
        {/* Title input */}
        <div className='form-row'>
          <label className='label-text' htmlFor='note-title'>Title</label>
          <input className='text-input' id='note-title' name='title' onChange={e => handleOnChangeTitleInput(e)}></input>
        </div>
        {/* Text input */}
        <div className='form-row'>
          <label className='label-text' htmlFor='note-text'>Text</label>
          <textarea className='text-area' id='note-text' name='text' rows={10} onChange={e => handleOnChangeTextInput(e)}></textarea>
        </div>
        {/* Categories selection */}
        <div className='form-row'>
          <label className='label-text' htmlFor='note-categories'>Categories</label>
          <CategoriesHorizontalList returnSelectedCategories={handleOnSelectCategory} />
        </div>
        {/* Buttons */}
        <div className='form-row buttons-row'>
          <div className='left-button-div'>
            <Button text="Create" type="primary" onClickEventHandler={e => handleOnClickCreate(e)}/>
          </div>
          <div className='right-button-div'>
            <Button text="Cancel" type="secondary" onClickEventHandler={e => handleOnClickCancel(e)}/>
          </div>
        </div>
      </form>
    </div>
  )
}
