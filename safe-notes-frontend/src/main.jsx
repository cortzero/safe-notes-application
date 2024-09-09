import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import NavBar from './components/NavBar'
import './index.css'
import NotesGrid from './components/NotesGrid'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <NavBar />
    <NotesGrid />
  </StrictMode>,
)
