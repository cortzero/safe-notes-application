import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import NavBar from './components/NavBar'
import MainComponent from './components/MainComponent'
import './index.css'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <NavBar />
    <MainComponent />
  </StrictMode>,
)
