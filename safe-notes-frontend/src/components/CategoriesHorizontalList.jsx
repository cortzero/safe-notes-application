import React, { useEffect, useRef, useState } from 'react'
import CategoryLabel from './CategoryLabel';
import '../styles/CategoriesHorizontalList.css';
import { getAllCategories } from '../services/CategoryService';

export default function CategoriesHorizontalList() {
  const [categories, setCategories] = useState([]);
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(true);
  const categoriesContainerRef = useRef(null);

  useEffect(() => {
    getAllCategories()
      .then(response => setCategories(response.data))
      .catch(err => console.log(err));
  }, []);

  useEffect(() => {
    const categoriesContainer = categoriesContainerRef.current;
    if (categoriesContainer) {
      categoriesContainer.addEventListener('scroll', updateArrows);
      updateArrows(); // Inital call when the component is first rendered to determine which arrows to show
      return () => categoriesContainer.removeEventListener('scroll', updateArrows);
    }
  }, []);

  const scroll = (direction) => {
    const scrollAmount = 200; // Number of pixels to scroll
    if (categoriesContainerRef.current) {
      categoriesContainerRef.current.scrollBy({
        left: direction === 'left' ? -scrollAmount : scrollAmount,
        behavior: 'smooth',
      });
    }
  };

  const updateArrows = () => {
    if (categoriesContainerRef.current) {
      const { scrollLeft, scrollWidth, clientWidth } = categoriesContainerRef.current;
      setCanScrollLeft(scrollLeft > 0);
      setCanScrollRight(scrollLeft < scrollWidth - clientWidth);
    }
  };

  const categoryComponents = categories.map(
    category => <CategoryLabel key={category.name} category={category.name} />
  );

  return (
    <div className='category-slider'>
      <button 
        className={`arrow left ${canScrollLeft ? '' : 'disabled'}`} 
        onClick={() => scroll('left')} 
        disabled={!canScrollLeft}
      >&#9664;</button>
      <div className='categories-container' ref={categoriesContainerRef}>
        {categoryComponents}
      </div>
      <button 
        className={`arrow right ${canScrollRight ? '' : 'disabled'}`}
        onClick={() => scroll('right')} 
        disabled={!canScrollRight}
      >&#9654;</button>
    </div>
  )
}
