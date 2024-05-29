const LibroRow = ({libro,onEdit,onDelete}) => {
    const handleEdit = () =>{
        onEdit(libro)
    }

    const handleDelete = () =>{
        onDelete(libro.id)
    }
    return(
        <tr>
            <td>{libro.nombre}</td>
            <td>{libro.autor}</td>
            <td>{libro.genero}</td>
            
            <td>
                <button onClick={handleEdit}>Editar 🖋</button>
                <button onClick={handleDelete}>Borrar 🗑</button>
            </td>
        </tr>
    )
}
export default LibroRow