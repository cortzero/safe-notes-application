import React from 'react';
import '../styles/CategoryLabel.css';

export default function CategoryLabel(props) {
  return (
    <div className='category-label'>
      <p className='category'>{props.category}</p>
    </div>
  )
}
