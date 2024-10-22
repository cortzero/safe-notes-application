import React, { useEffect, useRef, useState } from 'react'
import CategoryLabel from './CategoryLabel';
import { getAllCategories } from '../services/CategoryService';
import '../styles/CategoriesHorizontalList.css';

export default function CategoriesHorizontalList({ returnSelectedCategories }) {
  const [categories, setCategories] = useState([]);
  const [canScrollLeft, setCanScrollLeft] = useState(false);
  const [canScrollRight, setCanScrollRight] = useState(false);
  const selectedCategories = useRef([]);
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
      updateArrows();
      return () => categoriesContainer.removeEventListener('scroll', updateArrows);
    }
  }, []);
  
  const updateSelectedCategoriesList = (newCategory, operation) => {
    if (operation === 'append') {
      selectedCategories.current.push(newCategory);
    }
    else if (operation === 'remove') {
      selectedCategories.current = selectedCategories.current.filter(category => category != newCategory);
    }
    console.log(selectedCategories.current);
    returnSelectedCategories(selectedCategories.current);
  };

  const createCategoryComponents = () => {
    return categories.map(
      category => <CategoryLabel 
                    key={category.name} 
                    category={category.name} 
                    isSelected={false} 
                    appendCategory={newCategory => updateSelectedCategoriesList(newCategory, 'append')}
                    removeCategory={newCategory => updateSelectedCategoriesList(newCategory, 'remove')}
                  />
    );
  };

  let categoryComponents = createCategoryComponents();

  const scroll = (direction) => {
    const scrollAmount = 200; // Number of pixels to scroll
    if (categoriesContainerRef.current) {
      categoriesContainerRef.current.scrollLeft += direction === 'left' ? -scrollAmount : scrollAmount;
    }
  };

  const updateArrows = () => {
    if (categoriesContainerRef.current) {
      const { scrollLeft, scrollWidth, clientWidth } = categoriesContainerRef.current;
      setCanScrollLeft(scrollLeft > 0);
      setCanScrollRight(scrollLeft < scrollWidth - clientWidth);
    }
  };

  const clearSelectedCategoriesList = () => {
    selectedCategories.current = [];
    categoryComponents = createCategoryComponents();
    returnSelectedCategories(selectedCategories.current);
  };

  return (
    <div className='category-slider'>
      <div className={`button-container ${canScrollLeft ? '' : 'disabled'}`}>
        <button 
          type='button'
          onClick={() => scroll('left')}
        >&#9664;</button>
      </div>
      <ul id='categories-list' className='categories-container'
        ref={categoriesContainerRef}
      >
        {categoryComponents}
      </ul>
      <div className={`button-container ${canScrollRight ? '' : 'disabled'}`}>
        <button 
          type='button'
          onClick={() => scroll('right')}
        >&#9654;</button>
      </div>
    </div>
  )
}
