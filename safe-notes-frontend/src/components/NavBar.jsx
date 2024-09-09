import React from 'react';
import '../styles/NavBar.css';

export default function NavBar() {
  return (
    <div className='nav-bar'>
      <div className='left-side'>
        <div className='site-title'>
          Safe Notes
        </div>
      </div>
      <div className='right-side'>
        <div className='profile-img-div'>
          <img className='profile-img' src='src\assets\default-user-profile.png'></img>
        </div>
      </div>
    </div>
  )
}
