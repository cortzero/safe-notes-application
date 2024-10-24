import React, { useState } from 'react';
import Button from './Button';
import NoteCreationModal from './noteCreation/NoteCreationModal';

export default function AddNoteComponent({ onSaveNote }) {
  const [showModal, setShowModal] = useState(false);

  const openModal = () => setShowModal(true);
  const closeModal = () => setShowModal(false);

  return (
    <div>
      <Button type='primary' text='Add Note' onClickEventHandler={openModal} />
      <NoteCreationModal onSaveNote={onSaveNote} displayModal={showModal} onClickCloseModal={closeModal} />
    </div>
  )
}
