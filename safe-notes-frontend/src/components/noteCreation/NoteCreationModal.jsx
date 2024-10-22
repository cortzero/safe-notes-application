import React, { useRef, useState } from 'react';
import '../../styles/NoteCreationModal.css';
import CategoriesHorizontalList from '../CategoriesHorizontalList';
import Button from '../Button';
import { saveNote } from '../../services/NoteService';

export default function NoteCreationModal(props) {
  const [noteTitle, setNoteTitle] = useState('');
  const [noteText, setNoteText] = useState('');
  const [noteCategories, setNoteCategories] = useState([]);
  const titleInputRef = useRef();
  const textInputRef = useRef();

  const handleOnChangeTitleInput = e => setNoteTitle(e.target.value);
  const handleOnChangeTextInput = e => setNoteText(e.target.value);
  const handleOnSelectCategory = categories => setNoteCategories(categories);

  const handleOnClickCreate = e => {
    e.preventDefault();
    const currentDateTime = new Date();
    const currentDateTimeSplitted = currentDateTime.toISOString().split('T');
    const currentDateISOFormated = currentDateTimeSplitted[0];

    const note = {
      title: noteTitle,
      status: 'Active',
      date: currentDateISOFormated,
      text: noteText,
      categories: noteCategories
    };

    saveNote(note).then(response => {
      console.log('HTTP Status code: ' + response.status);
      console.log(response.data);
      closeModal();
    }).catch(err => console.log(err));
  }

  const handleOnClickCancel = e => {
    e.preventDefault();
    closeModal();
  }

  const closeModal = () => {
    props.onClickCloseModal();
    cleanAllInputFields();
  }

  const cleanAllInputFields = () => {
    titleInputRef.current.value = '';
    textInputRef.current.value = '';
  };

  return (
    <div style={{display: `${props.displayModal ? 'block' : 'none'}`}}>
      <div className='modal-background' onClick={closeModal}>
      </div>
      <form className='modal-form'>
        {/* Title input */}
        <div className='form-row'>
          <label className='label-text' htmlFor='note-title'>Title</label>
          <input className='text-input' id='note-title' name='title' onChange={e => handleOnChangeTitleInput(e)} ref={titleInputRef}></input>
        </div>
        {/* Text input */}
        <div className='form-row'>
          <label className='label-text' htmlFor='note-text'>Text</label>
          <textarea className='text-area' id='note-text' name='text' rows={10} onChange={e => handleOnChangeTextInput(e)} ref={textInputRef}></textarea>
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
