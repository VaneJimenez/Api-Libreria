import React, { useEffect, useState } from 'react';
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import axios from 'axios';
import './App.css'
import LibroTable from './components/LibroTable'
import LibroForm from './components/LibroForm'

function App() {
  const [libro, setLibro] = useState([])
  const [editingLibro,setEditingLibro] = useState(null) 
  
  useEffect(()=> {
    fetchLibro()
  }, [])

  const handleCreateOrUpdateLibro = async (libroData) => {
    if (editingLibro) {
      await axios.put(`http://localhost:8080/api/libros/${editingLibro.id}`, libroData)
    } else {
      await axios.post(`http://localhost:8080/api/libros`, libroData)
    }
  }

  const fetchLibro = async() =>{
    try {
      const response = await axios.get('http://localhost:8080/api/libros')
      setLibro(response.data)
    } catch (error) {
      console.log('Se encontró un error al cargar los libros: ', error)
    }
  }

  const handleEditLibro = (libro) => {
    setEditingLibro(libro)
  }

  const handleDeleteLibro = async(libroId) => {
    await axios.delete(`http://localhost:8080/api/libros/${libroId}`)
    fetchLibro()
  } 

  return (
    <div className='App'>
      <h1>Librería API</h1>
      
      <h2>Listado de los libros</h2>
      <LibroTable libros={libro} onEdit={handleEditLibro} onDelete={handleDeleteLibro}/>
      <h2>{editingLibro ? 'Editar libro' : 'Crear nuevo libro'}</h2>
      <LibroForm onSubmit={handleCreateOrUpdateLibro} initialLibro={editingLibro}/>
    </div>
  )
}

export default App
