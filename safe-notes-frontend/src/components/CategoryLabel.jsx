import React, { useState } from 'react';
import '../styles/CategoryLabel.css';

export default function CategoryLabel(props) {
  const [isSelected, setIsSelected] = useState(props.isSelected);

  const handleOnClick = () => {
    setIsSelected(!isSelected);
    if (!isSelected) {
      props.appendCategory(props.category);
    }
    else {
      props.removeCategory(props.category);
    }
  }

  return (
    <div className={`category-label ${isSelected && 'selected'}`} onClick={() => handleOnClick()}>
      <p className='category'>{props.category}</p>
    </div>
  )
}
