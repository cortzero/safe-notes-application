import React, { useState } from 'react';
import Button from './Button';
import NoteCreationModal from './noteCreation/NoteCreationModal';

export default function AddNoteComponent() {
  const [showModal, setShowModal] = useState(false);

  const openModal = () => setShowModal(true);

  const closeModal = () => setShowModal(false);

  return (
    <div>
      <Button type='primary' text='Add Note' onClickEventHandler={openModal} />
      <NoteCreationModal displayModal={showModal} onClickCloseModal={closeModal} />
    </div>
  )
}
