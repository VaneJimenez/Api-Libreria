import {useState} from "react"

function LibroForm ({onSubmit}){

    const [nombre,setNombre]=useState('')
    const [autor,setAutor]=useState('')
    const [genero,setGenero]=useState('')
    
    const handleNombreChange = (event) =>{
        setNombre(event.target.value)
    }
    const handleAutorChange = (event) =>{
        setAutor(event.target.value)
    }
    const handleGeneroChange = (event) =>{
        setGenero(event.target.value)
    }
    

    const handleSubmit =(event) =>{
        event.preventDefault()
        onSubmit({nombre,autor,genero})
        setNombre('')
        setAutor('')        
        setGenero('')
    }   

    return(
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Título" value={nombre} onChange={handleNombreChange} required/>
            <input type="text" placeholder="Autor" value={autor} onChange={handleAutorChange} required/>
            <input type="text" placeholder="Género" value={genero} onChange={handleGeneroChange} required/>
            
            <button type="submit">Añadir</button>
        </form>
    )
}
export default LibroForm
